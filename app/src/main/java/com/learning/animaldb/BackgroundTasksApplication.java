package com.learning.animaldb;

import android.app.Application;

import com.learning.animaldb.animal.AnimalsStorage;
import com.learning.animaldb.animal.AnimalsStorageProvider;
import com.learning.animaldb.animal.db.AnimalsDao;
import com.learning.animaldb.animal.db.SQLiteAnimalsDao;

/**
 * @author QuickNick.
 */

public class BackgroundTasksApplication extends Application implements AnimalsStorageProvider {

    private AnimalsStorage mAnimalsStorage;

    @Override
    public void onCreate() {
        super.onCreate();
        AnimalsDao animalsDao = new SQLiteAnimalsDao(this);
        mAnimalsStorage = new AnimalsStorage(animalsDao);
    }

    @Override
    public AnimalsStorage getAnimalsStorage() {
        return mAnimalsStorage;
    }
}
