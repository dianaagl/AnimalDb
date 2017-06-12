package com.learning.animaldb.animal;



import java.io.Serializable;
import java.util.Objects;

/**
 * @author QuickNick
 */
public class Animal implements Serializable {

    private long mId;
    private String mSpecies;
    private int mAge;
    private String mName;

    public Animal() {

    }

    public Animal(String species, int age, String name) {
        mSpecies = species;
        mAge = age;
        mName = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;

        Animal animal = (Animal) o;

        if (mId != animal.mId) return false;
        if (mAge != animal.mAge) return false;
        if (mSpecies != null ? !mSpecies.equals(animal.mSpecies) : animal.mSpecies != null) return false;
        return mName != null ? mName.equals(animal.mName) : animal.mName == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (mId ^ (mId >>> 32));
        result = 31 * result + (mSpecies != null ? mSpecies.hashCode() : 0);
        result = 31 * result + mAge;
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getSpecies() {
        return mSpecies;
    }

    public void setSpecies(String species) {
        mSpecies = species;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
