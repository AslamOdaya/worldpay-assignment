# Spring Boot REST API application

This is a small REST API spring boot app that demonstrates simple GET, POST, PUT HTTP requests using.

## Configuration
### Database
This uses the H2 embedded database. The configuration of it is defined in `application.properties`  by default the URL is set to `jbdc:h2:mem:myapp` but this can be changed. As this a embedded database, each time the application is run, the database will be emptied.

The database console can be accessed via `http://localhost:8080/h2-console/`. You can connect with the default value. if the JDBC URL is not set to `jbdc:h2:mem:myapp`, you must change it to this.

### Dependencies
This is a gradle project. All dependencies are added to the `build.gradle` file.


## Usage

### Running from an IDE
The project can be run from an IDE (IntelliJ) just like a java application. You can run the `AssignmentApplication` class from the IDE and it should fire up the spring boot app.

### Running from command line
This project can also run from the command line. To fire up the application use the command `gradle bootRun`.

## Building

To build a `.jar` file run command `gradle build`


### Testing

Unit and Integeration tests can be run via `gradlew test` or through the IDE.

You can test the API via software like `Postman` (recommended) or using `curl`.
#### Examples


`POST` - `http://localhost:8080/offers/` - Used to create a new offer

Request body:
```
{
   "offerName" :"10% off",
   "offerDescription" :"Add 10% off on items when this is applied",
   "offerStartDate" :"2019-07-10",
   "offerExpiryDate":"2019-08-10",
   "currency":"GBP"
}
```
Response body:
```
{
   "id": 1,
   "offerName" :"10% off",
   "offerDescription" :"Add 10% off on items when this is applied",
   "offerStartDate" :"2019-07-10",
   "offerExpiryDate":"2019-08-10",
   "currency":"GBP"
}
```

`GET` `http://localhost:8080/offers/1` - Used to get an offer based on Id

 Response Body:
```
{
   "id": 1,
   "offerName" :"10% off",
   "offerDescription" :"Add 10% off on items when this is applied",
   "offerStartDate" :"2019-07-10",
   "offerExpiryDate":"2019-08-10",
   "currency":"GBP"
}
```

`PUT` `http://localhost:8080/offers/1` - Used to cancel an offer based on Id.
Updates the expiry date to the current date to indicate it's not available after the current day.

```
{
   "id": 1,
   "offerName" :"10% off",
   "offerDescription" :"Add 10% off on items when this is applied",
   "offerStartDate" :"2019-07-10",
   "offerExpiryDate":"2019-08-10",
   "currency":"GBP"
}