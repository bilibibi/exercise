<#include "/tableAlias.include">
<#include "/java_copyright.include">

package ${fullPackage}.service.impl;


import java.util.List;
import java.util.Map;

import com.jiazi.dao.orm.ibatis.DaoRouter;
import com.value.vo.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ${className}ServiceImpl implements I${className}Service{
<#assign classNameFirstLower=table.classNameFirstLower>
    /**
     * 根据条件加载
     *
     * @param params
     * @return
     * @author
     */
    public List<${className}VO> load${className}(Map<String,Object> params){
        return (List<${className}VO>) daoRouter.query("${className}.load${className}", params);
    }
    /**
     * 分页加载
     *
     * @param page
     * @return
     * @author
     */
    public List<${className}VO> load${className}ForPage(Pagination page) {
            Map<String, Object> params = page.getParams();
        int count = daoRouter.count("${className}.load${className}", params);
        page.setCount(count);
        return (List<${className}VO>) daoRouter.query("${className}.load${className}", params, page.getStart(), page.getPageSize());
    }

    /**
     * 新增
     *
     * @param ${classNameFirstLower}
     * @return
     * @author
     */
    public void insert${className}(${className}VO ${classNameFirstLower}){
        daoRouter.insert("${className}.insert${className}",${classNameFirstLower});
    }

    /**
     * 修改
     *
     * @param ${classNameFirstLower}
     * @return
     * @author
     */
    public void update${className}(${className}VO ${classNameFirstLower}){
        daoRouter.update("${className}.update${className}",${classNameFirstLower});
    }

    /**
     * 删除
     *
     * @param ${classNameFirstLower}
     * @return
     * @author
     */
    public void delete${className}(${className}VO ${classNameFirstLower}){
        daoRouter.update("${className}.delete${className}",${classNameFirstLower});
    }

    @Autowired
    private DaoRouter daoRouter;
}
