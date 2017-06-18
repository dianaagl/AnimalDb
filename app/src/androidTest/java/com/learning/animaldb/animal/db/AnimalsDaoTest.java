package com.learning.animaldb.animal.db;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.learning.animaldb.EntitiesGenerator;
import com.learning.animaldb.animal.Animal;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestName;
import org.junit.rules.TestRule;

import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsIn.isIn;
import static org.hamcrest.core.Every.everyItem;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

/**
 * Created by Диана on 18.06.2017.
 */
public class AnimalsDaoTest {
    private static final String TAG = "DaoTest";

    private AnimalsDaoRule mDaoRule = new AnimalsDaoRule();
    private TestName mTestNameRule = new TestName();

    @Rule
    public TestRule rule = RuleChain
            .outerRule(mDaoRule)
            .around(mTestNameRule);

    @Before
    public void before() {
        Log.e(TAG, "before");
    }
    @Test
    public void testCreateTable(){
        Log.e(TAG, "method name = " + mTestNameRule.getMethodName());

        String[] names = mDaoRule.getmSqliteAnimalsDao().getColumnNames();
        assertThat(AnimalsContract.Animals._ID,is(names[0]));
        assertThat(AnimalsContract.Animals.SPECIES,is(names[1]));
        assertThat(AnimalsContract.Animals.AGE,is(names[2]));
        assertThat(AnimalsContract.Animals.NAME,is(names[3]));
    }

    @Test
    public void testInsertAnimal() {
        Log.e(TAG, "method name = " + mTestNameRule.getMethodName());
        Animal animal = EntitiesGenerator.createRandomAnimal(false);
        long id = mDaoRule.getmSqliteAnimalsDao().insertAnimal(animal);
        assertThat(true, is(id > 0));
    }

    @Test
    public void testGetAnimals() {
        Log.e(TAG, "method name = " + mTestNameRule.getMethodName());
        List<Animal> expected = EntitiesGenerator.createRandomAnimalsList(false);
        for (Animal animal : expected) {
            animal.setId(mDaoRule.getmSqliteAnimalsDao().insertAnimal(animal));
        }

        List<Animal> actual = mDaoRule.getmSqliteAnimalsDao().getmAnimals();
        assertThat(actual, everyItem(isIn(expected)));
    }

    @Test
    public void testGetAnimalById() {
        Log.e(TAG, "method name = " + mTestNameRule.getMethodName());
        Animal expected = EntitiesGenerator.createRandomAnimal(false);
        long id = mDaoRule.getmSqliteAnimalsDao().insertAnimal(expected);
        expected.setId(id);
        Animal actual = mDaoRule.getmSqliteAnimalsDao().getAnimalById(id);
        assertThat(actual, is(expected));
    }

    @Test
    public void testDeleteAnimal(){
        Log.e(TAG,"method name = " + mTestNameRule.getMethodName());
        Animal deleteAnimal = EntitiesGenerator.createRandomAnimal(false);
        List<Animal> expectedList = mDaoRule.getmSqliteAnimalsDao().getmAnimals();
        long id = mDaoRule.getmSqliteAnimalsDao().insertAnimal(deleteAnimal);

        deleteAnimal.setId(id);
        int numberDelete = mDaoRule.getmSqliteAnimalsDao().deleteAnimal(deleteAnimal);
        List<Animal> actual = mDaoRule.getmSqliteAnimalsDao().getmAnimals();
        assertThat(actual, not(hasItem(deleteAnimal)));
        assertThat(true,is(numberDelete == 1));
    }

    @Test
    public void testUpdateAnimal(){
        Log.e(TAG,"method name = " + mTestNameRule.getMethodName());
        Animal updateAnimal = EntitiesGenerator.createRandomAnimal(false);
        long id = mDaoRule.getmSqliteAnimalsDao().insertAnimal(updateAnimal);

        Animal newAnimal = EntitiesGenerator.createRandomAnimal(false);
        newAnimal.setId(id);
        int numberUpdates = mDaoRule.getmSqliteAnimalsDao().updateAnimal(newAnimal);
        Animal actual = mDaoRule.getmSqliteAnimalsDao().getAnimalById(id);
        assertThat(true, is(numberUpdates == 1));
        assertThat(newAnimal, is(actual));
    }

    @After
    public void after() {
        Log.e(TAG, "after");
    }
}
