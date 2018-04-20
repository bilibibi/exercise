package com.value.auto.pagecontrol;

import com.value.auto.util.StringHelper;

public class Select extends PageControl {
    public Select(){
        this.type="select";
    }
    /**
     * select的数据源，这个select的内容来自哪个table
     */
    private String dataSource;
    /**
     * select显示的值
     */
    private String optionLabel;
    /**
     * 提交时select传递的值
     */
    private String optionValue;
    private String tableName;
    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getOptionLabel() {
        return optionLabel;
    }

    public void setOptionLabel(String optionLabel) {
        this.optionLabel = optionLabel;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }
    public String getJavaOptionLabel(){
        return StringHelper.makeAllWordFirstLetterUpperCase(this.optionLabel);
    }
    public String getJavaOptionValue(){
        return StringHelper.makeAllWordFirstLetterUpperCase(this.optionValue);
    }

    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public String getTableJavaName() {
        return StringHelper.makeAllWordFirstLetterUpperCase(this.tableName);
    }
}
