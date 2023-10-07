package com.slinlong.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;

/**
 * @ClassName ExcelUtil
 * @Description TODO
 * @Author z2006
 * @Date 2020/8/10 7:55
 * @Version 1.0
 **/
public class ExcelUtil {


    /**
     * 打开Excel文件，并返回WorkBook对象
     * @param filePath
     * @return
     */
    public static HSSFWorkbook getExcel(String filePath){
        String path=filePath;
        HSSFWorkbook wb=null;
        try {
            InputStream is=new FileInputStream(path);
            POIFSFileSystem pfs=new POIFSFileSystem(is);
            wb=new HSSFWorkbook(pfs);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    public static String getCellValueStr(Cell cell){
        String value=null;
        if (cell==null){
            value="";
        }else {
            switch (cell.getCellType()){
                case BOOLEAN:
                    value=Util.numberFormat(cell.getBooleanCellValue()?1:0,0);
                    break;
                case NUMERIC:
                    value=Util.numberFormat(cell.getNumericCellValue(),0);
                    break;
                default:
                    value=cell.getStringCellValue();
            }
        }
        return value;
    }

    public static String getCellValueStr(Cell cell,int point){
        String value=null;
        if (cell==null){
            value="";
        }else {
            switch (cell.getCellType()){
                case BOOLEAN:
                    value=Util.numberFormat(cell.getBooleanCellValue()?1:0,0);
                    break;
                case NUMERIC:
                    value=Util.numberFormat(cell.getNumericCellValue(),point);
                    break;
                default:
                    value=cell.getStringCellValue();
            }
        }
        return value;
    }

    public static void outExcel(Workbook wb, String filePath){
        String path=filePath;
        FileOutputStream fos= null;
        try {
            fos = new FileOutputStream(path);
            wb.write(fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
