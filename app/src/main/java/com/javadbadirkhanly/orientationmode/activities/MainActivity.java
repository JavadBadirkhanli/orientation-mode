package com.javadbadirkhanly.orientationmode.activities;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.javadbadirkhanly.orientationmode.R;
import com.javadbadirkhanly.orientationmode.adapters.ExpandableGroupsAdapter;
import com.javadbadirkhanly.orientationmode.fragments.StorageFragment;
import com.javadbadirkhanly.orientationmode.models.Group;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements StorageFragment.TaskCallbacks{

    private final String TAG = getClass().getSimpleName();
    private static final String TAG_TASK_FRAGMENT = "task_fragment";

    private RecyclerView recyclerView;
    private ExpandableRecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private StorageFragment mTaskFragment;

    private List<Group> groupList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groupList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewMainActivity);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ExpandableGroupsAdapter(MainActivity.this, groupList);
        recyclerView.setAdapter(adapter);

        FragmentManager fm = getSupportFragmentManager();
        mTaskFragment = (StorageFragment) fm.findFragmentByTag(TAG_TASK_FRAGMENT);

        // First time when application launch
        if (mTaskFragment == null) {
            mTaskFragment = new StorageFragment();
            fm.beginTransaction().add(mTaskFragment, TAG_TASK_FRAGMENT).commit();
        }

        // Across configuration changing
        if (mTaskFragment.getData() != null) {
            groupList.addAll(mTaskFragment.getData());

            adapter = new ExpandableGroupsAdapter(MainActivity.this, groupList);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onPreExecute() {
        // You can start here a progress bar
        Log.d(TAG, "onPreExecute: callback from Fragment");
    }

    @Override
    public void onPostExecute() {
        // You can stop here a progress bar or some other operations
        Log.d(TAG, "onPostExecute: callback from Fragment");

        if (mTaskFragment.getData() != null) {
            Log.d(TAG, "onCreate: size of data: " + mTaskFragment.getData().size());
            groupList.addAll(mTaskFragment.getData());

            adapter = new ExpandableGroupsAdapter(MainActivity.this, groupList);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, "onSaveInstanceState: Saving");
        adapter.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState: Restoring");
        adapter.onRestoreInstanceState(savedInstanceState);
    }
}
