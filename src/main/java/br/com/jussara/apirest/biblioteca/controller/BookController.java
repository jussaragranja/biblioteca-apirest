package br.com.jussara.apirest.biblioteca.controller;

import br.com.jussara.apirest.biblioteca.model.BookModel;
import br.com.jussara.apirest.biblioteca.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jussaragranja
 * Books Controller
 */

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Biblioteca")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    @ApiOperation(value = "Get a list of all books")
    public List<BookModel> listBooks(){
        return bookService.listBooks();
    }

    @GetMapping("/book/{id}")
    @ApiOperation(value = "Get book by id")
    public BookModel getBookById(@PathVariable(value = "id") long id){
        return bookService.getBookById(id);
    }

    @PostMapping("/create-book")
    @ApiOperation(value = "Register a new book")
    @ResponseStatus(value = HttpStatus.CREATED)
    public BookModel createBook(@RequestBody BookModel bookModel){
        return bookService.createBook(bookModel);
    }

    @DeleteMapping("/delete-book")
    @ApiOperation(value = "Delete a book")
    public void deleteBook(@RequestBody BookModel bookModel){
        bookService.deleteBook(bookModel);
    }

    @DeleteMapping("/delete-book-id/{id}")
    @ApiOperation(value = "Delete a book by id")
    public BookModel deleteBookById(@PathVariable(value = "id") long id){
        return bookService.deleteBookById(id);
    }

    @PutMapping("/update-book")
    @ApiOperation(value = "Update a book")
    public BookModel updateBook(@RequestBody BookModel bookModel){
        return bookService.updateBook(bookModel);
    }
}
