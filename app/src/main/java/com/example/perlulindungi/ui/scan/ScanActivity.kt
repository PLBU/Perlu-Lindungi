package com.example.perlulindungi.ui.scan

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.perlulindungi.databinding.ActivityScanBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.ResultPoint
import com.google.zxing.client.android.BeepManager
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.journeyapps.barcodescanner.DefaultDecoderFactory
import java.util.*


//import me.dm7.barcodescanner.zxing.ZXingScannerView


//class ScanActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {
class ScanActivity : AppCompatActivity() {
    private lateinit var scanBinding: ActivityScanBinding
    private lateinit var barcodeView: DecoratedBarcodeView
    private lateinit var lastText: String
    private lateinit var beepManager: BeepManager

    private val callback: BarcodeCallback = object : BarcodeCallback {
        override fun barcodeResult(result: BarcodeResult) {
            Log.d("RESULT", result.text)
            if (result.text == null || result.text == lastText) {
                // Prevent duplicate scans
                return
            }
            lastText = result.text
            barcodeView.setStatusText(result.text)
            beepManager.playBeepSoundAndVibrate()

//            //Added preview of scanned barcode
//            val imageView = findViewById(R.id.barcodePreview)
//            imageView.setImageBitmap(result.getBitmapWithResultPoints(Color.YELLOW))
        }

        override fun possibleResultPoints(resultPoints: List<ResultPoint>) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scanBinding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(scanBinding.root)

        lastText = ""

        val intent: Intent = Intent("com.google.zxing.client.android.SCAN")
        intent.putExtra("PROMPT_MESSAGE", "")

        barcodeView = scanBinding.scanBarcodeView
        val formats: Collection<BarcodeFormat> =
        listOf(BarcodeFormat.QR_CODE, BarcodeFormat.CODE_39)
        barcodeView.barcodeView.decoderFactory = DefaultDecoderFactory(formats)
        barcodeView.initializeFromIntent(intent)
        barcodeView.decodeContinuous(callback)

        scanBinding.scanBackBtn.setOnClickListener { onBackPressed() }

        beepManager = BeepManager(this)
    }

    override fun onResume() {
        super.onResume()
        barcodeView.resume()
    }

    override fun onPause() {
        super.onPause()
        barcodeView.pause()
    }
}