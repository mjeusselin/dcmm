package fr.mjeu.dcmm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import fr.mjeu.dcmm.exception.DcmException;

@SpringBootApplication
public class DcmApplication {
	
	static Logger logger = LoggerFactory.getLogger(DcmApplication.class);
	
	private static final String DEBUG_DCMM_EXECUTION = "DcmManager execution";
	private static final String DEBUG_DCMM_INSTANCIATION = "DcmManager instanciation";
	private static final String DEBUG_ENDING_STATUS = "end status ";
	private static final String INFO_LAUNCHING = "Launching Dicom Manager Application with parameters : ";
	private static final String INFO_PARAM_CHANGE_PATIENT_ID_VALUE = "dcm.change.patient.id.value=";
	private static final String INFO_PARAM_CHANGE_PATIENT_ID_OVERWRITE = "dcm.change.patient.id.overwrite.original.file=";
	private static final String INFO_PARAM_IN_FOLDER_ABSOLUTE_PATH_STR = "dcm.in.folder.absolute.path.str=";
	private static final String INFO_PARAM_IN_FILENAME = "dcm.in.filename=";
	private static final String INFO_PARAM_OUT_FOLDER_ABSOLUTE_PATH_STR = "dcm.out.folder.absolute.path.str=";
	private static final String INFO_PARAM_OUT_FILENAME_SUFFIX = "dcm.out.filename.suffix=";
	private static final int STATUS_INIT = -1;
	private static final int STATUS_SUCCESS = 0;
	private static final int STATUS_ERROR = 3;
	
	@Value( "${dcm.change.patient.id.value}" )
	private String changePatientIdValue;
	
	@Value( "${dcm.change.patient.id.overwrite.original.file}" )
	private String changePatientIdOverwriteOriginalFile;
	
	@Value( "${dcm.in.folder.absolute.path.str}" )
	private String inFolderAbsolutePathStr;
	
	@Value( "${dcm.in.filename}" )
	private String inFilename;
	
	@Value( "${dcm.out.folder.absolute.path.str}" )
	private String outFolderAbsolutePathStr;
	
	@Value( "${dcm.out.filename.suffix}" )
	private String outFilenameSuffix;
	
	private static int status = STATUS_INIT;
	
	public static void main(String[] args) {
        SpringApplication.run(DcmApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            logger.info(INFO_LAUNCHING);
            logger.info(INFO_PARAM_CHANGE_PATIENT_ID_VALUE+changePatientIdValue);
            logger.info(INFO_PARAM_CHANGE_PATIENT_ID_OVERWRITE+changePatientIdOverwriteOriginalFile);
            logger.info(INFO_PARAM_IN_FOLDER_ABSOLUTE_PATH_STR+inFolderAbsolutePathStr);
            logger.info(INFO_PARAM_IN_FILENAME+inFilename);
            logger.info(INFO_PARAM_OUT_FOLDER_ABSOLUTE_PATH_STR+outFolderAbsolutePathStr);
            logger.info(INFO_PARAM_OUT_FILENAME_SUFFIX+outFilenameSuffix);
            
            try {
            	logger.debug(DEBUG_DCMM_INSTANCIATION);
	            DcmManager dcmm = new DcmManager(
	            		changePatientIdValue,
	            		changePatientIdOverwriteOriginalFile,
	            		inFilename,
	            		inFolderAbsolutePathStr,
	            		outFilenameSuffix,
	            		outFolderAbsolutePathStr);
	            logger.debug(DEBUG_DCMM_EXECUTION);
	            dcmm.execute();
	            status = STATUS_SUCCESS;
	            logger.debug(DEBUG_ENDING_STATUS + status);
            } catch(DcmException d) {
            	logger.error(d.getMessage());
            	status = STATUS_ERROR;
            }
            System.exit(status);
        };
    }
    
    
}
