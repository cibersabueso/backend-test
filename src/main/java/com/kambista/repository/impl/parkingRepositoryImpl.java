package com.kambista.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import org.springframework.jdbc.core.RowMapper;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.kambista.model.ResultModel;
import com.kambista.model.registerParkingPayModel;
import com.kambista.model.vehicletypeModel;
import com.kambista.model.request.vehicleLicenseRequestModel;
import com.kambista.model.request.vehicleRequestModel;
import com.kambista.repository.parkingRepository;

@Repository
public class parkingRepositoryImpl extends JdbcDaoSupport implements parkingRepository{

	@Autowired 
	DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	
	@Override
	public List<vehicletypeModel> allVehicletype() {
		
		String sql = "select * from vehicletype";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		
		List<vehicletypeModel> list = new ArrayList<vehicletypeModel>();
		for(Map<String, Object> row:rows){
			vehicletypeModel vehicleType = new vehicletypeModel();
			vehicleType.setVehicleTypeId((Long) row.get("vehicleTypeId"));
			vehicleType.setName((String) row.get("name"));
			vehicleType.setCreatedAt((Date) row.get("createdAt"));
			vehicleType.setUpdatedAt((Date) row.get("updatedAt"));
			list.add(vehicleType);
		}
		return list;
	}

	@Override
	public ResultModel registerVehiche(vehicleRequestModel vehicleRequest) {
		
		ResultModel resultModel = new ResultModel();
		
		String sql = "INSERT INTO parking.vehicle "
				+ "(vehicleTypeId, licenseNumber, createdAt, updatedAt) "
				+ "VALUES("+vehicleRequest.getVehicleTypeId()+", '"+vehicleRequest.getLicenseNumber()+"', now(), now());";
		
		try {			
			int result = getJdbcTemplate().update(sql);
			resultModel.setCode(result == 0 ? 401 : 200);
			resultModel.setIsSuccess(result == 0 ? false : true);
		}catch (Exception e) {
			resultModel.setCode(500);
			resultModel.setIsSuccess(false);
			resultModel.setMessage(e.getMessage());
		}
		
		return resultModel;
	}


	@SuppressWarnings("deprecation")
	@Override
	public registerParkingPayModel registerParking(vehicleLicenseRequestModel vehicleLicenseRequest) {
		
		registerParkingPayModel registerParking = new registerParkingPayModel();
		String sql = "";
		
		if(vehicleLicenseRequest.getIsExit()) {
			sql = "call registerParkingOutPay('"+vehicleLicenseRequest.getLicenseNumber()+"')";
			
			return (registerParkingPayModel)getJdbcTemplate().queryForObject(sql, new Object[]{}, new RowMapper<registerParkingPayModel>(){
				public registerParkingPayModel mapRow(ResultSet rs, int rwNumber) throws SQLException {
					
					registerParking.setLicenseNumber(rs.getString("licenseNumber"));
					registerParking.setPrice(rs.getString("price"));
					registerParking.setMinutes(rs.getLong("minutes"));
					registerParking.setPay(rs.getBigDecimal("pay"));

					return registerParking;
				}
			});
			
		}else {
			sql = "call registerParkingIn('"+vehicleLicenseRequest.getLicenseNumber()+"')";
			int result = getJdbcTemplate().update(sql);
			registerParking.setLicenseNumber(result == 0 ? null : vehicleLicenseRequest.getLicenseNumber());
			return registerParking;
		}

	}
}
