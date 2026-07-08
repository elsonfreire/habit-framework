package br.com.habit.modules.studist.topics;

import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.framework.user_collection.model.UserCollectionItem;
import br.com.habit.modules.studist.enums.StudyAreaType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "study_topics")
public class StudyTopic extends UserCollectionItem {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LearningStatusType status;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StudyAreaType subject;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DifficultyType difficulty;

    public StudyTopic(
            User user,
            LearningStatusType status,
            String title,
            StudyAreaType subject,
            DifficultyType difficulty) {
        this.user = user;
        this.status = status;
        this.title = title;
        this.subject = subject;
        this.difficulty = difficulty;
    }
}