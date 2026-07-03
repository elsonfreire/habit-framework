package br.com.habit.modules.readist.bookshelf;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.framework.user_collection.exceptions.UserCollectionItemNotFoundException;
import br.com.habit.modules.framework.user_collection.service.UserCollectionService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService implements UserCollectionService<BookRequest, BookResponse, ReadingStatusType> {
  private final BookRepository bookRepository;

  @Override
  public Map<ReadingStatusType, List<BookResponse>> findAllByUser(User user) {
    return bookRepository.findAllByUser(user).stream()
        .collect(
            Collectors.groupingBy(
                Book::getStatus, Collectors.mapping(BookResponse::new, Collectors.toList())));
  }

  @Override
  public BookResponse create(BookRequest requestDto, User user) {
    Book book =
        new Book(
            user,
            ReadingStatusType.WANT_TO_READ,
            requestDto.title(),
            requestDto.author(),
            requestDto.length());
    return new BookResponse(bookRepository.save(book));
  }

  @Override
  public BookResponse updateStatus(UUID id, ReadingStatusType status, User user) {
    Book book = bookRepository.findByIdAndUser(id, user).orElseThrow(UserCollectionItemNotFoundException::new);
    book.setStatus(status);

    return new BookResponse(bookRepository.save(book));
  }

  @Override
  public void delete(UUID id, User user) {
    Book book = bookRepository.findByIdAndUser(id, user).orElseThrow(UserCollectionItemNotFoundException::new);
    bookRepository.delete(book);
  }
}