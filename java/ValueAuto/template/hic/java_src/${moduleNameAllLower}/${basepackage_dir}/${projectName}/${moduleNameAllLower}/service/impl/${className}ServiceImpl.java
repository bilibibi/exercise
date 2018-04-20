<#include "/tableAlias.include">
<#include "/java_copyright.include">

package ${fullPackage}.service.impl;


import java.util.List;

import ${basePackage}.flexframework.util.DefaultMessageSource;
import com.jiazi.flexframework.exception.BusinessException;
import ${fullPackage}.service.I${className}Service;
import ${basePackage}.framework.orm.ibatis.DaoRouter;
import ${basePackage}.${projectName}.${table.moduleNameAllLower}.vo.${table.className}VO;


public class ${className}ServiceImpl extends DefaultMessageSource implements I${className}Service{

    <#assign classNameFirstLower=table.classNameFirstLower> 
    public List<${className}VO> loadListBy${className}VO(${className}VO ${classNameFirstLower}VO){
        return (List<${className}VO>)daoRouter.query("${moduleNameCaptital}_${className}.loadBy${className}VO", ${classNameFirstLower}VO);
    } 
    <#if table.hasKeyField>
    <#assign primaryLabel=table.keyField.javaNameCapital>
    <#assign primaryLabelLower=table.keyField.javaNameLower>
    <#assign primaryType=table.keyField.javaType>
    public void delete${className}VOBy${primaryLabel}(${primaryType} ${primaryLabelLower}){
        if (${primaryLabelLower} == null){          
            return;
        }
        daoRouter.delete("${moduleNameCaptital}_${className}.delete${className}VOBy${primaryLabel}",${primaryLabelLower});
    }
    
    </#if>
    
    public ${className}VO add${className}VO(${className}VO ${classNameFirstLower}VO){
        if(${classNameFirstLower}VO == null){
            return null;
        }
        try {
            ${primaryType} ${primaryLabelLower} = (${primaryType})daoRouter.insert("${moduleNameCaptital}_${className}.insert${className}VO", ${classNameFirstLower}VO);
            ${classNameFirstLower}VO.set${primaryLabelLower?cap_first}(${primaryLabelLower});
        }
        catch ( RuntimeException e ) {
            throw new BusinessException(this.getMessage("user.update.error"), e);
        }
        return ${classNameFirstLower}VO;
    }
    
    public ${className}VO update${className}VO(${className}VO ${classNameFirstLower}VO){
        if(${classNameFirstLower}VO == null){
            return null;
        }
        
        try {
            daoRouter.update("${moduleNameCaptital}_${className}.update${className}VOBy${primaryLabel}", ${classNameFirstLower}VO);
        }
        catch ( RuntimeException e ) {
            throw new BusinessException(this.getMessage("user.update.error"), e);
        }
        return ${classNameFirstLower}VO;
    }
    
    public void delete${className}VO(${className}VO ${classNameFirstLower}VO){
        <#if table.hasKeyField>
        <#assign primaryLabel=table.keyField.javaNameCapital>
        delete${className}VOBy${primaryLabel}(${classNameFirstLower}VO.get${primaryLabel}());
        <#else>
        //TODO add code here
        </#if>
    }
    
    public ${className}VO audit${className}VO(${className}VO ${classNameFirstLower}VO){
        if(${classNameFirstLower}VO == null){
            return null;
        }
        try {
            daoRouter.update("${moduleNameCaptital}_${className}.update${className}VOBy${primaryLabel}", ${classNameFirstLower}VO);
        }
        catch ( RuntimeException e ) {
            throw new BusinessException(this.getMessage("user.update.error"), e);
        }
        return ${classNameFirstLower}VO;
    }
    /**
     * Use daoRouter to operate with database.
     */
    private DaoRouter daoRouter;
    
    /**
     * @return daoRouter
     */
    public DaoRouter getDaoRouter() {
        return daoRouter;
    }
    
    /**
     * Spring set this daoRouter.
     * @param daoRouter
     */
    public void setDaoRouter(DaoRouter daoRouter) {
        this.daoRouter = daoRouter;
    }
   
}