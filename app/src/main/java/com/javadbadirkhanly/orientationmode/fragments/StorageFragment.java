package com.javadbadirkhanly.orientationmode.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.javadbadirkhanly.orientationmode.models.Group;
import com.javadbadirkhanly.orientationmode.models.GroupItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by javadbadirkhanly on 12/13/16.
 */

public class StorageFragment extends Fragment {

    private final String TAG = getClass().getSimpleName();

    private TaskCallbacks mCallbacks;
    private Activity activity;
    private List<Group> groupList, dataList;

    public interface TaskCallbacks {
        void onPreExecute();
        void onPostExecute();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            activity = (Activity) context;
            mCallbacks = (TaskCallbacks) activity;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate: is active");

        // Retain this fragment across configuration changes.
        setRetainInstance(true);

        groupList = new ArrayList<>();
        dataList = new ArrayList<>();

        // Create and execute the background task.
        new MyAsyncTask().execute();
    }

    private class MyAsyncTask extends AsyncTask<Void, Integer, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (mCallbacks != null) {
                mCallbacks.onPreExecute();
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            // set data in UI thread
            if (mCallbacks != null) {
                setData(groupList);
                mCallbacks.onPostExecute();
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {

            // get data in background Thread
            try {
                fillingGroups();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private String loadJSONFromAsset() {
        String json = null;
        InputStream is = null;
        Log.d(TAG, "loadJSONFromAsset: loading");

        try {

            is = activity.getAssets().open("document.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {

            ex.printStackTrace();
            return null;

        } finally {

            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(TAG, "loadJSONFromAsset: Problem with input stream", e);
                }
            }
        }
        return json;
    }

    private void fillingGroups() throws JSONException {
        Log.d(TAG, "fillingGroups: filling");
        String json = loadJSONFromAsset();

        if (json == null)
            return;

        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("array");

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonObj = jsonArray.getJSONObject(i);

            Group group = new Group();
            group.setName(jsonObj.getString("name"));
            group.setId(jsonObj.getInt("id"));

            List<GroupItem> itemList = new ArrayList<>();

            JSONArray jsonArr = jsonObj.getJSONArray("items");
            for (int j = 0; j < jsonArr.length(); j++) {
                JSONObject jsonItemObject = jsonArr.getJSONObject(j);
                GroupItem groupItem = new GroupItem();
                groupItem.setName(jsonItemObject.getString("name"));
                groupItem.setId(jsonItemObject.getInt("id"));
                groupItem.setParentId(i);

                itemList.add(groupItem);
            }
            group.setItemList(itemList);
            groupList.add(group);
        }
        Log.d(TAG, "GroupList size is: " + groupList.size());
    }

    public void setData(List<Group> listData){
        this.dataList = listData;
    }

    public List<Group> getData(){
        return dataList;
    }
}
