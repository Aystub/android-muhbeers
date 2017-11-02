package com.aystub.muhbeers.screens.addbeer;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RatingBar;
import android.widget.Toolbar;

import com.aystub.muhbeers.R;
import com.aystub.muhbeers.data.Beer;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class AddBeerActivity extends Activity implements View.OnClickListener{

    private FrameLayout addPhotoLayout;
    private Button saveAction;
    private EditText beerName;
    private RatingBar ratingBar;
    private Toolbar toolbar;
    private FirebaseFirestore firestore;
    private FirebaseAuth firebaseAuth;


    public static Intent createIntent(Context context) {
        return new Intent(context, AddBeerActivity.class);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beer);
        init();
    }


    private void init() {
        findAllTheViews();
        initCustomToolbar();
        initFirebaseStuff();
        initSaveActionClickListener();
    }


    private void initCustomToolbar() {
        toolbar.setNavigationOnClickListener(this);
        setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void findAllTheViews() {
        addPhotoLayout = findViewById(R.id.add_photo_layout);
        saveAction = findViewById(R.id.save);
        beerName = findViewById(R.id.beer_name_edit_text);
        ratingBar = findViewById(R.id.rating_bar);
        toolbar = findViewById(R.id.toolbar);
    }


    private void initFirebaseStuff() {
        firestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
    }


    private void initSaveActionClickListener() {
        saveAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBeer();
            }
        });
    }


    private void saveBeer() {
        CollectionReference beerDatabase = firestore.collection(Beer.COLLECTION_NAME);
        String taster = firebaseAuth.getCurrentUser() != null ? firebaseAuth.getCurrentUser().getDisplayName() : "";
        Beer beer = new Beer("", beerName.getText().toString(), ratingBar.getRating(), taster);
        beerDatabase.add(beer)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showErrorSnackbar();
                    }
                });
    }


    private void showErrorSnackbar() {
        Snackbar.make(saveAction, R.string.error_writing_data, Snackbar.LENGTH_LONG).show();
    }


    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}
