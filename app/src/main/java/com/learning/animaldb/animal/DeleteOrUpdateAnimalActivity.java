package com.learning.animaldb.animal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.learning.animaldb.R;
import com.learning.animaldb.animal.db.AnimalsContract;

/**
 * Created by Диана on 12.06.2017.
 */
public class DeleteOrUpdateAnimalActivity extends Activity {
    private EditText mNameEditText;
    private EditText mAgeEditText;
    private EditText mSpecieEditText;

    private Button mUpdateButton;
    private Button mDeleteButton;

    private AnimalsStorage mAnimalsStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.delete_or_update_animal_activity);
        AnimalsStorageProvider provider = (AnimalsStorageProvider) getApplication();
        mAnimalsStorage = provider.getAnimalsStorage();

        super.onCreate(savedInstanceState);
        mNameEditText = (EditText) findViewById(R.id.update_name);
        mAgeEditText = (EditText) findViewById(R.id.update_age);
        mSpecieEditText = (EditText) findViewById(R.id.update_specie);

        mDeleteButton = (Button) findViewById(R.id.delete_animal_button);
        mUpdateButton = (Button) findViewById(R.id.update_animal_button);

        final Intent intent = getIntent();

        mNameEditText.setText(intent.getStringExtra(AnimalsContract.Animals.NAME));
        mAgeEditText.setText(String.valueOf(intent.getIntExtra(AnimalsContract.Animals.AGE,0)));
        mSpecieEditText.setText(intent.getStringExtra(AnimalsContract.Animals.SPECIES));

        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animal animal = new Animal(intent.getStringExtra(AnimalsContract.Animals.SPECIES),intent.getIntExtra(
                        AnimalsContract.Animals.AGE,0),
                        intent.getStringExtra(AnimalsContract.Animals.NAME));

                animal.setId(intent.getLongExtra(AnimalsContract.Animals.ID,0));
                mAnimalsStorage.deleteAnimal(animal);
                finish();
            }
        });
        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animal animal = new Animal(String.valueOf(mSpecieEditText.getText()),
                        Integer.parseInt(String.valueOf(mAgeEditText.getText())),
                        String.valueOf(mNameEditText.getText()));
                animal.setId(intent.getLongExtra(AnimalsContract.Animals.ID,0));
                mAnimalsStorage.updateAnimal(animal);
                finish();
            }
        });


    }
}
