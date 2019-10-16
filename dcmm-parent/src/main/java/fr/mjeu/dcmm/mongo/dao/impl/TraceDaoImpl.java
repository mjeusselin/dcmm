package fr.mjeu.dcmm.mongo.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import fr.mjeu.dcmm.exception.DcmException;
import fr.mjeu.dcmm.exception.DcmExceptionMessage;
import fr.mjeu.dcmm.mongo.dao.DaoConfig;
import fr.mjeu.dcmm.mongo.dao.TraceDao;
import fr.mjeu.dcmm.mongo.model.TraceDto;
import fr.mjeu.dcmm.mongo.repository.TraceRepository;

@Service
@Import(DaoConfig.class)
public class TraceDaoImpl implements TraceDao {
	@Autowired
	public TraceRepository traceRepository;
	
	@Value( "${dcm.mongo.modifications.tracking}" )
	private String dcmMongoModificationsTracking;
	
	@Override
	public void createTrace(TraceDto trace) throws DcmException {
		boolean databaseAvailable = Boolean.valueOf(dcmMongoModificationsTracking);
		if(databaseAvailable) {
			traceRepository.insert(trace);
		} else {
			throw new DcmException(DcmExceptionMessage.ERROR_MONGO_MODIFICATIONS_TRACKING_NOT_CONFIGURED.getMessage());
		}
	}
}
