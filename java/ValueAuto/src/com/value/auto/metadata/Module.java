package com.value.auto.metadata;

import java.util.ArrayList;
import java.util.List;

import com.value.auto.pagecontrol.PageControl;
import com.value.auto.util.StringHelper;

/**
 * TODO Comment of Module
 * 工程模块
 */
public class Module {
    /**
     * module名
     */
    private String moduleName;
    
    private String moduleChinaName;
    
    /**
     * 模块对应的table 
     */
    private List<Table> tables = new ArrayList<Table>();
    private List<View> views = new ArrayList<View>();
    private List<PageControl> ajaxControls = new ArrayList<PageControl>();
    public String getModuleName() {
        return moduleName;
    }
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    public String getModuleNameFirstCapital() {
        return StringHelper.capitalize(moduleName);
    }
    public String getModuleNameFirstLower() {
        return StringHelper.uncapitalize(moduleName);
    }
    public String getModuleNameAllLower(){
        return StringHelper.uncapitalizeAll(moduleName);
    }
    public List<Table> getTables() {
        return tables;
    }
    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
    public List<View> getViews() {
        return views;
    }
    public void setViews(List<View> views) {
        this.views = views;
    }
    public List<PageControl> getAjaxControls() {
        return ajaxControls;
    }
    public void setAjaxControls(List<PageControl> ajaxControls) {
        this.ajaxControls = ajaxControls;
    }
    public String getModuleChinaName() {
        return moduleChinaName;
    }
    public void setModuleChinaName(String moduleChinaName) {
        this.moduleChinaName = moduleChinaName;
    }
    
}
