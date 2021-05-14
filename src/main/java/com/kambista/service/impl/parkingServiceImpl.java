package com.kambista.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kambista.model.ResultModel;
import com.kambista.model.registerParkingPayModel;
import com.kambista.model.vehicletypeModel;
import com.kambista.model.request.vehicleLicenseRequestModel;
import com.kambista.model.request.vehicleRequestModel;
import com.kambista.repository.parkingRepository;
import com.kambista.service.parkingService;

@Service
public class parkingServiceImpl implements parkingService{

	@Autowired parkingRepository parkingRepo;
	
	@Override
	public List<vehicletypeModel> allVehicletype() {
		List<vehicletypeModel> list = parkingRepo.allVehicletype();
		return list;
	}

	@Override
	public ResultModel registerVehiche(vehicleRequestModel vehicleRequest) {
		return parkingRepo.registerVehiche(vehicleRequest);
	}

	@Override
	public registerParkingPayModel registerParking(vehicleLicenseRequestModel vehicleLicenseRequest) {
		return parkingRepo.registerParking(vehicleLicenseRequest);
	}

}
