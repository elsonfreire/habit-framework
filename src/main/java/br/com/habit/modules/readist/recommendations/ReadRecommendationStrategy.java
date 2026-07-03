package br.com.habit.modules.readist.recommendations;

import org.springframework.stereotype.Component;

import br.com.habit.modules.framework.recommendations.service.RecommendationStrategy;
import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.readist.profile.ReadProfile;

@Component
public class ReadRecommendationStrategy implements RecommendationStrategy {
    
  @Override
  public int calculateScore(User me, User other) {
    int score = 0;

    if (me.getCity() != null && me.getCity().equalsIgnoreCase(other.getCity())) {
      score += 5;
    } else if (me.getState() != null && me.getState().equalsIgnoreCase(other.getState())) {
      score += 3;
    }

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

    return score;
    //return Math.min(score, 10);
  }
}