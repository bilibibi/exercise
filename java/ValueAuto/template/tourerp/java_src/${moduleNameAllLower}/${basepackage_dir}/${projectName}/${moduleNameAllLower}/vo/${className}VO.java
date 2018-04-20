<#include "/tableAlias.include">
<#include "/java_copyright.include">
package ${fullPackage}.vo;

import com.youpeng.tourerp.core.vo.TourerpBaseVO;
<#list table.oneToMany as many>
import ${basePackage}.${projectName}.${many.moduleNameAllLower}.vo.${many.className}VO;
</#list>
public class ${className}VO extends TourerpBaseVO {
	<#list table.fields as field>
	/**
	 * ${field.columnComment}
	 */
	private ${field.javaType} ${field.javaNameLower};
	</#list>
	<#list table.oneToMany as many>
	private List<${many.tableNameFirstCapital}VO> ${many.tableNameFirstLower}List;
	</#list>
	public ${className}VO(){
	
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


