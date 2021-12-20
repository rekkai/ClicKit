package com.example.clickit

import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat.startActivityForResult
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Button to open camera to take picture
        findViewById<Button>(R.id.button).setOnClickListener {
            dispatchTakePictureIntent()
        }
    }

    private val REQUEST_IMAGE_CAPTURE = 1

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }
    }

    //Get Thumbnail
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            val imageBitmap = data?.extras?.get("data") as Bitmap

            findViewById<ImageView>(R.id.imageButton).setImageBitmap(
                imageBitmap
            )

            val image = InputImage.fromBitmap(imageBitmap, 0)

            val labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)

            var outputText = ""
            labeler.process(image)
                .addOnSuccessListener { labels ->
                    // Task Completed successfully
                    for (label in labels) {
                        val text = label.text
                        val confidence = (label.confidence * 100).roundToInt().toString()
                        val index = label.index
                        val textView = findViewById<TextView>(R.id.textView)
                        outputText += "$text : $confidence %\n"
                        textView.text = outputText
                    }
                }
        }
    }
}