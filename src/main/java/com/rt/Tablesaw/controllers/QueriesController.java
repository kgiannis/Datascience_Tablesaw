package com.rt.Tablesaw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import tech.tablesaw.api.Table;

import com.rt.Tablesaw.utils.TableStatic;

@Controller
public class QueriesController {

	@GetMapping(value="/showQueries")
	public String showQueries(ModelMap model){
		
		Table table = TableStatic.table;
		
		if (table == null || table.isEmpty()){
			model.addAttribute("status_danger", true);
			model.addAttribute("status_title", "Table Error! ");
			model.addAttribute("status_body", "DB is empty so no Table could be created");
			return "statistics";
		}
		
		/**---------- QUERIES ----------**/
		Table qTbl_1 = table.selectWhere(table.shortColumn("approval").isGreaterThan(50));
		Table qTbl_2 = table.selectWhere(table.dateColumn("date").isMonday());
		Table qTbl_3 = table.selectWhere(table.categoryColumn("who").equalToIgnoringCase("giannis"));
		
		model.addAttribute("qTbl_1", qTbl_1);
		model.addAttribute("qTbl_2", qTbl_2);
		model.addAttribute("qTbl_3", qTbl_3);
		
		return "queries";
	}
}
