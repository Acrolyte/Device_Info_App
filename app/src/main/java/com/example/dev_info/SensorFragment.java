package com.example.dev_info;

import static android.content.Context.SENSOR_SERVICE;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dev_info.databinding.FragmentSensorBinding;

import java.text.DecimalFormat;

public class SensorFragment extends Fragment {

    private FragmentSensorBinding binding;

    public SensorFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSensorBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DecimalFormat df = new DecimalFormat("#.000000");

        SensorManager sm = (SensorManager) getContext().getSystemService(SENSOR_SERVICE);

        Sensor gps = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        Sensor gyro = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        Sensor baro = sm.getDefaultSensor(Sensor.TYPE_PRESSURE);
        Sensor accel = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor rot_vec = sm.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        Sensor prox = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        Sensor ambi = sm.getDefaultSensor(Sensor.TYPE_LIGHT);


        if (gyro != null) {
            SensorEventListener gyroListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    binding.tvGyrotext.setText("Gyrometer");
                    binding.tvGyroVal1.setText("X = " + df.format(event.values[0]));
                    binding.tvGyroVal2.setText("Y = " + df.format(event.values[1]));
                    binding.tvGyroVal3.setText("Z = " + df.format(event.values[2]));
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            };

            sm.registerListener(gyroListener, gyro, SensorManager.SENSOR_DELAY_NORMAL);
        } else binding.llGyro.setVisibility(View.GONE);

        if (gps != null) {
            SensorEventListener gpsListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    binding.tvGpstext.setText("GPS");
                    binding.tvGpsVal1.setText("X = " + df.format(event.values[0]));
                    binding.tvGpsVal2.setText("Y = " + df.format(event.values[1]));
                    binding.tvGpsVal3.setText("Z = " + df.format(event.values[2]));
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            };

            sm.registerListener(gpsListener, gps, SensorManager.SENSOR_DELAY_NORMAL);
        } else binding.llGps.setVisibility(View.GONE);

        if (baro!= null) {
            SensorEventListener baroListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    binding.tvBarotxt.setText("Barometer");
                    binding.tvBaroVal1.setText("X = " + df.format(event.values[0]));
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            };

            sm.registerListener(baroListener, baro, SensorManager.SENSOR_DELAY_NORMAL);
        } else binding.llBaro.setVisibility(View.GONE);

        if (accel != null) {
            SensorEventListener accelListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    binding.tvAcceltxt.setText("Accelerometer");
                    binding.tvAccelVal1.setText("X = " + df.format(event.values[0]));
                    binding.tvAccelVal2.setText("Y = " + df.format(event.values[1]));
                    binding.tvAccelVal3.setText("Z = " + df.format(event.values[2]));
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            };

            sm.registerListener(accelListener, accel, SensorManager.SENSOR_DELAY_NORMAL);
        } else binding.llAccel.setVisibility(View.GONE);


        if (prox != null) {
            SensorEventListener proxListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    binding.tvProx.setText("Proximity");
                    binding.tvProxVal1.setText("X = " + df.format(event.values[0]));
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            };

            sm.registerListener(proxListener, prox, SensorManager.SENSOR_DELAY_NORMAL);
        } else binding.llProx.setVisibility(View.GONE);

        if (rot_vec != null) {
            SensorEventListener rotvecListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    binding.tvRotVec.setText("Rotation Vector");
                    binding.tvRotVecVal1.setText("X = " + df.format(event.values[0]));
                    binding.tvRotVecVal2.setText("Y = " + df.format(event.values[1]));
                    binding.tvRotVecVal3.setText("Z = " + df.format(event.values[2]));
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            };

            sm.registerListener(rotvecListener, rot_vec, SensorManager.SENSOR_DELAY_NORMAL);
        } else binding.llRotVec.setVisibility(View.GONE);

        if (ambi != null) {
            SensorEventListener ambiListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    binding.tvLight.setText("Ambience Light");
                    binding.tvLightVal1.setText("X = " + df.format(event.values[0]));
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            };

            sm.registerListener(ambiListener, ambi, SensorManager.SENSOR_DELAY_NORMAL);
        } else binding.llLight.setVisibility(View.GONE);


    }
}