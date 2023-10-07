package com.slinlong.controller;

import com.slinlong.MainApp;
import com.slinlong.util.Util;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * @ClassName SetDialogView
 * @Description TODO
 * @Author z2006
 * @Date 2020/9/4 18:52
 * @Version 1.0
 **/
public class SetDialogViewController implements Initializable {

    @FXML
    private Button openPathBtn;
    @FXML
    private TextField pathFld;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MainApp.controllers.put(this.getClass().getSimpleName(),this);
        MainViewController controller=(MainViewController)MainApp.controllers.get(MainViewController.class.getSimpleName());

        OnlineModelViewController controller1=(OnlineModelViewController)MainApp.controllers.get(OnlineModelViewController.class.getSimpleName());

        pathFld.setText(controller.getPath());

        openPathBtn.setOnAction(event -> {
            DirectoryChooser dirDialog=new DirectoryChooser();
            dirDialog.setTitle("选择默认文件目录");
            dirDialog.setInitialDirectory(new File("c:\\"));
            File dir=dirDialog.showDialog(null);
            if (dir!=null){
                pathFld.setText(dir.getAbsolutePath());
                String path=pathFld.getText();
                controller.setPath(path);
                File file=new File(Util.getFileName("config/Set.xml").substring(5));
                controller.saveSetDataToFile(file);

                controller1.getFileChooser1().setInitialDirectory(new File(path));
                controller1.getFileChooser2().setInitialDirectory(new File(path));
                controller.getDialogStage().close();
            }
        });

    }
}
