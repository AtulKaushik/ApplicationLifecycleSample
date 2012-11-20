/**
 * 
 */
package com.kaushik.applicationlifecyclesample;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * @author Atul Kaushik (kaushik.atul@gmail.com)
 *
 */
public class ApplicationLifecycleService extends Service{

    String TAG = ApplicationLifecycleService.class.getSimpleName();
 // Binder given to clients
    public final IBinder mBinder = new AppLifecycleServiceBinder();
    
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
        public void onDestroy() {
        Log.i(TAG, "onDestroy");
        Toast.makeText(ApplicationLifecycleService.this, "App has been terminated!", Toast.LENGTH_SHORT).show();
            super.onDestroy();
        }
    
    @Override
    public void onCreate() {
        Toast.makeText(ApplicationLifecycleService.this, "App has been brought to front!", Toast.LENGTH_SHORT).show();
        super.onCreate();
    }
    
    public class AppLifecycleServiceBinder extends Binder{
        
        ApplicationLifecycleService getService() {
            // Return this instance of ApplicationLifecycleService so clients can call public methods
            return ApplicationLifecycleService.this;
        }
    }
    
    public void sayHello() {
        Toast.makeText(ApplicationLifecycleService.this, "Hello World!", Toast.LENGTH_SHORT).show();
    }
    
}
