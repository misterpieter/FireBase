package be.volders.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListActivity2 extends AppCompatActivity {

    Button btnInput;
    Intent i;

    private ArrayList<Vaccin> mVaccins = new ArrayList<>();
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference = database.getReference();
    private static  final String TAG = "ListA: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list2);

        initDatabaseData();
    }


    private void initDatabaseData(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot vaccins = dataSnapshot.child("vaccin");
                for (DataSnapshot snapshot : vaccins.getChildren()) {
                    Vaccin vaccin = snapshot.getValue(Vaccin.class);
                    mVaccins.add(vaccin);
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
        RecyclerView recyclerView = findViewById(R.id.recycler_view2);
        RecyclerViewAdapter2 adapter2 = new RecyclerViewAdapter2(this, mVaccins);
        recyclerView.setAdapter(adapter2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
