<#include "/tableAlias.include">
<#include "/java_copyright.include">

package ${fullPackage}.service;

import java.util.List;
import java.util.Map;
import ${basePackage}.framework.web.Pagination;
import ${basePackage}.${projectName}.${table.moduleNameAllLower}.vo.${table.className}VO;

public interface I${className}Service {
    <#assign classNameFirstLower=table.classNameFirstLower>
    public List<${className}VO> loadListBy${className}VO(${className}VO ${classNameFirstLower}VO);
    <#if table.hasKeyField>
    <#assign primaryLabel=table.keyField.javaNameCapital>
    <#assign primaryLabelLower=table.keyField.javaNameLower>
    <#assign primaryType=table.keyField.javaType>
    public void delete${className}VOBy${primaryLabel}(${primaryType} ${primaryLabelLower}); 
    </#if>
    
    public ${className}VO add${className}VO(${className}VO ${classNameFirstLower}VO);
    
    public ${className}VO update${className}VO(${className}VO ${classNameFirstLower}VO);
    
    public void delete${className}VO(${className}VO ${classNameFirstLower}VO);

    public ${className}VO audit${className}VO(${className}VO ${classNameFirstLower}VO);
}