package com.value.auto.generator.strategy;

import com.value.auto.metadata.GeneratorModel;

import freemarker.template.Configuration;

public class GenSingleFileStrategy extends AGeneratorStrategy {

    @Override
    public void generateNewFileOrInsertIntoFile(GeneratorModel modelProvider,
                                                Configuration config,
                                                String templateRelativePath,
                                                String outputFilePath,
                                                String outRootDir,
                                                String moduleName)throws Exception {
        generate(modelProvider, config, templateRelativePath, outputFilePath, outRootDir);
    }

}
