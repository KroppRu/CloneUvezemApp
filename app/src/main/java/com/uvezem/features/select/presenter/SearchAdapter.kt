package com.uvezem.features.select.presenter

import android.support.design.widget.TextInputLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.uvezem.R

class SearchAdapter(val searchList: List<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(p0.context).inflate(android.R.layout.simple_list_item_1, p0, false)
        return SearchListViewHolder(itemView)
    }

    override fun getItemCount(): Int =
        searchList.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        viewHolder as SearchListViewHolder
        viewHolder.name?.text = searchList[position]
    }

    class SearchListViewHolder(val parent: View) : RecyclerView.ViewHolder(parent) {
        var name: TextView? = null

        init {
            val textInput: TextInputLayout = parent.findViewById(R.id.companyTIL)
            name = textInput.editText
        }
    }
}