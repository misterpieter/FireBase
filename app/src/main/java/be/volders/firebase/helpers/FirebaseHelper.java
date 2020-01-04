package be.volders.firebase.helpers;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import be.volders.firebase.models.Vaccin;

/**
 * Class responsible for all actions and properties of the Firebase Database.
 * which aren't many
 *
 * @author Gaetan Dumortier
 * @since 03/01/2020
 */
public class FirebaseHelper {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference();
    private ArrayList<Vaccin> vaccinsList = new ArrayList<>();

    public FirebaseHelper() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot vaccins = dataSnapshot.child("vaccin");

                for (DataSnapshot snapshot : vaccins.getChildren()) {
                    Vaccin vaccin = snapshot.getValue(Vaccin.class);

                    assert vaccin != null;
                    vaccinsList.add(vaccin);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("The read failed: " + error.getMessage());
            }
        });
    }

    public ArrayList<Vaccin> getVaccinsList() {
        return this.vaccinsList;
    }

}
