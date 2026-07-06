package br.com.habit.modules.musicist.recommendations;

import br.com.habit.modules.framework.recommendations.service.RecommendationTemplate;
import org.springframework.stereotype.Component;

import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.musicist.profile.MusicProfile;

@Component
public class MusicRecommendationTemplate extends RecommendationTemplate {
    
  @Override
  public int calculateScore(User me, User other) {
    int score = scoreByLocation(me, other);

    MusicProfile musicProfile = (MusicProfile) me.getDomainProfile();
    MusicProfile otherMusicProfile = (MusicProfile) other.getDomainProfile();

    if (musicProfile.getInterests() != null
        && !musicProfile.getInterests().isEmpty()
        && otherMusicProfile.getInterests() != null
        && !otherMusicProfile.getInterests().isEmpty()) {

      long common = musicProfile.getInterests().stream().filter(otherMusicProfile.getInterests()::contains).count();

      int minInterests = Math.min(musicProfile.getInterests().size(), otherMusicProfile.getInterests().size());
      double matchPercentage = (double) common / minInterests;
      score += (int) Math.round(matchPercentage * 3);
    }

    if (musicProfile.getFavoriteGenre() != null && musicProfile.getFavoriteGenre() == otherMusicProfile.getFavoriteGenre()) {
      score += 2;
    }

    return score;
  }
}
