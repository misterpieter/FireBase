package be.volders.firebase.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import be.volders.firebase.R;
import be.volders.firebase.models.Vaccin;

public class MultiSelectAdapter extends RecyclerView.Adapter<MultiSelectAdapter.MyViewHolder> {

    private List<Vaccin> mModelList;

    public MultiSelectAdapter(List<Vaccin> modelList) {
        mModelList = modelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Vaccin vaccin = mModelList.get(position);

        // Set UI members
        holder.vaccinName.setText(vaccin.getNaam());
        String ziektes = "";
        for (String ziekte : vaccin.getZiektes()) {
            ziektes += ziekte + " ";
        }
        holder.vaccinInfo.setText("Ziektes: " + ziektes);

        holder.view.setBackgroundColor(vaccin.isSelected() ? Color.CYAN : Color.WHITE);
        holder.vaccinName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClicked(vaccin, holder);
            }
        });
        holder.vaccinInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClicked(vaccin, holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mModelList == null ? 0 : mModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private TextView vaccinName, vaccinInfo;

        private MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            vaccinName = itemView.findViewById(R.id.name);
            vaccinInfo = itemView.findViewById(R.id.info);
        }
    }

    private void itemClicked(Vaccin vaccin, MyViewHolder holder) {
        vaccin.setSelected(!vaccin.isSelected());
        holder.view.setBackgroundColor(vaccin.isSelected() ? Color.CYAN : Color.WHITE);
    }
}
