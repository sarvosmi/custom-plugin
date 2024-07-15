package com.techbinomial.plugins.mycustomplugin;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;
import com.getcapacitor.PermissionState;
import android.Manifest;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import androidx.activity.result.ActivityResult;
import android.content.Intent;
import android.net.Uri;

import java.util.Calendar;
import android.provider.CalendarContract;

@CapacitorPlugin(name = "MyCustomPlugin",
    permissions = {
        @Permission(
            strings = { Manifest.permission.CAMERA }, 
            alias = MyCustomPluginPlugin.CAMERA),
        @Permission(
            strings = { Manifest.permission.READ_EXTERNAL_STORAGE, 
            Manifest.permission.WRITE_EXTERNAL_STORAGE },
            alias = MyCustomPluginPlugin.PHOTOS
        )
    }
)
public class MyCustomPluginPlugin extends Plugin {

    private MyCustomPlugin implementation = new MyCustomPlugin();
    static final String CAMERA = "camera";
    static final String PHOTOS = "photos";


    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }
    @PluginMethod()
    public void testPluginMethod(PluginCall call) {
        String value = call.getString("msg");
        JSObject ret = new JSObject();
        ret.put("value",value);
        call.resolve(ret);
    }

    @PluginMethod()
    public void pluginPermissionMethod(PluginCall call) {
        if (getPermissionState("camera") != PermissionState.GRANTED) {
            requestPermissionForAlias("camera", call, "pluginPermissionsCallback");
        } 
        else {
            //loadCamera(call);           
            JSObject ret = new JSObject();
            ret.put("value","Has the permission");
            call.resolve(ret);
        }
    }
    @PermissionCallback
    private void pluginPermissionsCallback(PluginCall call) {

        if (getPermissionState("camera") == PermissionState.GRANTED) {
            //loadCamera(call);             
            JSObject ret = new JSObject();
            ret.put("value","Got the permission");
            call.resolve(ret);

        } else {
            call.reject("Permission is required to take a picture");
        }
    }

    @PluginMethod()
    public void openWebsite(PluginCall call) {

        String siteURL = call.getString("siteURL");
        Intent intentObj = new Intent(Intent.ACTION_VIEW);
        intentObj.setData(Uri.parse(siteURL));
        getContext().startActivity(intentObj);
    }

       @PluginMethod()
    public void calenderTest(PluginCall call) {
        
        Intent calendarIntent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2023, 9, 29, 7, 40);
        Calendar endTime = Calendar.getInstance();
        endTime.set(2023, 9, 29, 9, 50);
        calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
        calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
        calendarIntent.putExtra(CalendarContract.Events.TITLE, "my calender event");
        calendarIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "random");

        getContext().startActivity(calendarIntent);
    }
}
