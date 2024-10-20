package id.adiandrea.predictivebackexample

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import id.adiandrea.predictivebackexample.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val onBackPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            //your custom onBackPressed logic
            //showConfirmationDialog()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //enable this to provide custom back navigation
        //onBackPressedDispatcher.addCallback(onBackPressedCallback)

        //bind data
        binding.name.text = intent.getStringExtra("name")
        Glide.with(this)
            .load(intent.getStringExtra("image"))
            .into(binding.image)
    }

    /**
     * call this on [onBackPressedCallback] to show confirmation dialog when navigate back
     * */
    private fun showConfirmationDialog() {
        val confirmDialog = AlertDialog.Builder(this)
        confirmDialog.setMessage("Are you sure want to go back?")
        confirmDialog.setCancelable(false)
        confirmDialog.setNegativeButton("Cancel") { d, _ ->
            d.dismiss()
        }
        confirmDialog.setPositiveButton("Confirm") { _, _ ->
            //stop intercepting the back gesture
            onBackPressedCallback.isEnabled = false

            //navigate back
            onBackPressedDispatcher.onBackPressed()
        }
        confirmDialog.show()
    }

}