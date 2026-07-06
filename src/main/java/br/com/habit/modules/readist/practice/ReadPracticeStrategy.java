package br.com.habit.modules.readist.practice;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.habit.modules.framework.practice.dto.PracticeRequest;
import br.com.habit.modules.framework.practice.model.Practice;
import br.com.habit.modules.framework.practice.service.PracticeStrategy;
import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.readist.enums.BookFormatType;
import br.com.habit.modules.readist.profile.ReadProfile;

@Component
public class ReadPracticeStrategy implements PracticeStrategy {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Practice createPracticeEntity(PracticeRequest request, User user) {
        
        ReadProfile readProfile = (ReadProfile) user.getDomainProfile();    
        ReadPracticeDomainData readData = objectMapper.convertValue(
            request.domainData(), 
            ReadPracticeDomainData.class
        );

        BookFormatType format = readData.format();
        
        if (format == null) {
            format = readProfile.getPreferredFormat(); 
        }
        
        return new ReadPractice(
            request.durationMinutes(),
            request.notes(),
            request.date(),
            user,
            readData.pagesRead(),
            format
        );
    }
}