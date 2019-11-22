package be.volders.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    User user;

    EditText txtName;
    EditText txtAge;
    EditText txtInfo;
    Button btnSave;
    Button btnList;
    String TAG = "MainActivity: ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = new User();
        final Intent i = new Intent(this,ListActivity.class);



// ----------------------- UI SETUP -----------------------
        txtName = findViewById(R.id.txtName);
        txtAge = findViewById(R.id.txtAge);
        txtInfo = findViewById(R.id.txtInfo);
        btnSave = findViewById(R.id.btnSave);
        btnList = findViewById(R.id.btnList);


// ----------------------- DATABASE SETUP -----------------------
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference reference = database.getReference();
        final DatabaseReference nodeRef = database.getReference("node");

        final DatabaseReference userRef = database.getReference().child("user");


// ----------------------- DATABASE POPULATION -----------------------
//        reference.child("basic key").setValue("basic value");
//        nodeRef.child("node key").setValue("node value");
//        nodeRef.child("child node").child("child key").setValue("child value");

//        user = new User("Pieter Volders","36","info");
//        userRef.push().setValue(user);
//        user = new User("Gaetan Dumortier","12","nog wa info");
//        userRef.push().setValue(user);





        // ----------------------- READ METHODS -----------------------
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                String data = "";
                data = dataSnapshot.getValue().toString();


                // ----------------------- TEST METHODS -----------------------
//                DataSnapshot basicKey = dataSnapshot.child("basic key");
//                DataSnapshot node = dataSnapshot.child("node");
//                DataSnapshot nodeKey = node.child("node key");
//                DataSnapshot childNode = node.child("child node");
//                DataSnapshot childKey = childNode.child("child key");
//
//
//                Log.d(TAG, "Database info: "
//                        +"\n\ndata: "+ data
//                        +"\nnode data: "+ node
//                        +"\nchild node data: "+ childNode
//                        +"\n\n1 "+ basicKey.getKey()+": "+basicKey.getValue()
//                        +"\n\n2 "+ childKey.getKey()+": "+childKey.getValue()
//                        +"\n\n3 "+ nodeKey.getKey()+": "+nodeKey.getValue()
//                );



                // ----------------------- GET ALL USERS  -----------------------
                DataSnapshot users = dataSnapshot.child("user");

//                // -----------------------      v1       -----------------------
//                Map<String,Object>mapper = (HashMap<String,Object>) users.getValue();
//                Log.d(TAG+ " map: ", mapper.toString());

                // -----------------------      v2       -----------------------
                for (DataSnapshot snapshot : users.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    Log.d(TAG+" snapshot: ", user.toString());
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        //click button
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtName.getText().toString();
                String age = txtAge.getText().toString();
                String info = txtInfo.getText().toString();

                if(name.isEmpty() || age.isEmpty()|info.isEmpty()){
                    Toast.makeText(MainActivity.this, "Gelieve alle velden in te vullen", Toast.LENGTH_SHORT).show();
                }
                else {
                    user.setInfo(info);
                    user.setAge(age);
                    user.setName(name);
                    userRef.push().setValue(user);
                    clear();

                    Toast.makeText(MainActivity.this, name+ " opgeslagen!", Toast.LENGTH_SHORT).show();
                    startActivity(i);
                }
            }
        });


        //click button
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(i);
            }
        });

    }


    private void clear() {
        txtName.setText("");
        txtAge.setText("");
        txtInfo.setText("");
    }
}
