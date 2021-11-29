package pl.edu.pk.ztp.librarymonolith.dto;

import org.springframework.stereotype.Service;
import pl.edu.pk.ztp.librarymonolith.repository.BookRentalRepository;

@Service
public class BookRentalService {

    private final BookRentalRepository bookRentalRepository;

    public BookRentalService(BookRentalRepository bookRentalRepository) {
        this.bookRentalRepository = bookRentalRepository;
    }

    public BookRentalDTO save(BookRentalDTO bookRentalDTO) {
        return bookRentalRepository.save(bookRentalDTO);
    }
}
