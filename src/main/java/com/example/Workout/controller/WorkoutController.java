package com.example.Workout.controller;

import com.example.Workout.dto.WorkoutDTO;
import com.example.Workout.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/workout")
@RequiredArgsConstructor
public class WorkoutController {
    private final WorkoutService workoutService;
    @PostMapping
    public ResponseEntity<WorkoutDTO> createWorkout(@RequestBody WorkoutDTO workoutDTO){
    return ResponseEntity.ok(workoutService.createWorkout(workoutDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Long id){
        workoutService.deleteWorkout(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<List<WorkoutDTO>> listWorkout(){

        return ResponseEntity.ok(workoutService.listWorkouts());
    }
    @GetMapping("/report")
    public ResponseEntity<String> generateReport(){
        return ResponseEntity.ok(workoutService.generateReport());
    }

}
