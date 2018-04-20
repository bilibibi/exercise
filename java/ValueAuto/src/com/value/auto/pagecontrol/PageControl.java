package com.value.auto.pagecontrol;

import com.value.auto.util.StringHelper;


public class PageControl {
    private String chinaName;
    /**
     * 控件类型
     */
    protected String type;
    /**
     * 控件标签
     */
    protected String label;
    /**
     * 控件名
     */
    protected String name;
    /**
     * 控件值
     */
    protected String value;
    /**
     * 是否可为空
     */
    protected boolean ifNull=false;
    /**
     * 是否为整数
     */
    protected boolean isInt=false;
    /**
     * 是否为浮点数
     */
    protected boolean isFloat=false;
    /**
     * 是否为日期
     */
    protected boolean isDate=false;
    /**
     * 数据类型
     */
    protected String dataType="String";
    /**
     * 哪些操作页面中显示这个空间
     * C  create新增页面
     * R  Retrieve 查询条件
     * D  delete删除页面
     * U  update修改页面 
     */
    protected String displayOperate;
    /**
     * 控件name对性的数据库字段的名字
     */
    protected String fieldName;
    /**
     * 模块名称
     */
    protected String moduleName;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getDisplayOperate() {
        return displayOperate;
    }
    public void setDisplayOperate(String displayOperate) {
        this.displayOperate = displayOperate;
    }
    public String getFieldName() {
        return fieldName;
    }
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    public String getFieldJavaName() {
        return StringHelper.makeAllWordFirstLetterUpperCase(this.fieldName);
    }
    public String getFieldJavaNameCapital() {
        return StringHelper.capitalize(getFieldJavaName());
    }

    public String getFieldJavaNameLower() {
        return StringHelper.uncapitalize(getFieldJavaName());
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
    public String getDataType() {
        return dataType;
    }
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    public boolean isIfNull() {
        return ifNull;
    }
    public void setIfNull(boolean ifNull) {
        this.ifNull = ifNull;
    }
    public boolean getIsInt() {
        return isInt;
    }
    public void setIsInt(boolean isInt) {
        this.isInt = isInt;
    }
    public boolean getIsFloat() {
        return isFloat;
    }
    public void setIsFloat(boolean isFloat) {
        this.isFloat = isFloat;
    }
    public boolean getIsDate() {
        return isDate;
    }
    public void setIsDate(boolean isDate) {
        this.isDate = isDate;
    }
    public String getChinaName() {
        return chinaName;
    }
    public void setChinaName(String chinaName) {
        this.chinaName = chinaName;
    }
}
