package com.unitec.ade.constants;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

public interface ApplicationConstants {
	
	public static final Long CASE_COUNT=new Long(100);
	public static final Morphia MORPHIA= new Morphia(); 
	public static final Datastore DATASTORE = MORPHIA.createDatastore(new MongoClient(), "police_crime_data");

}
