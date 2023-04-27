package com.example.dev_info;

import static android.content.Context.ACTIVITY_SERVICE;

import android.app.ActivityManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.os.StatFs;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DeviceFragment extends Fragment {

    public DeviceFragment() {
        // Required empty public constructor
    }

    TextView info;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_device, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Ram info
        ActivityManager am = (ActivityManager) getContext().getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(memoryInfo);
        long availRam = memoryInfo.availMem / (1024 * 1024);
        long totalRam = memoryInfo.totalMem / (1024 * 1024);

        //Storage info
        StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
        long totalDiskSpace = statFs.getBlockCountLong() * statFs.getBlockSizeLong() / (1024 * 1024);

        info = (TextView) view.findViewById(R.id.tv_info_txt);

        String manf = Build.MANUFACTURER;
        info.append("MANUFACTURER: " + manf + "\n");

        String model = Build.MODEL;
        info.append("MODEL: " + model + "\n");

        String hardware = Build.HARDWARE;
        info.append("HARDWARE: " + hardware + "\n");

        String version = Build.VERSION.RELEASE;
        info.append("ANDROID VERSION: " + version + "\n");

        info.append("AVAILABLE RAM: "+availRam+ " MB\n");
        info.append("TOTAL RAM: "+totalRam+" MB\n");

        info.append("TOTAL STORAGE SPACE: "+ totalDiskSpace + " MB\n");
    }

}