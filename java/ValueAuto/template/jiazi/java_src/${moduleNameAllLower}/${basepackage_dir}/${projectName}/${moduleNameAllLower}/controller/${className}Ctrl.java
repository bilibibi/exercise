<#include "/tableAlias.include">
<#include "/java_copyright.include">

package ${fullPackage}.service.impl;


import java.util.List;

import com.jiazi.dao.orm.ibatis.DaoRouter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
@Controller
public class ${className}Ctrl{
    <#assign classNameFirstLower=table.classNameFirstLower>
    private static final Log logger = LogFactory.getLog(${className}Ctrl.class);

    /**
     * 显示列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/${classNameFirstLower}s", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request){
        return null;
    }

    /**
     * 新增页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/${classNameFirstLower}/new", method = RequestMethod.GET)
    public ModelAndView newSession(HttpServletRequest request){
        return null;
    }

    /**
     * 创建
     * @param request
     * @return
     */
    @RequestMapping(value = "/${classNameFirstLower}", method = RequestMethod.POST)
    public ModelAndView create(HttpServletRequest request){
        return null;
    }

    /**
     * 编辑页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/${classNameFirstLower}/{id}/edit", method = RequestMethod.GET)
    public ModelAndView edit(HttpServletRequest request){
        return null;
    }
    /**
     * 修改
     * @param request
     * @return
     */
    @RequestMapping(value = "/${classNameFirstLower}/{id}", method = RequestMethod.PATCH)
    public ModelAndView update(HttpServletRequest request){
        return null;
    }
    /**
     * 删除
     * @param request
     * @return
     */
    @RequestMapping(value = "/${classNameFirstLower}/{id}", method = RequestMethod.DELETE)
    public ModelAndView destroy(HttpServletRequest request){
        return null;
    }

    @Autowired
    private DaoRouter daoRouter;
}
