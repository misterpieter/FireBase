package be.volders.firebase;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RecyclerUserViewAdapter extends RecyclerView.Adapter<RecyclerUserViewAdapter.ViewHolder> {

    private static final String TAG ="RecyclerUserViewAdapter";
    private Context mContext;
    private ArrayList<User> mUseres = new ArrayList<>();
    final DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("user");

    public RecyclerUserViewAdapter(Context mContext, ArrayList<User> mUseres) {
        this.mContext = mContext;
        this.mUseres = mUseres;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_user,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.name.setText(mUseres.get(position).getName());
        holder.gbDt.setText(mUseres.get(position).getGbDt());
        if(mUseres.get(position).getRisicoGroep() == true){
            holder.risico.setText("RISICO groep!");
        }
        if(mUseres.get(position).getZwanger() == true){
            holder.vrouw.setText("ZWANGER!");
        }
    }

    @Override
    public int getItemCount() {
        return mUseres.size();
    }

    //dit EERST maken
    public class ViewHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener{
        TextView name;
        TextView gbDt;
        TextView risico;
        TextView vrouw;

        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.li_naam);
            gbDt = itemView.findViewById(R.id.li_gbdt);
            risico = itemView.findViewById(R.id.li_risico);
            vrouw = itemView.findViewById(R.id.li_vrouw);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            User selectedUser = mUseres.get(position);
            Log.d(TAG, "onClick: "+selectedUser.toString()+" deleted");
            Toast.makeText(view.getContext(),selectedUser.getName()+" deleted", Toast.LENGTH_SHORT).show();
            userRef.child(selectedUser.getName()).removeValue();
            mUseres.clear();
        }

    }
}
