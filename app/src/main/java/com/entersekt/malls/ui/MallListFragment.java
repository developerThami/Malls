package com.entersekt.malls.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.entersekt.malls.ItemAdapter;
import com.entersekt.malls.MainActivity;
import com.entersekt.malls.R;
import com.entersekt.malls.UserAction;

import java.util.ArrayList;

public class MallListFragment extends Fragment implements ItemAdapter.OnItemSelectListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view, container, false);

        ArrayList<String> stringArrayList = getArguments() != null ? getArguments().getStringArrayList("key") : new ArrayList<>();
        ItemAdapter adapter = new ItemAdapter(stringArrayList,this);

        RecyclerView recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onItemSelect(int mallId) {
        MainActivity activity = (MainActivity) getActivity();
        UserAction userAction = activity.getUserAction();
        userAction.onMallSelected(mallId);
    }
}
