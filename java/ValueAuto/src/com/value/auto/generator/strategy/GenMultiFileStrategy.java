package com.value.auto.generator.strategy;

import java.util.List;

import com.value.auto.metadata.GeneratorModel;
import com.value.auto.metadata.Module;
import com.value.auto.metadata.Table;

import freemarker.template.Configuration;

public class GenMultiFileStrategy extends AGeneratorStrategy {

    @Override
    public void generateNewFileOrInsertIntoFile(GeneratorModel modelProvider, Configuration config,
                                                String templateRelativePath, String outputFilePath,
                                                String outRootDir,
                                                String moduleName)throws Exception {
        Object obj = modelProvider.getModel();
        if(obj instanceof Module){
            Module module = (Module)obj;
            List<Table> tables = module.getTables();
            for(Table table : tables){
                List<Table> oneToMany = table.getOneToMany();
                for(Table many : oneToMany){
                    generateNewFileOrInsertIntoFile(new GeneratorModel(many),
                                                    config, 
                                                    templateRelativePath, 
                                                    outputFilePath, 
                                                    outRootDir,
                                                    moduleName);
                }
                if(table.getModuleName().equals(moduleName)){
                    generate(new GeneratorModel(table), config, templateRelativePath, outputFilePath, outRootDir);
                }
            }
        }else if(obj instanceof Table){
            Table table =(Table)obj;
            List<Table> oneToMany = table.getOneToMany();
            for(Table many : oneToMany){
                generateNewFileOrInsertIntoFile(new GeneratorModel(many),
                                                config, 
                                                templateRelativePath, 
                                                outputFilePath, 
                                                outRootDir,
                                                moduleName);
            }
            if(table.getModuleName().equals(moduleName)){
                generate(new GeneratorModel(table), config, templateRelativePath, outputFilePath, outRootDir);
            }
            
        }
        
    }

}
