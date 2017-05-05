package com.unitec.ade.business;

import java.util.Arrays;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.unitec.ade.constants.ApplicationConstants;

public class ComplexQuery2 implements ApplicationConstants{

	public static Long totalTimeforRead=new Long(0);
	public static Long rowcount=new Long(0);
	public static Long size= new Long(0);
	
	public void runQuery(){
		
		Document groupIdFeilds=new Document();
		groupIdFeilds.put("state", "$state");
		groupIdFeilds.put("agency", "$agency_type");
		groupIdFeilds.put("weapon", "$weapon");
		long startTime=System.currentTimeMillis();

		AggregateIterable<Document> iterable=DB.getCollection("case").aggregate(Arrays.asList(
				Aggregates.match(
					Filters.and(
						Filters.eq("status","solved")
						,Filters.eq("state","California")
						,Filters.eq("perptrator.ethnicity","european")
						,Filters.eq("perptrator.gender","female")
						,Filters.eq("victims.ethnicity","african")
						,Filters.eq("victims.gender","male")
						,Filters.gt("perptrator.0.age",32.0)
						,Filters.lt("victims.0.age",30.0)
						,Filters.gt("date_time","1984/01/01")
						,Filters.lt("date_time","2012/12/31")
						))
				,Aggregates.group(groupIdFeilds,Accumulators.sum("count",1))
				));
		long stopTime=System.currentTimeMillis();

		totalTimeforRead=(stopTime-startTime);
		for(Document document:iterable){
			rowcount++;
			System.out.println(new Gson().toJson(document));
			size+=document.size();
		}
		
		System.out.println("\n\n\nNo of Rows retrieved "+rowcount);
		System.out.println("Total size of queries returned "+size+" bytes");
		System.out.println("Total time taken "+totalTimeforRead+"ms\n\n\n");
	}
}
