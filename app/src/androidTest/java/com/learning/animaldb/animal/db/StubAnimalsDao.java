package com.learning.animaldb.animal.db;

import com.learning.animaldb.animal.Animal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Диана on 18.06.2017.
 */
public class StubAnimalsDao implements AnimalsDao{
    public List<Animal> mAnimals;
    public boolean mGetAnimalsCalled;
    public long mLastAddedId;
    public boolean mGetAnimalByIdCalled;
    public boolean mUpdateAnimalCalled;
    public boolean mDeleteAnimalCalled;

    public StubAnimalsDao() {
        mAnimals = new ArrayList<>();
        mGetAnimalByIdCalled = false;
        mGetAnimalsCalled = false;
        mUpdateAnimalCalled = false;
        mDeleteAnimalCalled = false;
    }

    @Override
    public long insertAnimal(Animal animal) {
        mLastAddedId++;
        animal.setId(mLastAddedId);
        mAnimals.add(animal);
        return mLastAddedId;
    }

    @Override
    public List<Animal> getmAnimals() {
        mGetAnimalsCalled = true;
        return new ArrayList<>(mAnimals);
    }

    @Override
    public Animal getAnimalById(long id) {
        mGetAnimalByIdCalled = true;
        for(Animal animal: mAnimals){
            if(animal.getId() == id){
                return animal;
            }
        }
        return null;
    }

    @Override
    public int updateAnimal(Animal animal) {
        Animal oldAnimal = getAnimalById(animal.getId());
        mAnimals.remove(oldAnimal);
        mAnimals.add(animal);
        mUpdateAnimalCalled = true;

        return 1;
    }

    @Override
    public int deleteAnimal(Animal animal) {
        mDeleteAnimalCalled = true;
        boolean remove = mAnimals.remove(animal);
        if(remove){
            return 1;
        }
        else return 0;

    }

    public void insertAnimalsForTest(List<Animal> animals) {
        for (Animal animal : animals) {
            mLastAddedId++;
            animal.setId(mLastAddedId);
        }
        this.mAnimals.addAll(animals);
    }
}
