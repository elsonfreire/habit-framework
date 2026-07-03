package br.com.habit.modules.readist.bookshelf;

import java.util.UUID;

public record BookResponse(UUID id, String title, String author, BookLengthType length) {
  public BookResponse(Book book) {
    this(book.getId(), book.getTitle(), book.getAuthor(), book.getLength());
  }
}