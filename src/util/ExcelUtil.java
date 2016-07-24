package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelUtil {
	static NumberFormat format = NumberFormat.getInstance();
	
	/**
	 * 获取数字类型单元格
	 * @param cell
	 * @return
	 */
	public static String NumtoString(HSSFCell cell){
		format.setGroupingUsed(false);
		return format.format(cell.getNumericCellValue());
		
	}
	
	/**
	 * 获取字符串类型的单元格的日期
	 * @param cell
	 * @return
	 */
	public static String StringtoDate(HSSFCell cell){
		StringBuilder builder = new StringBuilder(cell.getStringCellValue());
		builder.setCharAt(builder.indexOf("年"), '-');
		builder.setCharAt(builder.indexOf("月"), '-');
		builder.deleteCharAt(builder.length()-1);
		return builder.toString();
	}
	
	public static String getcellvalue(Cell cell){
		if(cell == null)return null;
		String result = new String();
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BLANK:
			return null;
			
		case Cell.CELL_TYPE_BOOLEAN:
			result = String.valueOf(cell.getBooleanCellValue());
			return result;
			
		case Cell.CELL_TYPE_ERROR:
			return null;
			
		case Cell.CELL_TYPE_FORMULA:
			result = cell.getCellFormula();
			 try {
				 result = String.valueOf(cell.getNumericCellValue());
			 } catch (IllegalStateException e) {
				 result = String.valueOf(cell.getRichStringCellValue());
			}
			return result;
			
		case Cell.CELL_TYPE_NUMERIC:
			result = String.valueOf(cell.getNumericCellValue());
			return result;
			
		case Cell.CELL_TYPE_STRING:
			result = cell.getStringCellValue();
			if(result.equals(""))return null;
			return result;
			
		default:
			return null;
			
		}
	
	}
	
	/**
	 * 解析日期内容的单元格
	 * @param cell
	 * @return
	 */
	public static String getCellDate(Cell cell) {
		if(cell == null){
			return "";
		}
		String result = new String();
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC:// 数字类型
			if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
				SimpleDateFormat sdf = null;
				if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
						.getBuiltinFormat("h:mm")) {
					sdf = new SimpleDateFormat("HH:mm");
				} else {// 日期
					sdf = new SimpleDateFormat("yyyy-MM-dd");
				}
				Date date = cell.getDateCellValue();
				result = sdf.format(date);
			} else if (cell.getCellStyle().getDataFormat() == 58) {
				// 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				double value = cell.getNumericCellValue();
				Date date = org.apache.poi.ss.usermodel.DateUtil
						.getJavaDate(value);
				result = sdf.format(date);
			} else {
				
				Calendar date = HSSFDateUtil.getJavaCalendar(cell.getNumericCellValue());
				int year = date.get(Calendar.YEAR);
				int month = date.get(Calendar.MONTH)+1;
				int day = date.get(Calendar.DAY_OF_MONTH);
				
				
//				String format2 = fm.format(date);
//				System.out.println(string);
				
				result = year+"-"+month+'-'+day;
			}
			break;
		case HSSFCell.CELL_TYPE_STRING:// String类型
			StringBuilder builder = new StringBuilder(cell.getStringCellValue().trim());
			if(builder.indexOf("年")!= -1){
				builder.setCharAt(builder.indexOf("年"), '-');
				builder.setCharAt(builder.indexOf("月"), '-');
				builder.deleteCharAt(builder.length()-1);				
			}else{
				String replace = builder.toString().replace('.', '-');
				builder = new StringBuilder(replace);
			}
			result = builder.toString();
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			result = "";
		default:
			result = "";
			break;
		}
		return result;
	}
	
	
	
	

}
