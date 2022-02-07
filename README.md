# Remote Lock or Unlock feature in Vehicle

Send remote commands to lock or unlock a vehicle

## Use cases

Need to create module which will send lock or unlock command to cloud to maintain the state of a car.

1) Send a lock or unlock command to cloud - 

   Steps:

       1) Validate the VIN is valid by calling the API
       2) Check the Vehicle connectivity(TCU) is enabled by calling the API
       3) Validate the Remote Lock/Unlock (RLUL) feature is available in the vehicle by calling the API
       4) If the vehicle supports RLUL, Lock/unlock the vehicle based on the command issued
       5) Update the status to the master API
