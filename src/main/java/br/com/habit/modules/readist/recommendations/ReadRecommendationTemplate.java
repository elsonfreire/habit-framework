package br.com.habit.modules.readist.recommendations;

import br.com.habit.modules.framework.recommendations.service.RecommendationTemplate;
import org.springframework.stereotype.Component;

import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.readist.profile.ReadProfile;

@Component
public class ReadRecommendationTemplate extends RecommendationTemplate {
    
  @Override
  public int calculateScore(User me, User other) {
    int score = scoreByLocation(me, other);

    ReadProfile readProfile = (ReadProfile) me.getDomainProfile();
    ReadProfile otherReadProfile = (ReadProfile) other.getDomainProfile();

    if (readProfile.getInterests() != null
        && !readProfile.getInterests().isEmpty()
        && otherReadProfile.getInterests() != null
        && !otherReadProfile.getInterests().isEmpty()) {

      long common = readProfile.getInterests().stream()
          .filter(otherReadProfile.getInterests()::contains)
          .count();

      int minInterests = Math.min(readProfile.getInterests().size(), otherReadProfile.getInterests().size());
      double matchPercentage = (double) common / minInterests;
      score += (int) Math.round(matchPercentage * 3);
    }

    if (readProfile.getFavoriteGenre() != null && readProfile.getFavoriteGenre() == otherReadProfile.getFavoriteGenre()) {
      score += 2;
    }

    if (readProfile.getPreferredFormat() != null && readProfile.getPreferredFormat() == otherReadProfile.getPreferredFormat()) {
      score += 1;
    }
    
    if (readProfile.getLevel() != null && readProfile.getLevel() == otherReadProfile.getLevel()) {
      score += 1;
    }

    return Math.min(score, 10);
  }
}