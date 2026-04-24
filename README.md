# Workout Tracker

A secure and scalable RESTful backend application for managing workouts and exercises. This project allows users to register, authenticate using JWT, and create personalized workout plans with multiple exercises.

## Features

 ### User Authentication & Authorization
   - JWT-based login and registration
   - Secure API endpoints using Spring Security
     
 ### User Management
   - Each user can manage their own workouts
   - Data isolation per user
     
 ### Workout Management
   - Create, update, delete workouts
   - Schedule workouts with date & time
   - Mark workouts as completed
 ### Exercise Tracking
   - Add multiple exercises to a workout
   - Track sets, reps, and weight
 ### Workout Reports
  - Generate reports of completed workouts
  - View workout history
##Tech Stack
- Backend: Spring Boot, Spring Security
- Database: MySQL
- Authentication: JWT (JSON Web Token)
- ORM: Hibernate / JPA
- Build Tool: Maven
  
## Project Structure
- `controller` → REST APIs
- `service` → Business logic
- `repository` → Database layer
- `entity` → JPA entities (User, Workout, Exercise)
- `dto` → Data Transfer Objects
- `security` → JWT & Spring Security config
## API Endpoints
### Auth
-  `/api/auth/signup` → Register user
- `/api/auth/login` → Login & get JWT
### Workout
-  `/api/workout` → Manage workout

##Authentication

- All workout APIs require JWT token in header:
- Authorization: Bearer <token>

## Database Design
- One User → Many Workouts
- One Workout → Many Exercises
  
## Setup Instructions

- Clone the repository
- Configure MySQL in application.properties
- Run the application
mvn spring-boot:run

## Future Improvements
- Role-based authorization (Admin/User)
- Frontend integration (React)
- Workout analytics dashboard
- File/image upload for exercises
- Docker deployment

