package br.com.habit.modules.studist.practice;

import br.com.habit.modules.framework.practice.dto.PracticeRequest;
import br.com.habit.modules.framework.practice.model.Practice;
import br.com.habit.modules.framework.practice.service.PracticeStrategy;
import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.studist.enums.StudyAreaType;
import br.com.habit.modules.studist.profile.StudyProfile;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class StudyPracticeStrategy implements PracticeStrategy {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Practice createPracticeEntity(PracticeRequest request, User user) {

        StudyProfile studyProfile = (StudyProfile) user.getDomainProfile();
        StudyPracticeDomainData studyData = objectMapper.convertValue(
                request.domainData(),
                StudyPracticeDomainData.class
        );

        StudyAreaType area = studyData.area();

        if (area == null) {
            area = studyProfile.getArea();
        }

        return new StudyPractice(
                request.durationMinutes(),
                request.notes(),
                request.date(),
                user,
                area
        );
    }
}
