package fr.mjeu.dcmm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DcmApplication {
	
	static Logger logger = LoggerFactory.getLogger(DcmApplication.class);
	
	@Value( "${data.element.tag}" )
	private String dataElementTag;
	
	@Value( "${data.value.field}" )
	private String dataValueField;
	
	public static void main(String[] args) {
        SpringApplication.run(DcmApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            logger.info("Launching Dicom Manager Application with parameters : ");
            logger.info("data.element.tag="+dataElementTag);
            logger.info("data.value.field="+dataValueField);
        };
    }
}
