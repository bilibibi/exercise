package com.value.auto.generator;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.value.auto.generator.strategy.AGeneratorStrategy;
import com.value.auto.generator.strategy.StrategyFactory;
import com.value.auto.metadata.Architecture;
import com.value.auto.metadata.GeneratorModel;
import com.value.auto.metadata.Module;
import com.value.auto.provider.GlobalPropertiesProvider;
import com.value.auto.util.FileHelper;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class Generator {
    private static final String WEBAPP_GENERATOR_INSERT_LOCATION = "webapp-generator-insert-location";
    /**
     * 模板文件的路径
     */
    private List<File> templateRootDirs = new ArrayList<File>();
    /**
     * 文件生成好后的路径
     */
    public String outRootDir;
    
    public Generator() {
    }
    
    public void setTemplateRootDir(File templateRootDir) {
        setTemplateRootDirs(new File[]{templateRootDir});
    }

    public void setTemplateRootDirs(File[] templateRootDirs) {
        this.templateRootDirs = Arrays.asList(templateRootDirs);
    }
    
    public void addTemplateRootDir(File f) {
        templateRootDirs.add(f);
    }
    
    /**
     * 生成module
     * @param Screen
     * @throws Exception
     */
    public void generate(Module module) throws Exception {
        
        if(module == null) 
            throw new IllegalStateException("'module' is null");
        generateByModelProvider(new GeneratorModel(module));

        
    }
    /**
     * 生成框架
     * @param architecture
     * @throws Exception
     */
    public void generate(Architecture architecture) throws Exception{
        if(architecture == null) 
            throw new IllegalStateException("'architecture' is null");
        try{
        	generateByModelProvider(new GeneratorModel(architecture));
        }catch(Exception e){
            throw e;
        }
    }
    public void generateByModelProvider(GeneratorModel modelProvider) throws Exception {
        if(templateRootDirs.size() == 0) throw new IllegalStateException("'templateRootDirs' is empty");
        
        System.out.println();
        System.out.println("***************************************************************");
        System.out.println("* BEGIN generate " + modelProvider.getDisaplyText());
        System.out.println("***************************************************************");
        for(int i = 0; i < this.templateRootDirs.size(); i++) {
            File templateRootDir = (File)templateRootDirs.get(i);
            try{
            	generateByModelProvider(templateRootDir, modelProvider);
            }catch(Exception e){
                throw e;
            }
        }
    }
    
    private void generateByModelProvider(File templateRootDir, GeneratorModel modelProvider) throws Exception {
        if(templateRootDir == null) throw new IllegalStateException("'templateRootDir' must be not null");
        System.out.println("-------------------load template from templateRootDir = '"+templateRootDir.getAbsolutePath()+"'");
        
        List templateFiles = new ArrayList();
        FileHelper.listFiles(templateRootDir, templateFiles);
        
        for(int i = 0; i < templateFiles.size(); i++) {
            
            File templateFile = (File)templateFiles.get(i);
            String templateRelativePath = FileHelper.getRelativePath(templateRootDir, templateFile);
            String outputFilePath = templateRelativePath;
            
            if(templateFile.isDirectory() || templateFile.isHidden())
                continue;
            if(templateRelativePath.trim().equals(""))
                continue;
            if(templateFile.getName().toLowerCase().endsWith(".include")){
                System.out.println("[skip]\t\t endsWith '.include' template:"+templateRelativePath);
                continue;
            }

            // process non-template files
            if (!FileHelper.isTemplateFile(templateRelativePath)) {
                String targetFilename = getTargetFilename(modelProvider, outputFilePath);
                File absoluteOutputFilePath = getAbsoluteOutputFilePath(targetFilename);
                if(!absoluteOutputFilePath.exists()) {
                    FileUtils.copyFile(templateFile, absoluteOutputFilePath);
                    System.out.println("[copy]\t\t :"+absoluteOutputFilePath);
                }
                else {
                    System.out.println("[skip]\t\t :"+absoluteOutputFilePath);
                }
                continue;
            }
            
            // for test
            int testExpressionIndex = -1;
            if((testExpressionIndex = templateRelativePath.indexOf('@')) != -1) {
                outputFilePath = templateRelativePath.substring(0, testExpressionIndex);
                String testExpressionKey = templateRelativePath.substring(testExpressionIndex+1);
                Map map = getFilePathDataModel(modelProvider);
                Object expressionValue = map.get(testExpressionKey);
                if(!"true".equals(expressionValue.toString())) {
                    System.out.println("[not-generate]\t test expression '@"+testExpressionKey+"' is false,template:"+templateRelativePath);
                    continue;
                }
            }
            // generate file
            try {
                //根据文件名取得生成器
                AGeneratorStrategy generator = StrategyFactory.getGeneratorStrategy(templateRelativePath);
                Module module = null;
                if(modelProvider.getModel() instanceof Module){
                    module = (Module)modelProvider.getModel();
                }
                generator.generateNewFileOrInsertIntoFile(modelProvider, 
                                                          newFreeMarkerConfiguration(), 
                                                          templateRelativePath, 
                                                          outputFilePath,
                                                          this.getOutRootDir(),
                                                          module==null?null:module.getModuleName()
                                                          );
            }catch(Exception e) {
                throw new RuntimeException("generate '" + modelProvider.getDisaplyText() + "' occur error,template is: " + templateRelativePath, e);
            }
        }
    }

    private Configuration newFreeMarkerConfiguration() throws IOException {
        Configuration config = new Configuration();
        
        FileTemplateLoader[] templateLoaders = new FileTemplateLoader[templateRootDirs.size()];
        for(int i = 0; i < templateRootDirs.size(); i++) {
            templateLoaders[i] = new FileTemplateLoader((File)templateRootDirs.get(i));
        }
        MultiTemplateLoader multiTemplateLoader = new MultiTemplateLoader(templateLoaders);
        
        config.setTemplateLoader(multiTemplateLoader);
        config.setNumberFormat("###############");
        config.setBooleanFormat("true,false");
        return config;
    }

    private String getTargetFilename(GeneratorModel modelProvider, String templateFilepath) throws Exception {
        Map fileModel = getFilePathDataModel(modelProvider);
        StringWriter out = new StringWriter();
        Template template = new Template("filePath",new StringReader(templateFilepath),newFreeMarkerConfiguration());
        template.process(fileModel, out);
        return out.toString();
    }
    
    private Map getFilePathDataModel(GeneratorModel modelProvider) throws Exception {
        Map model = new HashMap();
        model.putAll(GlobalPropertiesProvider.getProperties()); //generator.properties的内容
        modelProvider.mergeFilePathModel(model);
        return model;
    }
    private File getAbsoluteOutputFilePath(String targetFilename) {
        String outRoot = getOutRootDir();
        File outputFile = new File(outRoot,targetFilename);
        outputFile.getParentFile().mkdirs();
        return outputFile;
    }


    public void clean() throws IOException {
        String outRoot = getOutRootDir();
        FileUtils.deleteDirectory(new File(outRoot));
        System.out.println("[Delete Dir]    "+outRoot);
    }

    private String getOutRootDir() {
        if(outRootDir == null) throw new IllegalStateException("'outRootDir' property must be not null.");
        return outRootDir;
    }

}
