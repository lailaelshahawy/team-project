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
As a player, I want to memory.view my past game results, personal bests, and achievements so that I can monitor my progress over time.

### User Story 4: Player Authentication

As a player, I want to create and securely access my account so that my game results and progress remain associated with my profile across sessions.


## Use cases:
### Trivia Challenge — Laila
- Start Trivia Quiz
- Submit Trivia Answer
- Review Trivia Results

### Memory Challenge — Alsade
1. Select a memory game difficulty 
2. Play game on relevant board by flipping cards

#### Use Case 1: Select a Memory Game Difficulty
Primary actor: Player
Preconditions: Player has opened the memory game and is on the game setup screen.
Trigger: Player chooses to start a new game.
Success outcome: The selected difficulty is saved, and the player is presented with the corresponding game board.

Main success scenario:
1. Player selects the option to start a new game.
2. System displays the available difficulty levels: Easy, Medium and Hard.
3. Player selects a difficulty level.
4. System configures the game board according to the selected difficulty.
5. System displays the game board with all cards face-down.
6. Player is ready to begin playing.

Extensions:
2a. Player decides not to start a game. System returns to the main menu.
3a. Player does not select a difficulty. System prompts the player to select one before proceeding.

#### Use Case 2: Play a Memory Game
Primary actor: Player
Preconditions: Player has selected a difficulty level, and the corresponding game board has been generated with all cards face-down.
Trigger: Player selects a card on the game board.
Success outcome: All matching pairs have been identified, the game is completed, and the player's performance data is displayed.

Main success scenario:
1. Player selects a face-down card.
2. System reveals the selected card.
3. Player selects a second face-down card.
4. System reveals the second card.
5. System checks whether the two revealed cards match.
6. If the cards match, the system keeps both cards face-up and updates the player's score.
7. Player continues selecting pairs of cards until all matching pairs have been identified.
8. System detects that all pairs have been matched and ends the game.
9. System displays player performance result. 

Extensions:
1a. Player selects a card that has already been matched or is currently face-up. System ignores the selection.
3a. Player attempts to select a third card while two unmatched cards are being displayed. System prevents the selection until the two cards are turned face-down.
6a. If the cards do not match, the system displays both cards temporarily before turning them face-down again and updates the score. 
7a. Player attempts to select another card before the unmatched cards are turned face-down. System prevents the selection.

### Progress Tracking — Angela
- View Game History
- View Personal Bests

### Player Authentication — Fatma
- Register Player Account
- Log In
- Log Out


## Minimum Viable Product

| Team member | Use case | Minimum functionality |
| :--- | :---: | ---: |
| Laila | Play Trivia challenge | Player selects a category and difficulty, answers 10 API-based multiple choice questions, and receives a final score with answer review|
| Alsade | Play Memory challenge | Player can play an easy card matching game on a 3x4 grid and receives a final score with whether they won or lost|
| Fatma | User authentication | XXX |
| Angela | Progress tracking | XXX |
The MVP will be a Java Swing applications that allows users to register and log in, play a card-matching memory game, API-based trivia quiz game, and memory.view their saved game results. Each team member will use clean architecture to implement the use case they are leading.


## Entities

### Shared Entities

- **Player:** Represents a registered user of the application, including their unique player ID, username, and account information. Used by authentication, gameplay, and progress tracking.

- **GameResult:** Represents the outcome of a completed mini-game, including the player ID, game type, difficulty, final score, and completion date. Used by both games and the progress-tracking feature.

### Trivia Challenge Entities (Laila)

- **TriviaQuestion:** Represents a multiple-choice trivia question, including the question text, four answer options, the correct answer, category, and difficulty.

- **TriviaSession:** Represents an active trivia quiz containing ten questions. Tracks the player's submitted answers, current question, score, and completion status.

### Memory Game Entities (Alsade)

- **MemoryGame:** Represents a card matching game session with game ID, difficulty and score.
- **Board:** Represents a game board with rows, columns, cards, difficulty.  

## API Usage 
### The Trivia API

- **API Name:** The Trivia API
- **Documentation:** https://the-trivia-api.com/docs/
  
- The Trivia Challenge will use The Trivia API to retrieve multiple-choice questions based on the player's selected category and difficulty level.

- **API Name:** Deck of Cards API
- **Documentation:** https://www.deckofcardsapi.com/
  
- The memory game will use the Deck of Cards API to retrieve the cards that will be displayed for matching. 
