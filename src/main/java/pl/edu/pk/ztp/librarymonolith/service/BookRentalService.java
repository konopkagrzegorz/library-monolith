package pl.edu.pk.ztp.librarymonolith.service;

import org.springframework.stereotype.Service;
import pl.edu.pk.ztp.librarymonolith.dto.BookRentalDTO;
import pl.edu.pk.ztp.librarymonolith.repository.BookRentalRepository;

import java.util.List;

@Service
public class BookRentalService {

    private final BookRentalRepository bookRentalRepository;

    public BookRentalService(BookRentalRepository bookRentalRepository) {
        this.bookRentalRepository = bookRentalRepository;
    }

    public BookRentalDTO save(BookRentalDTO bookRentalDTO) {
        return bookRentalRepository.save(bookRentalDTO);
    }

    public List<BookRentalDTO> findAll() {
        return bookRentalRepository.findAll();
    }
}
