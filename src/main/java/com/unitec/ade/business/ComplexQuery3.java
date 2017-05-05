package com.unitec.ade.business;

import java.util.Arrays;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.unitec.ade.constants.ApplicationConstants;

public class ComplexQuery3 implements ApplicationConstants{

	public static Long totalTimeforRead=new Long(0);
	public static Long rowcount=new Long(0);
	public static Long size= new Long(0);

	public void runQuery(){

		

		long startTime=System.currentTimeMillis();

		AggregateIterable<Document> iterable=DB.getCollection("case").aggregate(Arrays.asList(
				Aggregates.match(Filters.and(
						Filters.eq("relations.mRelation","aquaintance")
						,Filters.eq("state","Florida")
						,Filters.or(
								Filters.and(Filters.eq("relations.mPerpetrator.gender","male"),Filters.eq("relations.mVictim.gender","female"))
								,Filters.and(Filters.eq("relations.mPerpetrator.gender","female"),Filters.eq("relations.mVictim.gender","male"))))/*End of and*/)/*End of match*/
				,Aggregates.project(Projections.fields(new Document("state","$state"),new Document("victim_count",new Document("$size","$victims"))))
				,Aggregates.group(null,Accumulators.avg("victims_count","$victim_count"))
				));
		

		long stopTime=System.currentTimeMillis();
		
		
		totalTimeforRead=(stopTime-startTime);
		System.out.println("Time taken for first query "+totalTimeforRead +"ms");
		
		Double average = 1.0;
		
		for(Document document:iterable){
			rowcount++;
			System.out.println(new Gson().toJson(document));
			average=new Double(document.getDouble("victims_count"));
		}

		startTime=System.currentTimeMillis();
		FindIterable<Document> findIterable= DB.getCollection("case").find(Filters.and(
				Filters.eq("state","Florida"),Filters.where("this.victims.length >"+average)))
				.projection(new Document("_id",0)
						.append("state",1)
		                .append("victims", 1));
		stopTime=System.currentTimeMillis();
		for(Document document:findIterable){
			rowcount++;
			System.out.println(new Gson().toJson(document));
			size+=document.size();
		}
		
		Long timeTakenForCurrentQuery=(stopTime-startTime);
		totalTimeforRead+=(stopTime-startTime);
		
		System.out.println("Time taken for second query "+timeTakenForCurrentQuery +"ms");
		System.out.println("\n\n\nNo of Rows retrieved "+rowcount);
		System.out.println("Total size of queries returned "+size+" bytes");
		System.out.println("Total time taken "+totalTimeforRead +"ms\n\n\n");

}
}
