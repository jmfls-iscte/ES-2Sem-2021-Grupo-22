package excel;

import java.io.File;
import java.io.FileNotFoundException;

import metrics.*;

import metrics.Class;
import metrics.Method;
import metrics.Metrics;
import metrics.Package;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * Writes in an excel  file
 *
 */
public class ExcelWrite {

	private  String[] columns = {"MethodID","Package","Class","Method","NOM_class","LOC_class","WMC_class","is_God_Class","LOC_method","CYCLO_method","is_Long_Method"};
	private   ArrayList<Metrics> metrics; 
	public  int methodId;
	public Metrics m;
	public XSSFWorkbook workbook;


	/**
	 * Creates ExcelWrite and initializes the array metrics
	 */
	public ExcelWrite(){
		metrics = new ArrayList<Metrics>();
	}


	/**
	 * Adds the metrics to every method in the package list
	 * @param listPackages list of packages
	 */
	public void addMetrics (ArrayList<Package> listPackages){
		methodId = 1;

		for(Package p :listPackages) {
			for(Class c: p.getClass_list()) {
				for(Method m: c.getMethod_list()) {					
					Metrics m2 = new Metrics(methodId++,p.getName_Package(),c.getName_Class(),m.getName_method(),c.getNOM_class(),c.getLOC_class(),c.getWMC_class(),false,m.getLOC_method(),m.getCYCLO_method(),false);
					metrics.add(m2);
				}
			}
		}
	}

	/**
	 * Writes in the file 
	 * @param filePath path of the file
	 * @param listPackages list of packages
	 * @throws IOException
	 */
	public void writeFile(String filePath, ArrayList<Package> listPackages) throws IOException{

		addMetrics(listPackages);

		File file = new File(filePath);
		boolean exists = file.exists();
		if(exists == false) {
			workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Metrics");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 14);

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			XSSFRow headerRow = sheet.createRow(0);

			for (int i = 0; i < columns.length; i++) {
				XSSFCell cell = headerRow.createCell(i);
				cell.setCellValue(columns[i]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowNum = 1;

			for (Metrics metric : metrics) {
				XSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(metric.getMethodID());
				row.createCell(1).setCellValue(metric.getPackage());
				row.createCell(2).setCellValue(metric.getClass1());
				row.createCell(3).setCellValue(metric.getMethod());
				row.createCell(4).setCellValue(metric.getNOM_class());
				row.createCell(5).setCellValue(metric.getLOC_class());
				row.createCell(6).setCellValue(metric.getWMC_class());
				row.createCell(7).setCellValue(metric.getIs_God_Class());
				row.createCell(8).setCellValue(metric.getLOC_method());
				row.createCell(9).setCellValue(metric.getCYCLO_method());
				row.createCell(10).setCellValue(metric.getIs_Long_Method());
			}

			// Resize all columns to fit the content size
			for (int i = 0; i < columns.length; i++) {
				sheet.autoSizeColumn(i);
			}
		}else{
			throw new IOException();
		}

		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(filePath);
			workbook.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Gets the arrayList of metrics
	 * @return the arrayList of metrics
	 */
	public ArrayList<Metrics> getMetrics() {
		return metrics;
	}


}
