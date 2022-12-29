package com.example.truthtablegeneratorjavafx.model;

import java.util.ArrayList;
import java.util.Arrays;

public class ModelImpl implements Model{

    private boolean[][] boolGrid;
    private ArrayList<String>[] truthTable;

    public ModelImpl(){
        boolGrid = new boolean[0][0];
        truthTable = new ArrayList[0];

    }

    //TODO: get input from textfield, change from String[] to ArrayList<String> return type
    public ArrayList<String> parse(String formula){
        //TODO: fix this! this is a temp solution
        String[] infixTokens = formula.split(" ");


        return new ArrayList<>(Arrays.asList(infixTokens));
    }

    public void main(String formula){

        ArrayList<String> infixTokens = parse(formula);
        int numOperands = countOperands(infixTokens);
        ArrayList<String> rpnTokens = ShuntingYard.shunt(infixTokens);


        boolGrid = generateRows(numOperands);

        System.out.println("Printing out true/false combinations for " + numOperands + " operands...");
        System.out.println();
        for (int row = 0; row < boolGrid.length; row++){

            for (int col = 0; col < boolGrid[row].length; col++){
                System.out.print(boolGrid[row][col] + " ");
            }
            System.out.println();
        }

        System.out.println();

        System.out.println("Generating the 2d array of strings containing the each formula with their true/false combinations in RPN...");
        ArrayList<String>[] truthTable = new ArrayList[boolGrid.length];

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

            truthTable[row] = rowFormula;
        }
        System.out.println(" Done.");


        System.out.println();
        System.out.println("Now printing out the encoded truth table w/ the operand...");
        System.out.println();

        for (int row = 0; row < truthTable.length; row++){
            for (int i = 0; i < truthTable[row].size(); i++){
                System.out.print(truthTable[row].get(i) + " ");
            }
            System.out.println();
        }



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
}
