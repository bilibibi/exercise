<#include "/tableAlias.include">
<#include "/java_copyright.include">

package ${fullPackage}.service.impl;


import java.util.List;

import ${fullPackage}.service.I${className}Service;
import com.jiazi.dao.orm.ibatis.DaoRouter;
import ${basePackage}.${projectName}.${table.moduleNameAllLower}.vo.${table.className}VO;


public class ${className}ServiceImpl implements I${className}Service{

    private DaoRouter daoRouter;
    public DaoRouter getDaoRouter() {
        return daoRouter;
    }
    public void setDaoRouter(DaoRouter daoRouter) {
        this.daoRouter = daoRouter;
    }
   
}