package com.unitec.ade.main;

import static org.mongodb.morphia.aggregation.Group.grouping;
import static org.mongodb.morphia.aggregation.Group.sum;
import static org.mongodb.morphia.aggregation.Group.id;
import java.util.Iterator;

import org.mongodb.morphia.aggregation.AggregationPipeline;
import org.mongodb.morphia.aggregation.Projection;
import org.mongodb.morphia.query.Query;

import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBObject;
import com.unitec.ade.business.DataInsertManager;
import com.unitec.ade.business.RandomDataGenerator;
import com.unitec.ade.constants.ApplicationConstants;
import com.unitec.ade.entity.CaseRecord;

/**
 * Hello world!
 *
 */
public class MongoDatabaseManager implements ApplicationConstants
{
	private static RandomDataGenerator dataGenerator= new RandomDataGenerator();
	public static void main( String[] args )
	{
		try {
			//Initialize Package scan for morphia
			MORPHIA.mapPackage("com.unitec.ade");
			DATASTORE.ensureIndexes();
			
			//Setting DB Profiling command
			DBObject profilerCmd= new BasicDBObject();
			profilerCmd.put("profile","2");
			CommandResult result= DATASTORE.getDB().command(profilerCmd);
			System.out.println(result.toJson());

			System.out.println("Initaing Big Data analytics using mongoDB \n");
			System.out.println("No of data records to be generated is "+CASE_COUNT);
			
			final Query<CaseRecord> allcaseRecords = DATASTORE.createQuery(CaseRecord.class);                		
			System.out.println("Cleared present collection data\n "+DATASTORE.delete(allcaseRecords));
			
			System.out.println("Initiating insertion process\n");
			DataInsertManager insertionManager= new DataInsertManager();
			insertionManager.insertCasesRecords();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
		
	//	final Query<CaseRecord> query = DATASTORE.createQuery(CaseRecord.class);
	//	final List<CaseRecord> cases = query.asList();
		
		//ERROR
		
/*		AggregationPipeline pipeline= DATASTORE.createAggregation(CaseRecord.class)
				.group(grouping("state"),grouping("weapon",sum("weapon")))
				.project(Projection.projection("_id").suppress());
				Iterator<CaseRecord> iterator = pipeline.aggregate(CaseRecord.class);	
		while(iterator.hasNext()){
			CaseRecord c=iterator.next();
			System.out.println(c.getState()+" :: "+c.getmWeapon());
		}*/
	}

}
