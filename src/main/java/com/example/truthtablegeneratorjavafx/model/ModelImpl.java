package com.example.truthtablegeneratorjavafx.model;

import java.util.ArrayList;
import java.util.Arrays;

public class ModelImpl implements Model{

    private boolean[][] boolGrid;
    private String[][] truthTable;

    public ModelImpl(){
        boolGrid = new boolean[0][0];
        truthTable = new String[0][0];

    }

    //TODO: get input from textfield, change from String[] to ArrayList<String> return type
    public String[] parse(String formula){
        //TODO: fix this! this is a temp solution
        String[] infixTokens = formula.split(" ");


        return infixTokens;
    }

    public void main(String formula){

        ArrayList<String> infixTokens = new ArrayList<>(Arrays.asList(parse(formula)));
        ArrayList<String> rpnTokens = ShuntingYard.shunt(parse(formula));
        int numOperands = countOperands(infixTokens);

        boolGrid = generateRows(numOperands);
        System.out.println("Printing out true/false combinations for " + numOperands + " operands...");
        for (int row = 0; row < boolGrid.length; row++){
            for (int col = 0; col < boolGrid[row].length; col++){
                System.out.print(boolGrid[row][col] + " ");
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
