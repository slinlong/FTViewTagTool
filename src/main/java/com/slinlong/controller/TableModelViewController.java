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
        appendLog("��ӭʹ��FactoryTalk SE ��ǩ���ɹ���V4.0");
        appendLog("��ǰģʽ�������");
    }


    /**
     * ��ʼ��ҳ��
     */
    public void initializePane(){
        MainApp.controllers.put(this.getClass().getSimpleName(),this);
        MainViewController controller=(MainViewController)MainApp.controllers.get(MainViewController.class.getSimpleName());

        //��ʼ�����
        tableView.setItems(emps);
        tagTypeCol.setCellValueFactory(cellData->cellData.getValue().tagTypeProperty());
        alarmLevelCol.setCellValueFactory(cellData->cellData.getValue().alarmLevelProperty());
        alarmTypeCol.setCellValueFactory(cellData->cellData.getValue().alarmTypeProperty());
        alarmClassifyCol.setCellValueFactory(cellData->cellData.getValue().alarmClassifyProperty());
        tagNameCol.setCellValueFactory(cellData->cellData.getValue().tagNameProperty());
        tagDescriptionCol.setCellValueFactory(cellData->cellData.getValue().tagDescriptionProperty());
        plcTagNameCol.setCellValueFactory(cellData->cellData.getValue().plcTagNameProperty());

        //��ʼ���ļ���������
        fileChooser1.setTitle("��ģ����");
        fileChooser1.getExtensionFilters()
                .addAll(new FileChooser.ExtensionFilter("Excel File","*.xls"));
        fileChooser1.setInitialDirectory(new File(controller.getPath()));
        fileChooser2.setTitle("����Csv�ļ�");
        fileChooser2.getExtensionFilters()
                .addAll(new FileChooser.ExtensionFilter("CSV File","*.csv"));
        fileChooser2.setInitialDirectory(new File(controller.getPath()));

        //�򿪱�׼ģ��Excel���
        openExcelBtn1.setOnAction(event -> openExcelAction(1));
        //�򿪽ṹģ��Excel���
        openExcelBtn2.setOnAction(event -> openExcelAction(2));
        //����Tag��ǩ��
        outputTagTableBtn.setOnAction(event -> outputTagTableAction());
        //����Alarm��ǩ��
        outputAlarmTableBtn.setOnAction(event -> outputAlarmTableAction());
        //��ձ��
        clearTableBtn.setOnAction(event -> emps.clear());
        isV10Cbx.setSelected(true);
    }


    /**
     * ��Excelģ��
     * @param typeCode 1=��׼ģ�壬2=�ṹģ��
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
                appendLog("�ѵ���"+(typeCode==1?"��׼ģ��":"�ṹģ��")+"����"+path);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * ����Topic
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
     * ����Ȩ�ޱ���
     * @param tms ��ǩ���б�
     */
    public void updateSecurityCode(ArrayList<TagModel> tms){
        String securityCode=securityCodeTfd.getText();
        //�����ж�securityCode��A��Z����ĸ��*������
        if (!securityCode.equals("") && Util.isSecurityCode(securityCode)){
            for (TagModel tm : tms) {
                if (!tm.getSecurityCode().equals("") && Util.isSecurityCode(tm.getSecurityCode())){
                    tm.setSecurityCode(securityCode);
                }
            }
        }
    }


    /**
     * ����Tag��
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
     * ����AlarmTag��
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
     * ����ǩ�鱣�浽CSV�ļ�
     * @param tms ��ǩ��
     * @param ownerWindow
     */
    public void saveTagCsvAction(ArrayList<TagModel> tms, Window ownerWindow){

        File selectedFile=fileChooser2.showSaveDialog(ownerWindow);
        if (selectedFile!=null){
            try{
                String path=selectedFile.toURI().toURL().toExternalForm().substring(5);
                TagSheet.saveTagCsvFile(path,tms);
                appendLog("����SE Tag��񡪡�"+path);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    /**
     * �������鱣�浽CSV�ļ�
     * @param ams ������
     * @param ownerWindow
     */
    public void saveAlarmCsvAction(ArrayList<AlarmModel> ams, Window ownerWindow){
        File selectedFile=fileChooser2.showSaveDialog(ownerWindow);
        if (selectedFile!=null){
            try{
                String path=selectedFile.toURI().toURL().toExternalForm().substring(5);
                TagSheet.saveAlarmCsvFile(path,ams);
                appendLog("����SE Alarm��񡪡�"+path);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * ����Ϣ�ı����з�����Ϣ
     * @param message ��Ҫ���͵��ı���Ϣ
     */
    public void appendLog(String message){
        messageArea.appendText(Util.localDateToStr()+" "+Util.LocalTimeToStr()+"����" +message+"\n");
    }

    public FileChooser getFileChooser1() {
        return fileChooser1;
    }

    public FileChooser getFileChooser2() {
        return fileChooser2;
    }
}
