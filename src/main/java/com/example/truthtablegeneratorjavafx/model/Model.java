package com.example.truthtablegeneratorjavafx.model;

import com.example.truthtablegeneratorjavafx.model.Exceptions.DoubleOperandException;
import com.example.truthtablegeneratorjavafx.model.Exceptions.DoubleOperatorException;
import com.example.truthtablegeneratorjavafx.model.Exceptions.TorFException;
import com.example.truthtablegeneratorjavafx.model.Exceptions.UnknownTokenException;

import java.util.ArrayList;

public interface Model {

    /** takes the string in the textfield from the controller, and parses it into tokens before passing it to the shunter **/
    public ArrayList<String> parse(String formula) throws UnknownTokenException, TorFException, DoubleOperatorException, DoubleOperandException;

    public void main(String formula);

    public ArrayList<String>[] getTruthTable();

    public ArrayList<String> getVariables();
    public String getFormula();
    public String getUneditedFormula();
    public String getErrorMessage();
    public void notifyObservers();
    public void addObserver(ModelObserver observer);

    public void export();
    public void copy();
    public void openGitHub();
}
