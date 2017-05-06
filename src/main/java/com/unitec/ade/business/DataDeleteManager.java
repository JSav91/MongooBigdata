package com.unitec.ade.business;

import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.unitec.ade.constants.ApplicationConstants;

public class DataDeleteManager implements ApplicationConstants{

	public static Long totalTimeforRead=new Long(0);
	public static Long rowcount=new Long(0);
	public static Long size= new Long(0);
	
	//Delete all cases before 1990 which have been solved
	public void runQuery(){
		
		long startTime=System.currentTimeMillis();

		DeleteResult deleteResult=DB.getCollection("case").deleteMany(Filters.and(Filters.eq("status","solved"),Filters.lt("date_time","1990/01/01")));
		
		long stopTime=System.currentTimeMillis();
		
		totalTimeforRead=(stopTime-startTime);
		
		System.out.println("Deleted rows count"+deleteResult.getDeletedCount());
		System.out.println("Total time taken "+totalTimeforRead +"ms\n\n\n");
	}
}
