package com.ahsailabs.beritakita_kotlin

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class AddNewsActivity : AppCompatActivity() {
    private var tilTitle: TextInputLayout? = null
    private var tietTitle: TextInputEditText? = null
    private var tilSummary: TextInputLayout? = null
    private var tietSummary: TextInputEditText? = null
    private var tilBody: TextInputLayout? = null
    private var tietBody: TextInputEditText? = null
    private var mbtnPhoto: MaterialButton? = null
    private var ivPhoto: ImageView? = null

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
            builder.setItems(options) { dialogInterface, which -> }
            builder.create().show()
        }
    }
}