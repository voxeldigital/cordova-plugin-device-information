package br.com.voxeldigital.ionic.deviceinformation;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;


import android.os.Build;
import java.lang.reflect.Method;
import java.lang.Class;

import android.util.Log;

public class AndroidDeviceInformation extends CordovaPlugin {

private static final String TAG = "AndroidDeviceInformation";

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
       if ("getDeviceInfo".equals(action)) {
            JSONObject r = new JSONObject();            
            r.put("serial", this.getSerialNumber());
            callbackContext.success(r);
        }
        else {
            return false;
        }
        return true;
    }

     public String getSerialNumber() {
        
        String serialNumber;

        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);
            serialNumber = (String) get.invoke(c, "gsm.sn1");

            if (serialNumber.equals(""))
                serialNumber = (String) get.invoke(c, "ril.serialnumber");

            if (serialNumber.equals(""))
                serialNumber = (String) get.invoke(c, "ro.serialno");

            if (serialNumber.equals(""))
                serialNumber = (String) get.invoke(c, "sys.serialnumber");

            if (serialNumber.equals(""))
                serialNumber = Build.SERIAL;
            
            if (serialNumber.equals(""))
                serialNumber = null;
        } catch (Exception e) {
            e.printStackTrace();
            serialNumber = null;
        }
        
        Log.d(TAG, "SERIAL: " + serialNumber);
        return serialNumber;
        
    }
    
}
