package be.volders.firebase;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import be.volders.firebase.adapters.MultiSelectAdapter;
import be.volders.firebase.models.Vaccin;

public class RemainingVaccinsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<Vaccin> allVaccins = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remaining_vaccins);
        mRecyclerView = findViewById(R.id.recycler_view);

        /*
            Get the intent sent from the MultiSelectAdapter and get the Intent's extra,
            containing an ArrayList of all selected Vaccin objects.
        */
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getExtras() != null) {
                Bundle bundle = intent.getBundleExtra("bundle");
                assert bundle != null;
                ArrayList<Vaccin> selectedVaccins = (ArrayList<Vaccin>) bundle.getSerializable("vaccins");

                assert selectedVaccins != null;
                for (Vaccin selectedVaccin : selectedVaccins) {
                    System.out.println("selected: " + selectedVaccin.getNaam());
                    this.allVaccins.add(selectedVaccin);

                    setRecyclerView();
                }
            }
        }
    }

    public void setRecyclerView() {
        RecyclerView.Adapter mAdapter = new MultiSelectAdapter(this.allVaccins, this);
        LinearLayoutManager manager = new LinearLayoutManager(RemainingVaccinsActivity.this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }

}
