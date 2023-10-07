package com.slinlong.util;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;

import java.net.URL;

/**
 * @ClassName UIUtil
 * @Description TODO
 * @Author z2006
 * @Date 2020/8/10 17:07
 * @Version 1.0
 **/
public class UIUtil {
    public static FXMLLoader getLoader(String fxmlFilePath){
        URL path=new Object(){
            public URL getPath(){
                return this.getClass().getClassLoader().getResource(fxmlFilePath);
            }
        }.getPath();
        URL location=path;
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(location);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        return loader;
    }
}
