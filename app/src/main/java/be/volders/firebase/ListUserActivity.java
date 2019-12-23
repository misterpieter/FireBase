package be.volders.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListUserActivity extends AppCompatActivity {

    Button buttonInputNewPatient;
    Intent i;

    private ArrayList<User> mUsers = new ArrayList<>();
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference = database.getReference();
    private static  final String TAG = "ListA: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);

        buttonInputNewPatient = findViewById(R.id.buttonInputNewPatient);
        i = new Intent(this, AddUserActivity.class);

        initDatabaseData();

        //click button
        buttonInputNewPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(i);
            }
        });

    }


    private void initDatabaseData(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot users = dataSnapshot.child("user");
                for (DataSnapshot snapshot : users.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    mUsers.add(user);
                }
                initRecyclerView();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("The read failed: " + error.getMessage());
            }
        });

    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_user_view);
        RecyclerUserViewAdapter adapter = new RecyclerUserViewAdapter(this, mUsers);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
