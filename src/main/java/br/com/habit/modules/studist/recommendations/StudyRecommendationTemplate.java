package br.com.habit.modules.studist.recommendations;

import br.com.habit.modules.framework.recommendations.service.RecommendationTemplate;
import org.springframework.stereotype.Component;

import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.studist.profile.StudyProfile;

@Component
public class StudyRecommendationTemplate extends RecommendationTemplate {

    @Override
    public int calculateScore(User me, User other) {
        int score = scoreByLocation(me, other);

        StudyProfile studyProfile = (StudyProfile) me.getDomainProfile();
        StudyProfile otherStudyProfile = (StudyProfile) other.getDomainProfile();

        if (studyProfile.getGoals() != null
                && !studyProfile.getGoals().isEmpty()
                && otherStudyProfile.getGoals() != null
                && !otherStudyProfile.getGoals().isEmpty()) {

            long common = studyProfile.getGoals().stream().filter(otherStudyProfile.getGoals()::contains).count();

            int minGoals = Math.min(studyProfile.getGoals().size(), otherStudyProfile.getGoals().size());
            double matchPercentage = (double) common / minGoals;
            score += (int) Math.round(matchPercentage * 3);
        }

        if (studyProfile.getFavoriteMethod() != null && studyProfile.getFavoriteMethod() == otherStudyProfile.getFavoriteMethod()) {
            score += 2;
        }

        return score;
    }
}