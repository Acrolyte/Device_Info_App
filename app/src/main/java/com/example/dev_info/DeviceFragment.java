package com.example.dev_info;

import static android.content.Context.ACTIVITY_SERVICE;
import static android.content.Context.BATTERY_SERVICE;
import static android.content.Context.HARDWARE_PROPERTIES_SERVICE;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.os.HardwarePropertiesManager;
import android.os.StatFs;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.InputStream;


public class DeviceFragment extends Fragment {

    public DeviceFragment() {
        // Required empty public constructor
    }

    TextView info;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_device, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ActivityManager am = (ActivityManager) getContext().getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(memoryInfo);
        long availRam = memoryInfo.availMem / (1024 * 1024);
        long totalRam = memoryInfo.totalMem / (1024 * 1024);

        StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
        long totalDiskSpace = statFs.getBlockCountLong() * statFs.getBlockSizeLong() / (1024 * 1024);

        BatteryManager bm = (BatteryManager) getContext().getSystemService(BATTERY_SERVICE);
        int batLevel = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

        info = (TextView) view.findViewById(R.id.tv_info_txt);
        info.setText("");

        String manf = Build.MANUFACTURER;
        info.append("MANUFACTURER: " + manf + "\n\n");

        String model = Build.MODEL;
        info.append("MODEL: " + model + "\n\n");

        String version = Build.VERSION.RELEASE;
        info.append("ANDROID VERSION: " + version + "\n\n");

        info.append("AVAILABLE RAM: " + availRam + " MB\n\n");
        info.append("TOTAL RAM: " + totalRam + " MB\n\n");

        info.append("TOTAL STORAGE SPACE: " + totalDiskSpace + " MB\n\n");


        info.append("Battery: " + batLevel + " %\n\n");

        String output = "", processor = "", hardware = "";
        try {

            String[] DATA = {"/system/bin/cat", "/proc/cpuinfo"};
            ProcessBuilder processBuilder = new ProcessBuilder(DATA);
            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            byte[] byteArry = new byte[1024];
            output = "";
            while (inputStream.read(byteArry) != -1) {
                output = output + new String(byteArry);
            }
            inputStream.close();

            Log.d("CPU_INFO", output);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        String[] outputArr = output.split("\n");

        for (String i : outputArr) {
            if (i.contains("Processor"))
                processor = i;
            if (i.contains("Hardware"))
                hardware = i;
        }

        info.append(processor + "\n\n" + hardware + "\n\n");

        String gpu = null;
        ConfigurationInfo configurationInfo = am.getDeviceConfigurationInfo();
        gpu = configurationInfo.getGlEsVersion();

        info.append("GPU version: " + gpu);
    }

}