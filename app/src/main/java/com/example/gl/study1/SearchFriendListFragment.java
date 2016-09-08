package com.example.gl.study1;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gl.study1.Model.Friends;

import java.util.ArrayList;
import java.util.List;

public class SearchFriendListFragment extends Fragment {
    public static final String TAG= SearchFriendListFragment.class.getSimpleName();
    private ListResultAdapter listResultAdapter;
    private RecyclerView list;
    private List<Friends> data= new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_result_layout, container, false);
        list = (RecyclerView) view.findViewById(R.id.list_result);
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listResultAdapter = new ListResultAdapter(getContext(), data);
        list.setAdapter(listResultAdapter);
    }

    public void updateData(List<Friends> friendAdd) {
        this.data.addAll(friendAdd);
    }

}
