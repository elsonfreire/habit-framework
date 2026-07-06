package br.com.habit.modules.readist.bookshelf;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.habit.modules.framework.user_collection.controller.UserCollectionController;
import br.com.habit.modules.framework.user_collection.service.UserCollectionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bookshelf")
@RequiredArgsConstructor
public class BookController extends UserCollectionController<BookRequest, BookResponse, ReadingStatusType> {
  private final BookService bookService;

  @Override
  protected UserCollectionService<BookRequest, BookResponse, ReadingStatusType> service() {
    return bookService;
  }
}