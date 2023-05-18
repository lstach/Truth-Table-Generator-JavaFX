package com.example.truthtablegeneratorjavafx.controller;


import com.example.truthtablegeneratorjavafx.model.Model;

public class ControllerImpl implements Controller{

    private Model model;

    public ControllerImpl(Model model){
        this.model = model;
    }

    public void clickCalculate(String formula){
        model.main(formula);
    }

    //TODO: implement help pop-up
    public void clickHelp(){

    }

    public void clickSaveAs(){

    }

    @Override
    public void clickCopy() {

    }

    public void clickAbout(){

    }

}
