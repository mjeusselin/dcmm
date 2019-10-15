package fr.mjeu.dcmm.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

@Configuration
@PropertySource("classpath:application.properties")
public class DaoConfig {
	@Value( "${mongo.server}" )
	private String mongoServer;
	
	@Value( "${mongo.port}" )
	private int mongoPort;
	
	@Value( "${mongo.database}" )
	private String mongoDatabase;
	
	public @Bean MongoClient mongoClient() {
		return new MongoClient(this.mongoServer, this.mongoPort);
	}
	
	public @Bean MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoClient(), this.mongoDatabase);
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
