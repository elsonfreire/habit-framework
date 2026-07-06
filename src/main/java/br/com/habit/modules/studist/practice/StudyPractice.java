package br.com.habit.modules.studist.practice;

import br.com.habit.modules.framework.practice.model.Practice;
import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.studist.enums.StudyAreaType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "study_practice_sessions")
@Getter
@Setter
@NoArgsConstructor
public class StudyPractice extends Practice {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StudyAreaType area;

    public StudyPractice(Integer durationMinutes, String notes, LocalDate date, User user, StudyAreaType area) {
        super(durationMinutes, notes, date, user);
        this.area = area;
    }
}
