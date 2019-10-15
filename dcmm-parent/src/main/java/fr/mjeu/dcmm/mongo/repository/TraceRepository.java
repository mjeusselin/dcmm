package fr.mjeu.dcmm.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import fr.mjeu.dcmm.mongo.model.TraceDto;

@Repository
public interface TraceRepository extends MongoRepository<TraceDto, String>{
	public TraceDto findByTraceEvent(String traceEvent);
}
