package com.stardust.cont;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.stardust.model.EmpBean;
import com.stardust.model.SetupDB;

public class EmpDao {

	public List<EmpBean> getEmpList() {
		SetupDB db = new SetupDB();
		List<EmpBean> list = new ArrayList<EmpBean>();
		ResultSet rs = null;
		String sql = "SELECT employee_id, first_name, salary, hire_date FROM employees";
		rs = db.select(sql);
		
		try {
			while(rs.next()) {
				EmpBean eb = new EmpBean();
				eb.setEmployee_id(rs.getInt("employee_id"));
				eb.setFirst_name(rs.getString("first_name"));
				eb.setSalary(rs.getDouble("salary"));
				eb.setHire_date(rs.getString("hire_date"));
				
				list.add(eb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closeDB();
		}
		
		return list;
	}

	public void setExcel(List<EmpBean> empList, String root) {
		// Excel 생성하기
		Workbook wb = new XSSFWorkbook();
		System.out.println(root);
		
		try {
			FileOutputStream fos = new FileOutputStream(root + "workbook.xlsx");
			Sheet sh1 = wb.createSheet();
			for(int i = 0; i < empList.size(); i++) {
				Row row1 = sh1.createRow(i);
				Cell cell1 = row1.createCell(0);
				Cell cell2 = row1.createCell(1);
				Cell cell3 = row1.createCell(2);
				Cell cell4 = row1.createCell(3);
				cell1.setCellValue(empList.get(i).getEmployee_id());
				cell2.setCellValue(empList.get(i).getFirst_name());
				cell3.setCellValue(empList.get(i).getSalary());
				cell4.setCellValue(empList.get(i).getHire_date());
			}
			wb.write(fos);
			wb.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
