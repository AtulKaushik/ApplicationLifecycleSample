/**
 * 
 */
package com.kaushik.applicationlifecyclesample;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.kaushik.applicationlifecyclesample.ApplicationLifecycleService.AppLifecycleServiceBinder;

/**
 * @author Atul Kaushik (kaushik.atul@gmail.com)
 *
 */
public class LastActivity extends Activity{

    private boolean mBound;
    private ApplicationLifecycleService mService;
    private String TAG = LastActivity.class.getSimpleName();
  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.last_activity);
        
        findViewById(R.id.button3).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
               // Toast.makeText(LastActivity.this, "Finishing Activity App", Toast.LENGTH_SHORT).show();
                mService.sayHello();
            }
        });
    }

    
    @Override
    protected void onStart() {
        Log.i(TAG, "onStart; mBound ="+mBound);
        Intent intent = new Intent(LastActivity.this, ApplicationLifecycleService.class);
        bindService(intent , mServiceConnection, Context.BIND_AUTO_CREATE);
        super.onStart();
    }
    
    @Override
    protected void onStop() {
        Log.i(TAG, "onStop; mBound ="+mBound);
        if(mBound)
            unbindService(mServiceConnection);
        super.onStop();
    }
    
    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected");
            mBound = false;
            Toast.makeText(LastActivity.this, "Service UNBOUND to "+TAG, Toast.LENGTH_SHORT).show();
        }
        
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "onServiceConnected");
            AppLifecycleServiceBinder serviceBinder = (AppLifecycleServiceBinder) service;
            mService = serviceBinder.getService();
           mBound = true;
            Toast.makeText(LastActivity.this, "Service BOUND to "+TAG, Toast.LENGTH_SHORT).show();
        }
    };
}
