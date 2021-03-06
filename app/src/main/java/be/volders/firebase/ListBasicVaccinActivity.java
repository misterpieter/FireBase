package be.volders.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import be.volders.firebase.adapters.RecyclerVaccinViewAdapter;
import be.volders.firebase.helpers.DateHelper;
import be.volders.firebase.models.Vaccin;

public class ListBasicVaccinActivity extends AppCompatActivity {

    Intent i;
    Button btnVaccinInput;

    private ArrayList<Vaccin> mVaccins = new ArrayList<>();
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference = database.getReference();
    private static final String TAG = "ListA: ";
    private static final String TITLE = "Overzicht vaccins";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_vaccins);

        Objects.requireNonNull(getSupportActionBar()).setTitle(TITLE);

        btnVaccinInput = findViewById(R.id.buttonVaccinInput);
        i = new Intent(this, AddVaccinActivity.class);

        initDatabaseData();

        //click button
        btnVaccinInput.setOnClickListener(view -> startActivity(i));
    }

    private void initDatabaseData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot vaccins = dataSnapshot.child("vaccin");

                for (DataSnapshot snapshot : vaccins.getChildren()) {
                    Vaccin vaccin = snapshot.getValue(Vaccin.class);

                    mVaccins.add(vaccin);
                }
                initRecyclerView();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("The read failed: " + error.getMessage());
            }
        });
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view2);
        RecyclerVaccinViewAdapter adapter2 = new RecyclerVaccinViewAdapter(this, mVaccins);
        recyclerView.setAdapter(adapter2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
