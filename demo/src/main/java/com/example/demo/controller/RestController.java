package com.example.demo.controller;

import com.example.demo.service.HelperService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/v1/")
public class RestController {

    @RequestMapping(value = "/lockvehicle/{VIN}/{COMMAND}", method = RequestMethod.PUT)
    public ResponseEntity<Object> getVehicleLockStatus(@PathVariable("VIN") String VIN,@PathVariable("COMMAND") String command) {
//      @RequestMapping(value = "/lockvehicle/{VIN}", method = RequestMethod.PUT)
//    public ResponseEntity<Object> getVehicleLockStatus(@PathVariable("VIN") String VIN){
        HelperService helperService = new HelperService();
        boolean isValid = helperService.isValidVIN(VIN);
//        String command = "LOCK";
        System.out.println("VIN is valid? " + isValid);

        if (isValid) {
            boolean isTcuEnabled = helperService.isValidTCU(VIN);
            if (isTcuEnabled) {
                System.out.println("TCU is Enabled? " + isTcuEnabled);
                boolean isRLULavailable = helperService.isRLULavailable(VIN);
                if (isRLULavailable) {
                    System.out.println("is RLUL Available? " + isRLULavailable);
                    String lockStatus = helperService.lockStatus(VIN);
                    System.out.println("Vehivle Lock status " + lockStatus);
                    helperService.command(VIN,command);
                    return ResponseEntity.status(HttpStatus.OK).build();
                }
                else{
                    System.out.println("RLUL  function is not available");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }

            }
            else{
                System.out.println("TCU is not enabled");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

        }
        else{
            System.out.println("VIN is Not valid");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    private String getDBRemoteCommand(String command) {
        if("LOCK".equalsIgnoreCase(command)) {
            return "Y";
        } else {
            return "N";
        }
    }
}




