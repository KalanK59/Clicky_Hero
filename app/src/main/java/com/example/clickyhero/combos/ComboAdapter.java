package com.example.clickyhero.combos;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clickyhero.R;

import java.util.ArrayList;
import java.util.Collections;

public class ComboAdapter extends RecyclerView.Adapter<ComboAdapter.StudentViewHolder> {
    // List of combos
    private final ArrayList<Combos> comboList;
    // Context for starting activities
    private final Context context;


    // Constructor
    public ComboAdapter(ArrayList<Combos> comboList, Context context) {
        this.comboList = comboList;
        this.context = context;
        // Shuffle the combo list
//        Collections.shuffle(this.comboList);
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_clickyhero, parent, false);
        return new StudentViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        // Get element from the dataset at this position
        Combos combo = comboList.get(position);

        // Replace the contents of the view with that element
        holder.Name.setText(combo.getName());
        holder.imgArrow1.setImageResource(combo.getCombos()[0]);
        holder.imgArrow2.setImageResource(combo.getCombos()[1]);
        holder.imgArrow3.setImageResource(combo.getCombos()[2]);
        holder.imgArrow4.setImageResource(combo.getCombos()[3]);
        holder.imgArrow5.setImageResource(combo.getCombos()[4]);
        holder.imgArrow6.setImageResource(combo.getCombos()[5]);
        holder.imgArrow7.setImageResource(combo.getCombos()[6]);
        holder.imgArrow8.setImageResource(combo.getCombos()[7]);

        // Set background color based on the combo's correctness status
        switch (combo.getCorrect()) {
            // Default color
            case 0:
                holder.itemView.setBackgroundColor(Color.CYAN);
                break;
            // Correct combo
            case 1:
                holder.itemView.setBackgroundColor(Color.GREEN);
                break;
            // Incorrect combo
            case -1:
                holder.itemView.setBackgroundColor(Color.RED);
                break;
        }

        // Set onClickListener for the item view
        holder.itemView.setOnClickListener(view -> {
            onStudentClickListener.onStudentClick(position, comboList.get(position));
        });
    }

    // Return the size of the dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return comboList.size();
    }

    // Interface for handling item clicks
    public interface OnStudentClickListener {
        void onStudentClick(int position, Combos combo);
    }

    private OnStudentClickListener onStudentClickListener;

    // Setter for the OnStudentClickListener
    public void setOnStudentClickListener(OnStudentClickListener onStudentClickListener) {
        this.onStudentClickListener = onStudentClickListener;
    }

    // ViewHolder class for the RecyclerView items
    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView Name;
        ImageView imgArrow1, imgArrow2, imgArrow3, imgArrow4, imgArrow5, imgArrow6, imgArrow7, imgArrow8;
        LinearLayout Container;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize the UI components
            Name = itemView.findViewById(R.id.tvName);
            Container = itemView.findViewById(R.id.Container);
            imgArrow1 = itemView.findViewById(R.id.imgArrow);
            imgArrow2 = itemView.findViewById(R.id.imgArrow1);
            imgArrow3 = itemView.findViewById(R.id.imgArrow2);
            imgArrow4 = itemView.findViewById(R.id.imgArrow3);
            imgArrow5 = itemView.findViewById(R.id.imgArrow4);
            imgArrow6 = itemView.findViewById(R.id.imgArrow5);
            imgArrow7 = itemView.findViewById(R.id.imgArrow6);
            imgArrow8 = itemView.findViewById(R.id.imgArrow7);
        }
    }
}