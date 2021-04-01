package mx.unam.talalek.clase3

import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.MediaController
import android.widget.VideoView
import mx.unam.talalek.clase3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val botonPlay = binding.button7
        val botonStop = binding.button8
        val botonPausa = binding.button2
        val botonSelecciona = binding.button4
        val botonSelecciona2 = binding.button5
        val botonSelecciona3 = binding.button6
        val botonUrl = binding.buttonUrl
        val ruta1 = binding.editText1

        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.videoView)



        botonPlay.setOnClickListener {
            binding.videoView.start()
        }

        botonPausa.setOnClickListener {
            binding.videoView.pause()
        }

        botonStop.setOnClickListener {
            binding.videoView.stopPlayback()
        }

        botonSelecciona.setOnClickListener {
            val contenido: Uri = Uri.parse("android.resource://$packageName/${R.raw.fvsk}")
            binding.videoView.setVideoURI(contenido)
            binding.videoView.requestFocus()
        }

        botonSelecciona2.setOnClickListener {
            val contenido: Uri = Uri.parse("android.resource://$packageName/${R.raw.fvsk2}")
            binding.videoView.setVideoURI(contenido)
            binding.videoView.requestFocus()
        }

        botonSelecciona3.setOnClickListener {
            val contenido: Uri = Uri.parse("android.resource://$packageName/${R.raw.fvsk3}")
            binding.videoView.setVideoURI(contenido)
            binding.videoView.requestFocus()


        }

        botonUrl.setOnClickListener {

           val direccion = ruta1.text.toString()
            val contenido: Uri = Uri.parse("${direccion}")
            val vista = findViewById<VideoView>(R.id.videoView)
            vista.setMediaController(mediaController)
            vista.setVideoURI(contenido)
            vista.requestFocus()
            vista.start()
            binding.editText1.text.clear()
            hideKeyboard()
        }

    }

    private fun hideKeyboard() {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }
}