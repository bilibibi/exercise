<#include "/tableAlias.include">
<#include "/java_copyright.include">

package ${fullPackage}.service;

import com.value.vo.Pagination;
import java.util.List;
import java.util.Map;

public interface I${className}Service {
<#assign classNameFirstLower=table.classNameFirstLower>
    /**
     * 根据条件加载
     *
     * @param params
     * @return
     * @author
     */
    public List<${className}VO> load${className}(Map<String,Object> params);

    /**
     * 分页加载
     *
     * @param page
     * @return
     * @author
     */
    public List<${className}VO> load${className}ForPage(Pagination page);

    /**
     * 新增
     *
     * @param ${classNameFirstLower}
     * @return
     * @author
     */
    public void insert${className}(${className}VO ${classNameFirstLower});

    /**
     * 修改
     *
     * @param ${classNameFirstLower}
     * @return
     * @author
     */
    public void update${className}(${className}VO ${classNameFirstLower});

    /**
     * 删除
     *
     * @param ${classNameFirstLower}
     * @return
     * @author
     */
    public void delete${className}(${className}VO ${classNameFirstLower});
}
