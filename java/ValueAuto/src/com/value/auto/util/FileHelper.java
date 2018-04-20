package com.value.auto.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

    public static String getRelativePath(File baseDir, File file) {
        if (baseDir.equals(file))
            return "";
        if (baseDir.getParentFile() == null)
            return file.getAbsolutePath().substring(baseDir.getAbsolutePath().length());
        return file.getAbsolutePath().substring(baseDir.getAbsolutePath().length() + 1);
    }

    public static void listFiles(File file, List collector) throws IOException {
        collector.add(file);
        if ((!file.isHidden() && file.isDirectory()) && !isIgnoreFile(file)) {
            File[] subFiles = file.listFiles();
            for (int i = 0; i < subFiles.length; i++) {
                listFiles(subFiles[i], collector);
            }
        }
    }

    private static boolean isIgnoreFile(File file) {
        List<String> ignoreList = new ArrayList<String>();
        ignoreList.add(".svn");
        ignoreList.add("CVS");
        ignoreList.add(".cvsignore");
        ignoreList.add("SCCS");
        ignoreList.add("vssver.scc");
        ignoreList.add(".DS_Store");
        for (int i = 0; i < ignoreList.size(); i++) {
            if (file.getName().equals(ignoreList.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isTemplateFile(String fileName) {

        if (fileName == null) {
            return false;
        }

        List<String> templateList = new ArrayList<String>();
        templateList.add(".include");
        templateList.add(".java");
        templateList.add(".xml");
        templateList.add(".txt");
        templateList.add(".mxml");
        templateList.add(".as");
        templateList.add(".jsp");
        templateList.add(".properties");
        templateList.add(".html");
        for (int i = 0; i < templateList.size(); i++) {
            if (fileName.toLowerCase().endsWith(templateList.get(i))) {
                return true;
            }
        }

        return false;
    }

    public static boolean isPageFile(String fileName) {
        if (fileName == null) {
            return false;
        }

        List<String> templateList = new ArrayList<String>();
        templateList.add(".jsp");
        templateList.add(".html");
        templateList.add(".mxml");
        for (int i = 0; i < templateList.size(); i++) {
            if (fileName.toLowerCase().endsWith(templateList.get(i))) {
                return true;
            }
        }

        return false;
    }

    public static boolean isSlaveScreenFile(String fileName) {

        if (fileName == null) {
            return false;
        }

        List<String> templateList = new ArrayList<String>();
        templateList.add("dao.java");
        templateList.add("daoimpl.java");
        templateList.add(".xml");
        templateList.add("vo.java");
        templateList.add("vo.as");
        templateList.add(".txt");

        for (int i = 0; i < templateList.size(); i++) {
            if (fileName.toLowerCase().endsWith(templateList.get(i))) {
                return true;
            }
        }

        return false;
    }

    public static void deleteFile(File file){
        if (file.exists()){
            if (file.isFile()){
                file.delete();
            } else if (file.isDirectory()){
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++){
                    deleteFile(files[i]);
                }
            }
            file.delete();
        }else {
            System.out.println("所删除的文件不存在！" + '\n');
        }
    }
}
