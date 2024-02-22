package coba.quarkus.giry;

import api.dto.BankDto.BankRequestDto;
import api.enumeration.StatusBank;
import api.resource.BankResource;
import api.service.BankImpl;
import api.service.PersonImpl;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@TestHTTPEndpoint(BankResource.class)
public class BankResourceTest {
    @InjectSpy
    BankImpl bankService;

    @Test
    void getAllBankTestEndPoint(){
        given().when().get()
                .then().statusCode(200);

        Mockito.verify(bankService,Mockito.times(1)).getAll();
    }

    @Test
    void getBankByIdTestEndPoint(){
        given().pathParam("bankId", 2L).when()
                .get("/{bankId}")
                .then().body("bankId",equalTo(2))
                .statusCode(200);
        Mockito.verify(bankService,Mockito.times(1)).getById(2L);
    }

    @Test
    void addBankTestEndPoint(){
        BankRequestDto bankRequestDto = new BankRequestDto("BNI", "BUMN", StatusBank.NOT_ACTIVE);
        given().contentType("application/json")
                .body(bankRequestDto)
                .when().post("/add")
                .then().statusCode(200);
        Mockito.verify(bankService, Mockito.times(1)).create(bankRequestDto);
    }

    @Test
    void deleteBankTestEndPoint(){
        given().pathParam("bankId",1L)
                .when().delete("/delete/{bankId}")
                .then().statusCode(204);
        Mockito.verify(bankService, Mockito.times(1)).delete(1L);
    }

    @Test
    void updateBankTesEndPoint(){
        BankRequestDto bankRequestDto = new BankRequestDto("BRI", "BUMN", StatusBank.ACTIVE);
        given().pathParam("bankId", 2L)
                .contentType("application/json")
                .body(bankRequestDto)
                .when().put("/update/{bankId}")
                .then().statusCode(200);
        Mockito.verify(bankService,Mockito.times(1)).update(2L, bankRequestDto);
    }
}
