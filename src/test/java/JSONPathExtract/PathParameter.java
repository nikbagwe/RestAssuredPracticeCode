package JSONPathExtract;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class PathParameter {

    //query parameter = key value is defined in the requirement (Limit = 5) limit is fixed cant be changed value we can add as per requiement
    //path parameter = <any key, Value> , <year, 2010>
    @Test
    public void pathParamsGetCall()
    {       int year = 2010;
            String seasonYear = "2010";
            baseURI = "http://ergast.com";
            given().log().all()
                    .pathParam("year",year)
                    .when().log().all()
                    .get("/api/f1/{year}/circuits.json")
                    .then().log().all()
                    .assertThat()
                    .statusCode(200)
                    .and()
                    .body("MRData.CircuitTable.season",equalTo(seasonYear));
    }
}
