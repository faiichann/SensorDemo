package com.example.sensordemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

//	public final String TAG = "MainActivity";
//	SensorManager sensorManager;
//	Sensor sensor;
//	List<Sensor> deviceSensor;
//	Sensor magneticSensor;
//	EditText editTextTextMultiLine;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
//
//		sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
//		deviceSensor = sensorManager.getSensorList(Sensor.TYPE_ALL);
//		editTextTextMultiLine = findViewById(R.id.editTextTextMultiLine);
//		if(sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)!= null) {
//			Log.d(TAG, "Exist Magnetic sensor");
//			magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
//		} else {
//			Log.d(TAG, "No Magnetic sonsor");
//		}
//	}
//
//	SensorEventListener sensorEventListener = new SensorEventListener() {
//		@Override
//		public void onSensorChanged(SensorEvent sensorEvent) {
//
//		}
//
//		@Override
//		public void onAccuracyChanged(Sensor sensor, int i) {
//
//		}
//	};
SensorManager sm = null;
	TextView textView1 = null,textView3;
	List list;
	List<Sensor> deviceSensor;
	SensorManager sensorManager;
	EditText editTextTextMultiLine;

	SensorEventListener sel = new SensorEventListener(){
		public void onAccuracyChanged(Sensor sensor, int accuracy) {}
		public void onSensorChanged(SensorEvent event) {
			float[] values = event.values;
			textView1.setText("x: "+values[0]+"\ny: "+values[1]+"\nz: "+values[2]);
			String sensorName = " ";
			for (int i=0; i<deviceSensor.size();i++){
				sensorName += deviceSensor.get(i).getName()+"\n";
			}
			editTextTextMultiLine.setText(sensorName);
			}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//textView3 = (TextView)findViewById(R.id.textView3);
		editTextTextMultiLine = (EditText) findViewById(R.id.editTextTextMultiLine);
		sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		deviceSensor = sensorManager.getSensorList(Sensor.TYPE_ALL);


		/* Get a SensorManager instance */
		sm = (SensorManager)getSystemService(SENSOR_SERVICE);

		textView1 = (TextView)findViewById(R.id.textView2);

		list = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
		if(list.size()>0){
			sm.registerListener(sel, (Sensor) list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
		}else{
			Toast.makeText(getBaseContext(), "Error: No Accelerometer.", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	protected void onStop() {
		if(list.size()>0){
			sm.unregisterListener(sel);
		}
		super.onStop();
	}
}

