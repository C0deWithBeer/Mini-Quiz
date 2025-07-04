# Mini Quiz Application

A Java console-based application that allows users to take a quiz, manage questions, and update them. This project is built using Java and MySQL and follows a clean separation of concerns using the DAO pattern.

## Features

* Start a quiz with randomly ordered questions.
* Insert new quiz questions.
* Update existing quiz questions by ID.
* View all questions with options and correct answers.
* Uses a MySQL database to store and retrieve questions.
* Structured with clear layers (DAO, Model, Utility, Manager).

## Technologies Used

* Java (JDK 17+ recommended)
* JDBC (Java Database Connectivity)
* MySQL (tested with version 8+)

## Project Structure

```
MiniQuiz/
├── dao/           # Handles all database operations (QuizDAO.java)
├── models/        # Plain Old Java Object for Question (Question.java)
├── util/          # Utility/helper methods (DBConnection.java, Utility.java)
├── Manager.java   # Main logic and user interaction
├── Main.java      # Application entry point
```

## Database Setup

1. Ensure MySQL is running locally.
2. Create a database named `miniquiz_db`:

   ```sql
   CREATE DATABASE miniquiz_db;
   ```
3. Create the `questions` table:

   ```sql
   CREATE TABLE questions (
       id INT PRIMARY KEY AUTO_INCREMENT,
       question TEXT,
       optiona VARCHAR(255),
       optionb VARCHAR(255),
       optionc VARCHAR(255),
       optiond VARCHAR(255),
       answer VARCHAR(255)
   );
   ```
4. Update your MySQL credentials if needed in `DBConnection.java`.

## How to Run

1. Clone this repository:

   ```bash
   git clone https://github.com/yourusername/miniquiz.git
   cd miniquiz
   ```
2. Compile the application:

   ```bash
   javac Main.java
   ```
3. Run the application:

   ```bash
   java Main
   ```

## Why This Project?

This project was built to:

* Practice JDBC and database interaction in Java.
* Understand clean project structure using DAO pattern.
* Improve input handling, validation, and user-driven CLI interfaces.

## Future Improvements

* Add delete question functionality.
* Add user scoring history.
* Add question difficulty levels.
* Replace CLI with GUI (Swing/JavaFX) or a web front-end.

---

Built and maintained by Beermohamed. Yes it's me!.
