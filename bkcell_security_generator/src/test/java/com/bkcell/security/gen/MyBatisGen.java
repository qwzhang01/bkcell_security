package com.bkcell.security.gen;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyBatisGen {
    public static void main(String[] args) {
        String path = System.getProperty("user.dir") + "/bkcell_security_generator/src/test/resources";
        MyBatisGen.generator(path);
        System.out.println("done!");
    }

    public static void generator(String rootPath) {
        List<String> warnings = new ArrayList<>();
        try {
            File configFile = new File(rootPath + "/mybatis-generator.xml");
            //解析
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);
            //是否覆盖
            DefaultShellCallback dsc = new DefaultShellCallback(true);
            MyBatisGenerator mg = new MyBatisGenerator(config, dsc, warnings);
            mg.generate(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
