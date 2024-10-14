package JsonSeerver;

import static io.restassured.RestAssured.given;

import org.junit.Test;

import io.restassured.response.Response;

public class JsonServeroperations {

    @Test
    public void getData()
    {
        Response response = given()
        .header("Content-Type","application/json")
       .when().get("http://localhost:3000/posts");
       response.prettyPrint();
       response.then().assertThat().statusCode(200);
    }
    @Test
    public void CreateData()
    {
        Response response=given()
        .header("Content-Type","application/json")
        .body(
        """
        {
        "id": "1",
        "title": "Manual tesing "
    }
                
                """
        )
        .when().post("http://localhost:3000/posts");
        response.prettyPrint();
        response.then().assertThat().statusCode(201);
    }

    @Test
    public void deleteData()
    {
        Response response=given()
        .header("Content-Type","application/json")
.when().delete("http://localhost:3000/posts/1");
response.then().assertThat().statusCode(200);
    }

    @Test
    public void  Updatedata()
    {
        Response response = given()
        .header("Content-Type","application/json")
        .body("""
                 {
        "id": "3",
        "title": "api  tesing "
    }
                """)
.when().put("http://localhost:3000/posts/2");
response.then().assertThat().statusCode(200);
    }
    
}
