package Swagger_Pet_Store;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.*;

import java.io.File;

public class Pet_Store_Automation {
        @Test
        public void Create_user() {
                Response response = given()
                                .header("accept", "application/json")
                                .header("Content-Type", "application/json")
                                .body("{\r\n" + //
                                                "  \"id\": 1,\r\n" + //
                                                "  \"username\": \"vishalshukla123\",\r\n" + //
                                                "  \"firstName\": \"Vishal\",\r\n" + //
                                                "  \"lastName\": \"Shukla\",\r\n" + //
                                                "  \"email\": \"vishalshukla123@gmail.com\",\r\n" + //
                                                "  \"password\": \"Pankaj123\",\r\n" + //
                                                "  \"phone\": \"7771860135\",\r\n" + //
                                                "  \"userStatus\": 0\r\n" + //
                                                "}")
                                .when()
                                .post("https://petstore.swagger.io/v2/user\n");
                // print the response for debugging
                response.prettyPrint();
                // Assert
                response.then().assertThat().statusCode(200);

        }

        @Test
        public void create_list_of_users() {
                Response response = given()
                                .header("accept", "application/json")
                                .header("Content-Type", "application/json")
                                .body("""
                                                [
                                                  {
                                                    "id": 2,
                                                    "username": "vikram123",
                                                    "firstName": "Vikram",
                                                    "lastName": "shukla",
                                                    "email": "vikram123@gmail.com",
                                                    "password": "Vikram123",
                                                    "phone": "1234567890",
                                                    "userStatus": 0
                                                  }
                                                ]
                                                """)
                                .when().post("https://petstore.swagger.io/v2/user/createWithArray");
                // print the result
                response.prettyPrint();

                response.then().assertThat().statusCode(200);

        }

        @Test
        public void LogOutUser() {
                Response response = given()
                                .header("accept", "application/json")// set the accept header
                                .when()
                                .get("https://petstore.swagger.io/v2/user/logout");// specify the endpoint

                // print the response body
                response.prettyPrint();

                // assert compare the response code
                response.then().assertThat().statusCode(200);

        }

        @Test
        public void LogsUserintoTheSystem() {
                Response response = given()
                                .header("accept", "application/json")
                                .when()
                                .get("https://petstore.swagger.io/v2/user/login?username=vishalshukla123&password=Pankaj123");

                // print the response
                response.prettyPrint();

                // compare the response code
                response.then().assertThat().statusCode(200);

        }

        @Test
        public void DeleteUser() {
                Response response = RestAssured.given()
                                .header("accept", "application/json")// set the accept header
                                .when()
                                .delete("https://petstore.swagger.io/v2/user/Vishalshukla123");
                response.prettyPrint();
                // Check the status codes correctly
                if (response.getStatusCode() == 400) {
                        System.out.println("Invalid username supplied");
                } else if (response.getStatusCode() == 404) {
                        System.out.println("User not found");
                } else if (response.getStatusCode() == 200) {
                        System.out.println("User successfully deleted.");
                }
                response.then().assertThat().statusCode(404);
        }

        @Test
        public void UpdateUser() {
                Response response = given()
                                .header("accept", "application/json")
                                .header("Content-Type", "application/json")
                                .body("""
                                                {
                                                             "id": 3,
                                                             "username": "Pankaj123",
                                                             "firstName": "Pankaj",
                                                             "lastName": "Shukla",
                                                             "email": "Pankaj13@gmail.com",
                                                             "password": "Pankaj123",
                                                             "phone": "7771860135",
                                                             "userStatus": 0
                                                           }
                                                    """)
                                .when()
                                .put("https://petstore.swagger.io/v2/user/vishalshukla123");
                // print response
                response.prettyPrint();
                // compare the response code
                if (response.getStatusCode() == 400) {
                        System.out.println("\t\r\n" + //
                                        "Invalid user supplied");
                }
                if (response.getStatusCode() == 404) {
                        System.out.println("User not found");
                }
                response.then().assertThat().statusCode(200);

        }

