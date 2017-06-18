package com.learning.animaldb.animal;

import java.util.ArrayList;
import java.util.List;

import com.learning.animaldb.animal.db.AnimalsDao;

/**
 * @author QuickNick
 */
public class AnimalsStorage {

    private static final String TAG = AnimalsStorage.class.getSimpleName();

    private final AnimalsDao mAnimalsDao;
    private final List<OnContentChangeListener> mOnContentChangeListeners;

    public AnimalsStorage(AnimalsDao animalsDao) {
        mAnimalsDao = animalsDao;
        mOnContentChangeListeners = new ArrayList<>();
    }

    public List<Animal> getAnimals() {
        return mAnimalsDao.getmAnimals();
    }

    public void addAnimal(Animal animal) {
        mAnimalsDao.insertAnimal(animal);
        for (OnContentChangeListener listener : mOnContentChangeListeners) {
            listener.onAnimalAdded(this, animal);
        }
    }
    public void deleteAnimal(Animal animal){
        mAnimalsDao.deleteAnimal(animal);
        for (OnContentChangeListener listener : mOnContentChangeListeners) {
            listener.onAnimalAdded(this, animal);
        }
    }
    public void updateAnimal(Animal animal){
        mAnimalsDao.updateAnimal(animal);
        for (OnContentChangeListener listener : mOnContentChangeListeners) {
            listener.onAnimalAdded(this, animal);
        }
    }

    public void addOnContentChangeListener(OnContentChangeListener listener) {
        mOnContentChangeListeners.add(listener);
    }

    public void removeOnContentChangeListener(OnContentChangeListener listener) {
        mOnContentChangeListeners.remove(listener);
    }

    public interface OnContentChangeListener {

        void onAnimalAdded(AnimalsStorage sender, Animal animal);
    }
}
