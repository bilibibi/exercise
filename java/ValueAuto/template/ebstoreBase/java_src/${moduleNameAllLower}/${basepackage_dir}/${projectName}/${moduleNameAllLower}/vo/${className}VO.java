<#include "/tableAlias.include">
<#include "/java_copyright.include">
package ${fullPackage}.vo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import java.util.List;
import java.util.Map;
import ${basePackage}.framework.vo.BaseVO;
<#list table.oneToMany as many>
import ${basePackage}.${projectName}.${many.moduleNameAllLower}.vo.${many.className}VO;
</#list>
public class ${className}VO extends BaseVO {
	
	public static final String TABLE_ALIAS = "${className}";

	//columns START
	<#list table.fields as field>
	private ${field.javaType} ${field.javaNameLower};
	</#list>
	<#list table.oneToMany as many>
	private List<${many.tableNameFirstCapital}VO> ${many.tableNameFirstLower}List;
	</#list>
	//columns END
	public ${className}VO(){
	
	}

	public String toString() {
		return new ToStringBuilder(this)
		<#list table.fields as field>
			.append("${field.javaNameLower}", get${field.javaNameCapital}())
		</#list>
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
		<#list table.fields as field>
			.append(get${field.javaNameCapital}())
		</#list>
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ${className}VO == false) return false;
		if(this == obj) return true;
		${className}VO other = (${className}VO)obj;
		return new EqualsBuilder()
		<#list table.fields as field>
			.append(get${field.javaNameCapital}(), other.get${field.javaNameCapital}())
		</#list>
			.isEquals();
	}
	
	<#list table.fields as field>
    public void set${field.javaNameCapital}(${field.javaType} value) {
        this.${field.javaNameLower} = value;
    }
    public ${field.javaType} get${field.javaNameCapital}() {
        return this.${field.javaNameLower};
    }
    </#list>
    <#list table.oneToMany as many>
    public void set${many.tableNameFirstCapital}List(List<${many.tableNameFirstCapital}VO> ${many.tableNameFirstLower}List){
        this.${many.tableNameFirstLower}List=${many.tableNameFirstLower}List;
    }
    public List<${many.tableNameFirstCapital}VO> get${many.tableNameFirstCapital}List(){
        return ${many.tableNameFirstLower}List;
    }
    </#list>
}


