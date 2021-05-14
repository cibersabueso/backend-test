package com.kambista.service;

import java.util.List;

import com.kambista.model.ResultModel;
import com.kambista.model.registerParkingPayModel;
import com.kambista.model.vehicletypeModel;
import com.kambista.model.request.vehicleLicenseRequestModel;
import com.kambista.model.request.vehicleRequestModel;

public interface parkingService {
	List<vehicletypeModel> allVehicletype();
	ResultModel registerVehiche(vehicleRequestModel vehicleRequest);
	registerParkingPayModel registerParking(vehicleLicenseRequestModel vehicleLicenseRequest);
}
