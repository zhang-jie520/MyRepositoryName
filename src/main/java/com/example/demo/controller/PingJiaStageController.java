package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.view.*;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;


@FXMLController
public class PingJiaStageController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void pjButtonClick1(ActionEvent actionEvent) {
        DemoApplication.showView(DataStageView.class);
    }

    @FXML
    public void pjButtonClick2(ActionEvent actionEvent) {
        DemoApplication.showView(Data2StageView.class);
    }

    @FXML
    public void pjButtonClick3(ActionEvent actionEvent) {
        DemoApplication.showView(Data3StageView.class);
    }

    @FXML
    public void pjButtonClick4(ActionEvent actionEvent) {
        DemoApplication.showView(Data4StageView.class);
    }

    @FXML
    public void backButtonClick(ActionEvent actionEvent) {
        DemoApplication.showView(IndexStageView.class);
    }


    public void pjButtonClick11(ActionEvent actionEvent) {
        DemoApplication.showView(Data11StageView.class);
    }

    public void pjButtonClick21(ActionEvent actionEvent) {
        DemoApplication.showView(Data21StageView.class);
    }

    public void pjButtonClick31(ActionEvent actionEvent) {
        DemoApplication.showView(Data31StageView.class);
    }
}

