package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements EndPointsAsyncTask.EndPointsCallback {
    private View mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view = findViewById(R.id.tell_joke_widget);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickJoke();
            }
        });
        mProgress = findViewById(R.id.loading);

    }

    private void onClickJoke() {
        showProgress();

        EndPointsAsyncTask endPointsAsyncTask = new EndPointsAsyncTask(this, this);
        endPointsAsyncTask.execute();

        hideProgress();
    }

    private void showProgress() {
        mProgress.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        mProgress.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResultLoaded(String result) {
        Intent it = new Intent();
        if (getResources().getBoolean(R.bool.show_google_ad)) {
            it.setAction("com.udacity.gradle.builditbigger.DISPLAY_AD");

        } else {
            it.setAction("com.ericbraga.display.JOKE_DISPLAY");
        }

        it.putExtra("com.ericbraga.display.JOKE_EXTRA", result);

        startActivity(it);
    }
}
