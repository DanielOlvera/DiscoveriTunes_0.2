package com.example.daniel.discoveritunes_02

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.daniel.discoveritunes_02.view.SearchActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Thread.sleep(1000)

        val intent = Intent(applicationContext, SearchActivity::class.java)
        startActivity(intent)
        finish()
    }
}
