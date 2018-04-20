package com.value.auto.metadata;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.value.auto.util.JavaDataTypesUtils;
import com.value.auto.util.StringHelper;

public class Field {


	private static Log log = LogFactory.getLog(Field.class);

	private String sqlTypeName;

	private String sqlName;

	private boolean isPk;

	private boolean isFk;

	private int size;

	private boolean isNullable;
	
	private String defaultValue;
	
	private String viewType;
	
	private String viewLabel;
	
	private String fieldChinaName;
	
	private int sqlType;
	private int decimalDigits;
	private String columnComment;
    public Field(int sqlType,String sqlTypeName, String sqlName,
			int size, boolean isPk, boolean isNullable, String defaultValue,int decimalDigits,String columnComment) {
        this.sqlType = sqlType;
		this.sqlTypeName = sqlTypeName;
		this.sqlName = sqlName;
		this.size = size;
		this.isPk = isPk;
		this.isNullable = isNullable;
		this.defaultValue = defaultValue;
		this.decimalDigits = decimalDigits;
		this.columnComment = columnComment;
		log.debug(sqlName + " isPk -> " + isPk);

	}
	
    public Field() {
    	
    }
    public String getSqlTypeName() {
        return sqlTypeName;
    }

    public void setSqlTypeName(String sqlTypeName) {
        this.sqlTypeName = sqlTypeName;
    }

    public String getSqlName() {
        return sqlName;
    }

    public void setSqlName(String sqlName) {
        this.sqlName = sqlName;
    }

    public boolean isPk() {
        return isPk;
    }

    public void setPk(boolean isPk) {
        this.isPk = isPk;
    }

    public boolean isFk() {
        return isFk;
    }

    public void setFk(boolean isFk) {
        this.isFk = isFk;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean getIsNullable() {
        return isNullable;
    }

    public void setNullable(boolean isNullable) {
        this.isNullable = isNullable;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }
    public String getViewLabel() {
        return viewLabel;
    }

    public void setViewLabel(String viewLabel) {
        this.viewLabel = viewLabel;
    }

    public String getJavaName() {
        return StringHelper.makeAllWordFirstLetterUpperCase(getSqlName());
    }
    public String getJavaNameCapital() {
        return StringHelper.capitalize(getJavaName());
    }

    public String getJavaNameLower() {
        return StringHelper.uncapitalize(getJavaName());
    }
    public String getJavaType() {
        return JavaDataTypesUtils.getPreferredJavaType(getSqlType(), getSize(),
                getDecimalDigits());
    }
    public String getFlexType() {
        return JavaDataTypesUtils.getPreferredFlexType(getSqlType(), getSize(),
                getDecimalDigits());
    }
    public String getJavaTypeFullName(){
        return JavaDataTypesUtils.getFullNameJavaType(getSqlType(), getSize(),
                getDecimalDigits());
    }
    public String getValidatorType(){
        return JavaDataTypesUtils.getValidatorType(getSqlType(), getSize(),
                getDecimalDigits());
    }
    public int getSqlType() {
        return sqlType;
    }

    public void setSqlType(int sqlType) {
        this.sqlType = sqlType;
    }

    public int getDecimalDigits() {
        return decimalDigits;
    }

    public void setDecimalDigits(int decimalDigits) {
        this.decimalDigits = decimalDigits;
    }
    public boolean getJavaTypeIsString(){
        return "String".equals(getJavaType());
    }

    public String getFieldChinaName() {
        return fieldChinaName;
    }

    public void setFieldChinaName(String fieldChinaName) {
        this.fieldChinaName = fieldChinaName;
    }

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

}
