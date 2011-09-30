package com.capcay.sysinfo;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.widget.TextView;

public class SysInfoActivity extends Activity {
	TextView txtFreeInt, txtAvaInt, txtTotalInt, txtFreeExt, txtAvaExt, txtTotalExt;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        txtFreeInt = (TextView)findViewById(R.id.txtFreeInt);
        txtFreeInt.setText(""+freeInternalStorage()+" mb");
        txtAvaInt = (TextView)findViewById(R.id.txtAvaInt);
        txtAvaInt.setText(""+availableInternalStorage()+" mb");
        txtTotalInt = (TextView)findViewById(R.id.txtTotalInt);
        txtTotalInt.setText(""+totalInternalStorage()+" mb");
        txtFreeExt = (TextView)findViewById(R.id.txtFreeExt);
        txtFreeExt.setText(""+freeExternalStorage()+" mb");
        txtAvaExt = (TextView)findViewById(R.id.txtAvaExt);
        txtAvaExt.setText(""+availableExternalStorage()+" mb");
        txtTotalExt = (TextView)findViewById(R.id.txtTotalExt);
        txtTotalExt.setText(""+totalExternalStorage()+" mb");
        
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List rs = am.getRunningAppProcesses();
        for (int i=0; i>rs.size(); i++) {
        	ActivityManager.RunningServiceInfo rsi = (RunningServiceInfo) rs.get(i);
        	ActivityManager.MemoryInfo mi = (MemoryInfo) rs.get(i);
        }
    }
    
    private long availableInternalStorage() {
    	StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
    	long bytesAvailable = (long)stat.getAvailableBlocks()*(long)stat.getBlockSize();
    	return bytesAvailable/1048576;
    }
    
    private long freeInternalStorage() {
    	StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
    	long bytesAvailable = (long)stat.getFreeBlocks()*(long)stat.getBlockSize();
    	return bytesAvailable/1048576;
    }
    
    private long totalInternalStorage() {
    	StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
    	long bytesAvailable = (long)stat.getBlockCount()*(long)stat.getBlockSize();
    	return bytesAvailable/1048576;
    }
    
    private long totalExternalStorage() {
    	StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
    	long bytesAvailable = (long)stat.getBlockCount()*(long)stat.getBlockSize();
    	return bytesAvailable/1048576;
    }
    private long freeExternalStorage() {
    	StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
    	long bytesAvailable = (long)stat.getFreeBlocks()*(long)stat.getBlockSize();
    	return bytesAvailable/1048576;
    }
    private long availableExternalStorage() {
    	StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
    	long bytesAvailable = (long)stat.getAvailableBlocks()*(long)stat.getBlockSize();
    	return bytesAvailable/1048576;
    }
}