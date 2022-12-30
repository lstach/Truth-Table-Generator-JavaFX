# Truth-Table-Generator-JavaFX
This is a desktop GUI application built in JavaFX that generates a truth table for a given propositional logic formula.

The program uses the [Shunting Yard Algorithm](https://en.wikipedia.org/wiki/Shunting_yard_algorithm) to convert the formula entered by the user into [Reverse Polish Notaion](https://en.wikipedia.org/wiki/Reverse_Polish_notation) (RPN).  Doing this allows the computer to solve the formula, as solving a formula in standard infix notation is incredibly difficult for computers to do.  While the Shunting Yard Algorithm is typically used for numerical formulas, the concept is the same for propositional formulas, as those are still written in infix notation and have both operators and operands.

Currently, the UI is incredibly bare-bones and the textbox necessitates that each token of the formula be separated by a space.
