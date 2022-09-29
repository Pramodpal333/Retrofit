package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.retrofit.api.ApiInterface
import com.example.retrofit.api.ApiUtilities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private lateinit var tvData: TextView
    private lateinit var tvUrl: TextView
    private lateinit var tvLast: TextView

    private var storedName = ""
    private var storedUrl = ""
    private  var urlArray = ArrayList<String>()
    private var nameArray  = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvData = findViewById(R.id.tvData)
        tvUrl = findViewById(R.id.tvUrl)
        tvLast = findViewById(R.id.last)

        val usersApi = ApiUtilities.getSome().create(ApiInterface::class.java)

        GlobalScope.launch {
            val result = usersApi.getUsers()
            if (result.body() != null){

//                Log.d("PRAMOD","onCreate: ${result.body()}")
                withContext(Dispatchers.Main) {
                    result.body()!!.iterator().forEach {
                        Log.d("PRAMOD","onCreate: ${it.login}")
//                        tvData.text = it.login
                        var currentData = it.login
//                        storedName += currentData + " : \n"
//                        storedUrl +=  it.url + "  \n"
                        nameArray.add(currentData)
                        urlArray.add(it.url)
                    }
                    tvData.text = nameArray[0]
                    tvUrl.text = urlArray[0]
                }

            }
        }

        tvData.setOnClickListener{
          tvLast.text = "Textview Name Clicked"
        }

        tvUrl.setOnClickListener{
            tvLast.text = "Textview Url Clicked"
            Log.d("URL","$storedUrl")
        }

    }
}