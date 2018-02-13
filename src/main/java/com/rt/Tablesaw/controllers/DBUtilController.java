package com.rt.Tablesaw.controllers;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rt.Tablesaw.service.BushApprovalService;
import com.rt.Tablesaw.utils.DBTableUtils;

@Controller
public class DBUtilController {
	
	@Autowired private BushApprovalService bushApprovalService;
	
	@GetMapping(value="/dbutils")
	public String dbUtilsPage(ModelMap model){
		model.addAttribute("totalRecords", bushApprovalService.findAll().size());
		return "dbutils";
	}
	
	@PostMapping(value="/generateRecords")
	public String generateRecords(@RequestParam("recordsToGenerate") int recordsToGenerate, ModelMap model){
		Connection conn = bushApprovalService.getConn();
		DBTableUtils dbtu = new DBTableUtils();
		dbtu.addRecordsToDB(conn, recordsToGenerate);
		
		model.addAttribute("status_success", true);
		model.addAttribute("status_title", "New Records Generated: ");
		model.addAttribute("status_body", recordsToGenerate);
		return "dbutils";
	}
	
	@GetMapping(value="/deleteRecords")
	public String deleteRecords(ModelMap model){
		bushApprovalService.deleteAll();
		model.addAttribute("totalRecords", bushApprovalService.findAll().size());
		
		model.addAttribute("status_danger", true);
		model.addAttribute("status_title", "Truncated! ");
		model.addAttribute("status_body", "All table records deleted...");
		return "dbutils";
	}
}
