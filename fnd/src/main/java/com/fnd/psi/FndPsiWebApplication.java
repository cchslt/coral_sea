package com.fnd.psi;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableSwagger2
@MapperScan("com.fnd.psi.mapper")
@Slf4j
public class FndPsiWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(FndPsiWebApplication.class, args);
	}

}

