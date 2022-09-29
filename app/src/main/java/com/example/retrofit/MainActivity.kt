package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.api.ApiInterface
import com.example.retrofit.api.ApiUtilities
import com.example.retrofit.recyclerview.Item
import com.example.retrofit.recyclerview.MyAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.create

class MainActivity : AppCompatActivity() {

        private lateinit var recyclerView: RecyclerView
        private lateinit var myAdapter: MyAdapter

    private var storedName = ""
    private var storedUrl = ""
    private  var urlArray = ArrayList<String>()
    private var nameArray  = ArrayList<String>()
    private var  rvUserArray = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // RecyclerView
        recyclerView = findViewById(R.id.rvList)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        // RecyclerView

        // Adapter
        rvUserArray = ArrayList() // list
        myAdapter = MyAdapter(rvUserArray)
        // Adapter

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
                        var rvList = Item("${it.login}","${it.url}")
                        rvUserArray.add(rvList)
                        recyclerView.adapter = myAdapter
                    }

                }

            }
        }




    }
}