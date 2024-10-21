package com.example.nogizaka5th.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.AdapterListUpdateCallback

import androidx.recyclerview.widget.RecyclerView
import com.example.nogizaka5th.DetailActivity
import com.example.nogizaka5th.R
import com.example.nogizaka5th.model.Member

class ListMemberAdapter (private val listMember: ArrayList<Member>) : RecyclerView.Adapter<ListMemberAdapter.ListViewHolder>()     {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvCity: TextView = itemView.findViewById(R.id.tv_item_city)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
       val adapter = LayoutInflater .from(parent.context).inflate(R.layout.item_nogi, parent, false)
        return ListViewHolder(adapter)
    }

    override fun getItemCount(): Int =listMember.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val member = listMember[position]
        holder.imgPhoto.setImageResource(member.photo)
        holder.tvName.text = member.name
        holder.tvCity.text = member.getFormattedBirthplace()


        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listMember[holder.adapterPosition]) }

    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Member)
    }

}