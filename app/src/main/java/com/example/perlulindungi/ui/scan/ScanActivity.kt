package com.example.perlulindungi.ui.scan

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.perlulindungi.R
import com.example.perlulindungi.data.qrcode.CheckInRepo
import com.example.perlulindungi.data.qrcode.QrCodeModel
import com.example.perlulindungi.databinding.ActivityScanBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.ResultPoint
import com.google.zxing.client.android.BeepManager
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.journeyapps.barcodescanner.DefaultDecoderFactory

class ScanActivity : AppCompatActivity(), LocationListener, SensorEventListener  {
    private lateinit var scanBinding: ActivityScanBinding
    private lateinit var barcodeView: DecoratedBarcodeView
    private lateinit var lastText: String
    private lateinit var beepManager: BeepManager
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    private lateinit var scanViewModel: ScanViewModel

    private lateinit var sensorManager: SensorManager
    private var temperature: Sensor? = null
    private var isScanning = false;
    private var isSuccess = false;

    private val callback: BarcodeCallback = object : BarcodeCallback {
        override fun barcodeResult(result: BarcodeResult) {
            Log.d("RESULT", result.text)
            if (result.text == null || (result.text == lastText && isSuccess) || isScanning) {
                if (result.text == null) lastText = ""
                // Prevent duplicate scans
                return
            }
            lastText = result.text
            beepManager.playBeepSoundAndVibrate()

            checkIn()
        }

        override fun possibleResultPoints(resultPoints: List<ResultPoint>) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scanBinding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(scanBinding.root)

        lastText = ""

        val intent = Intent("com.google.zxing.client.android.SCAN")
        intent.putExtra("PROMPT_MESSAGE", "")

        barcodeView = scanBinding.scanBarcodeView
        val formats: Collection<BarcodeFormat> =
        listOf(BarcodeFormat.QR_CODE, BarcodeFormat.CODE_39)
        barcodeView.barcodeView.decoderFactory = DefaultDecoderFactory(formats)
        barcodeView.initializeFromIntent(intent)
        barcodeView.decodeContinuous(callback)

        scanBinding.scanBackBtn.setOnClickListener { onBackPressed() }

        beepManager = BeepManager(this)

        getLocation()

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        temperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
        if (temperature == null) {
            Log.d("TEMPERATURE SENSOR", "NOT AVAILABLE")

            scanBinding.scanTemp.text = "N/A"
        }
    }

    override fun onResume() {
        super.onResume()
        barcodeView.resume()

        sensorManager.registerListener(this, temperature, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        barcodeView.pause()

        sensorManager.unregisterListener(this)
    }

    private fun getLocation() {
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSION_REQUEST_ACCESS_FINE_LOCATION)
            return
        }
        locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_ACCESS_FINE_LOCATION) {
            when (grantResults[0]) {
                PackageManager.PERMISSION_GRANTED -> getLocation()
                PackageManager.PERMISSION_DENIED -> Log.e("LOCATION PERMISSION", "Not Granted")
            }
        }
    }

    companion object {
        private const val PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 100
    }

    override fun onLocationChanged(location: Location) {
        latitude = location.latitude
        longitude = location.longitude
    }

    private fun setCheckInStatus(color: Int, text: String, icon: Int, reason: String?) {
        scanBinding.scanResultImg.setImageResource(icon)
        scanBinding.scanResultImgContainer.backgroundTintList = ContextCompat.getColorStateList(applicationContext, color)
        scanBinding.scanSuccess.text = text
        scanBinding.scanReason.text = reason
    }

    fun checkIn() {
        isScanning = true;
        val data = QrCodeModel(lastText, latitude, longitude)

        val repo = CheckInRepo(data)

        scanViewModel = ScanViewModel(repo)
        scanViewModel.checkIn()
        scanViewModel.checkInResult.observe(this, androidx.lifecycle.Observer { response ->
            isScanning = false;
            isSuccess = false;
            if (response == null) return@Observer
            isSuccess = true;

            scanBinding.scanResultContainer.visibility = View.VISIBLE

            Log.d("USER STATUS", response.getData().getUserStatus())
            when (response.getData().getUserStatus()) {
                "green" -> setCheckInStatus(R.color.green, "Berhasil", R.drawable.ic_checkmark, null)
                "yellow" -> setCheckInStatus(R.color.green, "Berhasil", R.drawable.ic_checkmark,null)
                "red" -> setCheckInStatus(R.color.red, "Gagal", R.drawable.ic_cross, response.getData().getReason())
                "black" -> setCheckInStatus(R.color.red, "Gagal", R.drawable.ic_cross, response.getData().getReason())
            }

        })
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent) {
        val temperature = event.values[0]

        Log.d("TEMPERATURE", temperature.toString())

        scanBinding.scanTemp.text = "$temperature°C"
    }
}