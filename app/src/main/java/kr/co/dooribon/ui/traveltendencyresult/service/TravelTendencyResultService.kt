package kr.co.dooribon.ui.traveltendencyresult.service

import android.app.Service
import android.content.ContentValues
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.IBinder
import android.provider.MediaStore
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.co.dooribon.ui.traveltendencyresult.broadcast.DownloadResultImageReceiver
import kr.co.dooribon.utils.shortToast
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

/**
 * Travel Tendency Result Image Download Service
 *
 * TODO : LifecycleService Test
 * TODO : Need To Test
 */
class TravelTendencyResultService : LifecycleService() {

    // BroadCastReceiver
    private lateinit var receiver : DownloadResultImageReceiver

    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)
        return null
    }

    override fun onCreate() {
        super.onCreate()
        receiver = DownloadResultImageReceiver()
        val filter = IntentFilter(Intent.ACTION_SEND)
        registerReceiver(receiver,filter)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        uploadImage(intent)

        return START_STICKY
    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }

    private fun uploadImage(intent : Intent?) {
        lifecycleScope.launch(Dispatchers.IO) {
            val fileName = "${System.currentTimeMillis()}.jpg" // 사진 파일 이름

            intent?.getByteArrayExtra("image")?.let { byteArray ->
                val bitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
                var outputStream: OutputStream? = null

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    // getting the contentResolver
                    this@TravelTendencyResultService.contentResolver.also { resolver ->

                        // Content resolver will process the contentValues
                        val contentValues = ContentValues().apply {

                            // putting the file information in content values
                            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                        }

                        // Inserting the contentValues to contentResolver and getting the Uri
                        val imageUri: Uri? =
                            resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

                        // Opening an outputStream with the Uri that we got
                        outputStream = imageUri?.let { resolver.openOutputStream(it) }
                    }
                } else {
                    // These for devices running on android < Q
                    // So I don't think an explanation in needed here
                    val imagesDir =
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                    val image = File(imagesDir, fileName)
                    outputStream = FileOutputStream(image)
                }

                outputStream?.use {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
                    val sendActionIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                    }
                    sendBroadcast(sendActionIntent)
                }
                stopSelf()
            }
        }
    }
}