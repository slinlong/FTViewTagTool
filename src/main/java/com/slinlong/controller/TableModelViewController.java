package com.slinlong.controller;

import com.slinlong.MainApp;
import com.slinlong.function.TagSheet;
import com.slinlong.model.*;
import com.slinlong.util.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName TableModelViewController
 * @Description TODO
 * @Author z2006
 * @Date 2020/8/10 14:55
 * @Version 1.0
 **/
public class TableModelViewController implements Initializable {
    @FXML
    private TableView<ExcelModelProperty> tableView;
    @FXML
    private TableColumn<ExcelModelProperty,String> tagTypeCol;
    @FXML
    private TableColumn<ExcelModelProperty,String> alarmLevelCol;
    @FXML
    private TableColumn<ExcelModelProperty,String> alarmTypeCol;
    @FXML
    private TableColumn<ExcelModelProperty,String> alarmClassifyCol;
    @FXML
    private TableColumn<ExcelModelProperty,String> tagNameCol;
    @FXML
    private TableColumn<ExcelModelProperty,String> tagDescriptionCol;
    @FXML
    private TableColumn<ExcelModelProperty,String> plcTagNameCol;
    @FXML
    private Button openExcelBtn1;
    @FXML
    private Button openExcelBtn2;
    @FXML
    private Button outputTagTableBtn;
    @FXML
    private Button outputAlarmTableBtn;
    @FXML
    private Button clearTableBtn;

    @FXML
    private TextField topicTfd;
    @FXML
    private TextField securityCodeTfd;
    @FXML
    private CheckBox isV10Cbx;

    @FXML
    private TextArea messageArea;

    private ObservableList<ExcelModelProperty> emps= FXCollections.observableArrayList();

    private FileChooser fileChooser1=new FileChooser();
    private FileChooser fileChooser2=new FileChooser();

