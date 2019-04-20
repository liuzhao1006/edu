package com.lz.toy.common.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class ExcelUtil {
    /**
     * 将Excel文件转化为List
     * @param file
     * @return
     * @throws Exception
     */
    public static List readExcelToList(String file) throws Exception{
      /*File files = new File ("ld.xlsx");
      //下面两个方法拿到的路径为E:\workspace\framework_web\ld.xlsx,后面的解析会报找不到改文件
        String path1 = files.getAbsolutePath();
        String path2 = files.getCanonicalPath();
        //获取不到路径
        URL file1 = this.getClass().getClassLoader().getResource("ld.xlsx");*/
        List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        Sheet sheet = readExcel(file);
        int columnlen=sheet.getRow(0).getLastCellNum();
        for (Row row : sheet) {
            ArrayList<String> rowList = new ArrayList<String>();
            int rou = row.getRowNum();
            if (rou == 0) {
                continue;
            }
            for(int i=0;i<columnlen;i++){
                if(row.getCell(i)!=null) {
                    try {
                        String  cel = row.getCell(i).getStringCellValue();
                        rowList.add(cel);
                    }catch (Exception e){
                        double cell = row.getCell(i).getNumericCellValue();
                        rowList.add(String.valueOf(cell));
                    }
                }else{
                    rowList.add("");
                }
            }
            list.add(rowList);
        }
        return list;
    }

    /**
     * 读取Excel文件
     * @param file
     * @return
     * @throws Exception
     */
    public static Sheet readExcel(String file) throws Exception{
        List<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();
        if(file.isEmpty()){
            throw new Exception("文件为空");
        }
        File tempFile =new File( file.trim());
        //获取文件名
        String fileName = tempFile.getName();
        FileInputStream is = new FileInputStream(tempFile);
        /*InputStream is = file.getInputStream();
        String fileName = file.getOriginalFilename();*/
        //根据版本选择创建Workbook的方式
        Workbook wb = null;
        Sheet sheet = null;
        if(isExcel2007(fileName)){
            wb = new XSSFWorkbook(is);
            sheet = wb.getSheetAt(0);
        }else if(isExcel2003(fileName)){
            wb = new HSSFWorkbook(is);
            sheet = wb.getSheetAt(0);
        }else{
            throw new Exception("文件格式不正确");
        }
        return sheet;
    }

    /**
     * 2003年Excel格式校验
     * @param filePath
     * @return
     * @throws Exception
     */
    private static boolean isExcel2003(String filePath) throws Exception  {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }
    /**
     * 2007年Excel格式校验
     * @param filePath
     * @return
     * @throws Exception
     */
    private static boolean isExcel2007(String filePath) throws Exception {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    /**
     * 将String类转化为Date类型
     * @param s
     * @return
     * @throws Exception
     */
    public static Date toDate(String s) throws Exception{
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        Date date =  formatter.parse(s);
        return date;
    }

    /**
     * 检验日期是否合法
     * @param date
     * @return
     * @throws Exception
     */
    public static Boolean ckDate(String date)throws Exception{
        Pattern p = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        return p.matcher(date).matches();
    }
}
