package com.example.Workout.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="workout")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String workoutTitle;
    private String workoutNote;
    private String workoutType;
    private String workoutCategory;
    private String workoutDuration;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDateTime scheduledDateTime;
    private boolean isCompleted;
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "workout", orphanRemoval = true)
    private List<Exercise> exercises = new ArrayList<>();
}