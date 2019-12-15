package be.volders.firebase;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG ="RecyclerViewAdapter";
    private Context mContext;
    private ArrayList<User> mUseres = new ArrayList<>();
    final DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("user");;

    public RecyclerViewAdapter(Context mContext, ArrayList<User> mUseres) {
        this.mContext = mContext;
        this.mUseres = mUseres;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.name.setText(mUseres.get(position).getName());
        holder.age.setText(mUseres.get(position).getAge());
        holder.info.setText(mUseres.get(position).getInfo());
    }

    @Override
    public int getItemCount() {
        return mUseres.size();
    }

    //dit EERST maken
    public class ViewHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener{
        TextView name;
        TextView age;
        TextView info;

        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            age = itemView.findViewById(R.id.tv_age);
            info = itemView.findViewById(R.id.tv_info);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            User selectedUser = mUseres.get(position);
            Log.d(TAG, "onClick: "+selectedUser.toString()+" deleted");
            userRef.child(selectedUser.getId()).removeValue();
        }
    }
}
