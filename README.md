# device

This REST API is used for CRUD operations involving devices.

You don't need to set up a new database for test proposes.

If you want to set up a MySql database the schema should have the name device.

You can test with maven doing the following command:
mvn clean compile test

You can run the application with the following command:
mvn spring-boot:run

The following endpoints are available:

/device/all - GET

/device/find/{id} - GET

/device/find?brand=somebrand - GET

/device/add?name=somename&brand=somebrand - GET

/device/add - POST

/device/update/{id} - PUT

/device/delete/{id} - DELETE
