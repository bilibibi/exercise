package com.value.auto.metadata;

import java.util.ArrayList;
import java.util.List;
/**
 * 项目工程
 */
public class Project {
    /**
     * 一个项目对应多个模块 
     */
	private List<Module> modules = new ArrayList<Module>();
	private List<Table> database = new ArrayList<Table>(); 
	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
    public List<Table> getDatabase() {
        return database;
    }
    public void setDatabase(List<Table> database) {
        this.database = database;
    }
}
