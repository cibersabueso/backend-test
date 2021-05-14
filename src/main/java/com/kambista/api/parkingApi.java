package com.kambista.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kambista.model.ResultModel;
import com.kambista.model.registerParkingPayModel;
import com.kambista.model.vehicletypeModel;
import com.kambista.model.request.vehicleLicenseRequestModel;
import com.kambista.model.request.vehicleRequestModel;
import com.kambista.service.parkingService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/parking")
@ComponentScan("com.kambista.service.impl, com.kambista.repository.impl")

public class parkingApi {
    @Autowired
    private parkingService parkingServ;
    
    @GetMapping("/allvehicletype" )
    @ApiOperation(value = "All vehicle type")
    
    @ApiResponses({
            @ApiResponse(code = 200, message = "Resuelto correctamente"),
            @ApiResponse(code = 404, message = "No encontrado")
    })
    public List<vehicletypeModel> findAll(){
    	
        return parkingServ.allVehicletype();
        
    }
   
    @PostMapping("/registerVehicle" )
    @ApiOperation(value = "Register new Vehicle")  
    @ApiResponses({
            @ApiResponse(code = 200, message = "Resuelto correctamente"),
            @ApiResponse(code = 404, message = "No encontrado")
    })
    public ResultModel registerVehiche(@RequestBody vehicleRequestModel vehicleRequest){
    	
        return parkingServ.registerVehiche(vehicleRequest);
        
    }
    
    @PostMapping("/registerParking" )
    @ApiOperation(value = "register Parking")  
    @ApiResponses({
            @ApiResponse(code = 200, message = "Resuelto correctamente"),
            @ApiResponse(code = 404, message = "No encontrado")
    })
    public registerParkingPayModel registerParking(@RequestBody vehicleLicenseRequestModel vehicleLicenseRequest){
    	
        return parkingServ.registerParking(vehicleLicenseRequest);
        
    }
}
