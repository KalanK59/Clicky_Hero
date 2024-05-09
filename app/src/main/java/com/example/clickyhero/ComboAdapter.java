package com.example.clickyhero;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ComboAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Combination> combos;

    public ComboAdapter() {
    }



    public ComboAdapter(ArrayList<Combination> comboList) {
        this.combos = comboList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
