package neueda.tinyurl;

import javax.security.auth.message.config.AuthConfigFactory;

import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@SpringBootApplication
@ComponentScan(basePackages  = {"neueda.tinyurl","neueda.tinyurl.controller","neueda.tinyurl.orm"})
@EnableAutoConfiguration
public class TinyUrlApplicationTests extends SpringBootServletInitializer{

	public static void main(String[] args) {
		if(AuthConfigFactory.getFactory() == null) {
			AuthConfigFactory.setFactory(new AuthConfigFactoryImpl());
		}
		SpringApplication.run(TinyUrlApplicationTests.class, args);
	}

}

