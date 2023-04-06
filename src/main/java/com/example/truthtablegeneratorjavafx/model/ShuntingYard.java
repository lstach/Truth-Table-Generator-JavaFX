package com.example.truthtablegeneratorjavafx.model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/*TODO:
    1. count all operands
    2. create every possible combination of true/false for all operands (this represents 1 row in the truth table)
    3. generate each formula (as a string) with explicit true/false values according to the matrix of true/false combinations
    4. convert each formula into RPN
    5. evaluate each combination
    6. display output in truth table accordingly
 */


public class ShuntingYard {

    public static ArrayList<String> operators = new ArrayList<String>(Arrays.asList(
            "->", "/\\", "\\/", "<->", "!", "xor"
    ));

    //operands exclude 't' and 'f' because those are considered reserved keywords for "true" and "false" respectively.  Same for "x", which is reserve for xor
    public static ArrayList<String> operands = new ArrayList<>(Arrays.asList(
            "a", "b", "c", "d", "e", "g", "h", "i", "j", "k", "l", "m", "n",
            "p", "q", "s", "u", "v", "w", "y", "z"
    ));

    /*** shunt() takes each token (individual operator and operand) in infix (i.e. standard) notation, and converts it into a list ordered in RPN ***/
    /*** this DOESN'T evaluate the boolean expression; that is done by calculate using the output from this function. ***/
    public static ArrayList<String> shunt(ArrayList<String> tokens){
        Stack<String> operatorStack = new Stack<>();
        ArrayList<String> output = new ArrayList<>();

        for (int i = 0; i < tokens.size(); i++){ //while there are tokens to be read
            String token = tokens.get(i); //read a token
            if (isOperator(token)){ //if token is an operator
                while (!operatorStack.isEmpty() && precedenceLevel(token) <= precedenceLevel(operatorStack.peek())){ //while there is an operator at the top of the stack w/ greater precedence
                    if (precedenceLevel(token) > precedenceLevel(operatorStack.peek())){ //push the current token to the stack if it is of higher precedence
                        operatorStack.push(token);
                    }
                    else //add the next operator of the stack to the strOutput if it is of higher precedence (it will be performed first).
                    {
                        output.add(operatorStack.pop());
                    }
                }
                operatorStack.push(token); //loop has either exited or never run, meaning that the currently-read token is next in precedence compared to the stack
            }
            else if (isLeftParen(token)){ //pushes to operatorStack if it's left parenthesis
                operatorStack.push(token);
            }
            else if (isRightParen((token))){ //goes through every operator in the stack and adds it to output string until hitting the left parentheses.
                while (!operatorStack.peek().equals("(")){
                    output.add(operatorStack.pop());
                }
                operatorStack.pop(); //DOES NOT ADD LEFT PARENTHESES TO OUTPUT! Just gets rid of it.
            }
            /*** TODO: change to .isOperand(token) in production, using .isBoolean() for testing purposes ***/
            else if (isBoolean(token)){ //if token is an operand (i.e. a boolean variable) add it to the output
                output.add(token);
            }
            else if (isOperand(token)){
                output.add(token);
            }
        }

        while(!operatorStack.isEmpty()){
            output.add(operatorStack.pop());
        }
        return output;
    }


    //need to implement: !, xor
    //higher number -> higher precedent
    static int precedenceLevel(String operator){
        switch(operator){
            case"<->":
                return 0;
            case "->":
                return 1;
            case "xor":
            case "\\/":
                return 2;
            case "/\\":
                return 3;
            case "!":
                return 4;
        }
        return -1;
    }

    /*** reads a boolean expression in RPN (the output from shunt()) and outputs the result. ***/
    public static boolean calculate(ArrayList<String> tokens){
        Stack<Boolean> stack = new Stack<>();

        for (String token: tokens){

            if (isBoolean(token)){
                stack.push(Boolean.parseBoolean(token));
            }
            //'!' gets its own block because we don't want to pop 2 booleans from the stack if there's only 1
            else if (token.equals("!")){
                boolean p = stack.pop();
                boolean value = Evaluate.not(p);
                stack.push(value);
            }
            else{
                boolean q = stack.pop();
                boolean p = stack.pop();
                boolean value = true;
                switch (token){
                    case"/\\":
                        value = Evaluate.and(p,q);
                        break;
                    case "\\/":
                        value = Evaluate.or(p, q);
                        break;
                    case "<->":
                        value = Evaluate.iff(p, q);
                        break;
                    case "->":
                        value = Evaluate.implies(p, q);
                        break;
                    case "xor":
                        value = Evaluate.xor(p, q);
                        break;
                }
                stack.push(value);
            }
        }
        return stack.pop();
    }




    public static boolean isLeftParen(String s){
        return (s.equals("("));
    }

    public static boolean isRightParen(String s){
        return (s.equals(")"));
    }

    public static boolean isOperator(String s){
        return operators.contains(s);
    }

    public static boolean isOperand(String s){
        return operands.contains(s);
    }

    public static boolean isBoolean(String s){
        return (s.equalsIgnoreCase("true")||s.equalsIgnoreCase("false"));
    }
}