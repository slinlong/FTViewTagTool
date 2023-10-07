package com.slinlong.function;

import com.slinlong.model.*;
import com.slinlong.util.ExcelUtil;
import javafx.collections.ObservableList;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName TagSheet
 * @Description ��ǩ�������ط���
 * @Author z2006
 * @Date 2020/8/9 20:26
 * @Version 1.0
 **/
public class TagSheet {

    /**
     * ͨ��Excel�ļ���ȡExcelģ���б����(typeCode=1Ϊ��׼ģ�壬typeCode=2Ϊ�ṹģ��)
     * @param path Excel�ļ�·��
     * @param typeCode ���ͱ���
     * @return Excelģ���б����
     */
    public static ArrayList<ExcelModel> generateData(String path,int typeCode){
        ArrayList<ExcelModel> ems=new ArrayList<>();
        switch (typeCode){
            case 1:
                ems=generateData1(path);
                break;
            case 2:
                ems=generateData2(path);
                break;
            case 3:
                ems=generateData3(path);
                break;
            case 4:
                ems=generateData4(path);
                break;
            default:
        }
        return ems;
    }

    /**
     * ͨ��Excel�ļ���1��ģ�壩��ȡExcelģ���б����
     * @param path Excel·��
     * @return Excelģ���б����
     */
    public static ArrayList<ExcelModel> generateData1(String path){
        ArrayList<ExcelModel> datas=new ArrayList<ExcelModel>();

        Workbook wb= ExcelUtil.getExcel(path);
        Sheet sheet=wb.getSheetAt(0);
        int count=sheet.getLastRowNum();

        for (int i=1;i<count+1;i++){
            Row row=sheet.getRow(i);
            ExcelModel em=new ExcelModel();
            em.setTagType(ExcelUtil.getCellValueStr(row.getCell(1)));
            em.setAlarmLevel(ExcelUtil.getCellValueStr(row.getCell(2)));
            em.setAlarmType(ExcelUtil.getCellValueStr(row.getCell(3)));
            em.setAlarmClassify(ExcelUtil.getCellValueStr(row.getCell(4)));
            em.setTagName(ExcelUtil.getCellValueStr(row.getCell(5)));
            em.setPlcTagName(ExcelUtil.getCellValueStr(row.getCell(6)));
            em.setTagDescription(ExcelUtil.getCellValueStr(row.getCell(7)));
            datas.add(em);
        }
        return datas;
    }

    /**
     * ͨ��Excel�ļ���2��ģ�壩��ȡExcelģ���б����
     * @param path Excel·��
     * @return Excelģ���б����
     */
    public static ArrayList<ExcelModel> generateData2(String path){
        ArrayList<ExcelModel> datas=new ArrayList<ExcelModel>();

        Workbook wb= ExcelUtil.getExcel(path);
        Sheet sheet=wb.getSheetAt(0);
        int count=sheet.getLastRowNum();

        int m=1;
        Row row1=sheet.getRow(m);
        while(m<count+1 && !ExcelUtil.getCellValueStr(row1.getCell(5)).equals("")){
            int n=1;
            Row row2=sheet.getRow(n);
            while (n<count+1 && !ExcelUtil.getCellValueStr(row2.getCell(1)).equals("")) {
                ExcelModel em=new ExcelModel();
                em.setTagType(ExcelUtil.getCellValueStr(row2.getCell(1)));
                em.setAlarmLevel(ExcelUtil.getCellValueStr(row2.getCell(2)));
                em.setAlarmType(ExcelUtil.getCellValueStr(row2.getCell(3)));
                em.setAlarmClassify(ExcelUtil.getCellValueStr(row2.getCell(4)));
                String tagName=ExcelUtil.getCellValueStr(row1.getCell(5))+ExcelUtil.getCellValueStr(row2.getCell(6));
                em.setTagName(tagName);

                String plcTagName=ExcelUtil.getCellValueStr(row1.getCell(7))+ExcelUtil.getCellValueStr(row2.getCell(8));
                em.setPlcTagName(plcTagName);

                String description=ExcelUtil.getCellValueStr(row1.getCell(9))+" "+ExcelUtil.getCellValueStr(row2.getCell(10));
                em.setTagDescription(description);

                datas.add(em);

                n=n+1;
                row2=sheet.getRow(n);
            }
            m=m+1;
            row1=sheet.getRow(m);
        }
        return datas;
    }

