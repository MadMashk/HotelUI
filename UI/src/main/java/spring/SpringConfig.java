package spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@ComponentScan({"view","actions", "hibernate", "items", "menuFactory", "model", "repository", "service" })
@PropertySource("classpath:properties/data.properties")
public class SpringConfig {

}
