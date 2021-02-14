package br.com.jussara.apirest.biblioteca.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author jussaragranja
 * Books Model
 */

@Entity(name = "book")
@Data
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String subtitle;
    private String publishingCompany;
    private String type;
    private int numberPages;
    private String author;
    private int quantity;
    private BigDecimal value;

}
