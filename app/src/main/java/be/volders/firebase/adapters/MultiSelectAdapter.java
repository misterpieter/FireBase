package be.volders.firebase.adapters;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import be.volders.firebase.GivenVaccinsActivity;
import be.volders.firebase.R;
import be.volders.firebase.RemainingVaccinsActivity;
import be.volders.firebase.models.Vaccin;

/**
 * The adapter responsible for handling multi-selecting of items in a RecycleView.
 *
 * @author Gaetan Dumortier
 * @since 02/01/2020
 */
public class MultiSelectAdapter extends RecyclerView.Adapter<MultiSelectAdapter.MyViewHolder> {

    private ArrayList<Vaccin> vaccinList;
    private ArrayList<Vaccin> selectedVaccins = new ArrayList<>();
    private AppCompatActivity activityContext;

    public MultiSelectAdapter(ArrayList<Vaccin> vaccinList, AppCompatActivity activityContext) {
        this.vaccinList = vaccinList;
        this.activityContext = activityContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Vaccin vaccin = vaccinList.get(position);

        // Set UI members
        holder.vaccinName.setText(vaccin.getNaam());

        StringBuilder ziektes = new StringBuilder();
        for (String ziekte : vaccin.getZiektes()) {
            ziektes.append(ziekte).append(" ");
        }
        holder.vaccinInfo.setText("Ziektes: " + ziektes);
        holder.view.setBackgroundColor(Color.WHITE);

        // Only let the GivenVaccinsActivity have a click-able recycleview.
        if (this.activityContext.getClass().equals(GivenVaccinsActivity.class)) {
            holder.view.setBackgroundColor(vaccin.isSelected() ? Color.CYAN : Color.WHITE);

            holder.vaccinName.setOnClickListener(view -> itemClicked(vaccin, holder));
            holder.vaccinInfo.setOnClickListener(view -> itemClicked(vaccin, holder));
        }
    }

    @Override
    public int getItemCount() {
        return vaccinList == null ? 0 : vaccinList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView vaccinName, vaccinInfo;

        private MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            vaccinName = itemView.findViewById(R.id.name);
            vaccinInfo = itemView.findViewById(R.id.info);
        }
    }

    /**
     * Called when an item is clicked within the RecycleView.
     *
     * @param vaccin The Vaccin object that has been clicked.
     * @param holder The ViewHolder object, containing the required UI elements
     */
    private void itemClicked(Vaccin vaccin, MyViewHolder holder) {
        vaccin.setSelected(!vaccin.isSelected());
        holder.view.setBackgroundColor(vaccin.isSelected() ? Color.CYAN : Color.WHITE);
        this.selectedVaccins.add(vaccin);

        if (!vaccin.isSelected()) {
            this.selectedVaccins.remove(vaccin);
        }

        /*
          Start a new activity to @link{#RemainingVaccinsActivity} when the btnOnProceed button is clicked.
        */
        activityContext.findViewById(R.id.btnProceed).setOnClickListener(view -> {
            /*
              Start a new intent and create a new Bundle containing an ArrayList of remaining vaccines.
              Next, add the Bundle to the intent as an extra and start the intent.
            */
            ArrayList<Vaccin> remainingVaccins = new ArrayList<>();
            for (Vaccin vaccin1 : this.vaccinList) {
                if (!vaccin1.isSelected()) {
                    remainingVaccins.add(vaccin1);
                }
            }

            Intent intent = new Intent(activityContext, RemainingVaccinsActivity.class);
            Bundle bundle = new Bundle();

            bundle.putSerializable("vaccins", remainingVaccins);
            intent.putExtra("bundle", bundle);
            activityContext.startActivity(intent);
        });
    }
}
