<#include "/tableAlias.include">
package ${voPackage};

import java.util.List;
import java.util.Map;
<#list table.oneToMany as many>
import ${basePackage}.${projectName}.${many.moduleNameAllLower}.vo.${many.className}VO;
</#list>
public class ${className}Vo {
	
	//columns START
	<#list table.fields as field>
	<#if table.hasLinkField==false || field.javaNameLower!=table.linkField.javaNameLower>
	private ${field.javaType} ${field.javaNameLower};
    </#if>
	</#list>
	<#list table.oneToMany as many>
	private List<${many.tableNameFirstCapital}VO> ${many.tableNameFirstLower}List;
	</#list>
	//columns END
	public ${className}Vo(){
	
	}
	<#list table.fields as field>
    <#if table.hasLinkField==false || field.javaNameLower!=table.linkField.javaNameLower>
    public void set${field.javaNameCapital}(${field.javaType} value) {
        this.${field.javaNameLower} = value;
    }
    public ${field.javaType} get${field.javaNameCapital}() {
        return this.${field.javaNameLower};
    }
    </#if>
    </#list>
    <#list table.oneToMany as many>
    public void set${many.tableNameFirstCapital}List(List<${many.tableNameFirstCapital}VO> ${many.tableNameFirstLower}List){
        this.${many.tableNameFirstLower}List=${many.tableNameFirstLower}List;
    }
    public List<${many.tableNameFirstCapital}VO> get${many.tableNameFirstCapital}List(){
        return ${many.tableNameFirstLower}List;
    }
    </#list>
    <#list table.manyToOne as one>
    public void set${one.tableNameFirstCapital}VO(${one.tableNameFirstCapital}VO ${one.tableNameFirstLower}VO){
        this.${one.tableNameFirstLower}VO=${one.tableNameFirstLower}VO;
    }
    public ${one.tableNameFirstCapital}VO get${one.tableNameFirstCapital}VO(){
        return ${one.tableNameFirstLower}VO;
    }
    public void set${table.linkField.javaNameCapital}(${one.keyField.javaType} ${one.keyField.javaNameLower}){
        if(${one.tableNameFirstLower}VO == null){
            ${one.tableNameFirstLower}VO = new ${one.tableNameFirstCapital}VO();
        }
        this.${one.tableNameFirstLower}VO.set${one.keyField.javaNameCapital}(${one.keyField.javaNameLower});
    }
    public ${one.keyField.javaType} get${table.linkField.javaNameCapital}(){
        if(${one.tableNameFirstLower}VO == null){
            return null;
        }
        return this.${one.tableNameFirstLower}VO.get${one.keyField.javaNameCapital}();
    }
    </#list>
}


