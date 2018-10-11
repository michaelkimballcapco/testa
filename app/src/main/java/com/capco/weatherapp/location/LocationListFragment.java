package com.capco.weatherapp.location;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.capco.weatherapp.ApplicationState;
import com.capco.weatherapp.R;

import java.util.List;

public class LocationListFragment extends Fragment implements LocationListView {

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_locations,
                container, false);
        recyclerView = view.findViewById(R.id.lv_locations);
        ApplicationState.getLocationListPresenter().registerLocationListView(this);
        return view;
    }

    @Override
    public void loadLocations(List<Location> locations) {
        final LocationRecyclerViewAdapter adapter = new LocationRecyclerViewAdapter(getContext(), locations);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter.setClickListener(new LocationRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), "Clicked: " + adapter.getItem(position).getName(), Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}
