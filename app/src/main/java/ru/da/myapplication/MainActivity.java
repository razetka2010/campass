package ru.da.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SensorEventListener {
 private ImageView ivDinanik;
 private TextView tvDegree;
 private float current_degree = 0f;
    private SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init () {
        ivDinanik = findViewById(R.id.ivDinamic);
        tvDegree = findViewById(R.id.textView);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(
                this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME
        );
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float degree = Math.round(event.values[0]);
        tvDegree.setText("pog: " + Float.toString(degree));
        RotateAnimation ra = new RotateAnimation(current_degree,
                -degree,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
        );
        ra.setDuration(210);
        ra.setFillAfter(true);

        ivDinanik.startAnimation(ra);
        current_degree = -degree;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}