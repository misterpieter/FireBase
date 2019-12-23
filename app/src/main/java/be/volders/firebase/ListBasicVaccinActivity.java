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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ListBasicVaccinActivity extends AppCompatActivity {

    Intent i;
    Button btnVaccinInput;
    String patientName;
    String patientDob;

    private ArrayList<Vaccin> mVaccins = new ArrayList<>();
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference = database.getReference();
    private static final String TAG = "ListA: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_vaccins);

        btnVaccinInput = findViewById(R.id.buttonVaccinInput);
        i = new Intent(this, AddVaccinActivity.class);

        // Read patient data from AddUserActivity
        Intent patientData = getIntent();
        if (patientData.getExtras() != null) {
            patientName = patientData.getStringExtra("patient_name");
            patientDob = patientData.getStringExtra("patient_dob");
        }

        initDatabaseData();

        //click button
        btnVaccinInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(i);
            }
        });
    }

    private void initDatabaseData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot vaccins = dataSnapshot.child("vaccin");
                int age = 0;

                // Requires API level 26 or above.
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    if (patientDob != null) {
                        age = DateHelper.calculateAgeInMonths(patientDob);
                        System.out.println("leeftijd in maanden: " + age);
                    }
                }

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
