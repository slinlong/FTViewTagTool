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
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName OnlineModelViewController
 * @Description TODO
 * @Author z2006
 * @Date 2020/8/10 14:55
 * @Version 1.0
 **/
public class OnlineModelViewController implements Initializable {

    /**Table 1 结构体表**/
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

    /**Table2 列组表**/
    @FXML
    private TableView<ExcelModelProperty> tableView2;
    @FXML
    private TableColumn<ExcelModelProperty,String> tagNameCol2;
    @FXML
    private TableColumn<ExcelModelProperty,String> tagDescriptionCol2;
    @FXML
    private TableColumn<ExcelModelProperty,String> plcTagNameCol2;

    @FXML
    private TextField topicTfd;
    @FXML
    private TextField securityCodeTfd;

    @FXML
    private Button outputTagTableBtn;
    @FXML
    private Button outputAlarmTableBtn;

    @FXML
    private Button newStructBtn;
    @FXML
    private Button copyStructBtn;
    @FXML
    private Button delStructBtn;
    @FXML
    private Button clearStructBtn;


    @FXML
    private Button newGroupBtn;
    @FXML
    private Button copyGroupBtn;
    @FXML
    private Button delGroupBtn;
    @FXML
    private Button clearGroupBtn;

    @FXML
    private Button openExcelBtn1;
    @FXML
    private Button openExcelBtn2;

    @FXML
    private Button saveModelBtn;

    @FXML
    private Button generateDefaultModel1Btn;
    @FXML
    private Button generateDefaultModel2Btn;

    @FXML
    private CheckBox isV10Cbx;


    @FXML
    private TextArea messageArea;

    private FileChooser fileChooser1=new FileChooser();
    private FileChooser fileChooser2=new FileChooser();

