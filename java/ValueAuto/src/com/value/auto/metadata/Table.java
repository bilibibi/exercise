package com.value.auto.metadata;


import java.util.ArrayList;
import java.util.List;

import com.value.auto.util.StringHelper;

public class Table {
    private String moduleName;
    private String tableName;
    private String className;
    private String key;
    private List<String> primaryKeyColumns;
    private List<Field> fields = new ArrayList<Field>();
    private List<Table> oneToMany = new ArrayList<Table>();
    private List<Table> manyToOne = new ArrayList<Table>();
    private String linkKey;
    private Field linkField;
    private Field keyField;
    private String outPutType;
    private String functionName;
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        tableName = StringHelper.uncapitalizeAll(tableName);
        this.tableName = tableName;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public List<Field> getFields() {
        return fields;
    }
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
    public List<Table> getOneToMany() {
        return oneToMany;
    }
    public void setOneToMany(List<Table> oneToMany) {
        this.oneToMany = oneToMany;
    }
    public String getLinkKey() {
        return linkKey;
    }
    public void setLinkKey(String linkKey) {
        this.linkKey = linkKey;
    }
    public List<String> getPrimaryKeyColumns() {
        return primaryKeyColumns;
    }
    public void setPrimaryKeyColumns(List<String> primaryKeyColumns) {
        this.primaryKeyColumns = primaryKeyColumns;
    }
    public Boolean getHasKeyField() {
        return this.keyField != null;
    }
    public Field getFieldByName(String fieldName) {
        if (fieldName == null) {
            return null;
        }
        List<Field> fields = getFields();
        int size = fields.size();
        for(int i = 0; i < size; i++) {
            Field c = (Field)fields.get(i);
            if(c != null && fieldName.equalsIgnoreCase(c.getSqlName()))
                return c;
        }
        return null;
    }
    public Field getKeyField() {
        return keyField;
    }
    public void setKeyField(Field keyField) {
        this.keyField = keyField;
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
    public String getModuleNameFirstCapital() {
        return StringHelper.capitalize(moduleName);
    }
    public Field getLinkField() {
        return linkField;
    }
    public void setLinkField(Field linkField) {
        this.linkField = linkField;
    }
    public boolean getHasLinkField() {
        return linkField!=null;
    }
    public List<Table> getManyToOne() {
        return manyToOne;
    }
    public void setManyToOne(List<Table> manyToOne) {
        this.manyToOne = manyToOne;
    }
	public String getOutPutType() {
		return outPutType;
	}
	public void setOutPutType(String outPutType) {
		this.outPutType = outPutType;
	}
	
	public String getClassName(){
	    if(className==null||"".equals(className)){
	        className = StringHelper.makeAllWordFirstLetterUpperCase(this.tableName);
        }
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public String getClassNameFirstLower(){
        return StringHelper.uncapitalize(this.getClassName());
    }
    public String getClassNameAllUpperCase(){
        return this.className.toUpperCase();
    }
    public String getFunctionName() {
        return functionName;
    }
    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }
    
}
