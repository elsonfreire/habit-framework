package br.com.habit.modules.studist.profile;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface StudyProfileRepository extends JpaRepository<StudyProfile, UUID> {
}
