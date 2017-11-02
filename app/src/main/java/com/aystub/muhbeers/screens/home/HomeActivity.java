package com.aystub.muhbeers.screens.home;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;
import com.aystub.muhbeers.R;
import com.aystub.muhbeers.data.Beer;
import com.aystub.muhbeers.screens.addbeer.AddBeerActivity;
import com.aystub.muhbeers.screens.home.adapter.BeerRecyclerAdapter;
import com.aystub.muhbeers.screens.welcome.WelcomeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class HomeActivity extends Activity implements BeerRecyclerAdapter.OnBeerSelectedListener {

    private FirebaseAuth firebaseAuth;
    private BeerRecyclerAdapter adapter;
    private FirebaseFirestore firestore;


    public static Intent createIntent(Context context) {
        return new Intent(context, HomeActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (adapter != null) {
            adapter.startListening();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (adapter != null) {
            adapter.stopListening();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sign_out: {
                firebaseAuth.signOut();
                startActivity(WelcomeActivity.createIntent(this));
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    private void init() {
        initFirebaseStuff();
        initRecycler();
        initToolbar();
        initFAB();
    }


    private void initFirebaseStuff() {
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
    }


    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);
    }


    private void initFAB() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAddBeerActivity();
            }
        });
    }


    private void startAddBeerActivity() {
        startActivity(AddBeerActivity.createIntent(this));
    }


    private void initRecycler() {
        Query query = firestore.collection(Beer.COLLECTION_NAME)
                .orderBy("rating", Query.Direction.DESCENDING)
                .limit(50);

        RecyclerView recycler = findViewById(R.id.recycler);
        adapter = new BeerRecyclerAdapter(query, this);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }


    @Override
    public void onBeerSelected(Beer beer) {
        // TODO: Setup and go to a beer detail screen
    }
}
