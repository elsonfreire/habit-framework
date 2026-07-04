package br.com.habit.modules.studist.topics;

import java.util.UUID;

import br.com.habit.modules.studist.enums.StudyAreaType;

public record StudyTopicResponse(UUID id, String title, StudyAreaType subject, DifficultyType difficulty) {
    public StudyTopicResponse(StudyTopic topic) {
        this(topic.getId(), topic.getTitle(), topic.getSubject(), topic.getDifficulty());
    }
}