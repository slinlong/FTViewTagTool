package com.slinlong.util;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName Util
 * @Description TODO
 * @Author z2006
 * @Date 2020/8/10 8:02
 * @Version 1.0
 **/
public class Util {

    public static String getFileNameOld(String fileName){
        String fn=Util.class.getClassLoader().getResource(fileName).getPath();
        return  fn;
    }

    public static String getFileName(String fileName){
        URL path=new Object(){
            public URL getPath(){
                return this.getClass().getClassLoader().getResource(fileName);
            }
        }.getPath();
        return path.toString();
    }

    public static String numberFormat(Number d,int n){
        String u="#";
        for(int i=0;i<n;i++){
            if (i==0) {
                u=u+".0";
            }else {
                u=u+"0";
            }
        }
        DecimalFormat df=new DecimalFormat(u);
        return df.format(d);
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static String nowTimeStr(){
        LocalTime rightNow=LocalTime.now();
        String rightNowStr= DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(rightNow);
        return rightNowStr;
    }

    /**
     * 返回LocalDate类型的格式化字符串：yyyyMMdd
     * @param date LoadDate类型
     * @return 格式化后的字符串
     */
    public static String localDateToStr(LocalDate date){
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static String localDateToStr(){
        LocalDate date=LocalDate.now();
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }


    public static String LocalTimeToStr(LocalTime time){
        return time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public static String LocalTimeToStr(){
        LocalTime time=LocalTime.now();
        return time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }


    public static Boolean isSecurityCode(String code){
        Boolean codeBool=false;

        if(code.length()!=1){
            codeBool=false;
        }else{
            if (
                    code.equals("A") ||
                            code.equals("B") ||
                            code.equals("C") ||
                            code.equals("D") ||
                            code.equals("E") ||
                            code.equals("F") ||
                            code.equals("G") ||
                            code.equals("H") ||
                            code.equals("I") ||
                            code.equals("J") ||
                            code.equals("K") ||
                            code.equals("M") ||
                            code.equals("N") ||
                            code.equals("O") ||
                            code.equals("P") ||
                            code.equals("Q") ||
                            code.equals("R") ||
                            code.equals("S") ||
                            code.equals("T") ||
                            code.equals("U") ||
                            code.equals("V") ||
                            code.equals("W") ||
                            code.equals("X") ||
                            code.equals("Y") ||
                            code.equals("Z") ||
                            code.equals("*")
            ){
                codeBool=true;
            }
        }
        return codeBool;
    }


}
