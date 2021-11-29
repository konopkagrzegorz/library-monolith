package pl.edu.pk.ztp.librarymonolith.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import pl.edu.pk.ztp.librarymonolith.views.ResourcesView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_books")
public class BookDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonView(ResourcesView.Normal.class)
    private Integer id;

    @Column(name = "title", nullable = false)
    @JsonView(ResourcesView.Normal.class)
    private String title;

    @Column(name = "author", nullable = false)
    @JsonView(ResourcesView.Normal.class)
    private String author;

    @Column(name = "quantity", nullable = false)
    @JsonView(ResourcesView.Normal.class)
    private Integer quantity;

    @JsonManagedReference
    @OneToMany(mappedBy = "book")
    @JsonView(ResourcesView.Normal.class)
    private List<BookRentalDTO> rentals;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<BookRentalDTO> getRentals() {
        return rentals;
    }

    public void setRentals(List<BookRentalDTO> rentals) {
        this.rentals = rentals;
    }
}
