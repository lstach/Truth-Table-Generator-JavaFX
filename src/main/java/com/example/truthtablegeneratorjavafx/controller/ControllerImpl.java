package com.example.truthtablegeneratorjavafx.controller;


import com.example.truthtablegeneratorjavafx.model.Model;

import java.io.File;
import java.io.IOException;

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

    public void clickSaveAs(File location) {
        try {
            model.export(location);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void clickCopy() {
        model.copy();
    }

    @Override
    public void clickGitHub() {
        model.openGitHub();
    }


}
