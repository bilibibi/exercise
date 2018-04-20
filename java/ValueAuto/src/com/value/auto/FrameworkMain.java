package com.value.auto;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.value.auto.generator.Generator;
import com.value.auto.metadata.Architecture;
import com.value.auto.metadata.Module;
import com.value.auto.metadata.Project;
import com.value.auto.provider.GlobalPropertiesProvider;
import com.value.auto.util.FileHelper;
import com.value.auto.util.ProviderHelper;
import com.value.auto.util.ViewConfigUtil;

public class FrameworkMain {
	
	private static Log log = LogFactory.getLog(FrameworkMain.class);
	static ConsoleTextArea consoleTextArea = null;
	static {
	    try {
            consoleTextArea = new ConsoleTextArea();
        }
        catch(IOException e) {
            System.err.println(
                    "不能创建LoopedStreams：" + e);
            System.exit(1);
        }

        consoleTextArea.setFont(java.awt.Font.decode("monospaced"));
        System.out.println("***************************************************");
        System.out.println("********      Code generation starts      *********");
        System.out.println("***************************************************");
	}
	private static final String DEFAULT_SOURCE_DIR = "applications";
	private static final String DEFAULT_DEST_DIR = GlobalPropertiesProvider.getProperties().getProperty("target.outputPath"); //"generated";
	private static final String DEFAULT_ARCHITECTURE_OUTPUTPATH=GlobalPropertiesProvider.getProperties().getProperty("target.architecture.outputPath");
	private static final String DEFAULT_MODULE_OUTPUTPATH=GlobalPropertiesProvider.getProperties().getProperty("target.module.outputPath");
	private static final String DEFAULT_GENERATOR_CONTENT="all";
	private static final String DEFAULT_GENERATOR_MODULE="all";
	private static Map<String, Object> arguments = new HashMap<String, Object>();
	static{
		arguments.put("source", DEFAULT_SOURCE_DIR);
		arguments.put("dest", DEFAULT_DEST_DIR);
		arguments.put("modules", DEFAULT_GENERATOR_MODULE);
		arguments.put("generatorContent", DEFAULT_GENERATOR_CONTENT);
	}
	
	// args :
	// -source name (optional) default: applications
	// -dest (optional) default: generated
	// spring flex etc. --- generated modules parameters
	public static void main(String[] argv) throws Exception {
	    try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        	e.printStackTrace();
        }

        JFrame frame = new JFrame("代码自动生成");
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        frame.getContentPane().add(new JScrollPane(consoleTextArea),
            java.awt.BorderLayout.CENTER);
        frame.setBounds(0, 0, screenSize.width, screenSize.height-30);
        frame.setVisible(true);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(
                java.awt.event.WindowEvent evt) {
                System.exit(0);
            }
        });
        // 1. read the argv
		if (!handleCommandArgument(argv)){
			log.error("FrameworkMain>> handleCommandArgument: error.");
			return;
		}
		// 用castor解组xml配置文件
		Project configProject = ViewConfigUtil.parseViewConfig();
		
		// 根据配置文件取得数据库相应的tables
		Project result = null;
		try{
		    result = ProviderHelper.process(configProject);
		}catch(Exception e){
		    log.error(e.getMessage());
		    throw e;
		}
		// 生成的内容，如：只生成module  -gc module
		String generatorContent = (String)arguments.get("generatorContent");
        String sourcePath ="template";
		if("architecture".equals(generatorContent)||"all".equals(generatorContent)){
            sourcePath = GlobalPropertiesProvider.getProperties().getProperty("architecturePath");
            Generator g = new Generator();
            g.setTemplateRootDir(new File(sourcePath).getAbsoluteFile());
            g.outRootDir = (String) arguments.get("dest")+DEFAULT_ARCHITECTURE_OUTPUTPATH;
            Architecture arc = new Architecture();
            arc.setDatabase(result.getDatabase());
            g.generate(arc);
        }
		if("module".equals(generatorContent)||"all".equals(generatorContent)){
            sourcePath = GlobalPropertiesProvider.getProperties().getProperty("modulePath");
            String projectPath = FrameworkMain.class.getClassLoader().getResource(".").getPath();
            if (result != null) {
                List<Module> modules = result.getModules();
                GlobalPropertiesProvider.database = result.getDatabase();
                if (modules != null&&modules.size()>0) {
                    int size = modules.size();
                    String outRootDir = (String) arguments.get("dest")+DEFAULT_MODULE_OUTPUTPATH;
                    File outFile = new File(outRootDir);
                    File temp_outFile = new File(outRootDir+"_temp");
                    if(outFile.exists()){
                        outFile.renameTo(temp_outFile);
                    }
                    try{
                        for (int i = 0; i < size; i++) {
                            Module module = (Module) modules.get(i);
                            if(isContainModule(module.getModuleName(),arguments.get("modules"))){
                                Generator g = new Generator();
                                g.setTemplateRootDir(new File(projectPath+"../"+sourcePath).getAbsoluteFile());
                                g.outRootDir = outRootDir;
                                g.generate(module);
                            }
                        }
                        if(temp_outFile.exists()){
                            FileHelper.deleteFile(temp_outFile);
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                        FileHelper.deleteFile(outFile);
                        temp_outFile.renameTo(outFile);
                        FileHelper.deleteFile(temp_outFile);
                    }
                }
            }
        }
		System.out.println("***************************************************");
		System.out.println("********     Code generation finished     *********");
		System.out.println("***************************************************");
	}
	
	private static boolean handleCommandArgument(String[] argv) {
		if (argv != null) {
			List<String> mainArguments = new ArrayList<String>();
			for (int i = 0; i < argv.length;) {
				String argument = argv[i++];
				if (argument == null || argument.trim().length() == 0) {
					continue;
				}
				argument = argument.trim().toLowerCase();

				if ("-source".equals(argument)) {
					while (true) {
						if (i >= argv.length) {
							// ERROR
							return false;
						}
						String source = argv[i++];
						if (StringUtils.isEmpty(source)) {
							continue;
						}
						arguments.put("source", source);
						break;
					}
				}

				if ("-dest".equals(argument)) {
					while (true) {
						if (i >= argv.length) {
							// ERROR
							return false;
						}
						String dest = argv[i++];
						if (StringUtils.isEmpty(dest)) {
							continue;
						}
						arguments.put("dest", dest);
						break;
					}
				}
				//gc<==>generatorContent
				if ("-gc".equals(argument)) {
                    while (true) {
                        if (i >= argv.length) {
                            // ERROR
                            return false;
                        }
                        String dest = argv[i++];
                        if (StringUtils.isEmpty(dest)) {
                            continue;
                        }
                        arguments.put("generatorContent", dest);
                        break;
                    }
                }

				if (!argument.startsWith("-")) {
					if (StringUtils.isEmpty(argument)) {
						continue;
					}
					mainArguments.add(argument);
				}
			}
			if (mainArguments.size() > 0) {
				arguments.put("modules", mainArguments);
			}
		}
		return true;
	}
	public static boolean isContainModule(String moduleName , Object moduleNames){
	    if(moduleNames instanceof String){
	        return true;
	    }
	    List<String> listName = (List<String>)moduleNames;
	    if(moduleName==null||"".equals(moduleName)){
	        return false;
	    }
	    for(String name : listName){
	        if(moduleName.trim().toLowerCase().equals(name)){
	            return true;
	        }
	    }
	    return false;
	}
	

}
