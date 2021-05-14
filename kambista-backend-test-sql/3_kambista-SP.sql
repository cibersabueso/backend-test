CREATE PROCEDURE parking.registerParkingIn(IN inLicenseNumber VARCHAR(6))
begin
	declare v_vehicleId  int(11) unsigned;       
	select vehicleId into v_vehicleId from vehicle where licenseNumber = inLicenseNumber LIMIT 1; 
	INSERT INTO parking (vehicleId, parkingStart, parkingEnd, createdAt, updatedAt) VALUES(v_vehicleId, now(), NULL, now(), now());
END


CREATE PROCEDURE parking.registerParkingOutPay(IN inLicenseNumber VARCHAR(6))
begin
	declare ISearchvehicleId  int(11) unsigned;
	declare IparkingID  int(11) unsigned;

	select vehicleId into ISearchvehicleId from vehicle where licenseNumber = inLicenseNumber LIMIT 1;
    select parkingId into IparkingID from parking where vehicleId = ISearchvehicleId and parkingEnd is null LIMIT 1;
    update parking set parkingEnd = now(), updatedAt = now() where vehicleId = ISearchvehicleId and parkingEnd is null LIMIT 1;
	select v.vehicleId, v.vehicleTypeId, v.licenseNumber, v.name , v.price,  TIMESTAMPDIFF(MINUTE,p.parkingStart,p.parkingEnd) as minutes, TIMESTAMPDIFF(MINUTE,p.parkingStart,p.parkingEnd)*cast(v.price as decimal(5,2)) as pay 
	 from (select v.vehicleId, v.vehicleTypeId,  v.licenseNumber , f.name, f.price from vehicle v ,feed f where v.vehicleTypeId = f.vehicleTypeId ) v  inner join parking p on v.vehicleId = p.vehicleId and p.parkingId = IparkingID where v.vehicleId = ISearchvehicleId;
end