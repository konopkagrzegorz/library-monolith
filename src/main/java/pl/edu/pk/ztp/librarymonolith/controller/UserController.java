package pl.edu.pk.ztp.librarymonolith.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.ztp.librarymonolith.dto.UserDTO;
import pl.edu.pk.ztp.librarymonolith.service.BookRentalService;
import pl.edu.pk.ztp.librarymonolith.service.UserService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final BookRentalService bookRentalService;

    @Autowired
    public UserController(UserService userService, BookRentalService bookRentalService) {
        this.userService = userService;
        this.bookRentalService = bookRentalService;
    }

    @GetMapping("")
    public List<UserDTO> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") final Integer userID) {
        UserDTO user = userService.findByUserId(userID);
        if (Objects.isNull(user))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") final Integer userID) {
        if (userID == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        UserDTO userDTO = userService.findByUserId(userID);
        if (Objects.isNull(userDTO))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if (bookRentalService.findAll().stream()
                .filter(bookRentalDTO -> bookRentalDTO.getUser() == userDTO)
                .anyMatch(bookRentalDTO -> bookRentalDTO.getReturned() == null)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        userService.deleteUserById(userID);
        return ResponseEntity.status(HttpStatus.OK).build();
}

    @PostMapping("")
    public UserDTO postUser(@RequestBody final UserDTO userDTO) {
        return userService.createUser(userDTO);
    }
}
