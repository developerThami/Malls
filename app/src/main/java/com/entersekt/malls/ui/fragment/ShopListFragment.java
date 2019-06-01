package com.entersekt.malls.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.entersekt.malls.adapter.ItemAdapter;
import com.entersekt.malls.R;
import com.entersekt.malls.ui.UiPresenter;
import com.entersekt.malls.ui.activity.MainActivity;

import java.util.ArrayList;

public class ShopListFragment extends Fragment {

    public static final String CITY_NAME = "cityName";
    public static final String MALL_NAME = "mallName";
    private static final String TITLE = " shops";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view, container, false);
        MainActivity activity = (MainActivity) getActivity();

        ArrayList<String> stringArrayList = getArguments() != null
                ? getArguments().getStringArrayList(UiPresenter.KEY)
                : new ArrayList<>();

        String cityName = getArguments().getString(MALL_NAME, getArguments().getString(CITY_NAME));
        activity.getSupportActionBar().setTitle(cityName.concat(TITLE));

        ItemAdapter adapter = new ItemAdapter(stringArrayList);

        RecyclerView recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        return view;
    }

}
