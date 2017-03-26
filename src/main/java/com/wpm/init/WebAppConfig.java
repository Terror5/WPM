package com.wpm.init;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import com.wpm.formatter.*;

@Configuration
@EnableWebMvc
@Import({DataBaseConfig.class}) 
@ComponentScan("com.wpm")
@PropertySource("classpath:application.properties")
public class WebAppConfig extends WebMvcConfigurerAdapter{

	@Resource
	private Environment env;
    
	@Bean
    public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(new String[]{
				"/WEB-INF/defs/tiles-main.xml"
		});
        return tilesConfigurer;
    }

    @Bean
    public TilesViewResolver tilesViewResolver() {
        TilesViewResolver tilesViewResolver = new TilesViewResolver();
        tilesViewResolver.setViewClass(TilesView.class);
        tilesViewResolver.setOrder(2);
        return tilesViewResolver;
    }
    
    @Bean
    public ResourceBundleMessageSource messageSource() {
            ResourceBundleMessageSource source = new ResourceBundleMessageSource();               
            source.setBasename(env.getRequiredProperty("message.source.basename"));
            source.setUseCodeAsDefaultMessage(true);
            return source;
    }
    
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/resources/**")
    	.addResourceLocations("/resources/");
    }

    @Override
	public void addFormatters(FormatterRegistry registry){
    	registry.addFormatter(new UserFormatter());    	
    	registry.addFormatter(new TeamFormatter()); 
    	registry.addFormatter(new DateFormatter());
    	registry.addFormatter(new IterationFormatter());
    	registry.addFormatter(new WorkitemFormatter());
    	registry.addFormatter(new ProjectFormatter());
    	registry.addFormatter(new TaskFormatter());
    	registry.addFormatter(new RegistrationFormatter());
    	registry.addFormatter(new OpenuproleFormatter());
    	registry.addFormatter(new ByteFormatter());
    	
    }
    
    
    @Bean
    public JavaMailSenderImpl javaMailSenderImpl()
    {
    	JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
    	javaMailSenderImpl.setHost(env.getProperty("mail.host"));
    	javaMailSenderImpl.setUsername(env.getProperty("mail.username"));
    	javaMailSenderImpl.setPassword(env.getProperty("mail.password"));
    	javaMailSenderImpl.setPort(Integer.valueOf(env.getProperty("mail.port")));
    	Properties prop = new Properties();
    	prop.setProperty("mail.smtp.ssl.trust", env.getProperty("mail.smtp.ssl.trust"));
    	prop.setProperty("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
    	prop.setProperty("mail.smtp.auth",env.getProperty("mail.smtp.auth"));
    	javaMailSenderImpl.setJavaMailProperties(prop);
    	
    	return javaMailSenderImpl;
    }
    
  
}
