package br.com.spentTime.main;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.spentTime.model.Comment;
import br.com.spentTime.model.Content;
import br.com.spentTime.model.ContentType;
import br.com.spentTime.model.WorkLog;

public class ReadSpreadSheet {

	public static List<WorkLog> setWorkLog (String path) {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(path); //Workbook.getWorkbook(new File(path));
			List<WorkLog> listW = new ArrayList<WorkLog>();

			XSSFSheet sheet = workbook.getSheetAt(0);
			sheet.getNumMergedRegions();
			sheet.getPhysicalNumberOfRows();

			Iterator<Row> rowIterator = sheet.iterator();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getRowNum() > 0) {
					Iterator<Cell> cellIterator = row.cellIterator();

					WorkLog wl = new WorkLog();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						
						if (cell.getColumnIndex() == 0 && 
								!StringUtils.isBlank(cell.getStringCellValue())) {
							wl.setIssue(cell.getStringCellValue());
						}
						else if (cell.getColumnIndex() == 1 && 
								cell.getDateCellValue()!= null) {
							DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.000+0000'");
							Date date = cell.getDateCellValue();
							GregorianCalendar gc = new GregorianCalendar();
							gc.setTime(date);
							// add 3 hour for server
							gc.add(Calendar.HOUR, 3);
							String sDate = formatter.format(gc.getTime());
							wl.setStarted(sDate);
						}
						else if (cell.getColumnIndex() == 4 && 
								!StringUtils.isBlank(cell.getStringCellValue())) {
							String horario = cell.getStringCellValue().replaceAll("-", "").replaceAll(":", "h ")+"m";
							wl.setTimeSpent(horario);
						}
						else if (cell.getColumnIndex() == 5 && 
								!StringUtils.isBlank(cell.getStringCellValue())) {
							Comment comment = new Comment();
							//defaul
							comment.setType("doc");
							comment.setVersion(1);
							Content content = new Content();
							content.setType("paragraph");
							ContentType ct = new ContentType();
							ct.setType("text");
							ct.setText(cell.getStringCellValue());
							List<Content> listC = new ArrayList<Content>();
							List<ContentType> listCT = new ArrayList<ContentType>();
							listCT.add(ct);
							listC.add(content);
							content.setContent(listCT);
							comment.setContent(listC);
							
							wl.setComment(comment);
						}
					}
					
					if (!StringUtils.isBlank(wl.getIssue())) {
						listW.add(wl);
					}
				}
			}
			workbook.close();
			return listW;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
