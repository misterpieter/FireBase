package be.volders.firebase;

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
import java.util.List;

import be.volders.firebase.adapters.MultiSelectAdapter;
import be.volders.firebase.helpers.DateHelper;
import be.volders.firebase.models.Vaccin;

public class GivenVaccinsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    private ArrayList<Vaccin> mVaccins = new ArrayList<>();
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_given_vaccins);

        initDatabaseData();

        mRecyclerView = findViewById(R.id.recycler_view);
    }

    private void initDatabaseData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot vaccins = dataSnapshot.child("vaccin");
                int age = 0;

                /*
                // Requires API level 26 or above.
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    if (patientDob != null) {
                        age = DateHelper.calculateAgeInMonths(patientDob);
                        System.out.println("leeftijd in maanden: " + age);
                    }
                }
                 */

                for (DataSnapshot snapshot : vaccins.getChildren()) {
                    Vaccin vaccin = snapshot.getValue(Vaccin.class);
                    mVaccins.add(vaccin);
                }
                setRecyclerView();
            }


            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("The read failed: " + error.getMessage());
            }
        });
    }

    private void setRecyclerView() {
        mAdapter = new MultiSelectAdapter(mVaccins);
        LinearLayoutManager manager = new LinearLayoutManager(GivenVaccinsActivity.this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
