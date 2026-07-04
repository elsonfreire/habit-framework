package br.com.habit.modules.studist.topics;

import br.com.habit.modules.studist.enums.StudyAreaType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StudyTopicRequest(
        @NotBlank(message = "Title of the topic is required") String title,
        @NotNull(message = "Subject is required") StudyAreaType subject,
        @NotNull(message = "Difficulty is required") DifficultyType difficulty) {}