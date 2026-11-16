# Team Project

Please keep this up-to-date with information about your project throughout the term.

The readme should include information such as:
- a summary of what your application is all about
- a list of the user stories, along with who is responsible for each one
- information about the API(s) that your project uses 
- screenshots or animations demonstrating current functionality

By keeping this README up-to-date,
your team will find it easier to prepare for the final presentation
at the end of the term.


 # Cognitive mini-games application (Working title)
    
## Domain: 

**Educational entertainment**

Cognitive Mini-Games is a Java desktop application which features short, interactive challenges in visual memory and general knowledge. Players can create accounts, play games at different difficulty levels, and review their past results and achievements. The application will use a Java Swing graphical user interface, follow Clean Architecture and SOLID principles, and persist account and game data between sessions.

Our minimum viable product (MVP) consists of a card-matching memory game, API-powered trivia quiz, user authentication, and a player progress dashboard. Additional mini-games may be considered as future extensions, not as part of the initial MVP.

## User stories 

### User Story 1: Memory Challenge
As a player, I want to flip cards to find matching pairs at different difficulty levels so that I can challenge my visual memory and receive a score based on my performance.

### User Story 2: Trivia Challenge
As a player, I want to complete a ten-question trivia quiz in a category and difficulty level of my choice so that I can test my knowledge, receive a final score, and review my answers after the quiz.

### User Story 3: Progress Tracking
As a player, I want to view my past game results, personal bests, and achievements so that I can monitor my progress over time.

### User Story 4: Player Authentication

As a player, I want to create and securely access my account so that my game results and progress remain associated with my profile across sessions.


## Use cases:
Use Case 1: Play Memory Challenge

Use Case 2: Play Trivia Challenge

Use Case 3: View Player Progress

Use Case 4: Register Player Account

Use Case 5: Log in

Use Case 6: Log Out

## Minimum Viable Product

| Team member | Use case | Minimum functionality |
| :--- | :---: | ---: |
| Laila | Play Trivia challenge | Player selects a category and difficulty, answers 10 API-based multiple choice questions, and receives a final score with answer review|
| Alsade | Play Memory challenge | XXX |
| Fatma | User authentication | XXX |
| Angela | Progress tracking | XXX |
The MVP will be a Java Swing applications that allows users to register and log in, play a card-matching memory game, API-based trivia quiz game, and view their saved game results. Each team member will use clean architecture to implement the use case they are leading.


## Entities

### Shared Entities

- **Player:** Represents a registered user of the application, including their unique player ID, username, and account information. Used by authentication, gameplay, and progress tracking.

- **GameResult:** Represents the outcome of a completed mini-game, including the player ID, game type, difficulty, final score, and completion date. Used by both games and the progress-tracking feature.

### Trivia Challenge Entities (Laila)

- **TriviaQuestion:** Represents a multiple-choice trivia question, including the question text, four answer options, the correct answer, category, and difficulty.

- **TriviaSession:** Represents an active trivia quiz containing ten questions. Tracks the player's submitted answers, current question, score, and completion status.

### add other entities here

## API Usage 
### The Trivia API

- **API Name:** The Trivia API
- **Documentation:** https://the-trivia-api.com/docs/
  
- The Trivia Challenge will use The Trivia API to retrieve multiple-choice questions based on the player's selected category and difficulty level.