    /**
     * ͨ��Excel�ļ���3��ģ��,�ṹ����ȡExcelģ���б����
     * @param path Excel·��
     * @return Excelģ���б����
     */
    public static ArrayList<ExcelModel> generateData3(String path){
        ArrayList<ExcelModel> datas=new ArrayList<ExcelModel>();

        Workbook wb= ExcelUtil.getExcel(path);
        Sheet sheet=wb.getSheetAt(0);
        int count=sheet.getLastRowNum();

        for (int i=1;i<count+1;i++){
            Row row=sheet.getRow(i);
            String cell1=ExcelUtil.getCellValueStr(row.getCell(1));
            String cell2=ExcelUtil.getCellValueStr(row.getCell(2));
            String cell3=ExcelUtil.getCellValueStr(row.getCell(3));
            String cell4=ExcelUtil.getCellValueStr(row.getCell(4));
            String cell6=ExcelUtil.getCellValueStr(row.getCell(6));
            String cell8=ExcelUtil.getCellValueStr(row.getCell(8));
            String cell10=ExcelUtil.getCellValueStr(row.getCell(10));
            if(!cell1.equals("") || !cell2.equals("") || !cell3.equals("") || !cell6.equals("")){
                ExcelModel em=new ExcelModel();
                em.setTagType(cell1);
                em.setAlarmLevel(cell2);
                em.setAlarmType(cell3);
                em.setAlarmClassify(cell4);
                em.setTagName(cell6);
                em.setPlcTagName(cell8);
                em.setTagDescription(cell10);
                datas.add(em);
            }
        }
        return datas;
    }


    /**
     * ͨ��Excel�ļ���4��ģ��,���飩��ȡExcelģ���б����
     * @param path Excel·��
     * @return Excelģ���б����
     */
    public static ArrayList<ExcelModel> generateData4(String path){
        ArrayList<ExcelModel> datas=new ArrayList<ExcelModel>();

        Workbook wb= ExcelUtil.getExcel(path);
        Sheet sheet=wb.getSheetAt(0);
        int count=sheet.getLastRowNum();

        for (int i=1;i<count+1;i++){
            Row row=sheet.getRow(i);
            String cell5=ExcelUtil.getCellValueStr(row.getCell(5));
            String cell7=ExcelUtil.getCellValueStr(row.getCell(7));
            String cell9=ExcelUtil.getCellValueStr(row.getCell(9));

            if (!cell5.equals("") ||  !cell7.equals("") || !cell9.equals("") ){
                ExcelModel em=new ExcelModel();
                em.setTagName(cell5);
                em.setPlcTagName(cell7);
                em.setTagDescription(cell9);
                datas.add(em);
            }
        }
        return datas;
    }


    /**
     * Excelģ�������תΪProperty��ʽ
     * @param ems Excelģ�������
     * @return Property��ʽ������
     */
    public static List<ExcelModelProperty> generateDataProperty(ArrayList<ExcelModel> ems){
        ArrayList<ExcelModelProperty> emps=
                (ArrayList<ExcelModelProperty>)ems.stream().map(em->new ExcelModelProperty(em)).collect(Collectors.toList());
        return emps;
    }


