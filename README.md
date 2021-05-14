### 1) Ejecute los scripts de la base de datos estan en mysql por orden dentro de la carpeta "kambista-backend-test-sql"

- 1_parking_db.sql
- 2_data.sql
- 3_kambista-SP.sql

Los apis de spring son :

### Todos los tipo de vehiculos registrados

- SWAGGER : http://localhost:8080/swagger-ui.html

- GET : http://localhost:8080/parking/allvehicletype 

#### Para registrar un vehiculo
- POST : http://localhost:8080/parking/registerVehicle

``
body :
{
  "licenseNumber": "123456", // placa del vehiculo
  "vehicleTypeId": 2		 // tipo de vehiculo
}
``

#### Para registrar entrada y salida
- POST : http://localhost:8080/parking/registerParking :

``
body :
{
  "isExit": true,  // false es cuando va de entrada, y true cuando es de salida
  "licenseNumber": "E01A46" // numero de la placa
}
``

### Las apis estan en un archivo postman que esta en la carpeta : "Postman"
