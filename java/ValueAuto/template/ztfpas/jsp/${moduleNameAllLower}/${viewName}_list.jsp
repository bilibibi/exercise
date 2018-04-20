<#include "/viewAlias.include"> 
<#include "/xml_copyright.include">
<#include "/characterEscape.include"> 
<#include "/control.include"/>
<%@page import="java.util.Vector"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.dfkj.db.DbManager"%>
<%@page import="${voPackage}.${className}Vo"%>
<%@page import="com.dfkj.page.ZtfpasPage"%>
<%@taglib uri="dfkj" prefix="dfkj" %>
<%@taglib uri="ztfpas" prefix="ztfpas" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gbk"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title></title>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/jsp/css/table.css">
    <%
    	Vector v=(Vector)request.getAttribute("result");
    	ZtfpasPage pageParams = (ZtfpasPage)request.getAttribute("page");
    	int currentPage = pageParams.getCurrentPageNumber();
    	int totalPageSize = pageParams.getTotalPage();
    	String requestUrl = request.getContextPath()+"/MainController.do?ActionName=${actionPackage}.${className}Action&NextPage=/jsp/${moduleNameLower}/${classNameLower}_list.jsp";
    %>

</head>

<body>
    <script type="text/javascript">
    	function doFind(){
    			thisForm.action="<%=request.getContextPath()%>/MainController.do?ActionName=${actionPackage}.${className}Action&type=find&NextPage=/jsp/${moduleNameLower}/${classNameLower}_list.jsp";
    			thisForm.submit();
    	}
    	function doDelete(id){
        	if(confirm("确定删除？")){
				thisForm.action="<%=request.getContextPath()%>/MainController.do?ActionName=${actionPackage}.${className}Action&type=delete&pageNo=<%=currentPage %>&NextPage=/jsp/${moduleNameLower}/${classNameLower}_list.jsp&id="+id+"";
				thisForm.submit();
			}
        }
    </script>
<form name="thisForm" method="post">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0"
       style="border:1px solid #9dbacc;margin-left:3px;" bgcolor="#FFFFFF">
    <tr>
        <td height="38">
            <div class="tilti">
                <ul>
                    <li class="li1"></li>
                    <li class="li2">${moduleChinaName}维护</li>
                    <li class="li3"></li>
                    <div class="clear"></div>
                </ul>
            </div>
        </td>
    </tr>
    <tr>
        <td class="jc">
            &nbsp; &nbsp;<img src="<%=request.getContextPath()%>/jsp/images/tb_01.jpg" align="absbottom" style="margin-right:5px;"/>
            <a href="<%=request.getContextPath()%>/MainController.do?ActionName=${actionPackage}.${className}Action&type=addPage&pageNo=<%=currentPage %>&NextPage=/jsp/${moduleNameLower}/${classNameLower}Add.jsp">
            	添加
            </a>
        </td>
    </tr>
    <tr>
    	<td class="jc">
    		&nbsp; &nbsp;
    		<input type="button" onclick="doFind()" value="搜索"/>
    	</td>
    	
    </tr>
    <tr>
        <td valign="top">
            <table width="100%" cellpadding="0" cellspacing="0">
                <tr class="td1">
                    <td>&nbsp;</td>
                    <#list view.retrieveControl as control>
                    <td>${control.chinaName}</td>
                    </#list>
                    <td>操作</td>
                </tr>
               <%
               		for(int i=0;i<v.size();i++){
               			${className}Vo vo=(${className}Vo)v.elementAt(i);
               		
               %>
                <tr class="td3">
                	<td>&nbsp;</td>
                	<#list view.retrieveControl as control>
                    <td><%=vo.get${control.fieldJavaName}()%></td>
                    </#list>
                    <td>
                        <img src="<%=request.getContextPath()%>/jsp/images/tb_03.jpg" align="absbottom" style="margin-right:5px;"/><a href="
                        		<%=request.getContextPath()%>/MainController.do?ActionName=${actionPackage}.${className}Action&NextPage=/jsp/${moduleNameLower}/${classNameLower}Update.jsp&type=updatePage&pageNo=<%=currentPage %>&id=<%=vo.getId2().toString()%>" target="body">修改</a>
                        <img src="<%=request.getContextPath()%>/jsp/images/tb_02.jpg" align="absbottom" style="margin-right:5px;"/><a href="#" onclick="doDelete('<%=vo.getId2().toString() %>')">删除</a>
                    </td>
                </tr>
            	<%
               		}
            	%>
            </table>

        </td>
    </tr>
    <tr>
    	<td class="kiu">
    		<ztfpas:page totalPageSize="<%=totalPageSize %>" contextPath="<%=request.getContextPath()%>" 
    		requestUrl="<%=requestUrl%>" 
    		curentPageNo="<%=currentPage %>"></ztfpas:page>
    	</td>
    </tr>
</table>
</form>
</body>
</html>