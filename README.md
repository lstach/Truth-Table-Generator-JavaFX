# Truth-Table-Generator-JavaFX
This is a desktop GUI application built in JavaFX that generates a truth table for a given propositional logic formula.  It provides the ability to copy the output of the truth table to your clipboard and save the truth table as a .csv file.

The program uses the [Shunting Yard Algorithm](https://en.wikipedia.org/wiki/Shunting_yard_algorithm) to convert the formula entered by the user into [Reverse Polish Notaion](https://en.wikipedia.org/wiki/Reverse_Polish_notation) (RPN).  Doing this allows the computer to solve the formula for each combination of true and false, as solving a formula in standard infix notation is incredibly difficult for computers to do.  While the Shunting Yard Algorithm is typically used for numerical formulas, the concept is the same for propositional logic formulas, as those are still written in infix notation and have both operators and operands.

The program also implements an [abstract syntax tree](https://en.wikipedia.org/wiki/Abstract_syntax_tree) to validate input.  It delievers an error message based on the specific type of error received, such as mismatched parentheses, an operand that is preceded by another operand instead of an operator, unknown characters, etc.

# Install
1. Make sure you have [JDK 20.0.1](https://www.oracle.com/java/technologies/javase/jdk20-archive-downloads.html) installed.  
2. Download the .jar under 'releases'.
3. After that, just double click on the jar and you're good to go!

*Note that, at the moment, this program is only confirmed to work on Windows.  Even after installing JDK 20.0.1, Mac users have reported difficulties using it.*
