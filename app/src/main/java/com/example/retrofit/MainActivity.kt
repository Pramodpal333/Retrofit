package com.example.retrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.api.ApiInterface
import com.example.retrofit.api.ApiUtilities
import com.example.retrofit.recyclerview.Item
import com.example.retrofit.recyclerview.MyAdapter
import com.example.retrofit.recyclerview.urlClicked
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.create

class MainActivity : AppCompatActivity(), urlClicked {

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
        myAdapter = MyAdapter(rvUserArray,this)
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
                        urlArray.add(it.avatar_url)
                        Log.d("PROFILE","This $urlArray")
                        var rvList = Item("${it.login}","${it.avatar_url}")
                        rvUserArray.add(rvList)
                        recyclerView.adapter = myAdapter
                    }


                }

            }
        }




        myAdapter.onItemClickListener(object :MyAdapter.onItemClickedListener{
            override fun onItemClick(position: Int) {

                Toast.makeText(this@MainActivity,"$position clicked",Toast.LENGTH_LONG).show()
//                val intent = Intent(this@MainActivity, WebViewActivity::class.java)
               Log.d("SEE","$position" )
//                intent.putExtra("URL",)

            }

        })


    }

    override fun onUrlClicked(item: Item) {
        val new = item.url
        val i = Intent(this@MainActivity, WebViewActivity::class.java)
        i.putExtra("URL","$new")
        Log.d("YOO","$new")
        startActivity(i)

//     Toast.makeText(this,"Clicked on $item ", Toast.LENGTH_LONG).show()
//        Log.d("SEE","$item" )

    }
}