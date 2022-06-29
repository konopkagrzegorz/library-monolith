package pl.edu.pk.ztp.librarymonolith.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.pk.ztp.librarymonolith.dto.BookDTO;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookDTO, Integer> {

    @Query(value = "SELECT * from tbl_books", nativeQuery = true)
    List<BookDTO> findAll();

    @Query(value = "SELECT * FROM tbl_books WHERE id NOT IN (\n" +
            "    SELECT DISTINCT tbl_books.id FROM tbl_books JOIN tbl_rentals ON (tbl_books.id = tbl_rentals.bookid_fk) \n" +
            "        WHERE return_date IS NULL GROUP BY tbl_books.id HAVING tbl_books.quantity <= count(*)\n" +
            "    )",nativeQuery = true)
    List<BookDTO> findAllAvailable();

    @Query(value = "SELECT * FROM tbl_books JOIN tbl_rentals ON tbl_rentals.bookid_fk = tbl_books.id " +
            "JOIN tbl_users ON tbl_rentals.userid_fk = tbl_users.id WHERE tbl_rentals.bookid_fk = :bookid AND tbl_rentals.return_date IS NULL", nativeQuery = true)
    BookDTO findBookRentals(@Param("bookid") final Integer bookID);
}

