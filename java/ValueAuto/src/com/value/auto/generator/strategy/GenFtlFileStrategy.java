package com.value.auto.generator.strategy;

import java.util.List;

import com.value.auto.metadata.Architecture;
import com.value.auto.metadata.GeneratorModel;
import com.value.auto.metadata.Module;
import com.value.auto.metadata.View;

import freemarker.template.Configuration;

public class GenFtlFileStrategy extends AGeneratorStrategy {

    @Override
    public void generateNewFileOrInsertIntoFile(GeneratorModel modelProvider, Configuration config,
                                                String templateRelativePath, String outputFilePath,
                                                String outRootDir,
                                                String moduleName)throws Exception {
        Object obj = modelProvider.getModel();
        if(obj instanceof Module){
            Module module = (Module)obj;
            List<View> views  = module.getViews();
            for(View view : views){
                generateNewFileOrInsertIntoFile(new GeneratorModel(view),
                                                config, 
                                                templateRelativePath, 
                                                outputFilePath, 
                                                outRootDir,
                                                moduleName);
            }
        }else if(obj instanceof View){
            generate(new GeneratorModel(obj), config, templateRelativePath, outputFilePath, outRootDir);
        }else if(obj instanceof Architecture){
            generate(new GeneratorModel(obj), config, templateRelativePath, outputFilePath, outRootDir);
        }

    }

}
