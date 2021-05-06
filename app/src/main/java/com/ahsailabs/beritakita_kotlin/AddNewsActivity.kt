package com.ahsailabs.beritakita_kotlin

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.ahsailabs.beritakita_kotlin.utils.PermissionUtil
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.squareup.picasso.Picasso
import pl.aprilapps.easyphotopicker.ChooserType
import pl.aprilapps.easyphotopicker.EasyImage
import pl.aprilapps.easyphotopicker.MediaFile
import pl.aprilapps.easyphotopicker.MediaSource


class AddNewsActivity : AppCompatActivity() {
    private var tilTitle: TextInputLayout? = null
    private var tietTitle: TextInputEditText? = null
    private var tilSummary: TextInputLayout? = null
    private var tietSummary: TextInputEditText? = null
    private var tilBody: TextInputLayout? = null
    private var tietBody: TextInputEditText? = null
    private var mbtnPhoto: MaterialButton? = null
    private var ivPhoto: ImageView? = null

    private var easyImage: EasyImage? = null
    private var permissionUtil: PermissionUtil? = null
    private var mediaFile: MediaFile? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_news)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<ExtendedFloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        title = "Form Tambah Berita"
        //or
        //supportActionBar?.title = "Form Tambah Berita"

        loadViews()
        setupListeners()
        setupEasyImage()
    }


    private fun loadViews() {
        tilTitle = findViewById(R.id.tilTitle)
        tietTitle = findViewById(R.id.tietTitle)
        tilSummary = findViewById(R.id.tilSummary)
        tietSummary = findViewById(R.id.tietSummary)
        tilBody = findViewById(R.id.tilBody)
        tietBody = findViewById(R.id.tietBody)
        mbtnPhoto = findViewById(R.id.mbtnPhoto)
        ivPhoto = findViewById(R.id.ivPhoto)
    }


    private fun setupListeners() {
        mbtnPhoto!!.setOnClickListener {
            val builder = MaterialAlertDialogBuilder(this@AddNewsActivity)
            builder.setTitle("How do you get the image?")
            val options = arrayOf("Camera", "Gallery")
            builder.setItems(options) { dialogInterface, which ->
                if (options[which] == "Camera") {
                    permissionUtil = PermissionUtil.checkPermissionAndGo(
                        this@AddNewsActivity,
                        1003,
                        { easyImage!!.openCameraForImage(this@AddNewsActivity) },
                        Manifest.permission.CAMERA
                    )
                } else {
                    easyImage!!.openDocuments(this@AddNewsActivity)
                }
            }
            builder.create().show()
        }
    }

    private fun setupEasyImage() {
        easyImage = EasyImage.Builder(this)
            .setChooserType(ChooserType.CAMERA_AND_DOCUMENTS)
            .build()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (easyImage != null) {
            easyImage!!.handleActivityResult(requestCode, resultCode, data, this,
                object : EasyImage.Callbacks {
                    override fun onImagePickerError(throwable: Throwable, mediaSource: MediaSource) {}

                    override fun onMediaFilesPicked(mediaFiles: Array<MediaFile>, mediaSource: MediaSource) {
                        mediaFile = mediaFiles[0]
                        Picasso.get().load(mediaFile!!.file).into(ivPhoto)
                    }

                    override fun onCanceled(mediaSource: MediaSource) {}
                })
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (permissionUtil != null) {
            permissionUtil!!.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }


    companion object {
        fun start(context: Context) {
            val addNewsIntent = Intent(context, AddNewsActivity::class.java)
            context.startActivity(addNewsIntent)
        }
    }

}