package br.com.habit.modules.studist.profile;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.framework.user.service.DomainProfileUpdater;

@Component
@RequiredArgsConstructor
public class StudyProfileUpdater implements DomainProfileUpdater {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void update(User user, Object domainProfileData) {
        if (!(user.getDomainProfile() instanceof StudyProfile profile)) {
            return;
        }

        if (domainProfileData == null) {
            return;
        }

        StudyProfileData data =
                objectMapper.convertValue(domainProfileData, StudyProfileData.class);

        if (data.level() != null) profile.setLevel(data.level());
        if (data.area() != null) profile.setArea(data.area());
        if (data.favoriteMethod() != null) profile.setFavoriteMethod(data.favoriteMethod());
        if (data.goals() != null) {
            profile.getGoals().clear();
            profile.getGoals().addAll(data.goals());
        }
    }
}