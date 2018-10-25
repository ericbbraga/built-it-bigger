package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndPointsAsyncTask extends AsyncTask<Void, Void, String> {
    private static final String TAG = "EndPointsAsyncTask";
    private static MyApi myApiService = null;
    private Context mContext;
    private CountingIdlingResource mIdlingResource;
    private EndPointsCallback mCallback;

    public interface EndPointsCallback {
        void onResultLoaded(String result);
    }


    EndPointsAsyncTask(Context context, EndPointsCallback callback) {
        mContext = context;
        mCallback = callback;
        mIdlingResource = new CountingIdlingResource("Sync");
    }


    @Override
    protected String doInBackground(Void... params) {

        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null)
                // options for running against local devappserver
                // - 10.0.2.2 is localhost's IP address in Android emulator
                // - turn off compression when running against local devappserver
                .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            mIdlingResource.increment();

            return myApiService.tellMeSomeJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Log.i(TAG, "onPostExecute: " + result);
        mCallback.onResultLoaded(result);
        mIdlingResource.decrement();
    }
}
