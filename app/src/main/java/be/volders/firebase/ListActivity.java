package be.volders.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListActivity extends AppCompatActivity {

    Button btnInput;
    Intent i;

    private ArrayList<User> mUsers = new ArrayList<>();
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference = database.getReference();
    private static  final String TAG = "ListA: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        btnInput = findViewById(R.id.buttoninput);
        i = new Intent(this,MainActivity.class);

        initDatabaseData();


        //click button
        btnInput.setOnClickListener(new View.OnClickListener() {
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



//    private void initData(){
//        mUsers.add(new User("user 1","1", "info 5"));
//        mUsers.add(new User("user 2","1", "info 4"));
//        mUsers.add(new User("user 3","1", "info 3"));
//        mUsers.add(new User("user 4","1", "info 2"));
//        mUsers.add(new User("user 5","1", "info 1"));
//
////        initRecyclerView();
//    }



    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mUsers);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
