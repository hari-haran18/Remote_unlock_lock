package com.example.demo.service;

import com.example.demo.data.RLULValidityResponse;
import com.example.demo.data.VINValidityResponse;
import com.example.demo.data.VehicleLockStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

//@Component
public class HelperService {
    //    @Autowired
    RestTemplate restTemplate;

    public HelperService() {
        this.restTemplate = new RestTemplate();
    }

    public boolean isValidVIN(String VIN) {
        String fooResourceUrl = "http://localhost:9101/api/v1/isValidVehicle";
        ResponseEntity<VINValidityResponse> response
                = restTemplate.getForEntity(fooResourceUrl + "/" + VIN, VINValidityResponse.class);
        if (response.getBody() != null) {
            return response.getBody().valid;
        }
        return false;
    }

    public boolean isValidTCU(String VIN) {
        String fooResourceUrl = "http://localhost:9101/api/v1/isTCUEnabled";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl + "/" + VIN, String.class);
        if (response.getBody() != null) {
            return response.getBody().equalsIgnoreCase("Y");
        }
        return false;
    }

    public boolean isRLULavailable(String VIN) {
        String fooResourceUrl = "http://localhost:9101/api/v1/isValidFeature/" + VIN + "/RLUL";
        ResponseEntity<RLULValidityResponse> response
                = restTemplate.getForEntity(fooResourceUrl, RLULValidityResponse.class);
        if (response.getBody() != null) {
            return response.getBody().valid;
        }
        return false;
    }

    public String lockStatus(String VIN) {
        String fooResourceUrl = "http://localhost:9101/api/v1/lockStatus/" + VIN;
        ResponseEntity<VehicleLockStatus> response = restTemplate.getForEntity(fooResourceUrl, VehicleLockStatus.class);
        if (response.getBody() != null) {
            return response.getBody().isLocked;
        }
        return "";
    }

    public void command(String VIN, String command) {
        String fooResourceUrl = "http://localhost:9101/api/v1/updateLockStatus/" + VIN + "/" + command;
        restTemplate.put(fooResourceUrl, VehicleLockStatus.class);
    }
}
