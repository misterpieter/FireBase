package be.volders.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import be.volders.firebase.adapters.MultiSelectAdapter;
import be.volders.firebase.models.Vaccin;

public class RemainingVaccinsActivity extends AppCompatActivity {

    private final static String TITLE = "Nog toe te dienen vaccins";

    private RecyclerView mRecyclerView;
    private ArrayList<Vaccin> allVaccins = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remaining_vaccins);
        Objects.requireNonNull(getSupportActionBar()).setTitle(TITLE);

        mRecyclerView = findViewById(R.id.recycler_view);
        Button btnNewPatient = findViewById(R.id.btnNewPatient);

        /*
            Get the intent sent from the MultiSelectAdapter and get the Intent's extra,
            containing an ArrayList of all selected Vaccin objects.
        */
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getExtras() != null) {
                Bundle bundle = intent.getBundleExtra("bundle");
                assert bundle != null;
                ArrayList <Vaccin> selectedVaccins = (ArrayList<Vaccin>) bundle.getSerializable("vaccins");

                assert selectedVaccins != null;
                for (Vaccin selectedVaccin : selectedVaccins) {
                    this.allVaccins.add(selectedVaccin);
                    setRecyclerView();
                }
            }
        }

        btnNewPatient.setOnClickListener(view -> {
            Intent newPatient = new Intent(this, AddUserActivity.class);
            startActivity(newPatient);
        });
    }

    public void setRecyclerView() {
        RecyclerView.Adapter mAdapter = new MultiSelectAdapter(this.allVaccins, this);
        LinearLayoutManager manager = new LinearLayoutManager(RemainingVaccinsActivity.this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }

}
