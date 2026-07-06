package br.com.habit.modules.readist.bookshelf;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookRequest(
    @NotBlank(message = "Title of the book is required") String title,
    @NotBlank(message = "Author of the book is required") String author,
    @NotNull(message = "Length is required") BookLengthType length
) {}