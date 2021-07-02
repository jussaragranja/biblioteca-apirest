package br.com.jussara.apirest.biblioteca.unit;

import br.com.jussara.apirest.biblioteca.controller.BookController;
import br.com.jussara.apirest.biblioteca.model.BookModel;
import br.com.jussara.apirest.biblioteca.repository.BookRepository;
import br.com.jussara.apirest.biblioteca.service.BookService;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static br.com.jussara.apirest.biblioteca.constant.Constants.PATH_API_BOOKS;
import static br.com.jussara.apirest.biblioteca.constant.Constants.PATH_API_BOOK_ID;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestGetBookByIdUnit {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BookRepository bookRepository;

    @MockBean
    private BookController bookControllerMock;

    @MockBean
    private BookService bookServiceMock;

    @Autowired
    public BookService bookService;

    BookModel mockBookModel = new BookModel();

    @Test
    public void testServiceGetListBooks() throws Exception {
        List<BookModel> booksModel = singletonList(mockBookModel);

        given(bookControllerMock.listBooks())
            .willReturn(booksModel);

        mockMvc.perform(get(PATH_API_BOOKS)
            .contentType(APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void testServiceGetBookById() throws Exception {

        when(
                bookServiceMock.getBookById(Mockito.anyLong())).thenReturn(mockBookModel);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                PATH_API_BOOK_ID, 1L);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(HttpStatus.SC_OK, result.getResponse().getStatus());
    }

}
