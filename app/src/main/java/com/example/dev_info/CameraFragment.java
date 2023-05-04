package com.example.dev_info;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;

public class CameraFragment extends Fragment {

    TextView cameraInfoText;

    public CameraFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_camera, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cameraInfoText = (TextView) view.findViewById(R.id.tv_camera_info_txt);
        cameraInfoText.setText("");

        CameraManager manager = (CameraManager) getContext().getSystemService(Context.CAMERA_SERVICE);
        String[] cameraIds = new String[0];
        try {
            cameraIds = manager.getCameraIdList();
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }
        if (cameraIds.length == 0) {
            return;
        }

        String cameraId = cameraIds[0];
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.CAMERA}, 121);

        }
        try {
            manager.openCamera(cameraId, new CameraDevice.StateCallback() {
                @Override
                public void onOpened(CameraDevice camera) {
                    try {
                        CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
                        float[] focalLengths = characteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS);
                        float aperture = characteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_APERTURES)[0];
                        float megapixels = ((characteristics.get(CameraCharacteristics.SENSOR_INFO_PHYSICAL_SIZE).getWidth() *
                                characteristics.get(CameraCharacteristics.SENSOR_INFO_PHYSICAL_SIZE).getHeight()));
//                        cameraInfoText.append("\n" + Arrays.toString(focalLengths));
                        cameraInfoText.append("Aperture: " + "f/" + aperture);
                        cameraInfoText.append("\n" + "Megapixels: " + megapixels);

                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onDisconnected(CameraDevice camera) {
                }

                @Override
                public void onError(CameraDevice camera, int error) {
                }
            }, null);
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }


    }
}
