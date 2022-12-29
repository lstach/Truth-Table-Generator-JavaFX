package com.example.truthtablegeneratorjavafx.controller;


import com.example.truthtablegeneratorjavafx.model.Model;

public class ControllerImpl implements Controller{

    private Model model;

    public void clickCalculate(String formula){
        model.parse(formula);
    }

}
