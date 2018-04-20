package com.value.auto.metadata;

import java.util.ArrayList;
import java.util.List;

import com.value.auto.pagecontrol.PageControl;
import com.value.auto.util.StringHelper;

public class View {
    private String moduleName;
    private String moduleChinaName;
    private String viewName;
    private String tableName;
    private String type;
    private Table table;
    private List<PageControl> fieldControlTypeMap = new ArrayList<PageControl>();
    private List<DataGridColumn> dataGrid = new ArrayList<DataGridColumn>(); 
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getViewName() {
        return viewName;
    }
    public String getAsName() {
        return StringHelper.makeAllWordFirstLetterUpperCase(viewName);
    }
    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
    public String getViewNameFirstCapital(){
        return StringHelper.capitalize(StringHelper.makeAllWordFirstLetterUpperCase(viewName));
    }
    public String getModuleName() {
        return moduleName;
    }
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    public String getModuleNameAllLower(){
        return StringHelper.uncapitalizeAll(moduleName);
    }
    public Table getTable() {
        return table;
    }
    public void setTable(Table table) {
        this.table = table;
    }
    public List<PageControl> getFieldControlTypeMap() {
        return fieldControlTypeMap;
    }
    public void setFieldControlTypeMap(List<PageControl> fieldControlTypeMap) {
        this.fieldControlTypeMap = fieldControlTypeMap;
    }
    public List<DataGridColumn> getDataGrid() {
		return dataGrid;
	}
	public void setDataGrid(List<DataGridColumn> dataGrid) {
		this.dataGrid = dataGrid;
	}
	public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public String getModuleChinaName() {
        return moduleChinaName;
    }
    public void setModuleChinaName(String moduleChinaName) {
        this.moduleChinaName = moduleChinaName;
    }
    /**
     * 取得查询结果页面预示的字段
     * @return
     */
    public List<PageControl> getRetrieveControl(){
        List<PageControl> retrieveControl = new ArrayList<PageControl>();
        for(PageControl control : fieldControlTypeMap){
            if(control.getDisplayOperate()==null||control.getDisplayOperate().indexOf("R")!=-1){
                retrieveControl.add(control);
            }
        }
        return retrieveControl;
    }
    /**
     * 取得新增页面预示的字段
     * @return
     */
    public List<PageControl> getCreateControl(){
        List<PageControl> retrieveControl = new ArrayList<PageControl>();
        for(PageControl control : fieldControlTypeMap){
            if(control.getDisplayOperate()==null||control.getDisplayOperate().indexOf("C")!=-1){
                retrieveControl.add(control);
            }
        }
        return retrieveControl;
    } 
    /**
     * 取得更新页面预示的字段
     * @return
     */
    public List<PageControl> getUpdateControl(){
        List<PageControl> retrieveControl = new ArrayList<PageControl>();
        for(PageControl control : fieldControlTypeMap){
            if(control.getDisplayOperate()==null||control.getDisplayOperate().indexOf("U")!=-1){
                retrieveControl.add(control);
            }
        }
        return retrieveControl;
    }
}
