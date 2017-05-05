package com.unitec.ade.entity;

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
    protected ObjectId id;
	@Property("ethenicity")
    protected String ethnicity;
	@Property("gender")
    protected String gender;
	@Property("age")
    protected int age;

    public Person()
    {

    }

    public Person(ObjectId pId, String pEthnicity, String pGender, int pAge)
    {
        this.id = pId;
        this.ethnicity = pEthnicity;
        this.age = pAge;
        this.gender = pGender;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String pEthnicity) {
        this.ethnicity = pEthnicity;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String pGender) {
        this.gender = pGender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int pAge) {
        this.age = pAge;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId pId) {
        this.id = pId;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Person &&
                gender.equals(((Person) obj).getGender()) &&
                ethnicity.equals(((Person) obj).getEthnicity()) &&
                age == ((Person) obj).getAge();
    }
}
