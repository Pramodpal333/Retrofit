package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class WebViewActivity : AppCompatActivity() {

    private lateinit var wvUrl : WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

            wvUrl = findViewById(R.id.wvUrl)

//        val urlLink = getStringExtra("")
        wvUrl.webViewClient = WebViewClient()
        wvUrl.loadUrl("www.google.com")
    }
}