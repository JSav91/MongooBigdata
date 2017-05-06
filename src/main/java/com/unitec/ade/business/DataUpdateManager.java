package com.unitec.ade.business;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.unitec.ade.constants.ApplicationConstants;

public class DataUpdateManager implements ApplicationConstants{

	
	public static Long totalTimeforRead=new Long(0);
	public static Long rowcount=new Long(0);
	public static Long size= new Long(0);
	
	public void runQuery(){
		
		long startTime=System.currentTimeMillis();

		UpdateResult updateResult=DB.getCollection("case").updateMany(
				(Filters.and(Filters.eq("status","solved"),Filters.gt("date_time","1990/01/01"),Filters.lt("date_time","1991/01/01")))
				,Updates.combine(Updates.set("status","unsolved"))) ;
		
		long stopTime=System.currentTimeMillis();
		
		totalTimeforRead=(stopTime-startTime);
		
		System.out.println("Updated rows count"+updateResult.getModifiedCount());
		System.out.println("Total time taken "+totalTimeforRead +"ms\n\n\n");
	}
}
