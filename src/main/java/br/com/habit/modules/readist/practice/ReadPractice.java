package br.com.habit.modules.readist.practice;

import java.time.LocalDate;

import br.com.habit.modules.framework.practice.model.Practice;
import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.readist.enums.BookFormatType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "read_practice_sessions")
@Getter
@Setter
@NoArgsConstructor
public class ReadPractice extends Practice {

    @Column(nullable = false)
    private Integer pagesRead;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookFormatType format;

    public ReadPractice(Integer durationMinutes, String notes, LocalDate date, User user, Integer pagesRead, BookFormatType format) {
        super(durationMinutes, notes, date, user);
        this.pagesRead = pagesRead;
        this.format = format;
    }
}