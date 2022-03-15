package com.example.errand;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;


@SpringBootApplication
@MapperScan("com.example.errand.mapper")
public class ErrandApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErrandApplication.class, args);
        DocsConfig config = new DocsConfig();
        config.setProjectPath("D:\\软工\\毕业设计\\Errand"); // 项目根目录
        config.setProjectName("Errand"); // 项目名称
        config.setApiVersion("V1.0");       // 声明该API的版本
        config.setDocsPath("D:\\软工\\毕业设计\\Errand\\docs"); // 生成API 文档所在目录
        config.setAutoGenerate(Boolean.TRUE);  // 配置自动生成
        Docs.buildHtmlDocs(config); // 执行生成文档
    }

}
