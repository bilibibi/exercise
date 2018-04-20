package com.value.auto.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.value.auto.exception.DatabaseException;
import com.value.auto.metadata.Field;
import com.value.auto.metadata.Module;
import com.value.auto.metadata.Project;
import com.value.auto.metadata.Table;
import com.value.auto.metadata.View;

public class ProviderHelper {

    private static Log log = LogFactory.getLog(ProviderHelper.class);
    
    /**
     * 根据配置文件database的信息，从数据库中取得相应的table存放于
     * project对象
     * @param project
     * @return
     */
    public static Project process(Project project) throws Exception {
        
        if (project == null)
            return project;
        List<Table> database = project.getDatabase();
        Map<String,Table> tableMap = new HashMap<String,Table>();
        for(int i=0 ; i<database.size() ; i++){
            Table table = database.get(i);
            if(tableMap.containsKey(table.getTableName())){
                log.error("the database config error:the table:"+table.getTableName()+" has exist");
                throw new DatabaseException("the database config error:the table:"+table.getTableName()+" has exist");
            }
            Table newTable = new Table();
            try { 
                newTable = DbTableFactory.getInstance().getTable(table.getTableName());   
                newTable.setModuleName(table.getModuleName());
                newTable.setFunctionName(table.getFunctionName());
                newTable.setLinkKey(table.getLinkKey());
                newTable.setKey(table.getKey());
                newTable.setOutPutType(table.getOutPutType());
                newTable.setClassName(table.getClassName());
                String keyStr = table.getKey();
                if (keyStr != null && keyStr.length() > 0) {
                    Field keyField = newTable.getFieldByName(keyStr);
                    newTable.setKeyField(keyField);
                    if (keyField == null) {
                        log.error("the database config error:Field not found for key field with name " + keyStr);
                        throw new DatabaseException("the database config error:Field not found for key field with name " + keyStr);
                    }
                }
                String linkKey = table.getLinkKey();
                if(linkKey!=null && linkKey.length()>0){
                    Field linkKeyField = newTable.getFieldByName(linkKey);
                    newTable.setLinkField(linkKeyField);
                    if(linkKeyField == null){
                        log.error("the database config error:Field not found for link field with name " + linkKey);
                        throw new DatabaseException("the database config error:Field not found for link field with name " + linkKey);
                    }
                }
                tableMap.put(newTable.getTableName(), newTable);
                database.add(i, newTable);
                database.remove(i+1);
            }
            catch(Exception e) {
                log.error("process table failed: " + e);
                throw e;
            }
            
        }
        List<Module> modules = project.getModules();
        if (modules != null && modules.size() > 0) {
            int size = modules.size();
            for (int i = 0; i < size; i++) {
                try{
                    process(modules.get(i),tableMap);
                }catch(Exception e){
                    throw e;
                }
            }
        }
        
        return project;
    }
    
    /**
     * 根据配置文件module的信息，赋予其相关的table，并设置table之间的关系
     * 并配置view的相关数据
     * @param module
     * @param tableMap
     * @return
     */
    public static Module process(Module module,Map<String,Table> tableMap) throws Exception{
        
        if (module == null)
            return module;
        List<Table> tables = module.getTables();
        for(int i=0; i<tables.size() ; i++){
            Table table = tables.get(i);
            try{
                tables.add(i, process(table,tableMap,module.getModuleName()));
            }catch(Exception e){
                throw e;
            }
            tables.remove(i+1);
        }
        List<View> views = module.getViews();
        for(View view : views){
            view.setModuleName(module.getModuleName());
            view.setModuleChinaName(module.getModuleChinaName());
            Table table = tableMap.get(view.getTableName());
            view.setTable(table);
        }
        return module;
        
    }

    /**
     * 根据配置文件配置的table，取得数据库中的table，并设置table之间的关系
     * @param table
     * @param tableMap
     * @return
     */
    public static Table process(Table table,Map<String,Table> tableMap,String moduleName) throws Exception
    {
        Table newTable = tableMap.get(table.getTableName());
        if(newTable==null){
            log.error("moduleName:"+moduleName+" get error: the table:"+table.getTableName()+" is not exist");
            throw new DatabaseException("moduleName:"+moduleName+" get error: the table:"+table.getTableName()+" is not exist");
        }

        List<Table> oneToMany = table.getOneToMany();
        for(int i = 0 ; i<oneToMany.size() ;i++){
            Table newManyTable = process(oneToMany.get(i),tableMap,moduleName);
            newManyTable.getManyToOne().add(newTable);
            oneToMany.add(i,newManyTable);
            oneToMany.remove(i+1);
        }
        newTable.setOneToMany(oneToMany);
        return newTable;
    }
    /**
     * 检查配置文件配置的table是否再数据库中存在
     * @param tableName
     * @param tableMap
     * @return
     */
    public static boolean checkTableExist(String tableName,Map<String,Table> tableMap){
        if(tableName==null){
            return false;
        }
        if(tableMap.containsKey(tableName)){
            return true;
        }
        return false;
    }
}
