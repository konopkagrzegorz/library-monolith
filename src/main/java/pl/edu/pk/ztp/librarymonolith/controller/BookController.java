package pl.edu.pk.ztp.librarymonolith.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.ztp.librarymonolith.dto.*;
import pl.edu.pk.ztp.librarymonolith.service.BookRentalService;
import pl.edu.pk.ztp.librarymonolith.service.BookService;
import pl.edu.pk.ztp.librarymonolith.service.UserService;
import pl.edu.pk.ztp.librarymonolith.views.ResourcesView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final UserService userService;
    private final BookRentalService bookRentalService;

    @Autowired
    public BookController(BookService bookService, UserService userService, BookRentalService bookRentalService) {
        this.bookService = bookService;
        this.userService = userService;
        this.bookRentalService = bookRentalService;
    }

    @GetMapping(value = "")
    @ApiOperation(value = "Return all books available in repository",
    produces = "application/json")
    public List<BookDTO> getBooks(
            @ApiParam(value = "If true returns only available books (not rent)",example = "true")
            @RequestParam(required = false, value = "available") final boolean showOnlyAvailable) {
        return bookService.findAll(showOnlyAvailable);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Return book (if exists) by given ID",
            produces = "application/json")
    @JsonView({ResourcesView.Normal.class})
    public BookDTO getBookRentals(@PathVariable("id") final Integer bookID) {
        return bookService.findBookRentals(bookID);
    }

    @PatchMapping("/rent/{id}")
    @GetMapping("{id}")
    @ApiOperation(value = "Return book (if exists) by given ID",
            produces = "application/json")
    public ResponseEntity<BookDTO> patchRentBook(
            @ApiParam(value = "ID of book which user want to rent",example = "3")
            @PathVariable("id") final Integer bookID,
            @ApiParam(value = "ID of user who wants to rent a book",example = "30")
            @RequestHeader(required = false, value = "user") final Integer userID) {
        UserDTO user = userService.findByUserId(userID);
        BookDTO book = bookService.findById(bookID);
        if (Objects.isNull(book))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        if (Objects.isNull(user))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        int rentals = (int) book.getRentals().stream().filter(bookRentalDTO -> bookRentalDTO.getReturned() == null).count();
        int quantity = book.getQuantity();
        if (rentals == quantity)
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        else {
            BookRentalDTO bookRentalDTO = new BookRentalDTO();
            bookRentalDTO.setBook(book);
            bookRentalDTO.setUser(user);
            bookRentalDTO.setRental(LocalDateTime.now());
            book.getRentals().add(bookRentalDTO);
            bookRentalService.save(bookRentalDTO);
            book = bookService.updateBook(book);
        }
        return new ResponseEntity<>(book,HttpStatus.OK);
    }

    @PatchMapping("/return/{id}")
    @ApiOperation(value = "Return book (if exists) by given ID",
            consumes = "header",
            produces = "application/json")
    public ResponseEntity<BookDTO> patchReturnBook(
            @ApiParam(value = "ID of book which user want to return",example = "3")
            @PathVariable("id") final Integer bookID,
            @ApiParam(value = "ID of user who wants to return a book",example = "30")
            @RequestHeader("user") final Integer userID) {
        UserDTO user = userService.findByUserId(userID);
        BookDTO book = bookService.findById(bookID);
        if (Objects.isNull(book))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        if (Objects.isNull(user))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        int rentals = (int) book.getRentals().stream().filter(bookRentalDTO -> bookRentalDTO.getReturned() == null).count();
        if (rentals == 0)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        else {
            Optional<BookRentalDTO> bookRentalDTO = book.getRentals().stream()
                    .filter(rental -> rental.getUser().getId() == userID)
                    .filter(rental -> Objects.equals(rental.getBook().getId(), bookID))
                    .filter(rental -> rental.getReturned() == null)
                    .findFirst();
            if (!bookRentalDTO.isPresent())
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            else {
                bookRentalDTO.get().setReturned(LocalDateTime.now());
                bookRentalService.save(bookRentalDTO.get());
                bookService.updateBook(book);
            }
        }
        return new ResponseEntity<>(book,HttpStatus.OK);
    }
}
