package com.slinlong.model;

import com.sun.org.glassfish.external.statistics.annotations.Reset;
import org.apache.poi.hssf.record.cf.Threshold;

import javax.print.attribute.standard.Severity;
import java.util.Arrays;

/**
 * @ClassName AlarmModel
 * @Description SE AlarmTag模型，对应AlarmTable
 * @Author z2006
 * @Date 2020/8/9 16:09
 * @Version 1.0
 **/
public class AlarmModel {
    private String[] fileds=new String[80];


    public AlarmModel(){
        for (int i=0;i<80;i++){
            fileds[i]="";
        }
    }

    public AlarmModel(int code){
        this();
        switch (code){
            case 0:
                fileds[0]=";###002 - THIS LINE CONTAINS VERSION INFORMATION. DO NOT REMOVE!!!";
                break;
            case -1:
                fileds[0]=";ALARMS";
                break;
            case 1:
                fileds[0]=";Analog Alarms";
                break;
            case 2:
                fileds[0]=";Digital Alarms";
                break;
            case 11:
                fileds[0]=";Analog A";
                fileds[1]="Tagname";
                fileds[2]="HandshakeTagName";
                fileds[3]="HandshakeAutoReset";
                fileds[4]="Acktagname";
                fileds[5]="AckAutoreset";
                fileds[6]="Deadbandvalue";
                fileds[7]="DeadbandType";
                fileds[8]="Outofalarmlabel";
                fileds[9]="AlarmIdentification";
                fileds[10]="Ack File Msg";
                fileds[11]="Ack Printer Message";
                fileds[12]=" Ack Message Source";
                fileds[13]="Out of Alm file msg";
                fileds[14]="Out of Alm printer msg";
                fileds[15]="Out of Alm Message Source";
                fileds[16]="Thresh1 type";
                fileds[17]="Threshold";
                fileds[18]="Label";
                fileds[19]="MessageSource";
                fileds[20]="File msg";
                fileds[21]="Printer msg";
                fileds[22]="Direction";
                fileds[23]="Severity";
                fileds[24]="Thresh2 type";
                fileds[25]="Threshold";
                fileds[26]="Label";
                fileds[27]="MessageSource";
                fileds[28]="File msg";
                fileds[29]="Printer msg";
                fileds[30]="Direction";
                fileds[31]="Severity";
                fileds[32]="Thresh3 type";
                fileds[33]="Threshold";
                fileds[34]="Label";
                fileds[35]="MessageSource";
                fileds[36]="File msg";
                fileds[37]="Printer msg";
                fileds[38]="Direction";
                fileds[39]="Severity";
                fileds[40]="Thresh4 type";
                fileds[41]="Threshold";
                fileds[42]="Label";
                fileds[43]="MessageSource";
                fileds[44]="File msg";
                fileds[45]="Printer msg";
                fileds[46]="Direction";
                fileds[47]="Severity";
                fileds[48]="Thresh5 type";
                fileds[49]="Threshold";
                fileds[50]="Label";
                fileds[51]="MessageSource";
                fileds[52]="File msg";
                fileds[53]="Printer msg";
                fileds[54]="Direction";
                fileds[55]="Severity";
                fileds[56]="Thresh6 type";
                fileds[57]="Threshold";
                fileds[58]="Label";
                fileds[59]="MessageSource";
                fileds[60]="File msg";
                fileds[61]="Printer msg";
                fileds[62]="Direction";
                fileds[63]="Severity";
                fileds[64]="Thresh7 type";
                fileds[65]="Threshold";
                fileds[66]="Label";
                fileds[67]="MessageSource";
                fileds[68]="File msg";
                fileds[69]="Printer msg";
                fileds[70]="Direction";
                fileds[71]="Severity";
                fileds[72]="Thresh8 type";
                fileds[73]="Threshold";
                fileds[74]="Label";
                fileds[75]="MessageSource";
                fileds[76]="File msg";
                fileds[77]="Printer msg";
                fileds[78]="Direction";
                fileds[79]="Severity";
                break;
            case 22:
                fileds[0]=";Digital D";
                fileds[1]="Tagname";
                fileds[2]="type";
                fileds[3]="Label";
                fileds[4]="Severity";
                fileds[5]="Message Source";
                fileds[6]="File msg";
                fileds[7]="Printer msg";
                fileds[8]="Out of alm msg source";
                fileds[9]="Out Of alm File msg";
                fileds[10]="Out of alm Printer msg";
                fileds[11]="Ack msg source";
                fileds[12]="Ack File msg";
                fileds[13]="Ack Printer msg";
                fileds[14]="Alarm Identify";
                fileds[15]="Out of alm label";
                fileds[16]="Ack Tag Name";
                fileds[17]="Ack Auto Reset";
                fileds[18]="Handshake Tag Name";
                fileds[19]="Handshake Auto Reset";
                break;
            default:
        }
    }

    public AlarmModel(ExcelModel excelModel,boolean isV10){
        this();
        fileds[0]="D";
        fileds[1]=excelModel.getTagName();
        fileds[2]=excelModel.getAlarmType();
        fileds[4]=excelModel.getAlarmLevel();
        fileds[5]="U";
        fileds[8]="U";
        fileds[11]="U";
        if(isV10) {
            fileds[17] = "N";
            fileds[19] = "N";
        }
    }



    public String[] getFileds() {
        return fileds;
    }

    public void setFileds(String[] fileds) {
        this.fileds = fileds;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        for(int i=0;i<80;i++){
            if (i<79){
                sb.append(fileds[i]).append(",");
            }else{
                sb.append(fileds[i]);
            }
        }
        return sb.toString();
    }
}

