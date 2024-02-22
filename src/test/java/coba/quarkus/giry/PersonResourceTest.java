package coba.quarkus.giry;

import api.dto.PersonDto.MoneyRequestDto;
import api.dto.PersonDto.PersonRequestDto;
import api.resource.PersonResource;
import api.service.PersonImpl;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@TestHTTPEndpoint(PersonResource.class)
public class PersonResourceTest {

    @InjectSpy
    PersonImpl personService;

    @Test
    void getAllTestEndPoint(){
        given().when().get()
                .then().statusCode(200);
        Mockito.verify(personService,Mockito.times(1)).getAll();
    }

    @Test
    void getPersonByIdTestEndPoint(){
        given().pathParam("personId", 2L).when().get("/{personId}")
                .then().body("personId", equalTo(2))
                .statusCode(200);
        Mockito.verify(personService,Mockito.times(1)).getById(2L);
    }

    @Test
    void updatePersonTestEndPoint(){
        PersonRequestDto personRequestDto = new PersonRequestDto("Ib", "Bog", 5000.0);
        given().pathParam("personId", 2L)
                .contentType("application/json")
                .body(personRequestDto)
                .when().put("/update/{personId}")
                .then().statusCode(200);
        Mockito.verify(personService, Mockito.times(1)).update(2L, personRequestDto);
    }

    @Test
    void addPersonTestEndPoint(){
        PersonRequestDto personRequestDto = new PersonRequestDto("Budi", "Depok", 20000.0);
        given().contentType("application/json")
                .body(personRequestDto)
                .when().post("/add")
                .then().statusCode(200);
        Mockito.verify(personService,Mockito.times(1)).create(personRequestDto);
    }

    @Test
    void deletePersonTestEndPoint(){
        given().pathParam("personId", 1L).when()
                        .delete("/delete/{personId}").then().statusCode(204);
        Mockito.verify(personService, Mockito.times(1)).delete(1L);
    }

    @Test
    void topupMoneyPersonTestEndPoint(){
        MoneyRequestDto moneyRequestDto = new MoneyRequestDto(2000.0);
        given().pathParam("personId",2L).contentType("application/json")
                .body(moneyRequestDto)
                .when().put("/topup/{personId}")
                .then().statusCode(200);

        Mockito.verify(personService,Mockito.times(1)).topupMoney(2L, moneyRequestDto);
    }
}
