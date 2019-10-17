package fr.mjeu.dcmm.mongo.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration
@ComponentScan(basePackages = {"fr.mjeu.dcmm"})
@EnableMongoRepositories(basePackages = {"fr.mjeu.dcmm"})
@PropertySource(value="classpath:application.properties", ignoreResourceNotFound=true)
@PropertySource(value="file:${spring.config.location}", ignoreResourceNotFound=true)
public class DaoConfig {
	@Value( "${mongo.server}" )
	private String mongoServer;
	
	@Value( "${mongo.port}" )
	private int mongoPort;
	
	@Value( "${mongo.database}" )
	private String mongoDatabase;
	
	@Value( "${dcm.mongo.modifications.tracking}" )
	private String dcmMongoModificationsTracking;
	
	@Bean
	public MongoDbFactory mongoDbFactory() {
		MongoClient mongoClient = new MongoClient(mongoServer, mongoPort);
		return new SimpleMongoDbFactory(mongoClient, mongoDatabase);
	}
	
	@Bean
	public MongoTemplate mongoTemplate() {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