        @Test
        public void getUserbyUsername() {
                Response response = given()
                                .header("accept", "application/json")
                                .when()
                                .get("https://petstore.swagger.io/v2/user/vishalshukla123");

                // compare the response code
                if (response.getStatusCode() == 404) {
                        System.out.println("Invalid username supplied");

                }
                if (response.getStatusCode() == 400) {
                        System.out.println("User not found");
                }
                if (response.getStatusCode() == 200) {
                        System.out.println("successful operation");
                        response.prettyPrint();
                }
                response.then().assertThat().statusCode(200);
        }

        @Test
        public void create_list_of_users_with_given_input_Array() {
                Response respone = given()
                                .header("accept", "application/json")
                                .header("Content-Type", "application/json")
                                .body("""
                                                                        [
                                                  {
                                                    "id": 3,
                                                    "username": "Vipul123",
                                                    "firstName": "Vipul",
                                                    "lastName": "Shukla",
                                                    "email": "Vipulshukla123@gmail.com",
                                                    "password": "Vipul123",
                                                    "phone": "1234567890",
                                                    "userStatus": 0
                                                  }
                                                ]
                                                    """)
                                .when()
                                .post("https://petstore.swagger.io/v2/user/createWithList");
                // print the response
                respone.prettyPrint();
                // compare the response code
                respone.then()
                                .assertThat().statusCode(200);

        }

        // pet everything about pet
        @Test
        public void add_new_pet_toStore() {
                Response response = given()
                                .header("accept", "application/json")
                                .header("Content-Type", "application/json")
                                .body("""
                                                                {
                                                  "id": 1,
                                                  "category": {
                                                    "id": 1,
                                                    "name": "Tommy"
                                                  },
                                                  "name": "doggie",
                                                  "photoUrls": [
                                                    "string"
                                                  ],
                                                  "tags": [
                                                    {
                                                      "id": 2,
                                                      "name": "kutta "
                                                    }
                                                  ],
                                                  "status": "available"
                                                }
                                                                 """)
                                .when()
                                .post("https://petstore.swagger.io/v2/pet");

                // compare the response code
                if (response.getStatusCode() == 200) {
                        response.prettyPrint();
                }
                if (response.getStatusCode() == 405) {
                        System.out.println("Invalid input");
                }

        }

        @Test
        public void uploadImage() {
                // photo path
                File file = new File("C:\\Users\\acer\\Desktop\\Dogphoto.jpeg");

                Response response = given()
                                .contentType("multipart/form-data") // setting content type
                                .header("accept", "application/json") // accepting json response

                                .multiPart("file", file, "image/jpeg") // file upload with its mime type

                                .when()
                                .post("https://petstore.swagger.io/v2/pet/1/uploadImage");
                response.then().assertThat().statusCode(200);

                // print the respons e
                response.prettyPrint();

        }

        @Test
        public void updatePetDetails() {
                Response response = given()
                                .header("accept", "application/json")
                                .header("Content-Type", "application/json")
                                .body("""
                                                                           {
                                                  "id": 2,
                                                  "category": {
                                                    "id": 2,
                                                    "name": "Cat"
                                                  },
                                                  "name": "BIlla",
                                                  "photoUrls": [
                                                    "string"
                                                  ],
                                                  "tags": [
                                                    {
                                                      "id": 2,
                                                      "name": "Billi"
                                                    }
                                                  ],
                                                  "status": "available"
                                                }
                                                                                """)
                                .when()
                                .put("https://petstore.swagger.io/v2/pet");

                if (response.getStatusCode() == 200) {
                        response.prettyPrint();
                }
                if (response.getStatusCode() == 400) {
                        System.out.println("Invalid ID supplied");
                }
                if (response.getStatusCode() == 404) {
                        System.out.println("Pet not found");
                }
                if (response.getStatusCode() == 405) {
                        System.out.println("Validation exception");
                }
        }

        @Test
        public void find_pets_by_status() {
                Response response = given()
                                .header("accept", "application/json") // set header to accept json response
                                .queryParam("status", "available,pending,sold") // pass multipule status values

                                .when()
                                .get("https://petstore.swagger.io/v2/pet/findByStatus?status=available")
                                .then()
                                .statusCode(200) // verify the response code is 200(ok)
                                .extract().response();

                // print the response body json data
                System.out.println("Response body:");
                System.out.println(response.getBody().asPrettyString());
                response.then().assertThat().statusCode(200);

        }

