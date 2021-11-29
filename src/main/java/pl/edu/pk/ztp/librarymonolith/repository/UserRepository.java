package pl.edu.pk.ztp.librarymonolith.repository;

import org.apache.catalina.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pk.ztp.librarymonolith.dto.UserDTO;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserDTO,Integer> {

    @Query(value = "SELECT * from tbl_users", nativeQuery = true)
    List<UserDTO> findAll();

    @Query(value = "SELECT * from tbl_users where id = :id",nativeQuery = true)
    UserDTO findByUserId(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM tbl_users WHERE id = :id",nativeQuery = true)
    void deleteUserById(@Param("id") final Integer userID);

}
