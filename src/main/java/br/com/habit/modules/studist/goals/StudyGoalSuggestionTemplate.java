package br.com.habit.modules.studist.goals;

import org.springframework.stereotype.Component;

import br.com.habit.modules.framework.goals.service.GoalSuggestionTemplate;
import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.studist.profile.StudyProfile;

@Component
public class StudyGoalSuggestionTemplate extends GoalSuggestionTemplate {

    @Override
    public String buildPrompt(User user) {
        StringBuilder context = new StringBuilder("Generate exactly 3 weekly study goals");

        StudyProfile studyProfile = (StudyProfile) user.getDomainProfile();

        if (studyProfile.getArea() != null)
            context.append(" for someone studying ").append(studyProfile.getArea());
        if (studyProfile.getLevel() != null) context.append(", level ").append(studyProfile.getLevel());
        if (studyProfile.getFavoriteMethod() != null)
            context.append(", preferred method ").append(studyProfile.getFavoriteMethod());

        context.append(
                """
                            .
                            Each goal must be specific and achievable within 7 days.
                            Reply ONLY with a JSON array of strings. Example: ["Goal 1", "Goal 2", "Goal 3"]
                            No explanations, no markdown, just the JSON. Language: PT-BR.
                        """);

        return context.toString();
    }

}