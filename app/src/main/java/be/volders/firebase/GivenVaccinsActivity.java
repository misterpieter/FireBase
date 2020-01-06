package be.volders.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import be.volders.firebase.adapters.MultiSelectAdapter;
import be.volders.firebase.helpers.DateHelper;
import be.volders.firebase.models.User;
import be.volders.firebase.models.Vaccin;

public class GivenVaccinsActivity extends AppCompatActivity {

    private final static String TITLE = "Reeds toegediende vaccins";

    private RecyclerView mRecyclerView;
    public User user;
    private int userAge;
    private ArrayList<Vaccin> allVaccins = new ArrayList<>();

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference();

    private void initDatabaseData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot vaccins = dataSnapshot.child("vaccin");

                for (DataSnapshot snapshot : vaccins.getChildren()) {
                    Vaccin vaccin = snapshot.getValue(Vaccin.class);

                    assert vaccin != null;
                    // some real dirty-ass code
                    for (String minAge : vaccin.getMinLeeftijden()) {
                        // Vaccines till max. 6 years
                        if (userAge <= 72) {
                            if (Integer.parseInt(minAge) <= 72) {
                                if (!allVaccins.contains(vaccin)) {
                                    allVaccins.add(vaccin);
                                }
                            }
                            // Vaccines from 6 till 19 years
                        } else if (userAge >= 72 && userAge <= 228) {
                            if (Integer.parseInt(minAge) >= 72 && Integer.parseInt(minAge) <= 228) {
                                if (!allVaccins.contains(vaccin)) {
                                    allVaccins.add(vaccin);
                                }
                            }
                            // Vaccines from 19 years and up
                        } else if (userAge >= 228) {
                            if (Integer.parseInt(minAge) >= 228) {
                                if (!allVaccins.contains(vaccin)) {
                                    allVaccins.add(vaccin);
                                }
                            }
                        } else {
                            if (userAge <= Integer.parseInt(minAge)) {
                                if (!allVaccins.contains(vaccin)) {
                                    allVaccins.add(vaccin);
                                }
                            }
                        }
                    }
                }
                setRecyclerView();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("The read failed: " + error.getMessage());
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_given_vaccins);
        mRecyclerView = findViewById(R.id.recycler_view);
        Button btnNoVaccines = findViewById(R.id.btnNoVaccins);

        Objects.requireNonNull(getSupportActionBar()).setTitle(TITLE);

        /*
            Get the intent from the @link{#AddUserActivity}, containing
            the Serialized patient object.
         */
        Intent patientIntent = getIntent();
        if (patientIntent != null) {
            if (patientIntent.getExtras() != null) {
                Bundle bundle = patientIntent.getBundleExtra("patient_bundle");
                assert bundle != null;
                this.user = (User) bundle.getSerializable("patient");

                assert this.user != null;
                this.userAge = DateHelper.calculateAgeInMonths(this.user.getGbDt());

                initDatabaseData();
            }
        }

        /*
            Start a new activity to @link{#RemainingVaccinsActivity} when the btnNoVaccins is clicked.
         */
        btnNoVaccines.setOnClickListener(view -> {
            Intent intent = new Intent(this, RemainingVaccinsActivity.class);
            Bundle bundle = new Bundle();

            bundle.putSerializable("vaccins", allVaccins);
            intent.putExtra("bundle", bundle);
            startActivity(intent);
        });
    }

    public void setRecyclerView() {
        RecyclerView.Adapter mAdapter = new MultiSelectAdapter(this.allVaccins, this);
        LinearLayoutManager manager = new LinearLayoutManager(GivenVaccinsActivity.this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }

}
