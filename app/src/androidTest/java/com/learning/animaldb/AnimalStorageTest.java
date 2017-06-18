package com.learning.animaldb;

import com.learning.animaldb.animal.Animal;
import com.learning.animaldb.animal.AnimalsStorage;
import com.learning.animaldb.animal.db.StubAnimalsDao;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Диана on 18.06.2017.
 */
public class AnimalStorageTest {
    private StubAnimalsDao mAnimalsDao;
    private AnimalsStorage mAnimalsStorage;

    @Before
    public void setUp() {
        mAnimalsDao = new StubAnimalsDao();
        mAnimalsStorage = new AnimalsStorage(mAnimalsDao);
    }


    @Test
    public void testGetAnimals() {
        mAnimalsDao.insertAnimalsForTest(EntitiesGenerator.createRandomAnimalsList(false));
        List<Animal> actual = mAnimalsStorage.getAnimals();
        assertThat(actual, is(mAnimalsDao.mAnimals));
        assertThat(mAnimalsDao.mGetAnimalsCalled, is(true));
    }
    @Test
    public void testAddAnimal(){
        Animal animal = EntitiesGenerator.createRandomAnimal(false);
        mAnimalsStorage.addAnimal(animal);
        List<Animal> actual = mAnimalsStorage.getAnimals();
        assertThat(actual,is(mAnimalsDao.getmAnimals()));
        assertThat(mAnimalsDao.mLastAddedId,is(animal.getId()));
    }
    @Test
    public void testDeleteAnimal(){
        mAnimalsDao.mAnimals.add(EntitiesGenerator.createRandomAnimal(true));
        Animal animal = mAnimalsStorage.getAnimals().get(0);
        mAnimalsStorage.deleteAnimal(animal);
        List<Animal> actual = mAnimalsStorage.getAnimals();
        assertThat(actual, is(mAnimalsDao.mAnimals));
        assertThat(mAnimalsDao.mDeleteAnimalCalled, is(true));
    }

    @Test
    public void testUpdateAnimal(){
        mAnimalsDao.mAnimals.add(EntitiesGenerator.createRandomAnimal(true));
        Animal animal = mAnimalsDao.mAnimals.get(0);
        Animal newAnimal = EntitiesGenerator.createRandomAnimal(false);
        newAnimal.setId(animal.getId());
        mAnimalsStorage.updateAnimal(newAnimal);
        List<Animal> actual = mAnimalsStorage.getAnimals();
        assertThat(mAnimalsDao.mAnimals, is(actual));
        assertThat(true, is(mAnimalsDao.mUpdateAnimalCalled));
    }

}
