package com.slinlong.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName SetModel
 * @Description TODO
 * @Author z2006
 * @Date 2020/9/4 19:06
 * @Version 1.0
 **/
@XmlRootElement(name="set")
public class SetModel {
    @XmlElement(name="defaultPath")
    private String defaultPath;

    public String getDefaultPath() {
        return defaultPath;
    }

    public void setDefaultPaht(String defaultPath) {
        this.defaultPath = defaultPath;
    }
}
