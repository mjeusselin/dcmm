package fr.mjeu.dcmm;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import fr.mjeu.dcmm.dao.TraceDto;

@Repository
public interface TraceRepository extends MongoRepository<TraceDto, String>{
	public TraceDto findByTraceEvent(String traceEvent);
}
