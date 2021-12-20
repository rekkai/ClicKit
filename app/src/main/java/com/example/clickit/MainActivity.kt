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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Button to open camera to take picture
        findViewById<Button>(R.id.button).setOnClickListener {
            dispatchTakePictureIntent()
        }
    }
}

private val REQUEST_IMAGE_CAPTURE = 1

private fun dispatchTakePictureIntent() {
    val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    try {
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
    } catch (e: ActivityNotFoundException) {
        // display error state to the user

        //Get Thumbnail
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

        val imageBitmap = data?.extras?.get("data") as Bitmap

        findViewById<ImageView>(R.id.imageView).setImageBitmap(imageBitmap

        val image = InputImage.fromBitmap(imageBitmap, rotationDegrees: 0)
    }
}
var outputText = ''
labeler.process(image)
    .addOnSuccessListener { labels ->
        // Task Completed successfully
        Log.i("Dom", "successfully processed image"")
                for (label in labels) {
                    val text = label.text
                    val confidence = (label.confidence*100).roundToInt().toString()
                    val index = label.index
                    val textView = findViewByID<Textview>(R.id.textView)
                    outputText += "$text : $confidence %\n"
                    textView.text = outputText
                    Log.i("Dom", "LOOP" > [$TEXT]:$confidence"")
                }
    }

}