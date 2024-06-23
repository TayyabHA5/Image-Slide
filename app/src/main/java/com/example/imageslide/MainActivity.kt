package com.example.imageslide

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.imageslide.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    lateinit var handler : Handler
    private lateinit var runnable: Runnable
    private val slideInterval = 3000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val img : IntArray = intArrayOf(R.drawable.i1,R.drawable.i2,R.drawable.i3,R.drawable.i4)
        val viewPagerAdapter = ViewPagerAdapter(this,img)

        binding.viewPager.adapter = viewPagerAdapter
        handler = Handler(Looper.getMainLooper())
        runnable = Runnable {
            val nextItem = (binding.viewPager.currentItem + 1) % img.size
            binding.viewPager.currentItem = nextItem
        }

        // Start the automatic slide
        handler.postDelayed(runnable, slideInterval)
    }

    override fun onPause() {
        super.onPause()
        // Stop the automatic slide when the activity is not visible
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        // Resume the automatic slide when the activity is visible again
        handler.postDelayed(runnable, slideInterval)
    }
}


