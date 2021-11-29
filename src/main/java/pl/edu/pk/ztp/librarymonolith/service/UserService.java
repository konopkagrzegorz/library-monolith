package pl.edu.pk.ztp.librarymonolith.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pk.ztp.librarymonolith.dto.UserDTO;
import pl.edu.pk.ztp.librarymonolith.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll();
    }

    public UserDTO findByUserId(final Integer userID) {
        return userRepository.findByUserId(userID);
    }

    public void deleteUserById(final Integer userID) {
        userRepository.deleteUserById(userID);
    }

    public UserDTO createUser(UserDTO user) {
        return userRepository.save(user);
    }
}
