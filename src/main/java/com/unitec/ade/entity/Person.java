package com.unitec.ade.entity;

import java.util.UUID;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

/**
 * Created by roland on 22/04/17.
 */
@Entity("person")
public class Person {


	@Id
	@Property("id")
    protected ObjectId mId;
	@Property("ethenicity")
    protected String mEthnicity;
	@Property("gender")
    protected String mGender;
	@Property("age")
    protected int mAge;

    public Person()
    {

    }

    public Person(ObjectId pId, String pEthnicity, String pGender, int pAge)
    {
        this.mId = pId;
        mEthnicity = pEthnicity;
        mAge = pAge;
        mGender = pGender;
    }

    public String getEthnicity() {
        return mEthnicity;
    }

    public void setEthnicity(String pEthnicity) {
        this.mEthnicity = pEthnicity;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String pGender) {
        this.mGender = pGender;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int pAge) {
        this.mAge = pAge;
    }

    public ObjectId getId() {
        return mId;
    }

    public void setId(ObjectId pId) {
        this.mId = pId;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Person &&
                mGender.equals(((Person) obj).getGender()) &&
                mEthnicity.equals(((Person) obj).getEthnicity()) &&
                mAge == ((Person) obj).getAge();
    }
}
