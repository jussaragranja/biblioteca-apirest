package br.com.jussara.apirest.biblioteca.repository;

import br.com.jussara.apirest.biblioteca.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jussaragranja
 * Books Repository
 */

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {

    BookModel deleteById(long id);

}
