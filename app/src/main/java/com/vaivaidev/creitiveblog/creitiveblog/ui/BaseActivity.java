package com.vaivaidev.creitiveblog.creitiveblog.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.vaivaidev.creitiveblog.R;
import com.vaivaidev.creitiveblog.creitiveblog.utils.NetworkConnectionCheck;

/**
 * Created by Iva on 3/9/2018.
 */

public class BaseActivity extends AppCompatActivity {


    private BroadcastReceiver networkReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            determineConnectionStatus();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    private void determineConnectionStatus() {
        boolean connected = NetworkConnectionCheck.determineConnection(this);
        onNetworkChange(connected);
    }

    public void onNetworkChange(boolean connected) {
        ViewGroup viewGroup = findViewById(R.id.no_connection_bar);
        if (viewGroup != null) {
            if (connected) {
                viewGroup.setVisibility(View.GONE);
            } else {
                viewGroup.setVisibility(View.VISIBLE);
            }
        }
    }
}
