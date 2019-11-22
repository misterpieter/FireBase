package be.volders.firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG ="RecyclerViewAdapter";
    private Context mContext;
    private ArrayList<User> mUseres = new ArrayList<>();

    public RecyclerViewAdapter(Context mContext, ArrayList<User> mUseres) {
        this.mContext = mContext;
        this.mUseres = mUseres;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;    }

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
    public class ViewHolder extends RecyclerView.ViewHolder{

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
        }
    }
}
