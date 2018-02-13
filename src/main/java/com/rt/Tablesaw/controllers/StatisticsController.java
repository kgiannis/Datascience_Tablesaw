package com.rt.Tablesaw.controllers;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import tech.tablesaw.api.CategoryColumn;
import tech.tablesaw.api.DateColumn;
import tech.tablesaw.api.ShortColumn;
import tech.tablesaw.api.Table;

import com.rt.Tablesaw.service.BushApprovalService;
import com.rt.Tablesaw.utils.DBTableUtils;
import com.rt.Tablesaw.utils.TableStatic;

@Controller
public class StatisticsController {
	
	@Autowired private BushApprovalService bushApprovalService;

	
	@GetMapping(value="/showStatistics")
	public String showStatistics(ModelMap model) throws SQLException{
		Connection conn = bushApprovalService.getConn();
		DBTableUtils dbtu = new DBTableUtils();
		
		/** TABLE **/
		Table table = dbtu.getTableFromDB(conn, "bush_approval");
		TableStatic.table = table;
		
		if (table.isEmpty()){
			model.addAttribute("status_danger", true);
			model.addAttribute("status_title", "Table Error! ");
			model.addAttribute("status_body", "DB is empty so no Table could be created");
			return "statistics";
		}
		
		/** COLUMNS **/
		ShortColumn approval = table.shortColumn("approval");
		DateColumn date = table.dateColumn("date");
		CategoryColumn who = table.categoryColumn("who");
		
		bushApprovalService.addTableInfoToModel(model, table);
		bushApprovalService.addShortColumnInfoToModel(model, approval);
		bushApprovalService.addDateColumnInfoToModel(model, date);
		bushApprovalService.addCategoryColumnInfoToModel(model, who);
		
		return "statistics";
	}
	
}
