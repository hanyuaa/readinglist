package com.manning;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Demo_7 {
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";


    public static Workbook getWorkbok(InputStream in, File file) throws IOException {
        Workbook wb = null;
        if (file.getName().endsWith(EXCEL_XLS)) { //Excel 2003
            wb = new HSSFWorkbook(in);
        } else if (file.getName().endsWith(EXCEL_XLSX)) { // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

    public static void checkExcelVaild(File file) throws Exception {
        if (!file.exists()) {
            throw new Exception("文件不存在");
        }
        if (!(file.isFile() && (file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith(EXCEL_XLSX)))) {
            throw new Exception("文件不是Excel");
        }
    }

    public static void main(String[] args) throws Exception {
        File excelFile = new File("C:\\Users\\hanyu\\Desktop\\920-resume.xlsx"); // 创建文件对象
        FileInputStream in = new FileInputStream(excelFile); // 文件流
        checkExcelVaild(excelFile);
        Workbook workbook = getWorkbok(in, excelFile);

        Sheet sheet = workbook.getSheetAt(0); // 遍历第一个Sheet

        DecimalFormat df = new DecimalFormat("#");

        List<ResumeEntity> list = new ArrayList<>();

        int count = 0;
        for (Row row : sheet) {
            if (count < 2) {
                count++;
                continue;
            }
            if (row.getCell(0).toString().equals("")) {
                return;
            }

            int end = row.getLastCellNum();

            ResumeEntity resume = new ResumeEntity();

            for (int i = 0; i < end; i++) {
                Cell cell = row.getCell(i);
                if (cell == null) {
                    System.out.print("null" + "\t");
                    continue;
                }

                Object obj = getValue(cell);

                if (i == 0) {
                    resume.setName(obj.toString());
                } else if (i == 1) {
                    String mobile = df.format(Double.parseDouble(obj.toString()));
                    resume.setMobile(mobile);
                } else if (i == 2) {
                    resume.setJobIntention(doubleToInt(obj) + "");
                } else if (i == 3) {
                    boolean equals = obj.toString().equals("男");
                    if (equals) {
                        resume.setSex(1);
                    } else {
                        resume.setSex(2);
                    }
                } else if (i == 4) {
                    resume.setBirthYear(doubleToInt(obj));
                } else if (i == 5) {
                    resume.setWorkingYear(doubleToInt(obj));
                } else if (i == 6) {
                    resume.setEducation(doubleToInt(obj));
                } else if (i == 7) {
                    resume.setCity(doubleToInt(obj));
                }
            }
            list.add(resume);
            System.out.println(resume);
        }
        System.out.println(list);
    }

    private static int doubleToInt(Object obj) {
        Double obj1 = (Double) obj;
        int i1 = obj1.intValue();
        return i1;
    }

    private static Object getValue(Cell cell) {
        Object obj = null;
        switch (cell.getCellTypeEnum()) {
            case BOOLEAN:
                obj = cell.getBooleanCellValue();
                break;
            case ERROR:
                obj = cell.getErrorCellValue();
                break;
            case NUMERIC:
                obj = cell.getNumericCellValue();
                break;
            case STRING:
                obj = cell.getStringCellValue();
                break;
            default:
                break;
        }
        return obj;
    }

}
