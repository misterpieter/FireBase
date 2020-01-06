package be.volders.firebase;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import be.volders.firebase.adapters.MultiSelectAdapter;
import be.volders.firebase.helpers.DateHelper;
import be.volders.firebase.helpers.FirebaseHelper;
import be.volders.firebase.models.User;
import be.volders.firebase.models.Vaccin;

public class GivenVaccinsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private User user;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference();

    private ArrayList<Vaccin> allVaccins = new ArrayList<>();
    private ArrayList<Vaccin> filteredList = new ArrayList<>();

    private void initDatabaseData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot vaccins = dataSnapshot.child("vaccin");

                for (DataSnapshot snapshot : vaccins.getChildren()) {
                    Vaccin vaccin = snapshot.getValue(Vaccin.class);

                    allVaccins.add(vaccin);
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

        initDatabaseData();

        /*
            Get the intent sent from AddUserActivity and get the Intent's extra,
            containing the Serialized User object with patient info.
         */
        Intent patientIntent = getIntent();
        if (patientIntent != null) {
            if (patientIntent.getExtras() != null) {
                Bundle bundle = patientIntent.getBundleExtra("patient_bundle");
                assert bundle != null;
                this.user = (User) bundle.getSerializable("patient");
            }
        }

        /*
            Get the intent sent from the MultiSelectAdapter and get the Intent's extra,
            containing an ArrayList of all selected Vaccin objects.
        */
        /*
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getExtras() != null) {
                Bundle bundle = intent.getBundleExtra("bundle");
                assert bundle != null;
                ArrayList<Vaccin> selectedVaccins = (ArrayList<Vaccin>) bundle.getSerializable("vaccins");

                assert selectedVaccins != null;
                for (Vaccin selectedVaccin : selectedVaccins) {
                    System.out.println(selectedVaccin.getNaam());
                }
            }
        }
        */
        filterVaccins();
    }

    /**
     * Filter the list of all vaccins, based on the patient's age, gender, riskgroup, ...
     * and add all condition-matching vaccins to a new ArrayList, called filteredList.
     */
    private void filterVaccins() {
        // Requires API level 26 or above.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int patientAge = DateHelper.calculateAgeInMonths(this.user.getGbDt());
            // TODO: for-loop to check conditions (date of birth etc.)
        }
    }

    public void setRecyclerView() {
        RecyclerView.Adapter mAdapter = new MultiSelectAdapter(this.allVaccins, this);
        LinearLayoutManager manager = new LinearLayoutManager(GivenVaccinsActivity.this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }

}
