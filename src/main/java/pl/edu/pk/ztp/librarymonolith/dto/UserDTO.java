package pl.edu.pk.ztp.librarymonolith.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import pl.edu.pk.ztp.librarymonolith.converter.StringArrayToStringConverter;
import pl.edu.pk.ztp.librarymonolith.views.ResourcesView;

import javax.persistence.*;
import java.util.List;

@Entity(name = "user")
@Table(name = "tbl_users")
public class UserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,name = "id")
    @JsonView(ResourcesView.Normal.class)
    private Integer id;

    @Column(nullable = false,name = "name")
    @JsonView(ResourcesView.Normal.class)
    private String username;

    @Column(nullable = false, name = "roles")
    @Convert(converter = StringArrayToStringConverter.class)
    @JsonView(ResourcesView.Full.class)
    private String[] roles;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<BookRentalDTO> books;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public List<BookRentalDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookRentalDTO> books) {
        this.books = books;
    }
}