    //结构体可观察对象
    private ObservableList<ExcelModelProperty> tagStruct= FXCollections.observableArrayList();
    //列组可观察对象
    private ObservableList<ExcelModelProperty> tagGroup=FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MainApp.controllers.put(this.getClass().getSimpleName(),this);
        initializePane();
        appendLog("欢迎使用FactoryTalk SE 标签生成工具V4.0");
        appendLog("当前模式：在线编辑");
    }

    private void initializePane() {
        MainApp.controllers.put(this.getClass().getSimpleName(),this);
        MainViewController controller=(MainViewController)MainApp.controllers.get(MainViewController.class.getSimpleName());

        //初始化表格-Table1
        tableView.setItems(tagStruct);
        TableView.TableViewSelectionModel<ExcelModelProperty> tsm=tableView.getSelectionModel();
        tsm.setSelectionMode(SelectionMode.MULTIPLE);
        tagTypeCol.setCellValueFactory(cellData->cellData.getValue().tagTypeProperty());
        tagTypeCol.setCellFactory(ComboBoxTableCell.<ExcelModelProperty,String>forTableColumn("D","A","S"));
        alarmLevelCol.setCellValueFactory(cellData->cellData.getValue().alarmLevelProperty());
        alarmLevelCol.setCellFactory(ComboBoxTableCell.<ExcelModelProperty,String>forTableColumn("1","2","3","4","5","6","7","8"));
        alarmClassifyCol.setCellValueFactory(cellData->cellData.getValue().alarmClassifyProperty());
        alarmClassifyCol.setCellFactory(ComboBoxTableCell.<ExcelModelProperty,String>forTableColumn("Default","Important"));
        alarmTypeCol.setCellValueFactory(cellData->cellData.getValue().alarmTypeProperty());
        alarmTypeCol.setCellFactory(ComboBoxTableCell.<ExcelModelProperty,String>forTableColumn("ON","OFF","COS","COSON","COSOFF"));
        tagNameCol.setCellValueFactory(cellData->cellData.getValue().tagNameProperty());
        tagNameCol.setCellFactory(TextFieldTableCell.<ExcelModelProperty>forTableColumn());
        tagDescriptionCol.setCellValueFactory(cellData->cellData.getValue().tagDescriptionProperty());
        tagDescriptionCol.setCellFactory(TextFieldTableCell.<ExcelModelProperty>forTableColumn());
        plcTagNameCol.setCellValueFactory(cellData->cellData.getValue().plcTagNameProperty());
        plcTagNameCol.setCellFactory(TextFieldTableCell.<ExcelModelProperty>forTableColumn());

        //初始化表格-Table2
        tableView2.setItems(tagGroup);
        TableView.TableViewSelectionModel<ExcelModelProperty> tsm2=tableView2.getSelectionModel();
        tsm2.setSelectionMode(SelectionMode.MULTIPLE);
        tagNameCol2.setCellValueFactory(cellData->cellData.getValue().tagNameProperty());
        tagNameCol2.setCellFactory(TextFieldTableCell.<ExcelModelProperty>forTableColumn());
        tagDescriptionCol2.setCellValueFactory(cellData->cellData.getValue().tagDescriptionProperty());
        tagDescriptionCol2.setCellFactory(TextFieldTableCell.<ExcelModelProperty>forTableColumn());
        plcTagNameCol2.setCellValueFactory(cellData->cellData.getValue().plcTagNameProperty());
        plcTagNameCol2.setCellFactory(TextFieldTableCell.<ExcelModelProperty>forTableColumn());

        //初始化文件操作弹窗
        fileChooser1.setTitle("打开模板表格");
        fileChooser1.getExtensionFilters()
                .addAll(new FileChooser.ExtensionFilter("Excel File","*.xls"));
        fileChooser1.setInitialDirectory(new File(controller.getPath()));
        fileChooser2.setTitle("导出Csv文件");
        fileChooser2.getExtensionFilters()
                .addAll(new FileChooser.ExtensionFilter("CSV File","*.csv"));
        fileChooser2.setInitialDirectory(new File(controller.getPath()));

        //结构体操作按钮
        newStructBtn.setOnAction(event -> newRowAction("Struct"));
        copyStructBtn.setOnAction(event -> copyRowAction("Struct"));
        delStructBtn.setOnAction(event -> delRowAction("Struct"));
        clearStructBtn.setOnAction(event -> clearRowAction("Struct"));

        //列组操作按钮
        newGroupBtn.setOnAction(event -> newRowAction("Group"));
        copyGroupBtn.setOnAction(event -> copyRowAction("Group"));
        delGroupBtn.setOnAction(event -> delRowAction("Group"));
        clearGroupBtn.setOnAction(event -> clearRowAction("Group"));

        outputTagTableBtn.setOnAction(event -> outputTagTableAction());
        outputAlarmTableBtn.setOnAction(event -> outputAlarmTableAction());

        openExcelBtn1.setOnAction(event -> openExcelAction(1));
        openExcelBtn2.setOnAction(event -> openExcelAction(2));

        saveModelBtn.setOnAction(event -> saveModelAction());

        generateDefaultModel1Btn.setOnAction(event -> defaultExcelModel(1));
        generateDefaultModel2Btn.setOnAction(event -> defaultExcelModel(2));

        isV10Cbx.setSelected(true);

    }

    public void newRowAction(String sign){
        ExcelModelProperty ep=new ExcelModelProperty();
        if (sign.equals("Struct")){
            ep.setTagType("D");
            ep.setTagName("标签名");
            ep.setTagDescription("标签描述");
            ep.setPlcTagName("PLC的标签名");
            tagStruct.add(ep);
        } else {
            ep.setTagName("标签组名");
            ep.setTagDescription("标签组描述");
            ep.setPlcTagName("PLC的标签组名");
            tagGroup.add(ep);
        }
    }

    public void delRowAction(String sign){
        TableView.TableViewSelectionModel<ExcelModelProperty> tsm;
        if (sign.equals("Struct")){
            tsm=tableView.getSelectionModel();
        }else {
            tsm=tableView2.getSelectionModel();
        }
        if (tsm.isEmpty()){
            return;
        }
        ObservableList<Integer> list=tsm.getSelectedIndices();
        Integer[] selectedIndices=new Integer[list.size()];
        selectedIndices=list.toArray(selectedIndices);

        Arrays.sort(selectedIndices);

        for (int i=selectedIndices.length-1;i>=0;i--){
            tsm.clearSelection(selectedIndices[i].intValue());
            if (sign.equals("Struct")){
                tableView.getItems().remove(selectedIndices[i].intValue());
            }else{
                tableView2.getItems().remove(selectedIndices[i].intValue());
            }
        }
    }

    public void copyRowAction(String sign){
        TableView.TableViewSelectionModel<ExcelModelProperty> tsm;
        if (sign.equals("Struct")){
            tsm=tableView.getSelectionModel();
        }else {
            tsm=tableView2.getSelectionModel();
        }
        if (tsm.isEmpty()){
            return;
        }
        ObservableList<Integer> list=tsm.getSelectedIndices();
        Integer[] selectedIndices=new Integer[list.size()];
        selectedIndices=list.toArray(selectedIndices);

        Arrays.sort(selectedIndices);

        for (int i=selectedIndices.length-1;i>=0;i--){
            tsm.clearSelection(selectedIndices[i].intValue());
            if (sign.equals("Struct")){
                ExcelModelProperty emp=new ExcelModelProperty();
                emp.copy(tagStruct.get(selectedIndices[i].intValue()));
                tableView.getItems().add(emp);
            }else{
                ExcelModelProperty emp=new ExcelModelProperty();
                emp.copy(tagGroup.get(selectedIndices[i].intValue()));
                tableView2.getItems().add(emp);
            }
        }
    }

    public void clearRowAction(String sign){
        if (sign.equals("Struct")){
            tagStruct.clear();
        } else {
            tagGroup.clear();
        }
    }

    /**
     * 由Struct和Group生成ExcelModel标签对象列表,Groups为空，则按1类模板，否则按2类模板
     * @return ExcelModel标签对象列表
     */
    public ArrayList<ExcelModel> generateExcelModels(){
        ArrayList<ExcelModel> datas=new ArrayList<>();
        if (tagGroup.size()>0){
            tagGroup.forEach(group->{
                tagStruct.forEach(struct->{
                    ExcelModel em=new ExcelModel();
                    em.setTagType(struct.getTagType());
                    em.setTagName(group.getTagName() + struct.getTagName());
                    em.setTagDescription(group.getTagDescription()+struct.getTagDescription());
                    em.setPlcTagName(group.getPlcTagName()+struct.getPlcTagName());
                    em.setAlarmType(struct.getAlarmType());
                    em.setAlarmLevel(struct.getAlarmLevel());
                    em.setAlarmClassify(struct.getAlarmClassify());
                    datas.add(em);
                });
            });
        }else{
            tagStruct.forEach(struct->{
                ExcelModel em=new ExcelModel();
                em.setTagType(struct.getTagType());
                em.setTagName(struct.getTagName());
                em.setTagDescription(struct.getTagDescription());
                em.setPlcTagName(struct.getPlcTagName());
                em.setAlarmType(struct.getAlarmType());
                em.setAlarmLevel(struct.getAlarmLevel());
                em.setAlarmClassify(struct.getAlarmClassify());
                datas.add(em);
            });
        }
        return datas;
    }

    public void outputTagTableAction(){
        ArrayList<ExcelModel> datas=generateExcelModels();
        ArrayList<TagModel> tms=new ArrayList<>();
        updateTopic(datas);
        List<TagFolder> folders= TagSheet.generateTagFolder(datas);
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
        ArrayList<ExcelModel> datas=generateExcelModels();
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
     * 更新Topic
     */
    public void updateTopic(ArrayList<ExcelModel> datas){
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
     * 打开Excel模板
     * @param typeCode 1=标准模板，2=结构模板
     */
    public void openExcelAction(int typeCode){
        tagStruct.clear();
        tagGroup.clear();
        ArrayList<ExcelModel> structDatas=new ArrayList<>();
        ArrayList<ExcelModel> groupDatas=new ArrayList<>();
        File selectedFile=fileChooser1.showOpenDialog(openExcelBtn1.getScene().getWindow());
        if (selectedFile!=null){
            try{
                String path=selectedFile.toURI().toURL().toExternalForm().substring(5);
                if (typeCode==1) {
                    structDatas=TagSheet.generateData(path,1);
                }
                if (typeCode==2) {
                    structDatas=TagSheet.generateData(path,3);
                    groupDatas=TagSheet.generateData(path,4);
                }

                tagStruct.addAll(TagSheet.generateDataProperty(structDatas));
                tagGroup.addAll(TagSheet.generateDataProperty(groupDatas));
                appendLog("已导入"+(typeCode==1?"标准模板":"结构模板")+"——"+path);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void saveModelAction(){
        File selectedFile=fileChooser1.showSaveDialog(saveModelBtn.getScene().getWindow());
        if (selectedFile!=null){
            try {
                String path=selectedFile.toURI().toURL().toExternalForm().substring(5);
                boolean sign=TagSheet.saveModelXlsFile(path,tagStruct,tagGroup);
                appendLog("已保存模板表格——"+ path +"——本模板为：" + (sign?"标准模板":"结构模板"));
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


    public void defaultExcelModel(int type){
        tagGroup.clear();
        tagStruct.clear();
        if (type==1){
            ArrayList<ExcelModelProperty> emps=new ArrayList<ExcelModelProperty>();
            emps.add(new ExcelModelProperty("A","","","","Grid\\WZ114NWHPB\\PM\\012\\Ia","WZ11-4NWHPB ACB01开关柜 A相电流","[PLC_WZ114NB]PM[12].Ia"));
            emps.add(new ExcelModelProperty("A","","","","Grid\\WZ114NWHPB\\PM\\012\\Ib","WZ11-4NWHPB ACB01开关柜 B相电流","[PLC_WZ114NB]PM[12].Ib"));
            emps.add(new ExcelModelProperty("A","","","","Grid\\WZ114NWHPB\\PM\\012\\Ic","WZ11-4NWHPB ACB01开关柜 C相电流","[PLC_WZ114NB]PM[12].Ic"));
            emps.add(new ExcelModelProperty("A","","","","Grid\\WZ114NWHPB\\PM\\012\\Uab","WZ11-4NWHPB ACB01开关柜 AB线电压","[PLC_WZ114NB]PM[12].Uab"));
            emps.add(new ExcelModelProperty("A","","","","Grid\\WZ114NWHPB\\PM\\012\\Ubc","WZ11-4NWHPB ACB01开关柜 BC线电压","[PLC_WZ114NB]PM[12].Ubc"));
            emps.add(new ExcelModelProperty("A","","","","Grid\\WZ114NWHPB\\PM\\012\\Uca","WZ11-4NWHPB ACB01开关柜 CA线电压","[PLC_WZ114NB]PM[12].Uca"));
            emps.add(new ExcelModelProperty("A","","","","Grid\\WZ114NWHPB\\PM\\012\\kW","WZ11-4NWHPB ACB01开关柜 有功功率","[PLC_WZ114NB]PM[12].kW"));
            emps.add(new ExcelModelProperty("A","","","","Grid\\WZ114NWHPB\\PM\\012\\kVar","WZ11-4NWHPB ACB01开关柜 无功功率","[PLC_WZ114NB]PM[12].kVar"));
            emps.add(new ExcelModelProperty("A","","","","Grid\\WZ114NWHPB\\PM\\012\\PF","WZ11-4NWHPB ACB01开关柜 功率因数","[PLC_WZ114NB]PM[12].PF"));
            emps.add(new ExcelModelProperty("A","","","","Grid\\WZ114NWHPB\\PM\\012\\HZ","WZ11-4NWHPB ACB01开关柜 频率","[PLC_WZ114NB]PM[12].HZ"));
            emps.add(new ExcelModelProperty("D","4","ON","Default","Grid\\WZ114NWHPB\\PM\\012\\Comm_Fault","WZ11-4NWHPB ACB01开关柜 多功能表通讯故障","[PLC_WZ114NB]PM[12].Comm_Fault"));
            emps.add(new ExcelModelProperty("S","","","","Grid\\WZ114NWHPB\\PM\\012\\Name","WZ11-4NWHPB ACB01开关柜",""));
            tagStruct.addAll(emps);
        }else {
            ArrayList<ExcelModelProperty> emps1=new ArrayList<ExcelModelProperty>();
            emps1.add(new ExcelModelProperty("A","","","","Ia","A相电流","Ia"));
            emps1.add(new ExcelModelProperty("A","","","","Ib","B相电流","Ib"));
            emps1.add(new ExcelModelProperty("A","","","","Ic","C相电流","Ic"));
            emps1.add(new ExcelModelProperty("A","","","","Uab","AB线电压","Uab"));
            emps1.add(new ExcelModelProperty("A","","","","Ubc","BC线电压","Ubc"));
            emps1.add(new ExcelModelProperty("A","","","","Uca","CA线电压","Uca"));
            emps1.add(new ExcelModelProperty("A","","","","kW","有功功率","kW"));
            emps1.add(new ExcelModelProperty("A","","","","kVar","无功功率","kVar"));
            emps1.add(new ExcelModelProperty("A","","","","PF","功率因数","PF"));
            emps1.add(new ExcelModelProperty("A","","","","HZ","频率","HZ"));
            emps1.add(new ExcelModelProperty("D","4","ON","Default","Comm_Fault","多功能表通讯故障","Comm_Fault"));
            emps1.add(new ExcelModelProperty("S","","","","Name","",""));
            tagStruct.addAll(emps1);
            ArrayList<ExcelModelProperty> emps2=new ArrayList<ExcelModelProperty>();
            emps2.add(new ExcelModelProperty("","","","","Grid\\WZ114NWHPB\\PM\\012\\","WZ11-4NWHPB ACB01开关柜","[PLC_WZ114NB]PM[12]."));
            emps2.add(new ExcelModelProperty("","","","","Grid\\WZ114NWHPB\\PM\\013\\","WZ11-4NWHPB ACB02开关柜","[PLC_WZ114NB]PM[13]."));
            emps2.add(new ExcelModelProperty("","","","","Grid\\WZ114NWHPB\\PM\\014\\","WZ11-4NWHPB ACB03开关柜","[PLC_WZ114NB]PM[14]."));
            emps2.add(new ExcelModelProperty("","","","","Grid\\WZ114NWHPB\\PM\\015\\","WZ11-4NWHPB ACB04开关柜","[PLC_WZ114NB]PM[15]."));
            emps2.add(new ExcelModelProperty("","","","","Grid\\WZ114NWHPB\\PM\\016\\","WZ11-4NWHPB ACB05开关柜","[PLC_WZ114NB]PM[16]."));
            emps2.add(new ExcelModelProperty("","","","","Grid\\WZ114NWHPB\\PM\\017\\","WZ11-4NWHPB ACB06开关柜","[PLC_WZ114NB]PM[17]."));
            emps2.add(new ExcelModelProperty("","","","","Grid\\WZ114NWHPB\\PM\\018\\","WZ11-4NWHPB ACB07开关柜","[PLC_WZ114NB]PM[18]."));
            emps2.add(new ExcelModelProperty("","","","","Grid\\WZ114NWHPB\\PM\\019\\","WZ11-4NWHPB ACB08开关柜","[PLC_WZ114NB]PM[19]."));
            tagGroup.addAll(emps2);
        }
    }


    public FileChooser getFileChooser1() {
        return fileChooser1;
    }

    public FileChooser getFileChooser2() {
        return fileChooser2;
    }
}
