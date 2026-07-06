package br.com.habit.modules.readist.practice;

import br.com.habit.modules.readist.enums.BookFormatType;

public record ReadPracticeDomainData(
    Integer pagesRead,
    BookFormatType format
) {}