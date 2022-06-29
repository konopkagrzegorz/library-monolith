package pl.edu.pk.ztp.librarymonolith.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pk.ztp.librarymonolith.dto.BookDTO;
import pl.edu.pk.ztp.librarymonolith.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDTO> findAll(boolean showOnlyAvailable) {
        if (showOnlyAvailable) {
//            List<BookDTO> allBooks = bookRepository.findAll();
//            List<BookDTO> filteredBooks = allBooks.stream().filter(bookDTO -> {
//                long value =  bookDTO.getRentals().stream()
//                        .filter(bookRentalDTO -> bookRentalDTO.getReturned() == null).count();
//                if (value == bookDTO.getQuantity()) {
//                    return false;
//                }
//                return true;
//            }).collect(Collectors.toList());
//            return filteredBooks;
            return bookRepository.findAllAvailable();
        }
        return bookRepository.findAll();
    }

    public BookDTO findById(Integer id) {
        if (bookRepository.findById(id).isPresent())
            return bookRepository.findById(id).get();
        return null;
    }


    public BookDTO findBookRentals(Integer bookID) {
        return bookRepository.findBookRentals(bookID);
    }

    public BookDTO updateBook(BookDTO bookDTO) {
        return bookRepository.save(bookDTO);
    }
}
