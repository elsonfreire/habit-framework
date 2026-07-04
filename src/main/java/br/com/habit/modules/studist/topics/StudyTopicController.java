package br.com.habit.modules.studist.topics;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import br.com.habit.modules.framework.user_collection.controller.UserCollectionController;
import br.com.habit.modules.framework.user_collection.service.UserCollectionService;


@RestController
@RequestMapping("/study-topics")
@RequiredArgsConstructor
public class StudyTopicController extends UserCollectionController<StudyTopicRequest, StudyTopicResponse, LearningStatusType> {
    private final StudyTopicService topicService;

    @Override
    protected UserCollectionService<StudyTopicRequest, StudyTopicResponse, LearningStatusType> service() {
        return topicService;
    }
}