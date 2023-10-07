package com.slinlong.model;

/**
 * @ClassName TagFolder
 * @Description ����TagTable���ļ��ж���
 * @Author z2006
 * @Date 2020/8/9 19:14
 * @Version 1.0
 **/
public class TagFolder {
    private String folderName;

    public TagFolder(){
        setFolderName("");
    }

    @Override
    public String toString() {
        return "TagFolder{" +
                "folderName='" + folderName + '\'' +
                '}';
    }

    public TagFolder(String name){
        setFolderName(name);
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
}
