package fr.mjeu.dcmm.mongo.dao;

import fr.mjeu.dcmm.mongo.model.TraceDto;

public interface TraceDao {
	public void createTrace(TraceDto trace);
}
