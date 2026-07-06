package br.com.habit.modules.studist.profile;

import br.com.habit.modules.studist.enums.StudyAreaType;
import br.com.habit.modules.studist.enums.StudyGoalType;
import br.com.habit.modules.studist.enums.EducationLevelType;
import br.com.habit.modules.studist.enums.StudyMethodType;

import java.util.List;

public record StudyProfileData(StudyAreaType area, EducationLevelType educationLevel, StudyMethodType favoriteMethod,
                               List<StudyGoalType> goals) {
}
