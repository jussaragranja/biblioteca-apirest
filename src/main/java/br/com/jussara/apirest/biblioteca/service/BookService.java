package br.com.jussara.apirest.biblioteca.service;

import br.com.jussara.apirest.biblioteca.constant.Constants;
import br.com.jussara.apirest.biblioteca.exception.ResourceBadRequestException;
import br.com.jussara.apirest.biblioteca.exception.ResourceNotFoundException;
import br.com.jussara.apirest.biblioteca.model.BookModel;
import br.com.jussara.apirest.biblioteca.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<BookModel> listBooks(){
        return bookRepository.findAll();
    }

    public BookModel getBookById(long id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.MESSAGE_BOOK_NOT_FOUND));
    }

    public BookModel createBook(BookModel bookModel){
        if (bookModel.getId() != null){
            if(bookRepository.findById(bookModel.getId()).isPresent()) {
                throw new ResourceBadRequestException(Constants.MESSAGE_BOOK_REGISTERED);
            }
        }
        if(bookModel.getTitle() == null || bookModel.getAuthor() == null ||
                bookModel.getTitle().equals("") || bookModel.getAuthor().equals("") ||
                bookModel == null || bookModel.equals("")){
            throw new ResourceBadRequestException(Constants.MESSAGE_BOOK_CANNOT_BE_NULL);
        }
        return bookRepository.save(bookModel);
    }

    public void deleteBook(BookModel bookModel){
        if(!bookRepository.findById(bookModel.getId()).isPresent()) {
            throw new ResourceNotFoundException(Constants.MESSAGE_BOOK_NOT_FOUND);
        }
        bookRepository.delete(bookModel);
    }

    public BookModel deleteBookById(long id){
        if(bookRepository.findById(id) == null) {
            throw new ResourceNotFoundException(Constants.MESSAGE_BOOK_NOT_FOUND);
        }
        return bookRepository.deleteById(id);
    }

    public BookModel updateBook(BookModel bookModel){
        if(!bookRepository.findById(bookModel.getId()).isPresent()) {
            throw new ResourceNotFoundException(Constants.MESSAGE_BOOK_NOT_FOUND);
        }
        return bookRepository.save(bookModel);
    }
}