    ArrayList<ExcelModel> datas=new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MainApp.controllers.put(this.getClass().getSimpleName(),this);
        initializePane();
        appendLog("欢迎使用FactoryTalk SE 标签生成工具V4.0");
        appendLog("当前模式：表格导入");
    }


    /**
     * 初始化页面
     */
    public void initializePane(){
        MainApp.controllers.put(this.getClass().getSimpleName(),this);
        MainViewController controller=(MainViewController)MainApp.controllers.get(MainViewController.class.getSimpleName());

        //初始化表格
        tableView.setItems(emps);
        tagTypeCol.setCellValueFactory(cellData->cellData.getValue().tagTypeProperty());
        alarmLevelCol.setCellValueFactory(cellData->cellData.getValue().alarmLevelProperty());
        alarmTypeCol.setCellValueFactory(cellData->cellData.getValue().alarmTypeProperty());
        alarmClassifyCol.setCellValueFactory(cellData->cellData.getValue().alarmClassifyProperty());
        tagNameCol.setCellValueFactory(cellData->cellData.getValue().tagNameProperty());
        tagDescriptionCol.setCellValueFactory(cellData->cellData.getValue().tagDescriptionProperty());
        plcTagNameCol.setCellValueFactory(cellData->cellData.getValue().plcTagNameProperty());

        //初始化文件操作弹窗
        fileChooser1.setTitle("打开模板表格");
        fileChooser1.getExtensionFilters()
                .addAll(new FileChooser.ExtensionFilter("Excel File","*.xls"));
        fileChooser1.setInitialDirectory(new File(controller.getPath()));
        fileChooser2.setTitle("导出Csv文件");
        fileChooser2.getExtensionFilters()
                .addAll(new FileChooser.ExtensionFilter("CSV File","*.csv"));
        fileChooser2.setInitialDirectory(new File(controller.getPath()));

        //打开标准模板Excel表格
        openExcelBtn1.setOnAction(event -> openExcelAction(1));
        //打开结构模板Excel表格
        openExcelBtn2.setOnAction(event -> openExcelAction(2));
        //导出Tag标签表
        outputTagTableBtn.setOnAction(event -> outputTagTableAction());
        //导出Alarm标签表
        outputAlarmTableBtn.setOnAction(event -> outputAlarmTableAction());
        //清空表格
        clearTableBtn.setOnAction(event -> emps.clear());
        isV10Cbx.setSelected(true);
    }


    /**
     * 打开Excel模板
     * @param typeCode 1=标准模板，2=结构模板
     */
    public void openExcelAction(int typeCode){
        emps.clear();
        File selectedFile=fileChooser1.showOpenDialog(openExcelBtn1.getScene().getWindow());
        if (selectedFile!=null){
            try{
                String path=selectedFile.toURI().toURL().toExternalForm().substring(5);
                datas=TagSheet.generateData(path,typeCode);
                updateTopic();
                emps.addAll(TagSheet.generateDataProperty(datas));
                appendLog("已导入"+(typeCode==1?"标准模板":"结构模板")+"——"+path);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 更新Topic
     */
    public void updateTopic(){
        String topic=topicTfd.getText();
        if (!topic.equals("")){
            for (ExcelModel em :datas ) {
                String plcTag=em.getPlcTagName();
                if (!plcTag.equals("") && !plcTag.startsWith("[")){
                    em.setPlcTagName("["+topic+"]"+plcTag);
                }
            }
        }
    }

    /**
     * 更新权限编码
     * @param tms 标签组列表
     */
    public void updateSecurityCode(ArrayList<TagModel> tms){
        String securityCode=securityCodeTfd.getText();
        //增加判断securityCode是A至Z的字母和*的条件
        if (!securityCode.equals("") && Util.isSecurityCode(securityCode)){
            for (TagModel tm : tms) {
                if (!tm.getSecurityCode().equals("") && Util.isSecurityCode(tm.getSecurityCode())){
                    tm.setSecurityCode(securityCode);
                }
            }
        }
    }


    /**
     * 导出Tag表
     */
    public void outputTagTableAction(){
        ArrayList<TagModel> tms=new ArrayList<>();
        updateTopic();
        List<TagFolder> folders=TagSheet.generateTagFolder(datas);
        List<SETag> tags=TagSheet.generateTagModel(datas);

        tms.add(new TagModel(0));
        tms.add(new TagModel(-1));
        tms.add(new TagModel(100));
        tms.add(new TagModel(1));
        folders.stream().forEach(f->{
            TagModel tm=new TagModel(f);
            tms.add(tm);
        });
        tms.add(new TagModel(2));

        tags.stream().forEach(t->{
            if (t.getClass().getName().equals("com.slinlong.model.AnalogTag")){
                tms.add(new TagModel((AnalogTag) t));
            }
            if (t.getClass().getName().equals("com.slinlong.model.DigtalTag")){
                tms.add(new TagModel((DigtalTag) t));
            }
            if (t.getClass().getName().equals("com.slinlong.model.StringTag")){
                tms.add(new TagModel((StringTag)t));
            }
        });
        updateSecurityCode(tms);

        saveTagCsvAction(tms,outputTagTableBtn.getScene().getWindow());
    }

    /**
     * 导出AlarmTag表
     */
    public void outputAlarmTableAction(){
        ArrayList<AlarmModel> ams=new ArrayList<>();
        List<AlarmModel> alarms=TagSheet.generateAlarmModel(datas,isV10Cbx.isSelected());
        ams.add(new AlarmModel(0));
        ams.add(new AlarmModel());
        ams.add(new AlarmModel(-1));
        ams.add(new AlarmModel());
        ams.add(new AlarmModel(1));
        ams.add(new AlarmModel(11));
        ams.add(new AlarmModel());
        ams.add(new AlarmModel(2));
        ams.add(new AlarmModel(22));
        ams.addAll(alarms);

        saveAlarmCsvAction(ams,outputAlarmTableBtn.getScene().getWindow());
    }


    /**
     * 将标签组保存到CSV文件
     * @param tms 标签组
     * @param ownerWindow
     */
    public void saveTagCsvAction(ArrayList<TagModel> tms, Window ownerWindow){

        File selectedFile=fileChooser2.showSaveDialog(ownerWindow);
        if (selectedFile!=null){
            try{
                String path=selectedFile.toURI().toURL().toExternalForm().substring(5);
                TagSheet.saveTagCsvFile(path,tms);
                appendLog("导出SE Tag表格——"+path);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    /**
     * 将报警组保存到CSV文件
     * @param ams 报警组
     * @param ownerWindow
     */
    public void saveAlarmCsvAction(ArrayList<AlarmModel> ams, Window ownerWindow){
        File selectedFile=fileChooser2.showSaveDialog(ownerWindow);
        if (selectedFile!=null){
            try{
                String path=selectedFile.toURI().toURL().toExternalForm().substring(5);
                TagSheet.saveAlarmCsvFile(path,ams);
                appendLog("导出SE Alarm表格——"+path);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 给信息文本框中发送信息
     * @param message 需要发送的文本信息
     */
    public void appendLog(String message){
        messageArea.appendText(Util.localDateToStr()+" "+Util.LocalTimeToStr()+"——" +message+"\n");
    }

    public FileChooser getFileChooser1() {
        return fileChooser1;
    }

    public FileChooser getFileChooser2() {
        return fileChooser2;
    }
}
