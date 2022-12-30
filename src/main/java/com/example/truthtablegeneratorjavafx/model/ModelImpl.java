package com.example.truthtablegeneratorjavafx.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ModelImpl implements Model{


    /** boolGrid stores all combinations of true/false for a given formula, formulaGrid stores each formula in RPN
     * (with the answer for each formula as the last element in every row) and truthTable is a grid that is the closest
     * approximation of a truth table; that is, each row is a combination of true and false, while the last element is
     * the resulting boolean value for that combination.  truthTable omits operands.
     * **/
    private boolean[][] boolGrid;
    private ArrayList<String>[] formulaGrid;
    private ArrayList<String>[] truthTable;

    /** variables is an arraylist of Strings containing the variables entered by the user. **/
    private ArrayList<String> variables;
    /** formula string entered by the user **/
    private String formula;

    private ArrayList<ModelObserver> observers;

    public ModelImpl(){
        boolGrid = new boolean[0][0];
        formulaGrid = new ArrayList[0];
        truthTable = new ArrayList[0];

        variables = new ArrayList<>();
        formula = new String();

        observers = new ArrayList<>();
    }

    //TODO: get input from textfield, change from String[] to ArrayList<String> return type
    public ArrayList<String> parse(String formula){
        //TODO: fix this! this is a temp solution
        String[] infixTokens = formula.split(" ");


        return new ArrayList<>(Arrays.asList(infixTokens));
    }

    public void main(String formula){

        this.formula = formula;

        ArrayList<String> infixTokens = parse(formula);
        int numOperands = countOperands(infixTokens);
        variables = getOperands(infixTokens);

        ArrayList<String> rpnTokens = ShuntingYard.shunt(infixTokens);

        System.out.print("Generating true/false combinations for " + numOperands + " operands...");
        boolGrid = generateRows(numOperands);
        System.out.println("Done.\n");

        System.out.println("Printing out true/false combinations for " + numOperands + " operands...");
        System.out.println();
        for (int row = 0; row < boolGrid.length; row++){

            for (int col = 0; col < boolGrid[row].length; col++){
                System.out.print(boolGrid[row][col] + " ");
            }
            System.out.println();
        }

        System.out.println();

        System.out.println("Generating the 2d array of strings containing the each formula with their true/false combinations in RPN (i.e. generating formulaGrid)...");
        formulaGrid = new ArrayList[boolGrid.length];

        //TODO: within this double-for loop, add the answer to each rowFormula to the end of the rowFormula List
        for (int row = 0; row < boolGrid.length; row++){
            int col = 0;
            //visitedOperands can be made more efficient with a hashmap (index represents index in row, key represents operand)
            ArrayList<String> visitedOperands = new ArrayList<>();
            ArrayList<String> rowFormula = new ArrayList<>();
            for (int i = 0; i < rpnTokens.size(); i++){
                String token = rpnTokens.get(i);
                if (ShuntingYard.isOperand(token)){
                    if (!visitedOperands.contains(token)){
                        rowFormula.add(Boolean.toString(boolGrid[row][col]));
                        visitedOperands.add(token);
                        col++;
                    }
                    else{
                        int retrieve = visitedOperands.indexOf(token);
                        rowFormula.add(Boolean.toString(boolGrid[row][retrieve]));
                    }
                }
                else{
                    rowFormula.add(token);
                }
            }

            boolean rowAnswer = ShuntingYard.calculate(rowFormula);

            rowFormula.add(Boolean.toString(rowAnswer));

            formulaGrid[row] = rowFormula;
        }
        System.out.println(" Done.");

        System.out.println();
        System.out.println("Now printing out the encoded formula grid (w/ the operand)...");
        System.out.println();

        for (int row = 0; row < formulaGrid.length; row++){
            for (int i = 0; i < formulaGrid[row].size(); i++){
                System.out.print(formulaGrid[row].get(i) + " ");
            }
            System.out.println();
        }
        System.out.println("Done.");



        System.out.println();
        System.out.print("Now generating truthTable, the 2d matrix of strings containing each combination of true and false and the answer for each combination in the last column... ");

        truthTable = new ArrayList[boolGrid.length];

        for (int row = 0; row < boolGrid.length; row++){

            truthTable[row] = new ArrayList<>();

            for (int col = 0; col < boolGrid[0].length; col++){
                truthTable[row].add(Boolean.toString(boolGrid[row][col]));
            }

            String value = formulaGrid[row].get(formulaGrid[row].size() - 1); //gets the last element in the corresponding row of the formula grid, which is the actual value of the expression using that row's combination
            truthTable[row].add(value);
        }
        System.out.println("Done.");




        System.out.println();
        System.out.println("Now printing the truthTable....");
        System.out.println();

        for (int row = 0; row < truthTable.length; row++){
            for (int col = 0; col < truthTable[row].size(); col++){
                System.out.print(truthTable[row].get(col) + " ");
            }
            System.out.println();
        }
        System.out.println("Done.");

        notifyObservers();
    }

    public boolean[][] generateRows(int numOperands){
        boolean[][] grid = new boolean[(int)Math.pow(2, numOperands)][numOperands];

        //each iteration of the for loop builds 1 row of the truth table
        for (int i = 0; i < Math.pow(2, numOperands); i++){
            String bin = Integer.toBinaryString(i);
            while (bin.length() < numOperands){
                bin  = "0" + bin;
            }
            for (int j = 0; j < bin.length(); j++){
                if (bin.charAt(j) == '1'){
                    grid[i][j] = true;
                }
            }
        }
        return grid;
    }




    public int countOperands(ArrayList<String> tokens){
        ArrayList<String> uniqueOps = new ArrayList<>();
        for (int i = 0; i < tokens.size(); i++){
            String token = tokens.get(i);
            if(!uniqueOps.contains(token) && ShuntingYard.isOperand(token)){ //if the token is an operand and it hasn't already been counted
                uniqueOps.add(token);
            }
        }
        return uniqueOps.size();
    }

    private ArrayList<String> getOperands(ArrayList<String> tokens){
        ArrayList<String> uniqueOps = new ArrayList<>();
        for (int i = 0; i < tokens.size(); i++){
            String token = tokens.get(i);
            if(!uniqueOps.contains(token) && ShuntingYard.isOperand(token)){ //if the token is an operand and it hasn't already been counted
                uniqueOps.add(token);
            }
        }
        return uniqueOps;
    }

    public ArrayList<String>[] getTruthTable(){
        return truthTable;
    }

    @Override
    public ArrayList<String> getVariables() {
        return variables;
    }

    @Override
    public String getFormula() {
        return formula;
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++){
            observers.get(i).update(this);
        }
    }

    @Override
    public void addObserver(ModelObserver observer) {
        observers.add(observer);
    }

}
