package com.example.android.alphap.shakefunction;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.alphap.R;

/**
 * Created by asiagibson on 2/28/17.
 */

public class ShakeActivity extends AppCompatActivity {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetect mShakeDetector;

    TextView tv;
    ImageView potatoDude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        potatoDude = (ImageView) findViewById(R.id.game_potato);


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetect();
        mShakeDetector.setOnShakeListener(new ShakeDetect.OnShakeListener() {

            @Override
            public void onShake(int count) {


                handleShakeEvent(count);
            }
        });

    }

    private void handleShakeEvent(int count) {
        switch (count) {
            case 1:
                potatoDude.setVisibility(View.INVISIBLE);
                Toast.makeText(tv.getContext(), "1 SHAKE", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                potatoDude.setVisibility(View.VISIBLE);
                Toast.makeText(tv.getContext(), "2ND SHAKE", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                potatoDude.setVisibility(View.INVISIBLE);
                Toast.makeText(tv.getContext(), "3RD SHAAKE", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                potatoDude.setVisibility(View.VISIBLE);
                Toast.makeText(potatoDude.getContext(), "YOUR TATER POPPED", Toast.LENGTH_SHORT).show();
                break;
            default:
                potatoDude.setVisibility(View.VISIBLE);
                break;

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }
}
