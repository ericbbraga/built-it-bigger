package com.ericbraga.displayjokemodule;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DisplayJoke extends AppCompatActivity {

    private String JOKE_EXTRA_KEY = "com.ericbraga.display.JOKE_EXTRA";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_joke_layout);


        Intent it = getIntent();
        Bundle bundle = it.getExtras();

        TextView textView = findViewById(R.id.joke_message);

        if (bundle != null) {
            if (bundle.containsKey(JOKE_EXTRA_KEY)) {
                String joke = bundle.getString(JOKE_EXTRA_KEY);
                textView.setText(joke);
            }
        } else {
            textView.setText(R.string.no_jokes);
        }
    }
}
