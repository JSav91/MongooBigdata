package com.unitec.ade.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

/**
 * Created by roland on 22/04/17.
 */
@Entity("relationship")
public class Relationship {

	@Property("relation")
    private String mRelation;
	@Reference
    private Perpetrator mPerpetrator;
	@Reference
    private Victim mVictim;
    @Id
    private ObjectId mId;

    public ObjectId getmId() {
        return mId;
    }

    public void setmId(ObjectId mId) {
        this.mId = mId;
    }

    public Relationship()
    {

    }

    public Relationship(ObjectId pId, String mRelation, Perpetrator mPerpetrator, Victim mVictim) {
        this.mId = pId;
        this.mRelation = mRelation;
        this.mPerpetrator = mPerpetrator;
        this.mVictim = mVictim;
    }

    public String getRelation() {
        return mRelation;
    }

    public void setRelation(String pRelation) {
        this.mRelation = pRelation;
    }

    public Perpetrator getPerpetrator() {
        return mPerpetrator;
    }

    public void setPerpetrator(Perpetrator pPerpetrator) {
        this.mPerpetrator = pPerpetrator;
    }

    public Victim getVictim() {
        return mVictim;
    }

    public void setVictim(Victim pVictim) {
        this.mVictim = pVictim;
    }
}
