package com.tutorial.spring.ioc.basicioc.component;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
  "com.tutorial.spring.ioc.basicioc.javaconfig"
  //"com.tutorial.spring.ioc.basicioc.component"
})
public class AppConfig {

}
