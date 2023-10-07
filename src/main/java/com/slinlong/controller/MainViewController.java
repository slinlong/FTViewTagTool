package com.slinlong.controller;

import com.slinlong.MainApp;
import com.slinlong.model.SetModel;
import com.slinlong.util.UIUtil;
import com.slinlong.util.Util;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @ClassName MainViewController
 * @Description TODO
 * @Author z2006
 * @Date 2020/8/10 14:53
 * @Version 1.0
 **/
public class MainViewController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private MenuItem exitMenuItem;
    @FXML
    private MenuItem aboutMenuItem;
    @FXML
    private MenuItem helpMenuItem;
    @FXML
    private MenuItem importTableMenuItem;
    @FXML
    private MenuItem onlineMenuItem;
    @FXML
    private MenuItem pathSetMenuItem;

    private String path;

    private Stage dialogStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MainApp.controllers.put(this.getClass().getSimpleName(),this);

        File file=new File(Util.getFileName("config/Set.xml").substring(5));

        setPath("C:\\");
        //loadSetDataFromFile(file);

        loadFXML("fxml/OnlineModelView.fxml");

        exitMenuItem.setOnAction(event -> System.exit(0));
        aboutMenuItem.setOnAction(event -> loadFXML("fxml/AboutView.fxml"));
        importTableMenuItem.setOnAction(event -> loadFXML("fxml/TableModelView.fxml"));
        onlineMenuItem.setOnAction(event -> loadFXML("fxml/OnlineModelView.fxml"));
        helpMenuItem.setOnAction(event -> loadFXML("fxml/HelpView.fxml"));



        pathSetMenuItem.setOnAction(event -> {
            try {
                FXMLLoader loader= UIUtil.getLoader("fxml/SetDialogView.fxml");
                Parent root=loader.load();
                Scene scene=new Scene(root);

                dialogStage=new Stage();
                dialogStage.setTitle("设置");
                Image image=new Image(Util.getFileName("image/apple.png"));
                dialogStage.getIcons().add(image);
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.setScene(scene);
                dialogStage.showAndWait();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 加载FXML界面文件
     * @param path 界面文件相对路径
     */
    public void loadFXML(String path){
        try {
            FXMLLoader loader= UIUtil.getLoader(path);
            Parent root=loader.load();
            borderPane.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void loadSetDataFromFile(File file){
        try{
            SetModel setModel=JAXB.unmarshal(file,SetModel.class);
            if (setModel==null){
                setPath("C:\\");
                return;
            }
            setPath(setModel.getDefaultPath());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveSetDataToFile(File file){
        try{
            JAXBContext context=JAXBContext.newInstance(SetModel.class);
            Marshaller m=context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            SetModel setModel=new SetModel();
            setModel.setDefaultPaht(getPath());
            m.marshal(setModel,file);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
