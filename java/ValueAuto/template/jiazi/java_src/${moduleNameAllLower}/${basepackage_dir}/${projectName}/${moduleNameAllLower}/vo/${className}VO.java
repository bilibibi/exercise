<#include "/tableAlias.include">
<#include "/java_copyright.include">
package ${fullPackage}.vo;

<#list table.oneToMany as many>
import ${basePackage}.${projectName}.${many.moduleNameAllLower}.vo.${many.className}VO;
</#list>
public class ${className}VO{
    <#list table.fields as field>
    <#if field.sqlName!='modified_date' && field.sqlName!='modified_by' && field.sqlName!='created_date' && field.sqlName!='created_by' && field.sqlName!='data_status'>
    /**
     * ${field.columnComment}
     */
    private ${field.javaType} ${field.javaNameLower};
    </#if>
    </#list>
    public ${className}VO(){
    }

    <#list table.fields as field>
    <#if field.sqlName!='modified_date' && field.sqlName!='modified_by' && field.sqlName!='created_date' && field.sqlName!='created_by' && field.sqlName!='data_status'>
    public void set${field.javaNameCapital}(${field.javaType} value) {
        this.${field.javaNameLower} = value;
    }
    public ${field.javaType} get${field.javaNameCapital}() {
        return this.${field.javaNameLower};
    }
    </#if>
    </#list>
}


