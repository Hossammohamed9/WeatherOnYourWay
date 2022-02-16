package com.example.weatherontheway.ui.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.weatherontheway.R
import com.example.weatherontheway.databinding.ActivitySplashBinding
import com.example.weatherontheway.ui.mainactivity.MainActivity


class Splash : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    private lateinit var splashViewModel: SplashViewModel
    lateinit var topAnimation : Animation
    lateinit var botAnimation : Animation
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        splashViewModel= ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)
        )[SplashViewModel::class.java]

        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation)
        botAnimation = AnimationUtils.loadAnimation(this,R.anim.bot_animation)

        binding.logo.startAnimation(topAnimation)
        binding.nameId.startAnimation(botAnimation)




        splashViewModel.getSetting().observe(this, {
            splashViewModel.enableLocalization(this, it.lang)
        })
        @Suppress("DEPRECATION") val handler = Handler()

        handler.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 4000)
    }



}