        @Test
        public void find_Pet_by_Id() {
                Response response = given()
                                .header("accept", "application/json")
                                .when()
                                .get("https://petstore.swagger.io/v2/pet/3");
                if (response.getStatusCode() == 200) {
                        response.prettyPrint();
                }
                if (response.getStatusCode() == 400) {
                        System.out.println("Invalid ID Suoolied");
                }
                if (response.getStatusCode() == 404) {
                        System.out.println("pet not found ");
                }

                response.then().assertThat().statusCode(200);
        }

        @Test
        public void updatePetintoTheStore() {
                RestAssured.baseURI = "https://petstore.swagger.io/v2";

                Response response = given()
                                .header("accept", "application/json")
                                .header("Content-Type", "application/x-www-form-urlencoded")
                                .body("name=naughty&status=sold")
                                .when()
                                .post("/pet/3");
                if (response.getStatusCode() == 200) {
                        response.prettyPrint();
                }
                if (response.getStatusCode() == 405) {
                        System.out.println("invalid input");
                }

                response.then().assertThat().statusCode(200);

        }

        @Test
        public void DeletePet() {
                Response response = given()
                                .header("accept", "application/json")
                                .when()
                                .delete("https://petstore.swagger.io/v2/pet/3");
                if (response.getStatusCode() == 200) {
                        response.prettyPrint();
                }
                if (response.getStatusCode() == 400) {
                        System.out.println("Invalid ID Supplied");
                }
                if (response.getStatusCode() == 404) {
                        System.out.println("Pet not found");
                }
                response.then().assertThat().statusCode(200);

        }

        @Test
        public void Place_An_order_for_a_pet() {
                RestAssured.baseURI = "https://petstore.swagger.io/v2";
                Response response = given()
                                .header("accept", "application/json")
                                .header("Content-Type", "application/json")
                                .body("""
                                                                            {
                                                  "id": 1,
                                                  "petId": 1,
                                                  "quantity": 1,
                                                  "shipDate": "2024-09-20T06:33:49.713Z",
                                                  "status": "placed",
                                                  "complete": true
                                                }
                                                                                """)
                                .when()
                                .post("/store/order");
                if (response.getStatusCode() == 200) {
                        response.prettyPrint();
                }
                if (response.getStatusCode() == 400) {
                        System.out.println("Invalid order");
                }
                response.then().assertThat().statusCode(200);

        }

        // Store access to petstore orders
        @Test
        public void returnpet_Inventories_by_status() {
                RestAssured.baseURI = "https://petstore.swagger.io/v2";
                Response response = given()
                                .header("accept", "application/json")
                                .when()
                                .get("/store/inventory");
                response.then()
                                .assertThat().statusCode(200);
                if (response.getStatusCode() == 200) {
                        response.prettyPrint();
                }
                response.then().assertThat().statusCode(200);

        }

        @Test
        public void Find_purchase_order_by_id() {
                RestAssured.baseURI = "https://petstore.swagger.io/v2";
                Response response = given()
                                .header("accept", "application/json")
                                .when()
                                .get("/store/order/");

                if (response.getStatusCode() == 200) {
                        response.prettyPrint();
                }
                if (response.getStatusCode() == 400) {
                        System.out.println("INvalid ID supplied");
                }
                if (response.getStatusCode() == 404) {
                        System.out.println("Order not found");
                }
                response.then().assertThat().statusCode(200);
        }

        @Test
        public void Delete_purchase_order_by_ID() {
                RestAssured.baseURI = "https://petstore.swagger.io/v2";
                Response response = given()
                                .header("accept", "application/json")
                                .when()
                                .delete("store/order/1");
                if (response.getStatusCode() == 200) {
                        response.prettyPrint();
                }
                if (response.getStatusCode() == 400) {
                        System.out.println("Invalid ID Supplied");
                }
                if (response.getStatusCode() == 404) {
                        System.out.println("Order not found");
                }
                response.then().assertThat().statusCode(200);

        }
}
