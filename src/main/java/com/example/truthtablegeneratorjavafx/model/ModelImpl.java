package com.example.truthtablegeneratorjavafx.model;

public class ModelImpl implements Model{

    private boolean[][] rpnBoolGrid;
    private String[][] truthTable;

    public ModelImpl(){
        rpnBoolGrid = new boolean[0][0];
        truthTable = new String[0][0];

    }

    //TODO: get input from textfield, shunt
    public void shunt(){

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

}
