package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.whatsapp.Adapters.FragmentsAdapter;
import com.example.whatsapp.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.fragment.app.FragmentActivity;



public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    ActivityMainBinding binding;

    protected   void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth =FirebaseAuth.getInstance();

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("your_reference_path");

        binding.viewPager.setAdapter((new FragmentsAdapter(getSupportFragmentManager())));
        binding.tabLayout.setupWithViewPager(binding.viewPager);


        myRef.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){

                String value = dataSnapshot.getValue(String.class);
                Toast.makeText(MainActivity.this, ""+value, Toast.LENGTH_SHORT).show();
            }
       @Override
            public void onCancelled(@NonNull DatabaseError error){

       }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.settings:
                Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.layout:
             auth.signOut();
                Intent intent = new Intent(MainActivity.this , SignInActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}
