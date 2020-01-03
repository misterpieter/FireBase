package be.volders.firebase;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import be.volders.firebase.adapters.MultiSelectAdapter;
import be.volders.firebase.helpers.FirebaseHelper;
import be.volders.firebase.models.Vaccin;

public class GivenVaccinsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private ArrayList<Vaccin> allVaccins = new ArrayList<>();
    private ArrayList<Vaccin> filteredList = new ArrayList<>();
    private FirebaseHelper helper = new FirebaseHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_given_vaccins);
        mRecyclerView = findViewById(R.id.recycler_view);

        this.allVaccins = helper.getVaccinsList();

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
                    System.out.println(selectedVaccin.getNaam());
                }
            }
        }

        filterVaccins();
    }

    private void filterVaccins() {
        for (Vaccin vaccin : this.allVaccins) {
            System.out.println(vaccin.getNaam());
        }
        // TODO: for-loop to check conditions (date of birth etc.)

        /*
        int age = 0;
        // Requires API level 26 or above.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            if (patientDob != null) {
                age = DateHelper.calculateAgeInMonths(patientDob);
                System.out.println("leeftijd in maanden: " + age);
            }
        }
        */
        setRecyclerView();
    }

    private void setRecyclerView() {
        RecyclerView.Adapter mAdapter = new MultiSelectAdapter(this.filteredList, this);
        LinearLayoutManager manager = new LinearLayoutManager(GivenVaccinsActivity.this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }

}
