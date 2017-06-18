package com.learning.animaldb.animal.db;

import java.util.List;

import com.learning.animaldb.animal.Animal;

/**
 * Created by Тичер on 08.06.2017.
 */
public interface AnimalsDao {

    long insertAnimal(Animal animal);

    List<Animal> getmAnimals();

    Animal getAnimalById(long id);

    int updateAnimal(Animal animal);

    int deleteAnimal(Animal animal);
}
