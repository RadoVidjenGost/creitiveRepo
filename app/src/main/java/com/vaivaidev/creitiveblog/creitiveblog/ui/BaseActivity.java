package com.vaivaidev.creitiveblog.creitiveblog.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.vaivaidev.creitiveblog.creitiveblog.utils.NetworkConnectionCheck;

/**
 * Created by Iva on 3/9/2018.
 */

public abstract class BaseActivity extends AppCompatActivity {


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
    protected void onResume() {
        super.onResume();
        registerReceiver(networkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        determineConnectionStatus();
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            unregisterReceiver(networkReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    protected abstract void onNetworkChange(boolean connected);

    private void determineConnectionStatus() {
        boolean connected = NetworkConnectionCheck.determineConnection(this);
        onNetworkChange(connected);
    }




}
