package com.capco.weatherapp.location;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.capco.weatherapp.ApplicationState;
import com.capco.weatherapp.R;
import com.capco.weatherapp.gestures.SwipeHelper;
import com.capco.weatherapp.location.model.Location;

import java.util.List;

public class LocationListFragment extends Fragment implements LocationListView {

    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_location, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.map_button:
                ApplicationState.getMainPresenter().switchToMapFragment();
                break;
        }
        return true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_locations,
                container, false);
        recyclerView = view.findViewById(R.id.lv_locations);
        ApplicationState.getLocationListPresenter().registerLocationListView(this);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        return view;
    }

    @Override
    public void loadLocations(final List<Location> locations) {
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
        addGestureSupport(locations, adapter);
    }

    private void addGestureSupport(final List<Location> locations, final LocationRecyclerViewAdapter adapter){
        new SwipeHelper(getContext(), recyclerView) {
            @Override
            public void instantiateUnderlayButton(final RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
                underlayButtons.add(new SwipeHelper.UnderlayButton(
                        getString(R.string.delete),
                        0,
                        ContextCompat.getColor(getContext(), R.color.colorDanger),
                        getContext(),
                        new SwipeHelper.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                int position = viewHolder.getAdapterPosition();
                                Location location = adapter.getItem(position);
                                locations.remove(location);
                                adapter.notifyItemRemoved(position);
                                ApplicationState.getMainPresenter().getLocationBookmarkService().removeLocation(location.getName());
                                ApplicationState.getMapPresenter().reloadLocations();
                            }
                        }
                ));
            }
        };
    }
}
