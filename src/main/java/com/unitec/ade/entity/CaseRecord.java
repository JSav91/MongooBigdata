package com.unitec.ade.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

/**
 * Created by roland on 22/04/17.
 */
@Entity("case")
public class CaseRecord {

	@Id
	@Property("id")
    private ObjectId id;
    @Property("status")
    private String status;
    @Property("agency_type")
    private String agency_type;
    @Property("agency_name")
    private String agency_name;
    @Property("city")
    private String city;
    @Property("state")
    private String state;
    @Property("date")
    private Date date_time;
    @Property("crime_type")
    private String crime_type;
    @Property("weapon")
    private String weapon;
    @Reference
    private List<Perpetrator> perptrator;
    @Reference
    private List<Victim> victims;
    @Property("evidence")
    private List<String> evidence;
    @Reference
    private List<Relationship> relations;

    public CaseRecord() {
        this.perptrator = new ArrayList<Perpetrator>();
        this.victims = new ArrayList<Victim>();
        this.relations = new ArrayList<Relationship>();
        this.evidence = new ArrayList<String>();
    }

    public CaseRecord(ObjectId pId,String pStatus, String pAgencyType, String pAgencyName, String pCity, String pState, Date pDateTime, String pCrimeType,String weapon) {
        this();
        this.status = pStatus;
        this.agency_type = pAgencyType;
        this.agency_name = pAgencyName;
        this.city = pCity;
        this.state = pState;
        this.date_time = pDateTime;
        this.crime_type = pCrimeType;
        this.weapon=weapon;
        this.id = pId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String pStatus) {
        status = pStatus;
    }

    public List<String> getEvidence() {
        return evidence;
    }

    public void addEvidence(String pEvidence) {
        if (!evidence.contains(pEvidence)) {
            evidence.add(pEvidence);
        }
    }

    public void removeEvidence(String pEvidence) {
        if (evidence.contains(pEvidence)) {
            evidence.remove(pEvidence);
        }
    }

    public String getAgencyType() {
        return agency_type;
    }

    
    
    public String getmWeapon() {
		return weapon;
	}

	public void setmWeapon(String mWeapon) {
		this.weapon = mWeapon;
	}

	public void setAgencyType(String pAgencyType) {
        agency_type = pAgencyType;
    }

    public String getAgencyName() {
        return agency_name;
    }

    public void setAgencyName(String pAgencyName) {
        agency_name = pAgencyName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String pCity) {
        city = pCity;
    }

    public String getState() {
        return state;
    }

    public void setState(String pState) {
        state = pState;
    }

    public Date getDateTime() {
        return date_time;
    }

    public void setDateTime(Date pDateTime) {
        date_time = pDateTime;
    }

    public String getCrimeType() {
        return crime_type;
    }

    public void setCrimeType(String pCrimeType) {
        crime_type = pCrimeType;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId pId) {
        id = pId;
    }

    public void addRelationship(Relationship pRelation)
    {
        if (!relations.contains(pRelation)) {
            relations.add(pRelation);
        }
    }

    public void removeRelationship( Relationship pRelation)
    {
        while (relations.contains(pRelation))
        {
            relations.remove(pRelation);

        }
    }

    public List<Relationship> getAllRelationships()
    {
        return relations;
    }

    public void addPerpetrator(Perpetrator pPerp)
    {
        if (!perptrator.contains(pPerp)) {
            perptrator.add(pPerp);
        }
    }

    public void removePerpetrator(Perpetrator pPerp)
    {
        while (perptrator.contains(pPerp))
        {
            perptrator.remove(pPerp);
            for (Relationship r: relations)
            {
                if (r.getPerpetrator().equals(pPerp))
                {
                    removeRelationship(r);
                }
            }
        }
    }

    public List<Perpetrator> getAllPerpetrators()
    {
        return perptrator;
    }


    public void addVictim(Victim pVictim)
    {
        if (!victims.contains(pVictim)) {
            victims.add(pVictim);
        }
    }

    public void removeVictim(Victim pVictim)
    {
        while (victims.contains(pVictim))
        {
            victims.remove(pVictim);
            for (Relationship r: relations)
            {
                if (r.getVictim().equals(pVictim))
                {
                    removeRelationship(r);
                }
            }
        }
    }

    public List<Victim> getAllVictims()
    {
        return victims;
    }
}
