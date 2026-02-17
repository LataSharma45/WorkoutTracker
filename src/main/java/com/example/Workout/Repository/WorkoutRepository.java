package com.example.Workout.Repository;

import com.example.Workout.entity.User;
import com.example.Workout.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByUserOrderByScheduledDateTimeAsc(User user);
    List<Workout> findByUserAndIsCompletedTrue(User user);
}
