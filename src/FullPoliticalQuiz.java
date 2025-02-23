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

        questions.add(new QuizQuestion("What is the result of the following code?\n" +
                "\n" +
                "x = [1, 2, 3, 4]  \n" +
                "x[1:3] = [7, 8, 9]  \n" +
                "print(x)",
                Arrays.asList("Throws an error",
                        "[1, 7, 9, 4]",
                        "[1, 7, 8, 9, 4]",
                        "[1, 2, 3, 4, 7, 8, 9]"),
                "[1, 7, 8, 9, 4]"));

        questions.add(new QuizQuestion("Which operator checks if a value is not in a list?",
                Arrays.asList("not in",
                        "not",
                        "is not",
                        "!="),
                "not in"));

        questions.add(new QuizQuestion("What will the following code produce?\n" +
                "\n" +
                "t = (1, [2, 3], 4)  \n" +
                "t[1].append(5)  \n" +
                "print(t)",
                Arrays.asList("(1, [2, 3], 4) ",
                        "(1, [2, 3, 5], 4)",
                        "(1, 2, 3, 4, 5)",
                        "Throws an error"),
                "(1, [2, 3, 5], 4)"));

        questions.add(new QuizQuestion("Which of the following operations will modify the list?\n" +
                "\n" +
                "x = [1, 2, 3]",
                Arrays.asList("x.append(4)",
                        "y = x + [4]",
                        "x = x * 2",
                        "z = sorted(x)"),
                "x.append(4)"));

        questions.add(new QuizQuestion("What is a variable in Python?",
                Arrays.asList("A location in memory to store data",
                        "A function",
                        "A reserved word",
                        "A data type"),
                "A location in memory to store data"));

        questions.add(new QuizQuestion("What will be the result of the following code?\n" +
                "\n" +
                "x = (1, 2, 3)  \n" +
                "y = (1, 2, [3])  \n" +
                "z = y  \n" +
                "y[2].append(4)  \n" +
                "print(z)",
                Arrays.asList("(1, 2, [3, 4])",
                        "(1, 2, [3])",
                        "(1, 2, 3, 4)",
                        "Throws a TypeError"),
                "(1, 2, [3, 4])"));

        questions.add(new QuizQuestion("What will be the output of the following code?\n" +
                "\n" +
                "x = 0  \n" +
                "if x:  \n" +
                "    print(\"Non-zero\")  \n" +
                "elif not x:  \n" +
                "    print(\"Zero\")  \n" +
                "else:  \n" +
                "    print(\"Undefined\")",
                Arrays.asList("Throws an error",
                        "Zero",
                        "Non-zero",
                        "Undefined"),
                "Zero"));

        questions.add(new QuizQuestion("Evaluate the following complex logical operation:\n" +
                "\n" +
                "x = True  \n" +
                "y = False  \n" +
                "z = x or y and not x  \n" +
                "print(z)",
                Arrays.asList("True",
                        "None",
                        "Throws an error",
                        "False"),
                "True"));

        questions.add(new QuizQuestion("What will be the output of the following code?\n" +
                "\n" +
                "s1 = {1, 2, 3}  \n" +
                "s2 = {3, 4, 5}  \n" +
                "print(s1 & s2)",
                Arrays.asList("{3}",
                        "Throws an error",
                        "{1, 2, 3, 4, 5}",
                        "{}"),
                "{3}"));

        questions.add(new QuizQuestion("What is the output of this nested loop?\n" +
                "\n" +
                "for i in range(3):  \n" +
                "    for j in range(2):  \n" +
                "        if i == j:  \n" +
                "            break  \n" +
                "        print(f\"{i},{j}\", end=\" \")",
                Arrays.asList("0,0 1,1 2,2",
                        "No output",
                        "0,1 0,1 1,2 0,2 1,0 2,1",
                        "1,0 2,0 2,1"),
                "1,0 2,0 2,1"));

        questions.add(new QuizQuestion("What is the output of the following code?\n" +
                "\n" +
                "d = {'a': 1, 'b': 2, 'c': 3}  \n" +
                "for key, value in d.items():  \n" +
                "    d[key] = value + 1  \n" +
                "print(d)",
                Arrays.asList("{'a': 1, 'b': 2, 'c': 3, 'd': 4, 'e': 5, 'f': 6}",
                        "{'a': 1, 'b': 2, 'c': 3",
                        "Throws an error",
                        "{'a': 2, 'b': 3, 'c': 4}"),
                "{'a': 2, 'b': 3, 'c': 4}"));

        questions.add(new QuizQuestion("What is the result of the following code?\n" +
                "\n" +
                "s = {1, 2, 3}  \n" +
                "s.add(2)  \n" +
                "print(s)",
                Arrays.asList("{1, 2, 3}",
                        "{1, 3}",
                        "Throws an error",
                        "{1, 2, 3, 2}"),
                "{1, 2, 3}"));

        questions.add(new QuizQuestion("Which of the following is immutable in Python?",
                Arrays.asList("List",
                        "String",
                        "Set",
                        "Dictionary"),
                "String"));

        questions.add(new QuizQuestion("What will happen if you omit the else part in an if-else block?",
                Arrays.asList("The else block is mandatory in Python.",
                        "The code will execute normally if the condition is true.",
                        "A SyntaxError will be raised.",
                        "The code will execute only if the condition is false."),
                "The code will execute normally if the condition is true."));

        questions.add(new QuizQuestion("What will be the output of the following code?\n" +
                "\n" +
                "x = [1, 2, 3]  \n" +
                "print(type(x) is list)",
                Arrays.asList("None",
                        "True",
                        "Throws an error",
                        "False"),
                "True"));

        questions.add(new QuizQuestion("What will be the output of the following code?\n" +
                "\n" +
                "x = 10  \n" +
                "if x > 5:  \n" +
                "    pass  \n" +
                "else:  \n" +
                "    print(\"Less than 5\")",
                Arrays.asList("No output",
                        "None of the answers",
                        "Throws an error",
                        "Less than 5"),
                "No output"));

        questions.add(new QuizQuestion("What is the output of the following code?\n" +
                "\n" +
                "for i in range(1, 6):  \n" +
                "    if i % 2 == 0:  \n" +
                "        print(i, end=\" \")",
                Arrays.asList("2 4",
                        "1 2 3 4 5",
                        "1 3 5",
                        "2 4 6"),
                "2 4"));

        questions.add(new QuizQuestion("What is the result of the following operation?\n" +
                "\n" +
                "5 > 3 and 2 < 4",
                Arrays.asList("Throws an error",
                        "None",
                        "False",
                        "True"),
                "True"));

        questions.add(new QuizQuestion("What does the following code return?\n" +
                "\n" +
                "s = \"abcdef\"  \n" +
                "print(s[1:-1:2])",
                Arrays.asList("bfdc",
                        "bd",
                        "None",
                        "ace"),
                "bd"));

        questions.add(new QuizQuestion("What happens if a break statement is encountered inside a for loop?",
                Arrays.asList("The loop is terminated immediately.",
                        "The loop continues with the next iteration.",
                        "The program exits entirely.",
                        "An error is raised."),
                "The loop is terminated immediately."));

        questions.add(new QuizQuestion("What is the output of the following code?\n" +
                "\n" +
                "x, y = 10, 5  \n" +
                "print(x != y or x == 10)",
                Arrays.asList("False",
                        "True",
                        "None",
                        "Throws an error"),
                "True"));

        questions.add(new QuizQuestion("What will be the output of the following code?\n" +
                "\n" +
                "i = 1  \n" +
                "while i < 10:  \n" +
                "    if i == 5:  \n" +
                "        break  \n" +
                "    print(i, end=\" \")  \n" +
                "    i += 2  ",
                Arrays.asList("1 3 5",
                        "1 3 and terminates at 5",
                        "None",
                        "Throws an error"),
                "1 3 and terminates at 5"));

        questions.add(new QuizQuestion("What will be the output of the following code?\n" +
                "\n" +
                "list = [1, 2, 3, 4, 5]  \n" +
                "i = 1  \n" +
                "while i < 2:  \n" +
                "    print(\"\n" +
                "\".join(map(str, list)))  \n" +
                "    i += 1",
                Arrays.asList("Throws an error",
                        "1 1",
                        "1, 2, 3, 4, 5",
                        "1 \n 2 \n 3 \n 4 \n 5"),
                "1 \n 2 \n 3 \n 4 \n 5"));

        questions.add(new QuizQuestion("What will happen if the following code is executed?\n" +
                "\n" +
                "d = {'a': 1, 'b': 2, 'c': 3}  \n" +
                "d['d'] = 4  \n" +
                "print(d)",
                Arrays.asList("Throws an error",
                        "{'a': 1, 'b': 2, 'c': 3, 'd': 4}",
                        "{'a': 1, 'b': 2, 'c': 3}",
                        "None"),
                "{'a': 1, 'b': 2, 'c': 3, 'd': 4}"));

        questions.add(new QuizQuestion("What is the output of this conditional expression?\n" +
                "\n" +
                "x = 7  \n" +
                "result = \"Even\" if x % 2 == 0 else \"Odd\"  \n" +
                "print(result)",
                Arrays.asList("Even",
                        "None",
                        "Odd",
                        "7"),
                "Odd"));

        questions.add(new QuizQuestion("What will be the output of this code?\n" +
                "\n" +
                "from functools import reduce\n" +
                "result = reduce(lambda x, y: x + y, [])",
                Arrays.asList("Error",
                        "None",
                        "No output",
                        "[]"),
                "Error"));

        questions.add(new QuizQuestion("What is the output of the following code?\n" +
                "\n" +
                "class A:\n" +
                "    def display(self):\n" +
                "        return \"A\"\n" +
                "\n" +
                "class B(A):\n" +
                "    def display(self):\n" +
                "        return \"B\"\n" +
                "\n" +
                "obj = B()\n" +
                "print(obj.display())",
                Arrays.asList("B",
                        "A",
                        "Error",
                        "No output"),
                "B"));

        questions.add(new QuizQuestion("What will this code produce?\n" +
                "\n" +
                "nums = [1, 2, 3, 4, 5]\n" +
                "result = list(filter(lambda x: x % 2, nums))\n" +
                "print(result)",
                Arrays.asList("[2, 4]",
                        "No output",
                        "Error",
                        "[1, 3, 5]"),
                "[1, 3, 5]"));

        questions.add(new QuizQuestion("What is the output of the following code?\n" +
                "\n" +
                "def my_decorator(func):\n" +
                "    def wrapper():\n" +
                "        print(\"Before function call\")\n" +
                "        func()\n" +
                "        print(\"After function call\")\n" +
                "    return wrapper\n" +
                "\n" +
                "@my_decorator\n" +
                "def say_hello():\n" +
                "    print(\"Hello!\")\n" +
                "\n" +
                "say_hello()",
                Arrays.asList("No output",
                        "Print \"After function call\" first",
                        "Print \"Hello!\" only",
                        "Print \"Before function call\", \"Hello!\", \"After function call\""),
                "Print \"Before function call\", \"Hello!\", \"After function call\""));

        questions.add(new QuizQuestion("What will be the output of the following code?\n" +
                "\n" +
                "class Parent:\n" +
                "    def greet(self):\n" +
                "        print(\"Hello from Parent\")\n" +
                "\n" +
                "class Child(Parent):\n" +
                "    pass\n" +
                "\n" +
                "obj = Child()\n" +
                "obj.greet()",
                Arrays.asList("Error: Child must implement greet()",
                        "\"Hello from Parent\"",
                        "None",
                        "\"Hello from Child\""),
                "\"Hello from Parent\""));

        questions.add(new QuizQuestion("What is the output of the following code?\n" +
                "\n" +
                "def mystery(n):\n" +
                "    if n == 0:\n" +
                "        return 1\n" +
                "    return n * mystery(n - 1)\n" +
                "\n" +
                "print(mystery(5))",
                Arrays.asList("120",
                        "25",
                        "5",
                        "Error"),
                "120"));

        questions.add(new QuizQuestion("What is the output of the following code?\n" +
                "\n" +
                "nums = [1, 2, 3, 4]\n" +
                "result = map(lambda x: x * 2, filter(lambda y: y % 2, nums))\n" +
                "print(list(result))",
                Arrays.asList("[4, 8]",
                        "[1, 6]",
                        "[2, 6]",
                        "No output"),
                "[2, 6]"));

        questions.add(new QuizQuestion("What is the output of the following code?\n" +
                "\n" +
                "def func(a, b=5, c=10):\n" +
                "    return a + b + c\n" +
                "\n" +
                "print(func(1, c=20))",
                Arrays.asList("35",
                        "Error",
                        "16",
                        "26"),
                "26"));

        questions.add(new QuizQuestion("What is the output of the following code?\n" +
                "\n" +
                "nums = [0, 1, -1, 2, -2, 3, 0, -2]\n" +
                "result = filter(lambda x: x and x > 0, nums)\n" +
                "print(list(result))",
                Arrays.asList("Error",
                        "[1, 2, 3]",
                        "[0, 1, 2, 3]",
                        "[-1, -2]"),
                "[1, 2, 3]"));

        questions.add(new QuizQuestion("What is the output of the following code?\n" +
                "\n" +
                "from functools import reduce\n" +
                "\n" +
                "class Item:\n" +
                "    def __init__(self, price):\n" +
                "        self.__price = price\n" +
                "\n" +
                "    def get_price(self):\n" +
                "        return self.__price\n" +
                "\n" +
                "x1 = Item(1.5)\n" +
                "x2 = Item(1)\n" +
                "x3 = Item(3.5)\n" +
                "\n" +
                "cart = [x1, x2, x3]\n" +
                "budget = 10\n" +
                "\n" +
                "money_left = reduce(lambda acc, x: acc - x.get_price(), cart, budget)\n" +
                "\n" +
                "print(money_left)",
                Arrays.asList("4.0",
                        "6.0",
                        "No output",
                        "Error"),
                "4.0"));

        questions.add(new QuizQuestion("What is the output of the following code?\n" +
                "\n" +
                "nums = [1, 2, 3, 4]\n" +
                "result = filter(lambda x: x % 2, map(lambda y: y * 2, nums))\n" +
                "print(list(result))",
                Arrays.asList("[]",
                        "Error",
                        "[4, 8]",
                        "[2, 4, 6, 8]"),
                "[]"));

        questions.add(new QuizQuestion("What is the output of the following code?\n" +
                "\n" +
                "from functools import reduce\n" +
                "\n" +
                "class A:\n" +
                "    def __init__(self, nums):\n" +
                "        self.nums = nums\n" +
                "\n" +
                "    def multiply(self):\n" +
                "        return reduce(lambda x, y: x * y, self.nums)\n" +
                "\n" +
                "obj = A([1, 2, 3, 4])\n" +
                "print(obj.multiply())",
                Arrays.asList("24",
                        "10",
                        "[1, 2, 3, 4]",
                        "Error"),
                "24"));

        questions.add(new QuizQuestion("Polymorphism in Python allows:",
                Arrays.asList("Functional programming",
                        "Multiple inheritance",
                        "Secure data encapsulation",
                        "Methods to have the same name but different implementations"),
                "Methods to have the same name but different implementations"));

        questions.add(new QuizQuestion("Given the following code, what is the output?\n" +
                "\n" +
                "class A:\n" +
                "    def show(self):\n" +
                "        return \"Class A\"\n" +
                "\n" +
                "class B(A):\n" +
                "    pass\n" +
                "\n" +
                "obj = B()\n" +
                "print(obj.show())",
                Arrays.asList("Class B",
                        "Class A",
                        "Error",
                        "No output"),
                "Class A"));

        questions.add(new QuizQuestion("What is the output of this code?\n" +
                "\n" +
                "nums = [1, 2, 3]\n" +
                "result = map(lambda x: x ** 2, nums)\n" +
                "print(result)",
                Arrays.asList("[1, 4, 9]",
                        "No output",
                        "None",
                        "<map object>"),
                "<map object>"));

        questions.add(new QuizQuestion("What is the output of the following code?\n" +
                "\n" +
                "def decorator(func):\n" +
                "    def wrapper():\n" +
                "        print(\"Inside wrapper\")\n" +
                "    return wrapper\n" +
                "\n" +
                "@decorator\n" +
                "def greet():\n" +
                "    print(\"Hello!\")\n" +
                "\n" +
                "greet()",
                Arrays.asList("Error",
                        "\"Hello!\"",
                        "\"Inside wrapper\"",
                        "No output"),
                "\"Inside wrapper\""));

        questions.add(new QuizQuestion("What will happen if you pass a string as the iterable to reduce()?\n" +
                "\n" +
                "from functools import reduce\n" +
                "result = reduce(lambda x, y: x + y, \"abc\")",
                Arrays.asList("\"a + b + c\"",
                        "\"a\", \"b\", \"c\"",
                        "\"abcabcabc\"",
                        "\"abc\" concatenated as one string"),
                "\"abc\" concatenated as one string"));

        questions.add(new QuizQuestion("What is the correct syntax to inherit a class in Python?",
                Arrays.asList("class ChildClass(ParentClass):",
                        "class ParentClass inherit ChildClass:",
                        "class ParentClass derive ChildClass:",
                        "class ParentClass(ChildClass)"),
                "class ChildClass(ParentClass):"));

        questions.add(new QuizQuestion("What is the correct structure of a Python decorator?",
                Arrays.asList("A function that takes another function and returns a new function",
                        "A method that defines the __call__() method",
                        "A function that modifies global variables",
                        "A class that takes a function and returns an instance"),
                "A function that takes another function and returns a new function"));

        questions.add(new QuizQuestion("In Python, can a class inherit from multiple classes?",
                Arrays.asList("Only if the parent classes don’t have constructors",
                        "No",
                        "Yes",
                        "Only if the parent classes belong to the same module"),
                "Yes"));

        questions.add(new QuizQuestion("What is the output of this code?\n" +
                "\n" +
                "from functools import reduce\n" +
                "\n" +
                "nums = [1, 2, 3, 4]\n" +
                "result = reduce(lambda x, y: x * y, nums, 10)\n" +
                "print(result)",
                Arrays.asList("240",
                        "20",
                        "10",
                        "0"),
                "240"));

        questions.add(new QuizQuestion("What is the output of the following code?\n" +
                "\n" +
                "chars = ['a', 'b', 'c', 'A', 'B', 'C']\n" +
                "result = filter(lambda x: x.islower(), chars)\n" +
                "print(\"\".join(result))",
                Arrays.asList("'abcABC'",
                        "'abc'",
                        "'a', 'b', 'c'",
                        "'ABC'"),
                "'abc'"));

        questions.add(new QuizQuestion("What will be the output of the following code?\n" +
                "\n" +
                "class Engine:\n" +
                "    def start(self):\n" +
                "        return \"Engine started\"\n" +
                "\n" +
                "class Car:\n" +
                "    def __init__(self):\n" +
                "        self.engine = Engine()\n" +
                "\n" +
                "    def start_car(self):\n" +
                "        return self.engine.start()\n" +
                "\n" +
                "car = Car()\n" +
                "print(car.start_car())",
                Arrays.asList("No output",
                        "\"Engine started\"",
                        "\"Engine started\" and then \"Car started\"",
                        "Error"),
                "\"Engine started\""));

        questions.add(new QuizQuestion("What method is used to call the parent class constructor explicitly?",
                Arrays.asList("super()",
                        "self()",
                        "base()",
                        "parent()"),
                "super()"));

        questions.add(new QuizQuestion("What will happen if you try to access a key that does not exist in a dictionary using square brackets?\n" +
                "\n" +
                "d = {'a': 1, 'b': 2}\n" +
                "print(d['c'])",
                Arrays.asList("Throws an error",
                        "'c'",
                        "None",
                        "0"),
                "Throws an error"));

        questions.add(new QuizQuestion("What will happen if you try to access a key that does not exist in a dictionary using square brackets?\n" +
                "\n" +
                "d = {'a': 1, 'b': 2}\n" +
                "print(d['c'])",
                Arrays.asList("Throws an error",
                        "'c'",
                        "None",
                        "0"),
                "Throws an error"));

        questions.add(new QuizQuestion("What is the data type of the following?\n" +
                "\n" +
                "x = {1: \"a\", 2: \"b\", 3: \"c\"}",
                Arrays.asList("List",
                        "Dictionary",
                        "List",
                        "Set"),
                "Dictionary"));

        questions.add(new QuizQuestion("What will be the result of the following code?\n" +
                "\n" +
                "x = 0\n" +
                "while True:\n" +
                "    x += 1\n" +
                "    if x == 3:\n" +
                "        continue\n" +
                "    elif x == 5:\n" +
                "        break\n" +
                "    print(x, end=\" \")",
                Arrays.asList("1 2 4 5",
                        "1 2 4",
                        "Infinite loop",
                        "1 2 3"),
                "1 2 4"));

        questions.add(new QuizQuestion("What is the output of the following code?\n" +
                "\n" +
                "x, y, z = 5, \"Hello\", 3.2 \n" +
                "print(type(y))",
                Arrays.asList("<class 'list'>",
                        "<class 'str'>",
                        "<class 'float'>",
                        "<class 'int'>"),
                "<class 'str'>"));

        questions.add(new QuizQuestion("What will the following code produce?\n" +
                "\n" +
                "x = 5\n" +
                "y = 10\n" +
                "if x > 5:\n" +
                "    print(\"A\")\n" +
                "elif x == 5 and y < 10:\n" +
                "    print(\"B\")\n" +
                "elif y >= 10 or x < 5:\n" +
                "    print(\"C\")\n" +
                "else:\n" +
                "    print(\"D\")",
                Arrays.asList("D",
                        "A",
                        "B",
                        "C"),
                "C"));

        questions.add(new QuizQuestion("What is the output of the following code?\n" +
                "\n" +
                "for i in range(1, 6):\n" +
                "   if i % 2 == 0:\n" +
                "      print(i, end=\" \")",
                Arrays.asList("2 4",
                        "1 2 3 4 5",
                        "1 3 5",
                        "Throws an error"),
                "2 4"));

        questions.add(new QuizQuestion("What is the output of the following code?\n" +
                "\n" +
                "def outer(x):\n" +
                "    def inner(y):\n" +
                "        return x + y\n" +
                "    return inner\n" +
                "closure = outer(5)\n" +
                "print(closure(3))",
                Arrays.asList("8",
                        "5",
                        "Error",
                        "3"),
                "8"));

        questions.add(new QuizQuestion("What is the output of the following code?\n" +
                "\n" +
                "nums = [1, 2, 3, 4]\n" +
                "result = map(lambda x: x * 2, filter(lambda y: y % 2 == 0, nums))\n" +
                "print(list(result))",
                Arrays.asList("[2, 4, 6, 8]",
                        "[4, 8]",
                        "[1, 3]",
                        "[8]"),
                "[4, 8]"));

        questions.add(new QuizQuestion("Which of the following is a valid example of map()?",
                Arrays.asList("map([1, 2, 3], lambda x: x * 2)",
                        "map(lambda x: x * 2, range(3))",
                        "map(x * 2, [1, 2, 3])",
                        "map([1, 2, 3], x => x * 2)"),
                "map(lambda x: x * 2, range(3))"));

        questions.add(new QuizQuestion("What will be the output of the following code?\n" +
                "\n" +
                "add = lambda x, y: x + y\n" +
                "print(add(5, 3))",
                Arrays.asList("add(5,3)",
                        "8",
                        "Error",
                        "5, 3"),
                "8"));

        questions.add(new QuizQuestion("What is the output of the following code?\n" +
                "\n" +
                "class A:\n" +
                "    def __init__(self):\n" +
                "        self.__x = 10\n" +
                "    def get_x(self):\n" +
                "        return self.__x\n" +
                "\n" +
                "obj = A()\n" +
                "print(obj.__x)",
                Arrays.asList("Error",
                        "10",
                        "0",
                        "No output"),
                "Error"));

        questions.add(new QuizQuestion("What is the output of the following code?\n" +
                "\n" +
                "def func(a, b=10):\n" +
                "    return a + b\n" +
                "print(func(5))",
                Arrays.asList("5",
                        "15",
                        "No output",
                        "Error"),
                "15"));

        questions.add(new QuizQuestion("What is the output of the following code?\n" +
                "\n" +
                "strings = [\"apple\", \"banana\", \"cherry\", \"date\", \"elderberry\", \"abbott\"]\n" +
                "result = filter(lambda x: len(x) > 5 and x[0] == 'b', strings)\n" +
                "print(list(result))",
                Arrays.asList("['banana', 'cherry']",
                        "['cherry', 'date']",
                        "['banana']",
                        "['cherry', 'date']"),
                "['banana']"));

        questions.add(new QuizQuestion("What is the output of the following code?\n" +
                "\n" +
                "class A:\n" +
                "    def __init__(self):\n" +
                "        self.value = 5\n" +
                "    def show(self):\n" +
                "        return lambda x: x * self.value\n" +
                "obj = A()\n" +
                "func = obj.show()\n" +
                "print(func(2))",
                Arrays.asList("10",
                        "No output",
                        "2",
                        "5"),
                "10"));

        questions.add(new QuizQuestion("What will be the output of this membership operation?\n" +
                "\n" +
                "a = \"abcd\"\n" +
                "b = [\"a\", \"b\", \"c\", \"d\"]\n" +
                "print(\"abc\" in a and \"abc\" in b)",
                Arrays.asList("False",
                        "True",
                        "None",
                        "Throws an error"),
                "False"));

        questions.add(new QuizQuestion("What will happen if you assign a value to an undeclared variable in Python?",
                Arrays.asList("'NameError' will be raised\n",
                        "The variable will be automatically declared",
                        "The code will not run",
                        "Python will assign the value None to the variable"),
                "The variable will be automatically declared"));

        questions.add(new QuizQuestion("What is the output of the following code?\n" +
                "\n" +
                "def func(a, b=[]):\n" +
                "    b.append(a)\n" +
                "    return b\n" +
                "\n" +
                "print(func(1))\n" +
                "print(func(2))\n" +
                "print(func(3, []))\n" +
                "print(func(4))",
                Arrays.asList("[1], [1, 2], [3], [1, 2, 4]",
                        "[1], [1, 2], [3], [1, 2, 3, 4]",
                        "No output",
                        "[1], [2], [3], [4]"),
                "[1], [1, 2], [3], [1, 2, 4]"));

        questions.add(new QuizQuestion("Which symbol makes a variable private in Python?",
                Arrays.asList("$$",
                        "_",
                        "__",
                        "self"),
                "__"));
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
