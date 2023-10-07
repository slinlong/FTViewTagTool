package com.slinlong;

import com.slinlong.util.UIUtil;
import com.slinlong.util.Util;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MainApp
 * @Description JavaFX应用程序入口
 * @Author z2006
 * @Date 2020/8/10 17:04
 * @Version 1.0
 **/
public class MainApp extends Application {
    public static Map<String,Object> controllers=new HashMap<String,Object>();;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader= UIUtil.getLoader("fxml/MainView.fxml");
        Parent root=loader.load();
        Scene scene=new Scene(root);
        primaryStage.setTitle("FactoryTalk SE 标签生成工具 V4.0");
        primaryStage.setResizable(true);
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        Image image=new Image(Util.getFileName("image/apple.png"));
        primaryStage.getIcons().add(image);
        primaryStage.setHeight(600);
        primaryStage.setWidth(1200);
        primaryStage.show();
    }



}
