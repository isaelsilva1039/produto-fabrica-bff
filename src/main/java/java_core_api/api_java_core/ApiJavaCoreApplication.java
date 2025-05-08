package java_core_api.api_java_core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiJavaCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiJavaCoreApplication.class, args);
	}

}
