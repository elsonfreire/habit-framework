package br.com.habit.modules.studist.profile;

import br.com.habit.modules.framework.user.model.DomainProfile;
import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.framework.user.service.DomainProfileFactory;
import org.springframework.stereotype.Component;

@Component
public class StudyProfileFactory implements DomainProfileFactory {
    @Override
    public DomainProfile create(User user) {
        StudyProfile profile = new StudyProfile();
        profile.setUser(user);
        return profile;
    }
}
