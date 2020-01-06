package be.volders.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import be.volders.firebase.models.User;

public class AddUserActivity extends AppCompatActivity {

    private static final String TITLE = "Nieuwe patiënt";

    User user;
    EditText txtName, txtgbDt;
    RadioButton rbIsMan, rbIsWomen;
    CheckBox riskGroup;
    Button btnSave, btnListVaccins;
    String TAG = "AddUserActivity: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        Objects.requireNonNull(getSupportActionBar()).setTitle(TITLE);


        final Intent i2 = new Intent(this, ListBasicVaccinActivity.class);
        final Intent overview = new Intent(this, GivenVaccinsActivity.class);

// ----------------------- UI SETUP -----------------------
        txtName = findViewById(R.id.txtName);
        txtgbDt = findViewById(R.id.txt_gbdt);
        riskGroup = findViewById(R.id.chb_risico);
        rbIsMan = findViewById(R.id.rb_man);
        rbIsWomen = findViewById(R.id.rb_vrouw);

        btnSave = findViewById(R.id.btnSavePatient);
        btnListVaccins = findViewById(R.id.btnListVaccins);

// ----------------------- DATABASE SETUP -----------------------
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference reference = database.getReference();
        final DatabaseReference nodeRef = database.getReference("node");

        final DatabaseReference userRef = database.getReference().child("user");
        final DatabaseReference vaccinRef = database.getReference().child("vaccin");

// ----------------------- DATABASE POPULATION -----------------------
        reference.child("basic key").setValue("basic value");
        nodeRef.child("node key").setValue("node value");
        nodeRef.child("child node").child("child key").setValue("child value");

        // ----------------------- READ METHODS -----------------------
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                String data = "";
                data = dataSnapshot.getValue().toString();

                // ----------------------- GET ALL USERS  -----------------------
                DataSnapshot users = dataSnapshot.child("user");

                // -----------------------      v2       -----------------------
                for (DataSnapshot snapshot : users.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    // Log.d(TAG + " snapshot: ", user.toString());
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        // Called when the add patient button is clicked.
        btnSave.setOnClickListener(view -> {
            String name = txtName.getText().toString();
            String gebDt = txtgbDt.getText().toString();

            if (name.isEmpty() || gebDt.isEmpty()) {
                Toast.makeText(AddUserActivity.this, "Gelieve alle velden in te vullen", Toast.LENGTH_SHORT).show();
            } else {
                user = new User(name, gebDt);
                userRef.child(user.getName()).setValue(user);

                // Check if patient is in a riskgroup or not
                boolean isRiskGroup = riskGroup.isChecked();

                // Make out what gender is selected
                String gender = rbIsMan.isChecked() ? "m" : "v";

                // Assign values to patient
                user.setName(name);
                user.setGbDt(gebDt);
                user.setRisicoGroep(isRiskGroup);
                user.setGender(gender);

                // Create a new bundle, containing the serialized User object and start the GivenVaccins activity.
                Bundle bundle = new Bundle();
                bundle.putSerializable("patient", user);
                overview.putExtra("patient_bundle", bundle);
                startActivity(overview);

                // clear();
            }
        });

        btnListVaccins.setOnClickListener(view -> startActivity(i2));
    }

    private void clear() {
        txtName.setText("");
        txtgbDt.setText("");
        riskGroup.setChecked(false);
    }
}
