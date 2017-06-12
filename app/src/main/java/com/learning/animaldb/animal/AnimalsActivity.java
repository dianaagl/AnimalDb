package com.learning.animaldb.animal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import com.learning.animaldb.R;

/**
 * @author QuickNick
 */
public class AnimalsActivity extends AppCompatActivity {

    private static final int ANIMALS_LOADER_ID = 1;

    private AnimalsStorage mAnimalsStorage;
    private AnimalsAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AnimalsStorageProvider provider = (AnimalsStorageProvider) getApplication();
        mAnimalsStorage = provider.getAnimalsStorage();

        setContentView(R.layout.animals_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        mAdapter = new AnimalsAdapter();
        final ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(mAdapter);

        getSupportLoaderManager().initLoader(ANIMALS_LOADER_ID, null, new AnimalsLoaderCallbacks());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AnimalsActivity.this,
                        DeleteOrUpdateAnimalActivity.class);
                Animal animal = (Animal) listView.getItemAtPosition(position);
                intent.putExtra("name",animal.getName());
                intent.putExtra("id",animal.getId());
                intent.putExtra("age",animal.getAge());
                intent.putExtra("specie",animal.getSpecies());

                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.animals_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled = false;
        switch (item.getItemId()) {
            case R.id.add_animal_menu_item: {
                startActivity(AddAnimalActivity.newIntent(this));
                break;
            }

            default: {
                handled = super.onOptionsItemSelected(item);
                break;
            }
        }
        return handled;
    }

    private class AnimalsLoaderCallbacks implements LoaderManager.LoaderCallbacks<List<Animal>> {

        @Override
        public Loader<List<Animal>> onCreateLoader(int id, Bundle args) {
            return new AnimalsLoader(AnimalsActivity.this, mAnimalsStorage);
        }

        @Override
        public void onLoadFinished(Loader<List<Animal>> loader, List<Animal> data) {

            mAdapter.setAnimals(data);
        }

        @Override
        public void onLoaderReset(Loader<List<Animal>> loader) {
        }
    }
}
