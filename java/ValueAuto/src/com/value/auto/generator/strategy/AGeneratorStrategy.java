package com.value.auto.generator.strategy;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.value.auto.metadata.GeneratorModel;
import com.value.auto.provider.GlobalPropertiesProvider;
import com.value.auto.util.IOHelper;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public abstract class AGeneratorStrategy {
    private static final String WEBAPP_GENERATOR_INSERT_LOCATION = "webapp-generator-insert-location";
    public String getTargetFilename(GeneratorModel modelProvider, String templateFilepath,Configuration config) throws Exception {
        Map fileModel = getFilePathDataModel(modelProvider);
        StringWriter out = new StringWriter();
        Template template = new Template("filePath",new StringReader(templateFilepath),config);
        template.setEncoding("gbk");
        template.process(fileModel, out);
        return out.toString();
    }
    public Map getFilePathDataModel(GeneratorModel modelProvider) throws Exception {
        Map model = new HashMap();
        model.putAll(GlobalPropertiesProvider.getProperties()); //generator.properties的内容
        modelProvider.mergeFilePathModel(model);
        return model;
    }
    public Map getTemplateDataModel(GeneratorModel modelProvider) throws Exception {
        Map model = new HashMap();
        model.putAll(GlobalPropertiesProvider.getProperties()); //generator.properties的内容
        model.put("database", GlobalPropertiesProvider.database);
        modelProvider.mergeTemplateModel(model);
        return model;
    }
    public File getAbsoluteOutputFilePath(String targetFilename,String outRootDir) {
        File outputFile = new File(outRootDir,targetFilename);
        outputFile.getParentFile().mkdirs();
        return outputFile;
    }
    public boolean isFoundInsertLocation(Template template, Map model, File outputFile, StringWriter newFileContent) throws IOException, TemplateException {
        LineNumberReader reader = new LineNumberReader(new FileReader(outputFile));
        String line = null;
        boolean isFoundInsertLocation = false;
        
        PrintWriter writer = new PrintWriter(newFileContent);
        while((line = reader.readLine()) != null) {
            writer.println(line);
            // only insert once
            if(!isFoundInsertLocation && line.indexOf(WEBAPP_GENERATOR_INSERT_LOCATION) >= 0) {
                template.process(model,writer);
                writer.println();
                isFoundInsertLocation = true;
            }
        }
        
        writer.close();
        reader.close();
        return isFoundInsertLocation;
    }
    public void saveNewOutputFileContent(Template template, Map model, File outputFile) throws IOException, TemplateException {
        FileWriter out = new FileWriter(outputFile);
        template.process(model,out);
        out.close();
    }
    public void generate(GeneratorModel modelProvider,Configuration config,String templateRelativePath,String outputFilePath,String outRootDir) throws Exception{
        try {
            Template template = config.getTemplate(templateRelativePath);
            template.setEncoding("gbk");
            String targetFilename = getTargetFilename(modelProvider, outputFilePath,config);
            Map templateDataModel = getTemplateDataModel(modelProvider);
            File absoluteOutputFilePath = getAbsoluteOutputFilePath(targetFilename,outRootDir);
            // process inserting files
            if(absoluteOutputFilePath.exists()) {
                StringWriter newFileContentCollector = new StringWriter();
                if(isFoundInsertLocation(template, templateDataModel, absoluteOutputFilePath, newFileContentCollector)) {
                    System.out.println("[insert]\t generate content into:"+absoluteOutputFilePath);
                    IOHelper.saveFile(absoluteOutputFilePath, newFileContentCollector.toString());
                    return;
                }
            }
            //process generating files
            System.out.println("[generate]\t template:"+templateRelativePath+" to "+targetFilename);
            saveNewOutputFileContent(template, templateDataModel, absoluteOutputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    public abstract void generateNewFileOrInsertIntoFile(GeneratorModel modelProvider,
                                                         Configuration config,
                                                         String templateRelativePath,
                                                         String outputFilePath,
                                                         String outRootDir,
                                                         String moduleName)throws Exception;
}
