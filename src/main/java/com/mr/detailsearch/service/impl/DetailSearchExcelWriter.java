package com.mr.detailsearch.service.impl;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class DetailSearchExcelWriter extends AbstractExcelView {
 
    @Override
    protected void buildExcelDocument(Map<String, Object> ModelMap,HSSFWorkbook workbook, HttpServletRequest req, HttpServletResponse res) throws Exception {

        String excelName = "상세조회리스트";
        HSSFSheet worksheet = null;
        HSSFRow row = null;
 
        // 총기간 리스트
            excelName=URLEncoder.encode("상세조회리스트","UTF-8");
            worksheet = workbook.createSheet("상세조회리스트");
            HSSFFont bold_font = workbook.createFont();
            HSSFFont fontRed = workbook.createFont();
 
            // 진하고, 왼쪽정렬, 배경색(LIGHT_TURQUOISE)
            HSSFCellStyle bold_center_lt_style = workbook.createCellStyle();
            bold_font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            bold_center_lt_style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            bold_center_lt_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            bold_center_lt_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            bold_center_lt_style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            bold_center_lt_style.setFont(bold_font);
            bold_center_lt_style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            bold_center_lt_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            bold_center_lt_style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
 
            // 진하고,가운데정렬
            HSSFCellStyle bold_style = workbook.createCellStyle();
            bold_font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            bold_style.setFont(bold_font);
            bold_style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            bold_style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            bold_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            bold_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            bold_style.setBorderTop(HSSFCellStyle.BORDER_THIN);
 
            // 가운데정렬
            HSSFCellStyle center_style = workbook.createCellStyle();
            center_style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            center_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            center_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            center_style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            center_style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
 
            // 왼쪽정렬
            HSSFCellStyle left_style = workbook.createCellStyle();
            left_style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            left_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            left_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            left_style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            left_style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
 
            // 숫자형식(#,##0)
            HSSFCellStyle number_style = workbook.createCellStyle();
            number_style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
            number_style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            number_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            number_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            number_style.setBorderTop(HSSFCellStyle.BORDER_THIN);
 
            // 숫자형식(#,##0), 빨간색 폰트
            HSSFCellStyle redNumber_style = workbook.createCellStyle();
            fontRed.setColor(HSSFColor.RED.index);
            redNumber_style.setFont(fontRed);
            redNumber_style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            redNumber_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            redNumber_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            redNumber_style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            redNumber_style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
 
            // 진하고, 숫자형식(#,##0)
            HSSFCellStyle bNumber_style = workbook.createCellStyle();
            bNumber_style.setFont(bold_font);
            bNumber_style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            bNumber_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            bNumber_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            bNumber_style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            bNumber_style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
 
            List<Map> list = (List<Map>)ModelMap.get("mrSearchList");
            row = worksheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("MR NO");
            cell.setCellStyle(bold_center_lt_style);
            cell = row.createCell(1);
            cell.setCellValue("사업명");
            cell.setCellStyle(bold_center_lt_style);
            cell = row.createCell(2);
            cell.setCellValue("요청자");
            cell.setCellStyle(bold_center_lt_style);
            cell = row.createCell(3);
            cell.setCellValue("요청팀");
            cell.setCellStyle(bold_center_lt_style);
            cell = row.createCell(4);
            cell.setCellValue("기술검토팀");
            cell.setCellStyle(bold_center_lt_style);
            cell = row.createCell(5);
            cell.setCellValue("Job Eng.");
            cell.setCellStyle(bold_center_lt_style);
            cell = row.createCell(6);
            cell.setCellValue("수행팀");
            cell.setCellStyle(bold_center_lt_style);
            cell = row.createCell(7);
            cell.setCellValue("Project Eng.");
            cell.setCellStyle(bold_center_lt_style);
            cell = row.createCell(8);
            cell.setCellValue("진행상태");
            cell.setCellStyle(bold_center_lt_style);
            cell = row.createCell(9);
            cell.setCellValue("Capex No");
            cell.setCellStyle(bold_center_lt_style);
            cell = row.createCell(10);
            cell.setCellValue("품의금액(천원)");
            cell.setCellStyle(bold_center_lt_style);
            cell = row.createCell(11);
            cell.setCellValue("예산금액(천원)");
            cell.setCellStyle(bold_center_lt_style);
            cell = row.createCell(12);
            cell.setCellValue("공용자산 여부");
            cell.setCellStyle(bold_center_lt_style);
 
            int i = 1;
            for(Map map : list){
                row = worksheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(map.get("mrNo").toString());                                    
                cell.setCellStyle(center_style);
                cell = row.createCell(1);
                cell.setCellValue(map.get("mrReqTitle").toString());                                  
                cell.setCellStyle(left_style);
                cell = row.createCell(2);
                cell.setCellValue(map.get("reqEmpName").toString());                            
                cell.setCellStyle(center_style);
                cell = row.createCell(3);
                cell.setCellValue(map.get("reqTeamName").toString());
                cell.setCellStyle(left_style);
                cell = row.createCell(4);
                cell.setCellValue(map.get("jobTeamName").toString());
                cell.setCellStyle(left_style);
                cell = row.createCell(5);
                cell.setCellValue(map.get("jobEmpName").toString());
                cell.setCellStyle(center_style);
                cell = row.createCell(6);
                cell.setCellValue(map.get("actTeamName").toString());
                cell.setCellStyle(left_style);
                cell = row.createCell(7);
                cell.setCellValue(map.get("proEmpName").toString());
                cell.setCellStyle(center_style);
                cell = row.createCell(8);
                if("Z0030".equals(map.get("mrStepCd")) && ("Z0133".equals(map.get("procStCd")) || "Z0122".equals(map.get("procStCd")) || "Z0131".equals(map.get("procStCd")))){
                    cell.setCellValue("타당성검토 확인");
                }else{
                    cell.setCellValue(map.get("mrStepNm").toString());
                }
                cell.setCellStyle(center_style);
                cell = row.createCell(9);
                cell.setCellValue(map.get("capexNo").toString());
                cell.setCellStyle(number_style);
                cell = row.createCell(10);
                cell.setCellValue(map.get("costTotalSum").toString());
                cell.setCellStyle(number_style);
                cell = row.createCell(11);
                cell.setCellValue(map.get("sapCostTot").toString());
                cell.setCellStyle(number_style);
                cell = row.createCell(12);
                cell.setCellValue(map.get("clCd06").toString());
                cell.setCellStyle(center_style);

                i++;
            }
 
        // 각컬럼 크기조정
        for(int j=0; j<13; j++){
            worksheet.autoSizeColumn((short)j);
            worksheet.setColumnWidth(j, (worksheet.getColumnWidth(j))+1000 );  
        }
 
        res.setContentType("Application/Msexcel;charset=UTF-8");
        res.setHeader("Content-Disposition", "ATTachment; Filename="+excelName+".xls");
 
    }
}
