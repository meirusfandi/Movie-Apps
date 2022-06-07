package id.my.fanslab.movieapps.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.my.fanslab.movieapps.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private var _binding: ActivityProfileBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}