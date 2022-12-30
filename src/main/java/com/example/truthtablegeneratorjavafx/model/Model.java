package com.example.truthtablegeneratorjavafx.model;

import java.util.ArrayList;

public interface Model {

    /** takes the string in the textfield from the controller, and parses it into tokens before passing it to the shunter **/
    public ArrayList<String> parse(String formula);

    public void main(String formula);

    public ArrayList<String>[] getTruthTable();

    public ArrayList<String> getVariables();
    public String getFormula();
    public void notifyObservers();
    public void addObserver(ModelObserver observer);

}
