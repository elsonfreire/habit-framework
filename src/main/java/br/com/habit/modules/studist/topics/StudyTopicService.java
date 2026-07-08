package br.com.habit.modules.studist.topics;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.framework.user_collection.exceptions.UserCollectionItemNotFoundException;
import br.com.habit.modules.framework.user_collection.service.UserCollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudyTopicService implements UserCollectionService<StudyTopicRequest, StudyTopicResponse, LearningStatusType> {
    private final StudyTopicRepository topicRepository;

    public Map<LearningStatusType, List<StudyTopicResponse>> findAllByUser(User user) {
        return topicRepository.findAllByUser(user).stream()
                .collect(
                        Collectors.groupingBy(
                                StudyTopic::getStatus, Collectors.mapping(StudyTopicResponse::new, Collectors.toList())));
    }

    public StudyTopicResponse create(StudyTopicRequest requestDto, User user) {
        StudyTopic topic =
                new StudyTopic(
                        user,
                        LearningStatusType.TO_LEARN,
                        requestDto.title(),
                        requestDto.subject(),
                        requestDto.difficulty());
        return new StudyTopicResponse(topicRepository.save(topic));
    }

    public StudyTopicResponse updateStatus(UUID id, LearningStatusType status, User user) {
        StudyTopic topic = topicRepository.findByIdAndUser(id, user).orElseThrow(UserCollectionItemNotFoundException::new);
        topic.setStatus(status);

        return new StudyTopicResponse(topicRepository.save(topic));
    }

    public void delete(UUID id, User user) {
        StudyTopic topic = topicRepository.findByIdAndUser(id, user).orElseThrow(UserCollectionItemNotFoundException::new);
        topicRepository.delete(topic);
    }
}