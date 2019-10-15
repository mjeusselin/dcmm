package fr.mjeu.dcmm.mongo.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.mjeu.dcmm.mongo.dao.TraceDao;
import fr.mjeu.dcmm.mongo.model.TraceDto;
import fr.mjeu.dcmm.mongo.repository.TraceRepository;

@Service
public class TraceDaoImpl implements TraceDao {
	@Autowired
	public TraceRepository traceRepository;
	
	@Override
	public void createTrace(TraceDto trace) {
		traceRepository.insert(trace);
	}
}
