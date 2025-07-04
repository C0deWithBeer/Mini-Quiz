import DAO.QuizDAO;
import Models.Question;
import Util.Utility;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Manages the user interaction menu and delegates actions to QuizDAO.
 */
public class Manager {

    public void showMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine()); // safer than nextInt()
                switch (choice) {
                    case 1 -> startQuiz();
                    case 2 -> showAllQuestions();
                    case 3 -> insertQuestion();
                    case 4 -> updateQuestion();
                    case 5 -> {
                        System.out.println("\n👋 Thank you for using Mini Quiz! Goodbye!");
                        scanner.close(); // Close scanner only on exit
                        return;
                    }
                    default -> System.out.println("Invalid choice! Please try again.");
                }
            }
            catch (NumberFormatException e){
                System.out.println("⚠ Invalid input! Please enter a number.");
            }

            //int choice = scanner.nextInt();         |
            //                                        |   Not safe!
            //scanner.nextLine();                     |


        }
    }

    private void updateQuestion() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Utility util = new Utility();
        QuizDAO dao = new QuizDAO();

        System.out.print("Enter the ID of the question you want to update: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter question: ");
        String question = scanner.nextLine();
        question = util.addQuestionMarkIfNot(question);

        System.out.print("Enter Option A: ");
        String optionA = scanner.nextLine();

        System.out.print("Enter Option B: ");
        String optionB = scanner.nextLine();

        System.out.print("Enter Option C: ");
        String optionC = scanner.nextLine();

        System.out.print("Enter Option D: ");
        String optionD = scanner.nextLine();

        System.out.print("Enter the correct answer (must match exactly with one of the options): ");
        String answer = scanner.nextLine();

        Question questionObj = new Question(question, optionA, optionB, optionC, optionD, answer);
        dao.updateQuestion(questionObj, id);
    }

    private void insertQuestion() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Utility util = new Utility();
        QuizDAO dao = new QuizDAO();

        System.out.print("Enter question: ");
        String question = scanner.nextLine();
        question = util.addQuestionMarkIfNot(question);

        System.out.print("Enter option A: ");
        String optionA = scanner.nextLine();

        System.out.print("Enter option B: ");
        String optionB = scanner.nextLine();

        System.out.print("Enter option C: ");
        String optionC = scanner.nextLine();

        System.out.print("Enter option D: ");
        String optionD = scanner.nextLine();

        System.out.print("Enter answer (full text, must match one of the options): ");
        String answer = scanner.nextLine();

        Question questionObj = new Question(question, optionA, optionB, optionC, optionD, answer);
        dao.insertQuestion(questionObj);

    }

    private void showAllQuestions() throws SQLException {
        QuizDAO dao = new QuizDAO();
        Utility util = new Utility();
        List<Question> questionList = dao.getAllquestions();
        int index = 0;
        for (Question question : questionList){
            index++;
            System.out.print("\n--------------------------------------------------");
            System.out.printf("\nQuestion #%d : %s\n", index, question.getQuestion());
            System.out.printf("Option A: %s\n", question.getOptionA());
            System.out.printf("Option B: %s\n", question.getOptionB());
            System.out.printf("Option C: %s\n", question.getOptionC());
            System.out.printf("Option D: %s\n", question.getOptionD());
            System.out.printf("Answer: %s ( %s )\n", question.getAnswer(), util.getAnswerLabel(question));
            System.out.print("--------------------------------------------------");
        }
    }

    private void startQuiz() throws SQLException {

        QuizDAO dao = new QuizDAO();
        Utility util = new Utility();
        Scanner scanner = new Scanner(System.in);
        List<Question> questionList = dao.getAllquestions();
        Collections.shuffle(questionList);

        int index = 0;
        int score = 0;
        int totalQuestionCrossed = 0;

        for (Question question : questionList){

            index++;
            totalQuestionCrossed++;
            System.out.printf("\nQuestion #%s: %s\n", index, question.getQuestion());
            System.out.printf("A : %s\n", question.getOptionA());
            System.out.printf("B : %s\n", question.getOptionB());
            System.out.printf("C : %s\n", question.getOptionC());
            System.out.printf("D : %s\n", question.getOptionD());
            System.out.println("STOP --> to stop quiz and show scores");

            System.out.print("Enter the option: ");
            String answerByUser = scanner.nextLine();

            if (answerByUser.equalsIgnoreCase("STOP")){
                break;
            }

            if (!answerByUser.isEmpty()){
                if (util.checkAnswer(answerByUser.trim(), question)){
                    score++;
                }
            }

        }

        System.out.println("\nYou Score is: "+score+" / "+(totalQuestionCrossed-1));

    }

    private void printMenu() {
        System.out.println("""
        
        *** Welcome to Mini Quiz ***
        ============================
        1. Start Quiz
        2. Show All Questions
        3. Insert a New Question
        4. Update an Existing Question
        5. Exit
        """);
    }
}
