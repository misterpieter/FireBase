package be.volders.firebase.adapters;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import be.volders.firebase.GivenVaccinsActivity;
import be.volders.firebase.R;
import be.volders.firebase.models.Vaccin;

/**
 * The adapter responsible for handling multi-selecting of items in a RecycleView.
 *
 * @author Gaetan Dumortier
 * @since 02/01/2020
 */
public class MultiSelectAdapter extends RecyclerView.Adapter<MultiSelectAdapter.MyViewHolder> {

    private List<Vaccin> vaccinList;
    private ArrayList<Vaccin> selectedVaccins = new ArrayList<>();
    private GivenVaccinsActivity givenVaccinsActivity;

    public MultiSelectAdapter(List<Vaccin> vaccinList, GivenVaccinsActivity givenVaccinsActivity) {
        this.vaccinList = vaccinList;
        this.givenVaccinsActivity = givenVaccinsActivity;
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

    private void itemClicked(Vaccin vaccin, MyViewHolder holder) {
        vaccin.setSelected(!vaccin.isSelected());
        holder.view.setBackgroundColor(vaccin.isSelected() ? Color.CYAN : Color.WHITE);
        this.selectedVaccins.add(vaccin);

        if(!vaccin.isSelected()) {
            this.selectedVaccins.remove(vaccin);
        }

        /*
          Start a new activity when the btnOnProceed button is clicked.
        */
        givenVaccinsActivity.findViewById(R.id.btnProceed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                  Start a new intent and create a new Bundle containing the ArrayList of selected vaccins.
                  Next, add the Bundle to the intent as an extra and start the intent.
                */
                Intent intent = new Intent(givenVaccinsActivity, GivenVaccinsActivity.class);
                Bundle bundle = new Bundle();
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                bundle.putSerializable("vaccins", selectedVaccins);
                intent.putExtra("bundle", bundle);
                givenVaccinsActivity.startActivity(intent);
            }
        });
    }
}
