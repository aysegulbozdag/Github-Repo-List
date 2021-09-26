package com.example.simpleexample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simpleexample.model.RecyclerData
import com.example.simpleexample.R
import com.example.simpleexample.databinding.RecyclerListRowBinding

class Adapter(var context: Context) : RecyclerView.Adapter<Adapter.ItemHolder>() {

    var items = ArrayList<RecyclerData>()

    fun setUpdatedData(items: ArrayList<RecyclerData>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerListRowBinding>(
            inflater,
            R.layout.recycler_list_row, parent, false
        )
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.view.tvTitle.text = items[position].name
        holder.view.tvDesc.text = items[position].description
        val url = items[position].owner.avatar_url;

        Glide.with(context)
            .load(url)
            .into(holder.view.imageThumb)
        // holder.view.listener = this
    }

    override fun getItemCount(): Int {
        return items.size;
    }

    class ItemHolder(var view: RecyclerListRowBinding) : RecyclerView.ViewHolder(view.root) {

    }
}