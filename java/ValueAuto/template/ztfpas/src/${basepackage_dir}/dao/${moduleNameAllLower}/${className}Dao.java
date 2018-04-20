<#include "/tableAlias.include">
package ${daoPackage};

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import ${voPackage}.${className}Vo;
public class ${className}Dao {

    public Vector<${className}Vo> find${className}(Connection conn,String fid1,String eu2Name){
        PreparedStatement statement=null;
        Vector<${className}Vo> result=new Vector<${className}Vo>();
        ResultSet rs=null;
        StringBuffer sql=new StringBuffer();
        sql.append("select ");
        <#list table.fields as field>
        sql.append("${field.sqlName}, ");
        </#list>
        sql.append(" from ${table.tableName} ");
        if(fid1!=null&&!"".equals(fid1)){
            sql.append(" and m2.fid1=").append(fid1);
        }
        if(eu2Name!=null&&!"".equals(eu2Name)){
            sql.append(" and m2.m2name like '%").append(eu2Name).append("%' ");
        }
        try {
            statement=conn.prepareStatement(sql.toString());
            rs=statement.executeQuery();
            while(rs.next()){
                ${className}Vo vo=new ${className}Vo();
                <#list table.fields as field>
                <#if field.javaType='string'>
                vo.set${field.javaName}(rs.getString("${field.sqlName}"));
                <#elseif field.javaType='date'>
                vo.set${field.javaName}(rs.getDate("${field.sqlName}"));
                </#if>
                </#list>
                result.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                rs.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    public void add${className}(Connection conn,${className}Vo ${table.tableNameFirstLower}Vo){
        String id="";
        ResultSet rs=null;
        PreparedStatement statement=null;
        StringBuffer sql=new StringBuffer();
        sql.append("select case when (max(id2)+1) is null then 1 else (max(id2)+1) end as id from ${table.tableName}");
        try {
            statement=conn.prepareStatement(sql.toString());
            rs=statement.executeQuery();
            while(rs.next()){
                id=rs.getString("id");
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        StringBuffer sql1=new StringBuffer();
        sql1.append("insert into ${table.tableName}(id2,fid1,m2name,orderBy2,available2,u2,udate2) values("+id+",?,?,?,1,?,sysdate)");
        <#list table.fields as field>
        sql1.append("${field.sqlName}, ");
        </#list>
        sql1.append(") values ( "+id+" <#list table.fields as field>,?</#list>)");
        try {
            statement=conn.prepareStatement(sql1.toString());
            <#list table.fields as field>
            <#if field.javaType='string'>
            statement.setString(${field_index},${table.tableNameFirstLower}Vo.get${field.javaName}()));
            <#elseif field.javaType='date'>
            statement.setDate(${field_index},${table.tableNameFirstLower}Vo.get${field.javaName}()));
            </#if>
            </#list>
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                rs.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public ${className}Vo find${className}ById(Connection conn,String id){
        PreparedStatement statement=null;
        ResultSet rs=null;
        ${className}Vo vo=null;
        StringBuffer sql=new StringBuffer();
        sql.append("select ");
        <#list table.fields as field>
        sql.append("${field.sqlName}, ");
        </#list>
        sql.append(" from ${table.tableName} where ${table.keyField.sqlName}=?");
        try {
            statement=conn.prepareStatement(sql.toString());
            statement.setString(1, id);
            rs=statement.executeQuery();
            while(rs.next()){
                vo=new ${className}Vo();
                <#list table.fields as field>
                <#if field.javaType='string'>
                vo.set${field.javaName}(rs.getString("${field.sqlName}"));
                <#elseif field.javaType='date'>
                vo.set${field.javaName}(rs.getDate("${field.sqlName}"));
                </#if>
                </#list>
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                rs.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return vo;
    }
    
    public void update${className}(Connection conn,${className}Vo ${table.tableNameFirstLower}Vo){
        PreparedStatement statement=null;
        StringBuffer sql=new StringBuffer();
        sql.append("update ${table.tableName} set ");
        
        <#list table.fields as field>
        if(${table.tableNameFirstLower}Vo.get${field.javaName}()!=null && !"".equals(${table.tableNameFirstLower}Vo.get${field.javaName}())){
            sql.append(",${field.sqlName}="+${table.tableNameFirstLower}Vo.get${field.javaName}()+" ");
        }
        </#list>
        sql.append(" where ${table.keyField.sqlName}="+${table.tableNameFirstLower}Vo.get${table.keyField.javaName}()+" ");
        try {
            statement=conn.prepareStatement(sql.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void delete${className}(Connection conn,String id){        //逻辑删除用户，ua04修改
        PreparedStatement statement=null;
        StringBuffer sql=new StringBuffer();
        sql.append("update ${table.tableName}  set available2 =0 where ${table.keyField.sqlName}=?");
        try {
            statement=conn.prepareStatement(sql.toString());
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Vector<${className}Vo> search${className}(Connection conn,String fid1,String eu2Name){
        PreparedStatement statement=null;
        Vector<${className}Vo> result=new Vector<${className}Vo>();
        ResultSet rs=null;
        StringBuffer sql=new StringBuffer();
        sql.append("select ");
        <#list table.fields as field>
        sql.append("${field.sqlName}, ");
        </#list>
        sql.append(" from ${table.tableName} ");
        try {
            statement=conn.prepareStatement(sql.toString());
            rs=statement.executeQuery();
            while(rs.next()){
                ${className}Vo vo=new ${className}Vo();
                <#list table.fields as field>
                <#if field.javaType='string'>
                vo.set${field.javaName}(rs.getString("${field.sqlName}"));
                <#elseif field.javaType='date'>
                vo.set${field.javaName}(rs.getDate("${field.sqlName}"));
                </#if>
                </#list>
                result.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                rs.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}


