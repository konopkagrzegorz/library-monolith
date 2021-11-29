package pl.edu.pk.ztp.librarymonolith.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pk.ztp.librarymonolith.dto.BookRentalDTO;

@Repository
public interface BookRentalRepository extends JpaRepository<BookRentalDTO, Integer> {
}
