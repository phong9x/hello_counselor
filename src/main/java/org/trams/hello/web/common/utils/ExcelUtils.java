package org.trams.hello.web.common.utils;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.trams.hello.business.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExcelUtils extends AbstractController {
	@Resource
	public UserService userService;

	public static void createExcelFile(HttpServletRequest request, HttpServletResponse response, String title,
			List<String> header, List<List<String>> data) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String today = formatter.format(new Date());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + today + ".xls" + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		OutputStream out;
		try {
			out = response.getOutputStream();
			// String path =
			// request.getSession().getServletContext().getRealPath("/");
			// FileInputStream fileInput = new FileInputStream(path +
			// "excel_template/order_template.xls");
			HSSFWorkbook template_workbook = new HSSFWorkbook();
			HSSFSheet sheet = template_workbook.createSheet("Sheet 1");
			int columnIndex = 1;
			int rowIndex = 0;
			int maxColumn = 0;
			int cellIndex = 0;
			if (title != null && !title.equals("")) {
				rowIndex++;
			}
			
			HSSFRow rowhead = sheet.createRow(rowIndex);
			columnIndex = 0;
			for (int i = 0; i < header.size(); i++) {
				rowhead.createCell(i).setCellValue(header.get(i));
				
			}
			rowIndex++;
			
			for (List<String> listStr : data) {
				HSSFRow row = sheet.createRow(rowIndex);
				columnIndex = 0;
				for (String rowItem : listStr) {
					row.createCell(columnIndex).setCellValue(rowItem);
					sheet.autoSizeColumn(columnIndex);
					columnIndex++;
				}
				rowIndex++;
			}

//			if (title != null && !title.equals("")) {
//				sheet.addMergedRegion(new CellRangeAddress(0, 0, maxColumn, 1));
//				HSSFRow row = sheet.createRow(0);
//				row.createCell(columnIndex).setCellValue(title);
//				maxColumn++;
//			}

			try {
				template_workbook.write(out);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
