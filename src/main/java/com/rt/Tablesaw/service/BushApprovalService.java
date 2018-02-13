package com.rt.Tablesaw.service;

import java.sql.Connection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import tech.tablesaw.api.CategoryColumn;
import tech.tablesaw.api.DateColumn;
import tech.tablesaw.api.ShortColumn;
import tech.tablesaw.api.Table;

import com.rt.Tablesaw.dao.BushApprovalDAO;
import com.rt.Tablesaw.model.BushApproval;

@Service
@Transactional
public class BushApprovalService {

	@Autowired
	private BushApprovalDAO baDAO;
	
	
	public Connection getConn(){
		return baDAO.getConn();
	}
	
	public void deleteAll(){
		baDAO.deleteAll();
	}
	
	public void addTableInfoToModel(ModelMap model, Table table){
		model.addAttribute("tableSize", table.rowCount());
		model.addAttribute("tableName", table.name());
		model.addAttribute("tableShape", table.shape());
		model.addAttribute("tableColumnCount", table.columnCount());
		model.addAttribute("tablePrint", table.print());
		model.addAttribute("tableColumnNames", table.columnNames());
		model.addAttribute("tableIsEmpty", table.isEmpty());
	}
	
	public void addShortColumnInfoToModel(ModelMap model, ShortColumn approval){
		model.addAttribute("colApprovalMin", approval.min());
		model.addAttribute("colApprovalMax", approval.max());
		model.addAttribute("colApprovalMedian", approval.median());
		model.addAttribute("colApprovalMean", approval.mean());
		model.addAttribute("colApprovalSize", approval.size());
	}
	
	public void addDateColumnInfoToModel(ModelMap model, DateColumn date){
		model.addAttribute("colDateUnique", date.countUnique());
		model.addAttribute("colDateIsFriday", date.isFriday().size());
		model.addAttribute("colDateIsAugust", date.isInAugust().size());
		model.addAttribute("colDateIsInQ3", date.isInQ3().size());
		model.addAttribute("colDateIsFirstDayOfMonth", date.isFirstDayOfMonth().size());
	}
	
	public void addCategoryColumnInfoToModel(ModelMap model, CategoryColumn who){
		model.addAttribute("colWhoContains", who.contains("giannis"));
		model.addAttribute("colWhoUnique", who.countUnique());
		model.addAttribute("colWhoCountByCategory", who.countByCategory());
		model.addAttribute("colWhoIsAlpha", who.isAlpha().size());
		model.addAttribute("colWhoLast", who.last());
	}
	
	public void addShortColumnSelectionToModel(ModelMap model, ShortColumn approval){
		model.addAttribute("appIsEqualTo", approval.isEqualTo(1).size());
		model.addAttribute("appIsGreaterThan", approval.isGreaterThan(50).size());
		model.addAttribute("appIsLessThan", approval.isLessThan(50).size());
		model.addAttribute("appIsOdd", approval.isOdd().size());
		model.addAttribute("appIsZero", approval.isZero().size());
	}
	
	public void addDateColumnSelectionToModel(ModelMap model, DateColumn date){
		model.addAttribute("dayIsMonday", date.isMonday().size());
		model.addAttribute("dayIsAfterInt", date.isAfter(1).size());
		model.addAttribute("dayIsFirstDayOfMonth", date.isFirstDayOfMonth().size());
		model.addAttribute("dayIsMissing", date.isMissing().size());
		model.addAttribute("dayIsNotMissing", date.isNotMissing().size());
	}
	
	public void addCategoryColumnSelectionToModel(ModelMap model, CategoryColumn who){
		model.addAttribute("whoIsGiannis", who.equalToIgnoringCase("giannis").size());
		model.addAttribute("whoEndsWith", who.endsWith("is").size());
		model.addAttribute("whoLengthEqualTo", who.hasLengthEqualTo(7).size());
		model.addAttribute("whoIsAlphaNumeric", who.isAlphaNumeric().size());
		model.addAttribute("whoIsEqualTo", who.isEqualTo("GIANNIS").size());
	}
	
	
	//---------- CRUD Default ----------//
	
	public BushApproval findById(Long id){
		return baDAO.findById(id);
	}
	
	public List<BushApproval> findAll(){
		return baDAO.findAll();
	}
	
	public void save(BushApproval ba){
		baDAO.save(ba);
	}
	
	public void update(BushApproval ba){
		baDAO.update(ba);
	}
	
	public void delete(BushApproval ba){
		baDAO.delete(ba);
	}
}
