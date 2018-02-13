package com.rt.Tablesaw.controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import tech.tablesaw.api.Table;

import com.rt.Tablesaw.service.BushApprovalService;
import com.rt.Tablesaw.utils.DBTableUtils;
import com.rt.Tablesaw.utils.Generator;
import com.rt.Tablesaw.utils.TableStatic;

@Controller
public class IndexController {

	@Autowired private BushApprovalService bushApprovalService;
	
	
	@GetMapping(value = {"/", "/index"})
	public String index(ModelMap model) throws SQLException{
		
		Connection conn = bushApprovalService.getConn();
		DBTableUtils dbtu = new DBTableUtils();
		
		/** TABLE **/
		Table table = dbtu.getTableFromDB(conn, "bush_approval");
		TableStatic.table = table;
		
		conn.close();
		
		int sizeOfWhos = 10;
		List<String> whos = Generator.getRandomWhos(sizeOfWhos);
		model.addAttribute("whos", whos);
		
		return "index";
	}
}
