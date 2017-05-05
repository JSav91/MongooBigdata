package com.unitec.ade.business;

import java.nio.file.attribute.FileTime;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.Block;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.unitec.ade.constants.ApplicationConstants;

public class ComplexQuery1 implements ApplicationConstants{

	public static Long totalTimeforRead=new Long(0);
	public static Long rowcount=new Long(0);
	public static Long size= new Long(0);
	
	public void runQuery(){
		
		long startTime=System.currentTimeMillis();
		FindIterable<Document> iterable=DB.getCollection("case").find(Filters.and(Filters.eq("status","unsolved"),Filters.eq("city","Inyo")
				,Filters.eq("state","California"),Filters.eq("crime_type","murder or manslaughter")
				,Filters.gt("date_time","1984/01/01"),Filters.lt("date_time","2012/12/31")
				,Filters.eq("perptrator.ethnicity","african"),Filters.eq("perptrator.gender","male")
				,Filters.gt("perptrator.age",18.0),Filters.lt("victims.0.age",18.0)
				,Filters.or(Filters.eq("agency_type","Municipal Police"),Filters.eq("agency_type","County Police"))				
				))
		.projection(new Document("crime_type", 1)
				.append("_id",0)
                .append("state", 1)
                .append("city",1)
                .append("agency_type",1)
                .append("victims.age",1)
                .append("victims.gender",1)
                .append("victims.ethnicity",1)
                .append("perptrator.age",1)
                .append("perptrator.gender",1)
                .append("perptrator.ethnicity",1)
                .append("status", 1));
		long stopTime=System.currentTimeMillis();

		
		totalTimeforRead=(stopTime-startTime);
		for(Document document:iterable){
			rowcount++;
			System.out.println(new Gson().toJson(document));
			size+=document.size();
		}
		
		System.out.println("\n\n\nNo of Rows retrieved "+rowcount);
		System.out.println("Total size of queries returned "+size+" bytes");
		System.out.println("Total time taken "+totalTimeforRead +"ms\n\n\n");
		
	}
	
	Block<Document> printBlock = new Block<Document>() {
	       public void apply(final Document document) {
	           System.out.println(document.toJson());
	       }
	};
}
