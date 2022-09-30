package com.example.retrofit.recyclerview

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.R
import com.example.retrofit.WebViewActivity

class MyAdapter(private var list: List<Item>, private val listener : urlClicked) : RecyclerView.Adapter<MyAdapter.ItemViewHolder>() {

    private lateinit var mListener : onItemClickedListener

    interface onItemClickedListener{
            fun onItemClick(position: Int)
    }

    fun onItemClickListener (listener: onItemClickedListener){
        mListener = listener
    }


    inner class ItemViewHolder(itemView: View, listener: onItemClickedListener) : RecyclerView.ViewHolder(itemView){
        val myName : TextView = itemView.findViewById(R.id.tvName)
        val myUrl : TextView = itemView.findViewById(R.id.tvUrl)

        init {
            myUrl.setOnClickListener{

                listener.onItemClick(adapterPosition)
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.each_recyclerview,parent,false)



        val newViewHolder = ItemViewHolder(view,mListener)
        view.setOnClickListener{
        listener.onUrlClicked(list[newViewHolder.adapterPosition])
            Log.d("URL","what ${newViewHolder.adapterPosition}")
        }
        return newViewHolder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = list[position]
        holder.myName.text = item.name
        holder.myUrl.text = item.url



        holder.myUrl.setOnClickListener{
         val urlLink =  item.url
            Log.d("WHAT","This IS $urlLink")

        }
    }

    override fun getItemCount(): Int {
       return list.size
    }
}


interface urlClicked{
    fun onUrlClicked(item : Item)
}