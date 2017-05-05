package com.unitec.ade.business;

import java.util.Arrays;

import org.bson.Document;
import org.mongodb.morphia.aggregation.Projection;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.unitec.ade.constants.ApplicationConstants;

public class SimpleQuery1 implements ApplicationConstants{

	public static Long totalTimeforRead=new Long(0);
	public static Long rowcount=new Long(0);
	public static Long size= new Long(0);
	
	public void runQuery(){
		
		Document matchStartDate=new Document();
		matchStartDate.put("$gt","2007/01/01");
		Document matchEndDate=new Document();
		matchEndDate.put("$lt","2014/01/01");

		Document groupIdFeilds=new Document();
		groupIdFeilds.put("state", "$state");
		groupIdFeilds.put("date", "$date_time");
		groupIdFeilds.put("weapon", "$weapon");
		
		Document stage2GroupIdFeilds=new Document();
		stage2GroupIdFeilds.put("state", "$_id.state");
		stage2GroupIdFeilds.put("date", "$_id.date_time");
	
		Document sortFeilds=new Document();
		sortFeilds.put("count",-1);
		
		Document projectFeilds=new Document();
		projectFeilds.put("weapons",Projections.slice("$weapons",1));
		projectFeilds.put("count","1");

		long startTime=System.currentTimeMillis();
		AggregateIterable<Document> iterable=DB.getCollection("case").aggregate(Arrays.asList(

				Aggregates.match(Filters.eq("date_time",matchStartDate)),
				Aggregates.match(Filters.eq("date_time",matchEndDate)),
				Aggregates.group(groupIdFeilds,Accumulators.sum("weaponCount",1)),
				Aggregates.group(stage2GroupIdFeilds,Accumulators.push("weapons",new Document("weapon","$_id.weapon").append("count","$weaponCount"))),
				Aggregates.sort(sortFeilds),
				Aggregates.project(projectFeilds)
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
