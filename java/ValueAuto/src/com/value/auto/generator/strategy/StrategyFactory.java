package com.value.auto.generator.strategy;

import com.value.auto.util.FileHelper;

public class StrategyFactory {
    private StrategyFactory(){
    }
    public static AGeneratorStrategy getGeneratorStrategy(String templateRelativePath){
        AGeneratorStrategy generator = null;
        String fileName=null;
        if(templateRelativePath.indexOf("\\")!=-1){
            fileName = templateRelativePath.substring(templateRelativePath.indexOf("\\"));
        }else{
            fileName = templateRelativePath;
        }
        
        if(FileHelper.isPageFile(fileName)){
            generator = new GenPageFileStrategy();
        }else if(fileName.indexOf("${className}")!=-1){
            generator = new GenMultiFileStrategy();
        }else{
            generator = new GenSingleFileStrategy();
        }
        return generator;
    }
}
