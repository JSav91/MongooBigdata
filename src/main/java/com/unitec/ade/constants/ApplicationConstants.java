package com.unitec.ade.constants;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public interface ApplicationConstants {
	
	public static final Long CASE_COUNT=new Long(100000);
	
	public static final MongoClient MONGO_CLIENT = new MongoClient( "localhost" , 27017 );
	MongoDatabase DB = MONGO_CLIENT.getDatabase("police_crime_data");
	
}
	