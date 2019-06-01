package com.entersekt.malls.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.entersekt.malls.adapter.ItemAdapter;
import com.entersekt.malls.R;
import com.entersekt.malls.listener.CitySelectActionListener;
import com.entersekt.malls.ui.UiPresenter;
import com.entersekt.malls.ui.activity.MainActivity;

import java.util.ArrayList;

public class CityListFragment extends Fragment implements ItemAdapter.OnItemSelectListener {

    CitySelectActionListener listener;
    private static final String TITLE = "Cities";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view, container, false);
        MainActivity activity = (MainActivity) getActivity();

        activity.getSupportActionBar().setTitle(TITLE);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        ArrayList<String> stringArrayList = getArguments() != null
                ? getArguments().getStringArrayList(UiPresenter.KEY)
                : new ArrayList<>();

        ItemAdapter adapter = new ItemAdapter(stringArrayList, this);

        RecyclerView recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onItemSelect(int cityId, String cityName) {
        listener.onCitySelected(cityId, cityName);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (CitySelectActionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement CitySelectActionListener");
        }
    }

}