    /**
     * ͨ��Excelģ������ȡ��ǩ�ļ���
     * @param datas Excelģ����
     * @return �ļ����б����
     */
    public static List<TagFolder> generateTagFolder(ArrayList<ExcelModel> datas){
        ArrayList<String> strs=new ArrayList<>();
        for (ExcelModel em : datas ) {
            String s=em.getTagName();
            while(s.contains("\\")){
                int index=s.lastIndexOf("\\");
                s=s.substring(0,index);
                strs.add(s);
            }
        }
        ArrayList<TagFolder> folders=(ArrayList<TagFolder>)strs.stream()
                .distinct().sorted()
                .map(s -> new TagFolder(s))
                .collect(Collectors.toList());
        return folders;
    }


    /**
     * ͨ��Excelģ������ȡ��ǩ
     * @param datas Excelģ����
     * @return ��ǩ�б����
     */
    public static List<SETag> generateTagModel(ArrayList<ExcelModel> datas){
        ArrayList<SETag> tags=new ArrayList<>();

        for (ExcelModel em : datas) {
            if (em.getTagType().equals("D")){
                SETag tag=new DigtalTag();
                ((DigtalTag) tag).setTagName(em.getTagName());
                ((DigtalTag) tag).setTagDescription(em.getTagDescription());
                ((DigtalTag) tag).setAddress(em.getPlcTagName());
                ((DigtalTag) tag).setSecurityCode("*");
                tags.add(tag);
            }else if(em.getTagType().equals("A")){
                SETag tag=new AnalogTag();
                ((AnalogTag) tag).setTagName(em.getTagName());
                ((AnalogTag) tag).setTagDescription(em.getTagDescription());
                ((AnalogTag) tag).setAddress(em.getPlcTagName());
                ((AnalogTag) tag).setSecurityCode("*");
                tags.add(tag);
            }else if(em.getTagType().equals("S")){
                SETag tag=new StringTag();
                ((StringTag) tag).setTagName(em.getTagName());
                ((StringTag) tag).setTagDescription(em.getTagDescription());
                ((StringTag) tag).setAddress(em.getPlcTagName());
                ((StringTag) tag).setSecurityCode("*");
                tags.add(tag);
            }
        }
        return tags;
    }

    /**
     * ͨ��Excelģ�壬��ȡAlarm��ǩ��
     * @param datas Excelģ����
     * @return Alarmģ�ͱ�ǩ��
     */
    public static List<AlarmModel> generateAlarmModel(ArrayList<ExcelModel> datas,boolean isV10){
        ArrayList<AlarmModel> alarms=new ArrayList<>();

        for (ExcelModel em : datas ) {
            if (em.getTagType().equals("D") && !em.getAlarmType().equals("")){
                AlarmModel am=new AlarmModel(em,isV10);
                alarms.add(am);
            }
        }
        return alarms;
    }


