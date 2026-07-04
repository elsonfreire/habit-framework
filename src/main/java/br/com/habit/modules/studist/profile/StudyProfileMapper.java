package br.com.habit.modules.studist.profile;

import br.com.habit.modules.framework.user.model.DomainProfile;
import br.com.habit.modules.framework.user.service.DomainProfileMapper;
import org.springframework.stereotype.Component;

@Component
public class StudyProfileMapper implements DomainProfileMapper {
    @Override
    public Object toData(DomainProfile domainProfile) {
        StudyProfile studyProfile = (StudyProfile) domainProfile;

        return new StudyProfileData(
                studyProfile.getArea(),
                studyProfile.getLevel(),
                studyProfile.getFavoriteMethod(),
                studyProfile.getGoals()
        );
    }
}
