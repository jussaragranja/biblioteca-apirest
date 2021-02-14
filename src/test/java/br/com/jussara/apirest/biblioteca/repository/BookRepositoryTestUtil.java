package br.com.jussara.apirest.biblioteca.repository;

import br.com.jussara.apirest.biblioteca.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author jussaragranja
 * Books Repository TestUtil
 */

public interface BookRepositoryTestUtil extends JpaRepository<BookModel, Long> {

    @Query(value="SELECT * FROM book ORDER BY random() limit 1", nativeQuery=true)
    BookModel findRandomBook();

    @Query(value="SELECT * FROM book ORDER BY id desc limit 1", nativeQuery=true)
    BookModel findBookLastId();

}
