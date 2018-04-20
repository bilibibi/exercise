<#include "/tableAlias.include">
package ${actionPackage};
import java.sql.Connection;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dfkj.constants.Constants;
import com.dfkj.exception.SystemException;
import com.dfkj.page.PageConfig;
import com.dfkj.page.ZtfpasPage;
import ${daoPackage}.${className}Dao;
import ${voPackage}.${className}Vo;
import com.dfkj.project.dao.exception.DataAccessException;
import com.dfkj.util.LoginInfor;
import com.dfkj.web.IAction;
public class ${className}Action implements IAction {
	
    public void perform(HttpServletRequest request,
            HttpServletResponse response, ServletContext context,
            Connection connection) throws ServletException, SystemException,
            DataAccessException {
        String type = request.getParameter("type");
        Integer pageNo = null;
        if(request.getParameter("pageNo")==null||"".equals(request.getParameter("pageNo"))){
            pageNo = 1;
        }else{
            pageNo = Integer.valueOf(request.getParameter("pageNo"));
        }
        String searchFid1 = request.getParameter("searchFid1");
        String searchEu2Name = request.getParameter("searchEu2Name");
        request.setAttribute("searchFid1", searchFid1);
        request.setAttribute("searchEu2Name", searchEu2Name);
        //取得所有举措
        InitiativesDao initiativesDao = new InitiativesDao();
        Vector initiativesVector = initiativesDao.conditionByFind(connection, "pageList",null,"-1");
        Vector vInitiatives = new Vector();
        if(!"addPage".equals(type)&&!"updatePage".equals(type)){
            vInitiatives.add(new String[]{"","全部举措"});
        }
        for(int i = 0 ; i<initiativesVector.size() ; i++){
            InitiativesVo vo = (InitiativesVo)initiativesVector.get(i);
            vInitiatives.add(new String[]{vo.getId()+"",vo.getName()});
        }
        request.setAttribute("vInitiatives", vInitiatives);
        //进入主页面
        if(type==null || "".equals(type)){
            ${className}Dao ${table.tableNameFirstLower}Dao = new ${className}Dao();
            Vector<${className}Vo> result = ${table.tableNameFirstLower}Dao.find${className}(connection,searchFid1,searchEu2Name);
            ZtfpasPage page = new ZtfpasPage(result, pageNo, PageConfig.PAGE_SIZE);
            result = page.getResult();
            request.setAttribute("result", result);
            request.setAttribute("page", page);
        //新增操作页面
        }else if("addPage".equals(type)){
        //新增操作
        }else if("add".equals(type)){
            ${className}Vo ${table.tableNameFirstLower}Vo = new ${className}Vo();
            <#list table.fields as field>
            String ${field.javaNameLower} = request.getParameter("${field.javaName}");
            </#list>
            <#list table.fields as field>
            ${table.tableNameFirstLower}Vo.set${field.javaName}(new ${field.javaType}(${field.javaNameLower}));
            </#list>
            ${className}Dao dao=new ${className}Dao();
            dao.add${className}(connection, ${table.tableNameFirstLower}Vo);
            Vector<${className}Vo> result=new Vector<${className}Vo>();
            result=dao.find${className}(connection,searchFid1,searchEu2Name);
            ZtfpasPage page = new ZtfpasPage(result, pageNo, PageConfig.PAGE_SIZE);
            result = page.getResult();
            request.setAttribute("result", result);
            request.setAttribute("page", page);
        //进入详情页面
        }else if("detailPage".equals(type)){
            String id="";
            id=request.getParameter("id");
            ${className}Dao dao=new ${className}Dao();
            ${className}Vo vo=new ${className}Vo();
            vo=dao.find${className}ById(connection, id);
            request.setAttribute("vo", vo);
        //进入update页面
        }else if("updatePage".equals(type)){
            String id="";
            id=request.getParameter("id");
            ${className}Dao dao=new ${className}Dao();
            ${className}Vo vo=new ${className}Vo();
            vo=dao.find${className}ById(connection, id);
            request.setAttribute("vo", vo);
        //update 操作
        }else if("update".equals(type)){
            ${className}Vo ${table.tableNameFirstLower}Vo = new ${className}Vo();
            <#list table.fields as field>
            String ${field.javaNameLower} = request.getParameter("${field.javaName}");
            </#list>
            <#list table.fields as field>
            ${table.tableNameFirstLower}Vo.set${field.javaName}(new ${field.javaType}(${field.javaNameLower}));
            </#list>
            ${className}Dao dao=new ${className}Dao();
            dao.update${className}(connection, ${table.tableNameFirstLower}Vo);
            Vector<${className}Vo> result=new Vector<${className}Vo>();
            result=dao.find${className}(connection,searchFid1,searchEu2Name);
            ZtfpasPage page = new ZtfpasPage(result, pageNo, PageConfig.PAGE_SIZE);
            result = page.getResult();
            request.setAttribute("result", result);
            request.setAttribute("page", page);
        }else if("delete".equals(type)){
            String id = request.getParameter("id");
            ${className}Dao dao=new ${className}Dao();
            dao.delete${className}(connection, id);
            Vector<${className}Vo> result=new Vector<${className}Vo>();
            result=dao.find${className}(connection,searchFid1,searchEu2Name);
            ZtfpasPage page = new ZtfpasPage(result, pageNo, PageConfig.PAGE_SIZE);
            result = page.getResult();
            request.setAttribute("result", result);
            request.setAttribute("page", page);
        }
        request.setAttribute("pageNo", pageNo);
    }
}


