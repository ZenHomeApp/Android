package com.eduvilar.zenhome.fragments.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eduvilar.zenhome.R;
import com.eduvilar.zenhome.model.House;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by efraespada on 02/12/2017.
 */

public abstract class HomesAdapter extends RecyclerView.Adapter<HomesAdapter.HomeHolder> {

    private final List<House> houses = new ArrayList<>();

    public abstract void onClick(House house);

    public void refresh(List<House> houses) {
        this.houses.clear();
        this.houses.addAll(houses);
    }


    @Override
    public HomeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_house, parent, false);
        return new HomeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HomeHolder holder, int position) {
        final House house = houses.get(position);
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomesAdapter.this.onClick(house);
            }
        });
        holder.name.setText(house.getName());
    }

    @Override
    public int getItemCount() {
        return houses.size();
    }

    public class HomeHolder extends RecyclerView.ViewHolder {

        public LinearLayout content;
        public TextView name;

        public HomeHolder(View view) {
            super(view);
            content = (LinearLayout) view.findViewById(R.id.content);
            name = (TextView) view.findViewById(R.id.name);
        }
    }
}
