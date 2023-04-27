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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SensorFragment extends Fragment {

    TextView gpstxt, gyrotxt, barotxt, accelerotxt, rotvectxt, proxtxt, ambitxt;
    public SensorFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sensor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gpstxt = view.findViewById(R.id.tv_gps);
        gyrotxt = view.findViewById(R.id.tv_gyro);
        barotxt = view.findViewById(R.id.tv_baro);
        accelerotxt = view.findViewById(R.id.tv_accelero);
        rotvectxt = view.findViewById(R.id.tv_rotvec);
        proxtxt = view.findViewById(R.id.tv_prox);
        ambitxt = view.findViewById(R.id.tv_ambi_light);

        SensorManager sm = (SensorManager) getContext().getSystemService(SENSOR_SERVICE);

        //GPS, Gyroscope, Baro, Accelero, Rotation Vector, Proximity, Ambient light

        Sensor gps = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        Sensor gyro = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        Sensor baro = sm.getDefaultSensor(Sensor.TYPE_PRESSURE);
        Sensor accel = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor rot_vec = sm.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        Sensor prox = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        Sensor ambi = sm.getDefaultSensor(Sensor.TYPE_LIGHT);


        SensorEventListener gyroListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                gyrotxt.setText("Gyrometer = " + event.values[0]);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        SensorEventListener baroListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                barotxt.setText("Barometer = " + event.values[0]);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        SensorEventListener acceleroListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                accelerotxt.setText("Accelerometer = " + event.values[0]);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        SensorEventListener rotvecListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                rotvectxt.setText("Rotation Vector = " + event.values[0]);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        SensorEventListener proxListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                proxtxt.setText("Proximity = " + event.values[0]);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        SensorEventListener ambiListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                ambitxt.setText("Ambient Light = " + event.values[0]);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        SensorEventListener gpsListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                gpstxt.setText("GPS = " + event.values[0]);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        sm.registerListener(gpsListener, gps, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(gyroListener, gyro, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(baroListener, baro, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(acceleroListener, accel, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(rotvecListener, rot_vec, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(proxListener, prox, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(ambiListener, ambi, SensorManager.SENSOR_DELAY_NORMAL);
    }
}