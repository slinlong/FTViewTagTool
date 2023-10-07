package com.slinlong.controller;

import com.slinlong.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @ClassName AboutViewController
 * @Description TODO
 * @Author z2006
 * @Date 2020/9/4 16:54
 * @Version 1.0
 **/
public class AboutViewController implements Initializable {

    @FXML
    private Accordion root;
    @FXML
    private TitledPane v1Pane;
    @FXML
    private TitledPane v2Pane;
    @FXML
    private TitledPane v3Pane;
    @FXML
    private TitledPane v3_1Pane;
    @FXML
    private TitledPane v3_2Pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MainApp.controllers.put(this.getClass().getSimpleName(),this);
        root.setExpandedPane(v3_2Pane);
    }
}
