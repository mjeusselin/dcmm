package fr.mjeu.dcmm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.mjeu.dcmm.dao.TraceDto;

@Service
public class TraceDaoImpl implements TraceDao {
	@Autowired
	public TraceRepository traceRepository;
	
	@Override
	public void createTrace(TraceDto trace) {
		traceRepository.insert(trace);
	}
}
