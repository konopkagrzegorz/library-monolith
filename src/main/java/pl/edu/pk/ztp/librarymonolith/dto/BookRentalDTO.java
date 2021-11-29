package pl.edu.pk.ztp.librarymonolith.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import pl.edu.pk.ztp.librarymonolith.views.ResourcesView;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_rentals")
public class BookRentalDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    @JsonView(ResourcesView.Normal.class)
    private Integer id;

    @Column(name = "rental_date",nullable = false)
    @JsonView(ResourcesView.Normal.class)
    private LocalDateTime rental;

    @Column(name = "return_date",nullable = true)
    @JsonView(ResourcesView.Normal.class)
    private LocalDateTime returned;

    @ManyToOne
    @JoinColumn(name = "USERID_FK",referencedColumnName = "id",nullable = false)
    @JsonView(ResourcesView.Normal.class)
    @JsonBackReference
    private UserDTO user;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BOOKID_FK",referencedColumnName = "id",nullable = true)
    @JsonView(ResourcesView.Normal.class)
    private BookDTO book;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getRental() {
        return rental;
    }

    public void setRental(LocalDateTime rental) {
        this.rental = rental;
    }

    public LocalDateTime getReturned() {
        return returned;
    }

    public void setReturned(LocalDateTime returned) {
        this.returned = returned;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }
}
