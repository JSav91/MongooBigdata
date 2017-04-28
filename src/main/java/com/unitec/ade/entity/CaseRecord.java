package com.unitec.ade.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    private ObjectId mId;
    @Property("status")
    private String mStatus;
    @Property("agency_type")
    private String mAgencyType;
    @Property("agency_name")
    private String mAgencyName;
    @Property("city")
    private String mCity;
    @Property("state")
    private String mState;
    @Property("date")
    private Date mDateTime;
    @Property("crime_type")
    private String mCrimeType;
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
        this.mStatus = pStatus;
        this.mAgencyType = pAgencyType;
        this.mAgencyName = pAgencyName;
        this.mCity = pCity;
        this.mState = pState;
        this.mDateTime = pDateTime;
        this.mCrimeType = pCrimeType;
        this.weapon=weapon;
        this.mId = pId;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String pStatus) {
        mStatus = pStatus;
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
        return mAgencyType;
    }

    
    
    public String getmWeapon() {
		return weapon;
	}

	public void setmWeapon(String mWeapon) {
		this.weapon = mWeapon;
	}

	public void setAgencyType(String pAgencyType) {
        mAgencyType = pAgencyType;
    }

    public String getAgencyName() {
        return mAgencyName;
    }

    public void setAgencyName(String pAgencyName) {
        mAgencyName = pAgencyName;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String pCity) {
        mCity = pCity;
    }

    public String getState() {
        return mState;
    }

    public void setState(String pState) {
        mState = pState;
    }

    public Date getDateTime() {
        return mDateTime;
    }

    public void setDateTime(Date pDateTime) {
        mDateTime = pDateTime;
    }

    public String getCrimeType() {
        return mCrimeType;
    }

    public void setCrimeType(String pCrimeType) {
        mCrimeType = pCrimeType;
    }

    public ObjectId getId() {
        return mId;
    }

    public void setId(ObjectId pId) {
        mId = pId;
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
