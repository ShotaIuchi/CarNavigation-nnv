package com.example.nnv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nnvmd.TopFragment

class MainActivity : AppCompatActivity() {

    lateinit var top : TopFragment

    override fun onBackPressed() {
        if (!top.onBackPress()) {
            super.onBackPressed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        top = TopFragment.newInstance()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, top)
                .commitNow()
        }
    }
}