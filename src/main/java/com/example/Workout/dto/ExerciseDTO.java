package com.example.Workout.dto;

import lombok.Data;

@Data
public class ExerciseDTO {
    private Long id;
    private String name;
    private int sets;
    private int reps;
    private double weight;
}
