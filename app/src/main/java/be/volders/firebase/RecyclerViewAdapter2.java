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
import java.util.List;

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.ViewHolder> {

    private static final String TAG ="RecyclerViewAdapter2";
    private Context mContext;
    private List<Vaccin> mVaccins = new ArrayList<>();
    final DatabaseReference vaccinRef = FirebaseDatabase.getInstance().getReference().child("vaccin");;

    public RecyclerViewAdapter2(Context mContext, ArrayList<Vaccin> mVaccins) {
        this.mContext = mContext;
        this.mVaccins = mVaccins;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem2,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.vaccinName.setText(mVaccins.get(position).getNaam());
        holder.ziekteName.setText(mVaccins.get(position).showAllInfo());
    }

    @Override
    public int getItemCount() {
        return mVaccins.size();
    }

    //dit EERST maken
    public class ViewHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener{
        TextView vaccinName;
        TextView ziekteName;

        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            vaccinName = itemView.findViewById(R.id.vaccin_name);
            ziekteName = itemView.findViewById(R.id.ziekte_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Vaccin selectedVaccin = mVaccins.get(position);
            vaccinRef.child(selectedVaccin.getNaam()).removeValue();
            mVaccins.clear();
        }
    }
}
