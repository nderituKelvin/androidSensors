package com.bloggy.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity(), SensorEventListener  {


    lateinit var sensorManager : SensorManager
    lateinit var xVal : TextView; lateinit var yVal : TextView ;lateinit var zVal : TextView
    lateinit var xGyroVal : TextView; lateinit var yGyroVal : TextView ;lateinit var zGyroVal : TextView
    lateinit var xMagnoVal : TextView; lateinit var yMagnoVal : TextView ;lateinit var zMagnoVal : TextView
    lateinit var lightVal : TextView; lateinit var tempVal : TextView ;lateinit var humidVal : TextView; lateinit var pressureVal : TextView


    lateinit var accelerometer : Sensor ;lateinit var mGyro : Sensor ;lateinit var mMagno : Sensor;
    lateinit var mLight : Sensor

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(p0: SensorEvent?) {
        var sensor : Sensor = p0!!.sensor
        if(sensor.type == Sensor.TYPE_GYROSCOPE){
            xGyroVal.text = "X: "+p0!!.values[0].toString()
            yGyroVal.text = "Y: "+p0.values[1].toString()
            zGyroVal.text = "Z: "+p0.values[2].toString()
        }else if(sensor.type == Sensor.TYPE_ACCELEROMETER){
            xVal.text = "X: "+p0!!.values[0].toString()
            yVal.text = "Y: "+p0.values[1].toString()
            zVal.text = "Z: "+p0.values[2].toString()
        }else if(sensor.type == Sensor.TYPE_MAGNETIC_FIELD){
            xMagnoVal.text = "X: "+p0!!.values[0].toString()
            yMagnoVal.text = "Y: "+p0.values[1].toString()
            zMagnoVal.text = "Z: "+p0.values[2].toString()
        }else if(sensor.type == Sensor.TYPE_LIGHT){
            lightVal.text = "X: "+p0!!.values[0].toString()
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        xVal = findViewById(R.id.xVal) as TextView
        yVal = findViewById(R.id.yVal) as TextView
        zVal = findViewById(R.id.zVal) as TextView

        xGyroVal = findViewById(R.id.xGyro) as TextView
        yGyroVal = findViewById(R.id.yGyro) as TextView
        zGyroVal = findViewById(R.id.zGyro) as TextView

        xMagnoVal = findViewById(R.id.xMagno) as TextView
        yMagnoVal = findViewById(R.id.yMagno) as TextView
        zMagnoVal = findViewById(R.id.zMagno) as TextView

        lightVal = findViewById(R.id.light) as TextView

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        mGyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        mMagno = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        if(accelerometer != null){
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        }else{
            xVal.text = "Accelerometer"
            yVal.text = "Not"
            zVal.text = "Supported"
        }

        if(mGyro != null){
            sensorManager.registerListener(this, mGyro, SensorManager.SENSOR_DELAY_NORMAL)
        }else{
            xGyroVal.text = "GyroScope"
            yGyroVal.text = "Not"
            zGyroVal.text = "Supported"
        }

        if(mMagno != null){
            sensorManager.registerListener(this, mMagno, SensorManager.SENSOR_DELAY_NORMAL)
        }else{
            xMagnoVal.text = "Magno"
            yMagnoVal.text = "Not"
            zMagnoVal.text = "Supported"
        }

        if(mLight != null){
            sensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL)
        }else{
            lightVal.text = "Light Not Supported"
        }
    }
}
