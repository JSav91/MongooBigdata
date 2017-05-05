/**
 * 
 */
package com.unitec.ade.business;

import java.util.LinkedList;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.unitec.ade.constants.ApplicationConstants;
import com.unitec.ade.entity.CaseRecord;

/**
 * @author JOEL
 *
 */
public class DataInsertManager implements ApplicationConstants{
	
	private static RandomDataGenerator dataGenerator= new RandomDataGenerator();

	public static Long totalTimeforInsert=new Long(0);
	public void insertCasesRecords(){
		LinkedList<Document> documents= new LinkedList<Document>();
		Gson gson= new Gson();
		gson=new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
		CaseRecord caseRecord;
			try {
				System.out.println("Polpulating "+CASE_COUNT+" case records");
			for(int i=0;i<CASE_COUNT;i++){
				caseRecord=dataGenerator.generateCase();
				System.out.println(caseRecord.getDateTime());
				String caseRecordJson= gson.toJson(caseRecord);
				documents.add(Document.parse(caseRecordJson));
				System.out.println(caseRecordJson);
			}
			
	System.out.println("Initaite Insert Operation");
			long insertInitTime=System.currentTimeMillis();
			DB.getCollection("case").insertMany(documents);
			long insertCompletionTime=System.currentTimeMillis();
			System.out.println("Insert operation completed");
			long currentInsertTime= insertCompletionTime-insertInitTime;
			
			this.totalTimeforInsert=this.totalTimeforInsert+currentInsertTime;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