    /**
     * ����Tag�����鵽csv�ļ�
     * @param filePath Csv�ļ�·��
     * @param tms Tag������
     */
    public static void saveTagCsvFile(String filePath,ArrayList<TagModel> tms){
        try {
            Charset charset=Charset.forName("GBK");
            File f = new File(filePath);
            Writer out = Files.newBufferedWriter(f.toPath(),charset);
            for (TagModel tm : tms ) {
                out.write(tm.toString());
                out.write("\r\n");
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * ����Alarm�����鵽csv�ļ�
     * @param filePath Csv�ļ�·��
     * @param ams Alarm������
     */
    public static void saveAlarmCsvFile(String filePath,ArrayList<AlarmModel> ams){
        try {
            Charset charset=Charset.forName("GBK");
            File f = new File(filePath);
            Writer out = Files.newBufferedWriter(f.toPath(),charset);
            for (AlarmModel am : ams ) {
                out.write(am.toString());
                out.write("\r\n");
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean saveModelXlsFile(String filePath, ObservableList<ExcelModelProperty> tagStruct,ObservableList<ExcelModelProperty> tagGroup){
        boolean sign=true;
        Workbook wb=new HSSFWorkbook();
        Sheet sheet=wb.createSheet("Sheet1");
        Row row;

        if (tagGroup.size()>0){
            sign=false;
            //�ֶ���
            row=sheet.createRow(0);
            row.createCell(0).setCellValue("���");
            row.createCell(1).setCellValue("����");
            row.createCell(2).setCellValue("�����ȼ�");
            row.createCell(3).setCellValue("��������");
            row.createCell(4).setCellValue("�������");
            row.createCell(5).setCellValue("��λ����ǩ��");
            row.createCell(6).setCellValue("��λ����ǩ");
            row.createCell(7).setCellValue("��λ����ǩ��");
            row.createCell(8).setCellValue("��λ����ǩ");
            row.createCell(9).setCellValue("������");
            row.createCell(10).setCellValue("����");
            //������
            int maxRow=tagStruct.size()>tagGroup.size()?tagStruct.size():tagGroup.size();
            for (int i=0;i<maxRow;i++){
                //������д���Rows
                row=sheet.createRow(i+1);
            }
            //�ֱ�дStruct��Group
            for (int i=0;i<tagStruct.size();i++){
                row=sheet.getRow(i+1);
                row.createCell(0).setCellValue(i+1);
                ExcelModelProperty em=tagStruct.get(i);
                row.createCell(1).setCellValue(em.getTagType());
                if (em.getAlarmLevel().equals("")){
                    row.createCell(2).setCellValue(em.getAlarmLevel());
                }else {
                    row.createCell(2).setCellValue(Integer.parseInt(em.getAlarmLevel()));
                }
                row.createCell(3).setCellValue(em.getAlarmType());
                row.createCell(4).setCellValue(em.getAlarmClassify());
                row.createCell(6).setCellValue(em.getTagName());
                row.createCell(8).setCellValue(em.getPlcTagName());
                row.createCell(10).setCellValue(em.getTagDescription());
            }
            for (int i=0;i<tagGroup.size();i++){
                row=sheet.getRow(i+1);
                ExcelModelProperty em=tagGroup.get(i);
                row.createCell(5).setCellValue(em.getTagName());
                row.createCell(7).setCellValue(em.getPlcTagName());
                row.createCell(9).setCellValue(em.getTagDescription());
            }
            sheet.setColumnWidth(5,12000);
            sheet.setColumnWidth(6,5000);
            sheet.setColumnWidth(7,12000);
            sheet.setColumnWidth(8,5000);
            sheet.setColumnWidth(9,20000);
            sheet.setColumnWidth(10,9000);

        }else{
            sign=true;
            //�ֶ���
            row=sheet.createRow(0);
            row.createCell(0).setCellValue("���");
            row.createCell(1).setCellValue("����");
            row.createCell(2).setCellValue("�����ȼ�");
            row.createCell(3).setCellValue("��������");
            row.createCell(4).setCellValue("�������");
            row.createCell(5).setCellValue("��λ����ǩ");
            row.createCell(6).setCellValue("��λ����ǩ");
            row.createCell(7).setCellValue("����");
            //������
            for (int i=0;i<tagStruct.size();i++){
                row=sheet.createRow(i+1);
                row.createCell(0).setCellValue(i+1);
                ExcelModelProperty em=tagStruct.get(i);
                row.createCell(1).setCellValue(em.getTagType());
                if (em.getAlarmLevel().equals("")){
                    row.createCell(2).setCellValue(em.getAlarmLevel());
                }else {
                    row.createCell(2).setCellValue(Integer.parseInt(em.getAlarmLevel()));
                }
                row.createCell(3).setCellValue(em.getAlarmType());
                row.createCell(4).setCellValue(em.getAlarmClassify());
                row.createCell(5).setCellValue(em.getTagName());
                row.createCell(6).setCellValue(em.getPlcTagName());
                row.createCell(7).setCellValue(em.getTagDescription());
            }
            sheet.setColumnWidth(5,15000);
            sheet.setColumnWidth(6,15000);
            sheet.setColumnWidth(7,20000);

        }
        ExcelUtil.outExcel(wb,filePath);
        return sign;
    }

}
