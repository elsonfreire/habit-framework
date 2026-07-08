package br.com.habit.modules.readist.goals;

import org.springframework.stereotype.Component;

import br.com.habit.modules.framework.goals.service.GoalSuggestionTemplate;
import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.readist.profile.ReadProfile;

@Component
public class ReadGoalSuggestionTemplate extends GoalSuggestionTemplate {

    @Override
    public String buildPrompt(User user) {
        StringBuilder context = new StringBuilder("Generate exactly 3 weekly reading goals");

        ReadProfile readProfile = (ReadProfile) user.getDomainProfile();

        if (readProfile.getPreferredFormat() != null)
            context.append(" for a reader who prefers ").append(readProfile.getPreferredFormat());
        if (readProfile.getLevel() != null) 
            context.append(", level ").append(readProfile.getLevel());
        if (readProfile.getFavoriteGenre() != null)
            context.append(", favorite genre ").append(readProfile.getFavoriteGenre());

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