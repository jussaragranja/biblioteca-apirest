package br.com.jussara.apirest.biblioteca.controller;

import br.com.jussara.apirest.biblioteca.exception.ResourceNotFoundException;
import br.com.jussara.apirest.biblioteca.model.BookModel;
import br.com.jussara.apirest.biblioteca.repository.BookRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.jussara.apirest.biblioteca.constant.Constants.*;

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
    private BookRepository bookRepository;

    @GetMapping("/books")
    @ApiOperation(value = "Get a list of all books")
    public List<BookModel> listBooks(){
        return bookRepository.findAll();
    }

    @GetMapping("/book/{id}")
    @ApiOperation(value = "Get book by id")
    public BookModel getBookById(@PathVariable(value = "id") long id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE_BOOK_NOT_FOUND));
    }

    @PostMapping("/create-book")
    @ApiOperation(value = "Register a new book")
    @ResponseStatus(value = HttpStatus.CREATED)
    public BookModel createBook(@RequestBody BookModel bookModel){
        if (bookModel.getId() != null){
            if(bookRepository.findById(bookModel.getId()).isPresent()) {
                throw new ResourceNotFoundException(MESSAGE_BOOK_REGISTERED);
            }
        }
        if(bookModel.getTitle() == null || bookModel.getAuthor() == null ||
                bookModel.getTitle().equals("") || bookModel.getAuthor().equals("") ||
                bookModel == null || bookModel.equals("")){
            throw new ResourceNotFoundException(MESSAGE_BOOK_CANNOT_BE_NULL);
        }
        return bookRepository.save(bookModel);
    }

    @DeleteMapping("/delete-book")
    @ApiOperation(value = "Delete a book")
    public void deleteBook(@RequestBody BookModel bookModel){
        if(!bookRepository.findById(bookModel.getId()).isPresent()) {
            throw new ResourceNotFoundException(MESSAGE_BOOK_NOT_FOUND);
        }
        bookRepository.delete(bookModel);
    }

    @DeleteMapping("/delete-book-id/{id}")
    @ApiOperation(value = "Delete a book by id")
    public BookModel deleteBookById(@PathVariable(value = "id") long id){
        if(bookRepository.findById(id) == null) {
            throw new ResourceNotFoundException(MESSAGE_BOOK_NOT_FOUND);
        }
        return bookRepository.deleteById(id);
    }

    @PutMapping("/update-book")
    @ApiOperation(value = "Update a book")
    public BookModel updateBook(@RequestBody BookModel bookModel){
        if(!bookRepository.findById(bookModel.getId()).isPresent()) {
            throw new ResourceNotFoundException(MESSAGE_BOOK_NOT_FOUND);
        }
        return bookRepository.save(bookModel);
    }

}
