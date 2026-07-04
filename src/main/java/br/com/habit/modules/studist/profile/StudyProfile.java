package br.com.habit.modules.studist.profile;

import br.com.habit.modules.framework.user.model.DomainProfile;
import br.com.habit.modules.studist.enums.StudyAreaType;
import br.com.habit.modules.studist.enums.StudyGoalType;
import br.com.habit.modules.studist.enums.StudyLevelType;
import br.com.habit.modules.studist.enums.StudyMethodType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "study_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudyProfile extends DomainProfile {
    @Enumerated(EnumType.STRING)
    private StudyAreaType area;

    @Enumerated(EnumType.STRING)
    private StudyLevelType level;

    @Enumerated(EnumType.STRING)
    private StudyMethodType favoriteMethod;

    @ElementCollection(targetClass = StudyGoalType.class)
    @CollectionTable(
            name = "study_profile_goals",
            joinColumns = @JoinColumn(name = "study_profile_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "goal")
    private List<StudyGoalType> goals = new ArrayList<>();
}
