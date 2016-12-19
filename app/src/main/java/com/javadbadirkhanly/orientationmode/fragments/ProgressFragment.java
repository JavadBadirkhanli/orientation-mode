package com.javadbadirkhanly.orientationmode.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by javadbadirkhanly on 12/19/16.
 */

public class ProgressFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);


    }

    class ProgressAsync extends AsyncTask<Void, Integer, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException ie){
                    ie.printStackTrace();
                }
            }
            return null;
        }
    }
}
