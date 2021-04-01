package mx.unam.talalek.paginalogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentTransaction
import mx.unam.talalek.paginalogin.databinding.ActivityMainBinding
import kotlin.time.measureTimedValue


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transaction = this.supportFragmentManager.beginTransaction()
        val fragment_login = LoginFragmen()
        transaction.replace(binding.mainActivity.id, fragment_login)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()

    }
}