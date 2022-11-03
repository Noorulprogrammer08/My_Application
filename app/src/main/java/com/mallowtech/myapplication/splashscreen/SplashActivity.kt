package com.mallowtech.myapplication.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.mallowtech.myapplication.MainActivity
import com.mallowtech.myapplication.R
import com.mallowtech.myapplication.authendication.login.LoginActivity

@Suppress("DEPRECATION")
class SplashActivity() : AppCompatActivity(){
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        val splashscreen = findViewById<ImageView>(R.id.iv_logo)
        splashscreen.alpha = 0f
        splashscreen.animate().setDuration(1000).alpha(1f).withEndAction {
            val i = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}
   