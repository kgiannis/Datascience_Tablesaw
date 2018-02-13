package com.rt.Tablesaw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import tech.tablesaw.api.CategoryColumn;
import tech.tablesaw.api.DateColumn;
import tech.tablesaw.api.ShortColumn;
import tech.tablesaw.api.Table;

import com.rt.Tablesaw.service.BushApprovalService;
import com.rt.Tablesaw.utils.TableStatic;

@Controller
public class SelectionsController {

	@Autowired private BushApprovalService bushApprovalService;
	
	@GetMapping(value="showSelections")
	public String showSelections(ModelMap model){
		Table table = TableStatic.table;
		
		if (table == null || table.isEmpty()){
			model.addAttribute("status_danger", true);
			model.addAttribute("status_title", "Table Error! ");
			model.addAttribute("status_body", "DB is empty so no Table could be created");
			return "statistics";
		}
		
		
		/** COLUMNS **/
		ShortColumn approval = table.shortColumn("approval");
		DateColumn date = table.dateColumn("date");
		CategoryColumn who = table.categoryColumn("who");
		
		
		/**---------- SELECTIONS ----------**/
		bushApprovalService.addShortColumnSelectionToModel(model, approval);
		bushApprovalService.addDateColumnSelectionToModel(model, date);
		bushApprovalService.addCategoryColumnSelectionToModel(model, who);
		
		
		return "selections";
	}
}
