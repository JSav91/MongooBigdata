package com.unitec.ade.business;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.unitec.ade.constants.ApplicationConstants;

public class SimpleQuery2 implements ApplicationConstants{

	public static Long totalTimeforRead=new Long(0);
	public static Long rowcount=new Long(0);
	public static Long size= new Long(0);
	public void runQuery(){

		Document groupIdFeilds=new Document();
		groupIdFeilds.put("state", "$state");
		groupIdFeilds.put("year", "$date_time");
		groupIdFeilds.put("status", "$status");
		
		Document sortFeilds=new Document();
		sortFeilds.put("_id.year",1);

		long startTime=System.currentTimeMillis();
		AggregateIterable<Document> iterable=DB.getCollection("case").aggregate(Arrays.asList(

				Aggregates.match(Filters.eq("status","unsolved")),
				Aggregates.match(Filters.eq("state","Florida")),
				Aggregates.group(groupIdFeilds,Accumulators.sum("count",1)),
				Aggregates.sort(sortFeilds)
				));
		long stopTime=System.currentTimeMillis();
		
		totalTimeforRead=(stopTime-startTime);
		for(Document document:iterable){
			rowcount++;
			System.out.println(document.toJson());
			size+=document.size();
		}
		
		System.out.println("\n\n\nNo of Rows retrieved "+rowcount);
		System.out.println("Total size of queries returned "+size+" bytes");
		System.out.println("Total time taken "+totalTimeforRead +"ms\n\n\n");
	}

}
