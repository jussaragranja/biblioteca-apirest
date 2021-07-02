package br.com.jussara.apirest.biblioteca.config;

import io.restassured.RestAssured;
import io.restassured.config.ConnectionConfig;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

/**
 * @author jussaragranja
 * Base class for Execution
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SetupTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void before(){
        RestAssured.port = port;
        RestAssured.config().connectionConfig(new ConnectionConfig().closeIdleConnectionsAfterEachResponse());
    }
}
