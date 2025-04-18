import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FullPoliticalQuiz {
    static class QuizQuestion {
        String question;
        java.util.List<String> options;
        String correctAnswer;

        public QuizQuestion(String question, java.util.List<String> options, String correctAnswer) {
            this.question = question;
            this.options = new ArrayList<>(options);
            this.correctAnswer = correctAnswer;
        }
    }

    private java.util.List<QuizQuestion> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private int totalQuestions;
    private JFrame frame;
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionsGroup;
    private JButton nextButton, prevButton;

    private String[] userAnswers;

    public FullPoliticalQuiz() {
        initQuestions();
        totalQuestions = questions.size();
        userAnswers = new String[totalQuestions];

        Collections.shuffle(questions);

        buildUI();
        loadQuestion();
    }

    private void initQuestions() {
        questions = new ArrayList<>();

        questions.add(new QuizQuestion("If we remove elements from the beginning, this operation is faster in linked lists comparing with arrays",
                Arrays.asList("Disagree",
                        "Agree",
                        "",
                        ""),
                "Agree"));

        questions.add(new QuizQuestion("Which of these relates to linked lists advantage?",
                Arrays.asList("Direct access to every element",
                        "Needs less memory than array",
                        "Needs more memory than array",
                        "No need to pre-allocate memory (No buffer)"),
                "No need to pre-allocate memory (No buffer)"));

        questions.add(new QuizQuestion("Complexity Theory helps to determine the difficulty of a problem, often measured by how much time and space it takes to solve a particular problem",
                Arrays.asList("Agree",
                        "Disagree",
                        "",
                        ""),
                "Agree"));

        questions.add(new QuizQuestion("Predict output of following program\n" +
                "#include <stdio.h>\n\n" +
                "int fun(int n)\n" +
                "{\n" +
                "   if (n == 4)\n" +
                "       return n;\n" +
                "   else return 2*fun(n+1);\n" +
                "}\n\n" +
                "int main()\n" +
                "{\n" +
                "printf(\"%d \", fun(2)); // printing the value\n" +
                "return 0;\n" +
                "}",
                Arrays.asList("16",
                        "4",
                        "Runtime Error",
                        "8"),
                "16"));

        questions.add(new QuizQuestion("What is a time complexity of an algorithm that finds the size of Linked List?",
                Arrays.asList("O(2^N)",
                        "O(N*N)",
                        "O(N)",
                        "O(1)"),
                "O(N)"));

        questions.add(new QuizQuestion("In order to add items to the end in the array, the time complexity is",
                Arrays.asList("Linearithmic complexity O(N*logN)",
                        "Constant complexity 0(1)",
                        "Linear time complexity O(N)",
                        "Logarithmic complexity O(logN)"),
                "Constant complexity 0(1)"));

        questions.add(new QuizQuestion("Recursion is similar to which of the following?",
                Arrays.asList("Switch Case",
                        "if else if",
                        "Loop",
                        "If-else"),
                "Loop"));

        questions.add(new QuizQuestion("In order to remove items from the specific index of the array, the time complexity is",
                Arrays.asList("Constant complexity 0(1)",
                        "Logarithmic complexity O(logN)",
                        "Linear time complexity O(N)",
                        "Linearithmic complexity O(N*IogN)"),
                "Linear time complexity O(N)"));

        questions.add(new QuizQuestion("Consider the following recursive function fun(x, y). What is the value of fun(4, 3)\n" +
                "int fun(int x, int y)\n" +
                "{\n" +
                "   if (x == 0)\n" +
                "       return y;\n" +
                "   return fun(x - 1, x + y);\n" +
                "}\n",
                Arrays.asList("13",
                        "9",
                        "10",
                        "12"),
                "13"));

        questions.add(new QuizQuestion("Average time complexity of Bubble sort is?",
                Arrays.asList("O(n^2)",
                        "O(nlogn)",
                        "O(logn)",
                        "O(n)"),
                "O(n^2)"));

        questions.add(new QuizQuestion("What would be the asymptotic time complexity to add a node at the end of singly linked list, if the reference is initially referencing to the head of the list?",
                Arrays.asList("O(logn)",
                        "O(nlogn)",
                        "O(n^2)",
                        "O(n)"),
                "O(n)"));

        questions.add(new QuizQuestion("What is the worst-case runtime of an algorithm that compares two numbers.",
                Arrays.asList("O(n)",
                        "O(logN)",
                        "O(1)",
                        "O(n^2)"),
                "O(1)"));

        questions.add(new QuizQuestion("What is the optimal way to get predecessor's value in a Linked List?",
                Arrays.asList("there is no way to get access to the predecessor",
                        "start a new loop from head of a linked list",
                        "start a new loop from tail ofa linked list",
                        "store a pointer to the previous node"),
                "store a pointer to the previous node"));

        questions.add(new QuizQuestion("In linked list each node contain minimum of two fields. One field is data field to store the data second field is?",
                Arrays.asList("Node",
                        "Pointer to character",
                        "Pointer to integer",
                        "Pointer to node"),
                "Pointer to node"));

        questions.add(new QuizQuestion("Consider an implementation of unsorted singly linked list. Suppose it has its representation with a head reference only. Given the representation, which of the following operation can be\n" +
                "implemented in 0(1) time?\n" +
                "i) Insertion at the front of the linked list\n" +
                "ii) Insertion at the end of the linked list\n" +
                "iii) Deletion of the front node of the linked list\n" +
                "iv) Deletion of the last node of the linked list",
                Arrays.asList("I,II and III",
                        "I,II and IV",
                        "I and II",
                        "I and III"),
                "I and III"));

        questions.add(new QuizQuestion("What is the time complexity of the following code:\n\n" +
                "int a = 0;\n" +
                "   for (i = 0; i < N; i++) {\n" +
                "       for (j = N; j > i; j--) {\n" +
                "           a = a + i + j;\n" +
                "       }\n" +
                "}",
                Arrays.asList("O(N * Sqrt(N))",
                        "O(N)",
                        "O(N*N)",
                        "O(N*log(N))"),
                "O(N*N)"));

        questions.add(new QuizQuestion("What is the time, space complexity of following code:\n\n" +
                "int a = 0, b = 0;\n" +
                "for (i = 0; i < N; i++) {\n" +
                "   a = a + randO;\n" +
                "} \n for (j = 0; j < M; j++) {\n" +
                "   b = b + rand();\n" +
                "} \n Assume that rand() is 0(1) time, 0(1) space function.",
                Arrays.asList("O(N*M) time, O(1) space",
                        "O(N*M) time, O(N*M) space",
                        "O(N+M) time, O(N+M) space",
                        "O(N+M) time, O(1) space"),
                "O(N+M) time, O(1) space"));

        questions.add(new QuizQuestion("How many NULL references in a doubly linked list?",
                Arrays.asList("3",
                        "1",
                        "2",
                        "0"),
                "2"));

        questions.add(new QuizQuestion("Which of the following data structures is LIFO data structure?",
                Arrays.asList("Linked List",
                        "Tree",
                        "Stack",
                        "Queue"),
                "Stack"));

        questions.add(new QuizQuestion("What would be the asymptotic time complexity to find an element in the linked list?",
                Arrays.asList("O(n)",
                        "O(logn)",
                        "O(nlogn)",
                        "O(n^2)"),
                "O(n)"));

        questions.add(new QuizQuestion("What would be the asymptotic time complexity to add a node at the end of singly linked list, if the reference is initially referencing to the head of the list?",
                Arrays.asList("O(logn)",
                        "O(n^2)",
                        "O(nlogn)",
                        "O(n)"),
                "O(n)"));

        questions.add(new QuizQuestion("What does the following function do for a given Linked List with first node as head?\n" +
                "void fun1(Node head)\n" +
                "{\n" +
                "   if (head == NULL)\n" +
                "   return;\n" +
                "   fun1(head.next);\n" +
                "   printf(\"%d \", head.data);\n" +
                "}",
                Arrays.asList("Prints all nodes of linked lists in direct order",
                        "Prints alternate nodes in reverse order",
                        "Prints all nodes of linked list in reverse order",
                        "Prints alternate nodes of Linked List"),
                "Prints all nodes of linked list in reverse order"));

        questions.add(new QuizQuestion("How the getting process to a specific node in the linked list is called?",
                Arrays.asList("Traversal",
                        "Merging",
                        "Inserting",
                        "Extraction"),
                "Traversal"));

        questions.add(new QuizQuestion("What does the following function print for n = 25?\n" +
                "void fun(int n)\n" +
                "{\n" +
                "   if (n == 0)\n" +
                "       return;\n" +
                "\n\n printf( \"%d\" , n%2); // printing the value\n" +
                "fun (n/2);\n" +
                "}",
                Arrays.asList("10011",
                        "00000",
                        "11111",
                        "11001"),
                "10011"));

        questions.add(new QuizQuestion("Considering the fact that the memory is cheap nowadays, do you agree that time complexity is more important than space complexity",
                Arrays.asList("Disagree",
                        "Agree",
                        "",
                        ""),
                "Disagree"));

        questions.add(new QuizQuestion("Which of these relates to linked list's disadvantage?",
                Arrays.asList("No direct access to elements",
                        "Complexity in deletion",
                        "Complexity in insertion",
                        "Weakness in performance"),
                "No direct access to elements"));

        questions.add(new QuizQuestion("Find the slowest time",
                Arrays.asList("O(n!)",
                        "O(logn)",
                        "O(n^2)",
                        "O(n)"),
                "O(n!)"));

        questions.add(new QuizQuestion("What does the following function do?\n" +
                "int fun(int x, int y)\n" +
                "{\n" +
                "   if (y == 0) return 0;\n" +
                "   return (x + fun(x, y-1));\n" +
                "}",
                Arrays.asList("x*y",
                        "x + x*y",
                        "x^y",
                        "x + y"),
                "x*y"));

        questions.add(new QuizQuestion("If the last node of a linked list references back to the head node instead of containing the null reference we call it",
                Arrays.asList("circular linked list",
                        "Array list",
                        "singly linked list",
                        "linked list"),
                "circular linked list"));

        questions.add(new QuizQuestion("In order to remove items from the end of the array, the time complexity is",
                Arrays.asList("O(1)",
                        "O(logn)",
                        "O(n)",
                        "O(nlogn)"),
                "O(1)"));

        questions.add(new QuizQuestion("What is the time complexity of insert(index) method in LinkedList?",
                Arrays.asList("O(n)",
                        "O(logn)",
                        "O(nlogn)",
                        "O(n^2)"),
                "O(n)"));

        questions.add(new QuizQuestion("What is the time, space complexity of following code:\n" +
                "int a = 0, b = 0;\n" +
                "for (i = 0; i < N; i++) {\n" +
                "   for (j = 0; j < N;j++) {\n" +
                "       a = a + j;\n" +
                "   }\n" +
                "}\n" +
                "for (k = 0; k < N; k++) {\n" +
                "   b=b+k;\n" +
                "}",
                Arrays.asList("O(N*N) time, O(1) space",
                        "O(N+N) time, O(1) space",
                        "O(N*N*N) time, O(1) space",
                        "O(N*N) time, O(N) space"),
                "O(N*N) time, O(1) space"));
    }

    private void buildUI() {
        frame = new JFrame("Full Political Quiz");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLayout(new BorderLayout());

        Color bgColor = new Color(45, 45, 45);
        Color textColor = Color.WHITE;
        Color buttonColor = new Color(70, 70, 70);
        Color selectedColor = new Color(100, 100, 100);

        questionLabel = new JLabel("Question");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        questionLabel.setForeground(textColor);
        questionLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        questionLabel.setOpaque(true);
        questionLabel.setBackground(bgColor);
        frame.add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1, 10, 10));
        optionsPanel.setBackground(bgColor);

        optionButtons = new JRadioButton[4];
        optionsGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setFont(new Font("Arial", Font.PLAIN, 16));
            optionButtons[i].setForeground(textColor);
            optionButtons[i].setBackground(bgColor);
            optionButtons[i].setFocusPainted(false);
            optionButtons[i].setOpaque(true);
            optionButtons[i].setBorderPainted(true);
            optionButtons[i].setBorder(BorderFactory.createLineBorder(selectedColor));
            optionsGroup.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(optionsPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(bgColor);

        JPanel navPanel = new JPanel();
        navPanel.setBackground(bgColor);

        prevButton = new JButton("Previous");
        prevButton.setFont(new Font("Arial", Font.BOLD, 14));
        prevButton.setForeground(textColor);
        prevButton.setBackground(buttonColor);
        prevButton.setFocusPainted(false);
        prevButton.setBorder(BorderFactory.createLineBorder(selectedColor));
        prevButton.addActionListener(e -> {
            saveUserAnswer();
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--;
                loadQuestion();
            }
        });
        navPanel.add(prevButton);

        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.setForeground(textColor);
        nextButton.setBackground(buttonColor);
        nextButton.setFocusPainted(false);
        nextButton.setBorder(BorderFactory.createLineBorder(selectedColor));
        nextButton.addActionListener(e -> {
            saveUserAnswer();
            checkAndProvideFeedback();
            currentQuestionIndex++;
            loadQuestion();
        });
        navPanel.add(nextButton);

        bottomPanel.add(navPanel, BorderLayout.EAST);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.getContentPane().setBackground(bgColor);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private void loadQuestion() {
        if (currentQuestionIndex < totalQuestions) {
            optionsGroup.clearSelection();
            QuizQuestion current = questions.get(currentQuestionIndex);
            java.util.List<String> randomizedOptions = new ArrayList<>(current.options);
            Collections.shuffle(randomizedOptions);

            String formattedQuestion = current.question
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace(" ", "&nbsp;")
                    .replace("\n", "<br>");

            questionLabel.setText("<html><div style='font-family: Arial; font-size: 18px; white-space: pre;'>"
                    + (currentQuestionIndex + 1) + ". " + formattedQuestion
                    + "</div></html>");


            for (int i = 0; i < 4; i++) {
                optionButtons[i].setText(randomizedOptions.get(i));
            }

            if (userAnswers[currentQuestionIndex] != null) {
                for (JRadioButton rb : optionButtons) {
                    if (rb.getText().equals(userAnswers[currentQuestionIndex])) {
                        rb.setSelected(true);
                        break;
                    }
                }
            }
        } else {
            finishQuiz();
        }
    }




    private void saveUserAnswer() {
        for (JRadioButton rb : optionButtons) {
            if (rb.isSelected()) {
                userAnswers[currentQuestionIndex] = rb.getText();
                return;
            }
        }
        userAnswers[currentQuestionIndex] = null;
    }

    private void checkAndProvideFeedback() {
        QuizQuestion current = questions.get(currentQuestionIndex);
        String selected = null;
        for (JRadioButton rb : optionButtons) {
            if (rb.isSelected()) {
                selected = rb.getText();
                break;
            }
        }
        if (selected == null) {
            return;
        }
        if (!selected.equals(current.correctAnswer)) {
            JOptionPane.showMessageDialog(frame, "Wrong! The correct answer is: " + current.correctAnswer, "Feedback", JOptionPane.INFORMATION_MESSAGE);
        } else {
            score++;
        }
    }

    private void finishQuiz() {
        JOptionPane.showMessageDialog(frame, "Quiz Over! Your score: " + score + " out of " + totalQuestions);
        saveResults();
        frame.dispose();
    }

    private void saveResults() {
        try (FileWriter writer = new FileWriter("quiz_results.txt", true)) {
            writer.write("Score: " + score + " out of " + totalQuestions + "\n");
            writer.write("Date: " + new Date() + "\n\n");
        } catch (IOException e) {
            System.err.println("Error saving results: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FullPoliticalQuiz());
    }
}
