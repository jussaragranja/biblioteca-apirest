package br.com.jussara.apirest.biblioteca.test.book;

import br.com.jussara.apirest.biblioteca.config.SetupTest;
import br.com.jussara.apirest.biblioteca.model.BookModel;
import br.com.jussara.apirest.biblioteca.repository.BookRepositoryTestUtil;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static br.com.jussara.apirest.biblioteca.constant.Constants.MESSAGE_BOOK_NOT_FOUND;
import static br.com.jussara.apirest.biblioteca.constant.ConstantsTestUtil.*;
import static br.com.jussara.apirest.biblioteca.constant.Paths.PATH_BOOK_BY_ID;
import static br.com.jussara.apirest.biblioteca.constant.Paths.PATH_BOOK_ID_NULL;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author jussaragranja
 * Tests for endpoint get book by id (/book/{id})
 */

public class GetBookByIdTest extends SetupTest {

    @Autowired
    private BookRepositoryTestUtil bookRepositoryTestUtil;

    @Test
    public void deveRetornar200_quandoConsultarProdutoPorId(){

        BookModel bookModel = bookRepositoryTestUtil.findRandomBook();

        given()
            .pathParam(ID, bookModel.getId())
        .when()
            .get(PATH_BOOK_BY_ID)
        .then()
            .contentType(ContentType.JSON)
            .statusCode(HttpStatus.SC_OK)
            .body(ID, equalTo(bookModel.getId().intValue()))
            .body(TITLE, equalTo(bookModel.getTitle()))
            .body(SUBTITLE, equalTo(bookModel.getSubtitle()))
            .body(PUBLISHNGCOMPANY, equalTo(bookModel.getPublishingCompany()))
            .body(SUBTITLE, equalTo(bookModel.getSubtitle()));
    }

    @Test
    public void deveRetornar404_quandoConsultarProdutoComIdInexistente(){

        BookModel produtoModel = bookRepositoryTestUtil.findBookLastId();

        given()
            .pathParam(ID, produtoModel.getId()+1)
        .when()
            .get(PATH_BOOK_BY_ID)
        .then()
            .contentType(ContentType.JSON)
            .statusCode(HttpStatus.SC_NOT_FOUND)
            .body(PATH_MESSAGE, equalTo(MESSAGE_BOOK_NOT_FOUND));
    }

    @Test
    public void deveRetornar400_quandoConsultarProdutoComIdVazio(){

        given()
            .pathParam(ID, "")
        .when()
            .get(PATH_BOOK_BY_ID)
        .then()
            .contentType(ContentType.JSON).log().all()
            .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void deveRetornar400_quandoConsultarProdutoComIdNulo(){

        given()
        .when()
            .get(PATH_BOOK_ID_NULL)
        .then()
            .contentType(ContentType.JSON)
            .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    //TODO Adicionar exception para request contendo caracteres
    @Test
    public void deveRetornar404_quandoConsultarProdutoComIdInvalido(){

        given()
            .pathParam(ID, ID_INVALIDO)
        .when()
            .get(PATH_BOOK_BY_ID)
        .then()
            .contentType(ContentType.JSON).log().all()
            .statusCode(HttpStatus.SC_BAD_REQUEST).log().all();
    }
}
