package br.com.jussara.apirest.biblioteca.unit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static br.com.jussara.apirest.biblioteca.constant.Constants.PATH_API_BOOKS;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test() throws Exception {

//        mockMvc.perform(get(PATH_API_BOOKS)
//                .contentType(APPLICATION_JSON))
//                .andExpect(status().isOk());

        mockMvc
                .perform(MockMvcRequestBuilders.get(PATH_API_BOOKS))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(APPLICATION_JSON));
    }

/*    @Test
    public void test(){
        mockMvc
                .perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(ContentType.JSON));
    }*/
}
