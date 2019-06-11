package com.askjeffreyliu.githubsearch.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.askjeffreyliu.githubsearch.R
import com.askjeffreyliu.githubsearch.model.QueryItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*


class ItemAdapter :
    RecyclerView.Adapter<ViewHolder>() {

    private var mList: List<QueryItem>? = null

    fun updateList(list: List<QueryItem>?) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTextView.text = mList!![position].fullName
        holder.descriptionTextView.text = mList!![position].description
        holder.starTextView.text = "★" + mList!![position].stargazersCount
        Picasso.get().load(mList!![position].owner.avatarUrl).into(holder.avatarImageView)
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val nameTextView: TextView = view.nameTextView
    val descriptionTextView: TextView = view.descriptionTextView
    val starTextView: TextView = view.starTextView
    val avatarImageView: ImageView = view.avatarImageView
}