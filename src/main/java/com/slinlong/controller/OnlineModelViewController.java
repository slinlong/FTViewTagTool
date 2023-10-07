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

    /**Table 1 �ṹ���**/
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

    /**Table2 �����**/
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

    //�ṹ��ɹ۲����
    private ObservableList<ExcelModelProperty> tagStruct= FXCollections.observableArrayList();
    //����ɹ۲����
    private ObservableList<ExcelModelProperty> tagGroup=FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MainApp.controllers.put(this.getClass().getSimpleName(),this);
        initializePane();
        appendLog("��ӭʹ��FactoryTalk SE ��ǩ���ɹ���V4.0");
        appendLog("��ǰģʽ�����߱༭");
    }

    private void initializePane() {
        MainApp.controllers.put(this.getClass().getSimpleName(),this);
        MainViewController controller=(MainViewController)MainApp.controllers.get(MainViewController.class.getSimpleName());

        //��ʼ�����-Table1
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

        //��ʼ�����-Table2
        tableView2.setItems(tagGroup);
        TableView.TableViewSelectionModel<ExcelModelProperty> tsm2=tableView2.getSelectionModel();
        tsm2.setSelectionMode(SelectionMode.MULTIPLE);
        tagNameCol2.setCellValueFactory(cellData->cellData.getValue().tagNameProperty());
        tagNameCol2.setCellFactory(TextFieldTableCell.<ExcelModelProperty>forTableColumn());
        tagDescriptionCol2.setCellValueFactory(cellData->cellData.getValue().tagDescriptionProperty());
        tagDescriptionCol2.setCellFactory(TextFieldTableCell.<ExcelModelProperty>forTableColumn());
        plcTagNameCol2.setCellValueFactory(cellData->cellData.getValue().plcTagNameProperty());
        plcTagNameCol2.setCellFactory(TextFieldTableCell.<ExcelModelProperty>forTableColumn());

        //��ʼ���ļ���������
        fileChooser1.setTitle("��ģ����");
        fileChooser1.getExtensionFilters()
                .addAll(new FileChooser.ExtensionFilter("Excel File","*.xls"));
        fileChooser1.setInitialDirectory(new File(controller.getPath()));
        fileChooser2.setTitle("����Csv�ļ�");
        fileChooser2.getExtensionFilters()
                .addAll(new FileChooser.ExtensionFilter("CSV File","*.csv"));
        fileChooser2.setInitialDirectory(new File(controller.getPath()));

        //�ṹ�������ť
        newStructBtn.setOnAction(event -> newRowAction("Struct"));
        copyStructBtn.setOnAction(event -> copyRowAction("Struct"));
        delStructBtn.setOnAction(event -> delRowAction("Struct"));
        clearStructBtn.setOnAction(event -> clearRowAction("Struct"));

        //���������ť
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
            ep.setTagName("��ǩ��");
            ep.setTagDescription("��ǩ����");
            ep.setPlcTagName("PLC�ı�ǩ��");
            tagStruct.add(ep);
        } else {
            ep.setTagName("��ǩ����");
            ep.setTagDescription("��ǩ������");
            ep.setPlcTagName("PLC�ı�ǩ����");
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
     * ��Struct��Group����ExcelModel��ǩ�����б�,GroupsΪ�գ���1��ģ�壬����2��ģ��
     * @return ExcelModel��ǩ�����б�
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
     * ����AlarmTag��
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
     * ����Topic
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
     * ��Excelģ��
     * @param typeCode 1=��׼ģ�壬2=�ṹģ��
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
                appendLog("�ѵ���"+(typeCode==1?"��׼ģ��":"�ṹģ��")+"����"+path);
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
                appendLog("�ѱ���ģ���񡪡�"+ path +"������ģ��Ϊ��" + (sign?"��׼ģ��":"�ṹģ��"));
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


    public void defaultExcelModel(int type){
        tagGroup.clear();
        tagStruct.clear();
        if (type==1){
            ArrayList<ExcelModelProperty> emps=new ArrayList<ExcelModelProperty>();
            emps.add(new ExcelModelProperty("A","","","","Grid\\WZ114NWHPB\\PM\\012\\Ia","WZ11-4NWHPB ACB01���ع� A�����","[PLC_WZ114NB]PM[12].Ia"));
            emps.add(new ExcelModelProperty("A","","","","Grid\\WZ114NWHPB\\PM\\012\\Ib","WZ11-4NWHPB ACB01���ع� B�����","[PLC_WZ114NB]PM[12].Ib"));
            emps.add(new ExcelModelProperty("A","","","","Grid\\WZ114NWHPB\\PM\\012\\Ic","WZ11-4NWHPB ACB01���ع� C�����","[PLC_WZ114NB]PM[12].Ic"));
            emps.add(new ExcelModelProperty("A","","","","Grid\\WZ114NWHPB\\PM\\012\\Uab","WZ11-4NWHPB ACB01���ع� AB�ߵ�ѹ","[PLC_WZ114NB]PM[12].Uab"));
            emps.add(new ExcelModelProperty("A","","","","Grid\\WZ114NWHPB\\PM\\012\\Ubc","WZ11-4NWHPB ACB01���ع� BC�ߵ�ѹ","[PLC_WZ114NB]PM[12].Ubc"));
            emps.add(new ExcelModelProperty("A","","","","Grid\\WZ114NWHPB\\PM\\012\\Uca","WZ11-4NWHPB ACB01���ع� CA�ߵ�ѹ","[PLC_WZ114NB]PM[12].Uca"));
            emps.add(new ExcelModelProperty("A","","","","Grid\\WZ114NWHPB\\PM\\012\\kW","WZ11-4NWHPB ACB01���ع� �й�����","[PLC_WZ114NB]PM[12].kW"));
            emps.add(new ExcelModelProperty("A","","","","Grid\\WZ114NWHPB\\PM\\012\\kVar","WZ11-4NWHPB ACB01���ع� �޹�����","[PLC_WZ114NB]PM[12].kVar"));
            emps.add(new ExcelModelProperty("A","","","","Grid\\WZ114NWHPB\\PM\\012\\PF","WZ11-4NWHPB ACB01���ع� ��������","[PLC_WZ114NB]PM[12].PF"));
            emps.add(new ExcelModelProperty("A","","","","Grid\\WZ114NWHPB\\PM\\012\\HZ","WZ11-4NWHPB ACB01���ع� Ƶ��","[PLC_WZ114NB]PM[12].HZ"));
            emps.add(new ExcelModelProperty("D","4","ON","Default","Grid\\WZ114NWHPB\\PM\\012\\Comm_Fault","WZ11-4NWHPB ACB01���ع� �๦�ܱ�ͨѶ����","[PLC_WZ114NB]PM[12].Comm_Fault"));
            emps.add(new ExcelModelProperty("S","","","","Grid\\WZ114NWHPB\\PM\\012\\Name","WZ11-4NWHPB ACB01���ع�",""));
            tagStruct.addAll(emps);
        }else {
            ArrayList<ExcelModelProperty> emps1=new ArrayList<ExcelModelProperty>();
            emps1.add(new ExcelModelProperty("A","","","","Ia","A�����","Ia"));
            emps1.add(new ExcelModelProperty("A","","","","Ib","B�����","Ib"));
            emps1.add(new ExcelModelProperty("A","","","","Ic","C�����","Ic"));
            emps1.add(new ExcelModelProperty("A","","","","Uab","AB�ߵ�ѹ","Uab"));
            emps1.add(new ExcelModelProperty("A","","","","Ubc","BC�ߵ�ѹ","Ubc"));
            emps1.add(new ExcelModelProperty("A","","","","Uca","CA�ߵ�ѹ","Uca"));
            emps1.add(new ExcelModelProperty("A","","","","kW","�й�����","kW"));
            emps1.add(new ExcelModelProperty("A","","","","kVar","�޹�����","kVar"));
            emps1.add(new ExcelModelProperty("A","","","","PF","��������","PF"));
            emps1.add(new ExcelModelProperty("A","","","","HZ","Ƶ��","HZ"));
            emps1.add(new ExcelModelProperty("D","4","ON","Default","Comm_Fault","�๦�ܱ�ͨѶ����","Comm_Fault"));
            emps1.add(new ExcelModelProperty("S","","","","Name","",""));
            tagStruct.addAll(emps1);
            ArrayList<ExcelModelProperty> emps2=new ArrayList<ExcelModelProperty>();
            emps2.add(new ExcelModelProperty("","","","","Grid\\WZ114NWHPB\\PM\\012\\","WZ11-4NWHPB ACB01���ع�","[PLC_WZ114NB]PM[12]."));
            emps2.add(new ExcelModelProperty("","","","","Grid\\WZ114NWHPB\\PM\\013\\","WZ11-4NWHPB ACB02���ع�","[PLC_WZ114NB]PM[13]."));
            emps2.add(new ExcelModelProperty("","","","","Grid\\WZ114NWHPB\\PM\\014\\","WZ11-4NWHPB ACB03���ع�","[PLC_WZ114NB]PM[14]."));
            emps2.add(new ExcelModelProperty("","","","","Grid\\WZ114NWHPB\\PM\\015\\","WZ11-4NWHPB ACB04���ع�","[PLC_WZ114NB]PM[15]."));
            emps2.add(new ExcelModelProperty("","","","","Grid\\WZ114NWHPB\\PM\\016\\","WZ11-4NWHPB ACB05���ع�","[PLC_WZ114NB]PM[16]."));
            emps2.add(new ExcelModelProperty("","","","","Grid\\WZ114NWHPB\\PM\\017\\","WZ11-4NWHPB ACB06���ع�","[PLC_WZ114NB]PM[17]."));
            emps2.add(new ExcelModelProperty("","","","","Grid\\WZ114NWHPB\\PM\\018\\","WZ11-4NWHPB ACB07���ع�","[PLC_WZ114NB]PM[18]."));
            emps2.add(new ExcelModelProperty("","","","","Grid\\WZ114NWHPB\\PM\\019\\","WZ11-4NWHPB ACB08���ع�","[PLC_WZ114NB]PM[19]."));
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
