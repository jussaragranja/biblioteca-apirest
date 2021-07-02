package br.com.jussara.apirest.biblioteca.test;

import br.com.jussara.apirest.biblioteca.config.SetupTest;
import br.com.jussara.apirest.biblioteca.model.BookModel;
import br.com.jussara.apirest.biblioteca.repository.BookRepositoryTestUtil;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

import static br.com.jussara.apirest.biblioteca.constant.ConstantsTestUtil.ID;
import static br.com.jussara.apirest.biblioteca.constant.Paths.PATH_BOOKS;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author jussaragranja
 * Tests for endpoint get books list (/books)
 */

public class GetBooksTest extends SetupTest {

    @LocalServerPort
    int port;

    @Autowired
    private BookRepositoryTestUtil bookRepositoryTestUtil;

    @Disabled
    @Test
    public void deveRetornar200_contratoQuandoConsultarListaDeLivros(){

        given()
        .when()
            .get(PATH_BOOKS)
        .then()
            .contentType(ContentType.JSON)
            .statusCode(HttpStatus.SC_OK)
            .body(matchesJsonSchemaInClasspath("schema/getBooks.json"));
    }

    @Test
    public void deveRetornar200_quandoConsultarListaDeLivros(){

        List<BookModel> booksModel = bookRepositoryTestUtil.findAll();

        given()
        .when()
            .get(PATH_BOOKS)
        .then()
            .contentType(ContentType.JSON)
            .statusCode(HttpStatus.SC_OK);
    }
}
