/**
 * 
 */
package com.unitec.ade.business;

import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.unitec.ade.constants.ApplicationConstants;
import com.unitec.ade.entity.CaseRecord;

/**
 * @author JOEL
 *
 */
public class DataInsertManager implements ApplicationConstants{
	
	private static RandomDataGenerator dataGenerator= new RandomDataGenerator();

	public void insertCasesRecords(){
	
		try {
			
			int caseCount=0;
			long startTime;
			long stopTime;
			long timeTakenForInsert=0;
			startTime=System.currentTimeMillis();

			while(caseCount<CASE_COUNT){
				final CaseRecord caseRecord= dataGenerator.generateCase();
				long caseGenerationTime=System.currentTimeMillis();
				
				DATASTORE.save(caseRecord.getAllPerpetrators());
				DATASTORE.save(caseRecord.getAllVictims());
				DATASTORE.save(caseRecord.getAllRelationships());
				DATASTORE.save(caseRecord.getEvidence());
				DATASTORE.save(caseRecord);
				
				
				caseCount++;
			}
			stopTime=System.currentTimeMillis();
			timeTakenForInsert+= (stopTime-startTime);

			DB db= DATASTORE.getDB();

			System.out.println("DB Stats after inserting "+caseCount+" records\n Time taken (sec): "+(timeTakenForInsert)+"\n");
			System.out.println("Size of data "+(((Integer)db.getCollection("case").getStats().get("size"))/1024)+ "KB");
			
			System.out.println("Getting Profiler info");
			DBCursor findOptions= DATASTORE.getDB().getCollection("system.profile").find();
			System.out.println(findOptions.next());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
