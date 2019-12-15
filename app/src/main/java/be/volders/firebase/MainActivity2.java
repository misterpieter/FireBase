package be.volders.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2 extends AppCompatActivity {

    String TAG = "MainActivity: 2";

    Vaccin vaccin;

    EditText txtVaccinName;
    EditText txtZiekteNaam;

    Button btnSaveVaccin;
    Button btnShowListVaccins;
    Button btnAddZiekte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        vaccin = new Vaccin();
        final Intent i = new Intent(this,ListActivity2.class);

// ----------------------- UI SETUP -----------------------
        txtVaccinName = findViewById(R.id.txtVaccinName);
        txtZiekteNaam = findViewById(R.id.txtZiekteNaam);
        btnSaveVaccin = findViewById(R.id.btnSaveVaccin);
        btnShowListVaccins = findViewById(R.id.btnShowVaccinList);
        btnAddZiekte = findViewById(R.id.btnAddZiekte);


// ----------------------- DATABASE SETUP -----------------------
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference reference = database.getReference();
        final DatabaseReference vaccinRef = database.getReference().child("vaccin");


        vaccin = new Vaccin("IPV");
        vaccin.addWeekInterval(8);
        vaccin.addWeekInterval(12);
        vaccin.addWeekInterval(16);
        vaccin.addMaandInterval(13, 15);
        vaccin.addJaarInterval(5, 6);
        vaccin.addZiekte("Poliomyelitis");
        vaccin.addVaccin("IPV");
        vaccin.addVaccin("DTPa");
        vaccin.addVaccin("Hib");
        vaccin.addVaccin("HBV");
        vaccinRef.child(vaccin.getNaam()).setValue(vaccin);

        vaccin = new Vaccin("DTPa");
        vaccin.addWeekInterval(8);
        vaccin.addWeekInterval(12);
        vaccin.addWeekInterval(16);
        vaccin.addMaandInterval(13, 15);
        vaccin.addJaarInterval(5, 6);
        vaccin.addZiekte("Difterie");
        vaccin.addZiekte("Tetanus");
        vaccin.addZiekte("Kinkhoest");
        vaccin.addVaccin("IPV");
        vaccin.addVaccin("DTPa");
        vaccin.addVaccin("Hib");
        vaccin.addVaccin("HBV");
        vaccinRef.child(vaccin.getNaam()).setValue(vaccin);

        vaccin = new Vaccin("dTpa");
        vaccin.addJaarInterval(15, 16);
        vaccin.addJaarInterval(">= 25 jaar en elke 10 jaren");
        vaccin.addJaarInterval(">= 65 jaar");
        vaccin.addZiekte("Difterie");
        vaccin.addZiekte("Tetanus");
        vaccin.addZiekte("Kinkhoest");
        vaccin.setVrouw(true);
        vaccinRef.child(vaccin.getNaam()).setValue(vaccin);

        vaccin = new Vaccin("Hib");
        vaccin.addWeekInterval(8);
        vaccin.addWeekInterval(12);
        vaccin.addWeekInterval(16);
        vaccin.addMaandInterval(13, 15);
        vaccin.addZiekte("Haemophilus");
        vaccin.addZiekte("Influenzae");
        vaccin.addZiekte("type b (Hib)");
        vaccin.addVaccin("IPV");
        vaccin.addVaccin("DTPa");
        vaccin.addVaccin("Hib");
        vaccin.addVaccin("HBV");
        vaccinRef.child(vaccin.getNaam()).setValue(vaccin);

        vaccin = new Vaccin("HBV");
        vaccin.addWeekInterval(8);
        vaccin.addWeekInterval(12);
        vaccin.addWeekInterval(16);
        vaccin.addMaandInterval(13, 15);
        vaccin.addZiekte("Hepatitis B");
        vaccin.addVaccin("IPV");
        vaccin.addVaccin("DTPa");
        vaccin.addVaccin("Hib");
        vaccin.addVaccin("HBV");
        vaccinRef.child(vaccin.getNaam()).setValue(vaccin);




        // ----------------------- READ METHODS -----------------------
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String data = "";
                data = dataSnapshot.getValue().toString();


                // ----------------------- GET ALL Vaccins  -----------------------
                DataSnapshot vaccins = dataSnapshot.child("vaccin");

                for (DataSnapshot snapshot : vaccins.getChildren()) {
                    Vaccin vaccin = snapshot.getValue(Vaccin.class);
                    Log.d(TAG+" snapshot: ", vaccin.toString());
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        //click button
        btnSaveVaccin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtVaccinName.getText().toString();
                String ziekteNaam = txtZiekteNaam.getText().toString();

                if(name.isEmpty()){
                    Toast.makeText(MainActivity2.this, "Gelieve eerst een vaccin naam in te vullen", Toast.LENGTH_SHORT).show();
                }
                else {
                    vaccin.setNaam(name);
                    vaccin.addZiekte(ziekteNaam);
                    vaccinRef.child(vaccin.getNaam()).setValue(vaccin);

                    clear();

                    Toast.makeText(MainActivity2.this, name+ " opgeslagen!", Toast.LENGTH_SHORT).show();
                    startActivity(i);
                }
            }
        });


        //click button
        btnShowListVaccins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(i);
            }
        });

    }


    private void clear() {
        txtVaccinName.setText("");
        txtZiekteNaam.setText("");
    }
}
