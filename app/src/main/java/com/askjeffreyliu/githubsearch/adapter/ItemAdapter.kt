package com.askjeffreyliu.githubsearch.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.askjeffreyliu.githubsearch.R
import com.askjeffreyliu.githubsearch.model.QueryItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*


class ItemAdapter(private val listener: (QueryItem) -> Unit) :
    RecyclerView.Adapter<ItemAdapter.MyViewHolder>() {

    private var mList: List<QueryItem>? = null

    fun updateList(list: List<QueryItem>?) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) =
        holder.bind(mList!![position], listener)

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: QueryItem, listener: (QueryItem) -> Unit) = with(itemView) {
            nameTextView.text = item.fullName
            descriptionTextView.text = item.description
            starTextView.text = "★" + item.stargazersCount
            Picasso.get().load(item.owner.avatarUrl).into(avatarImageView)

            setOnClickListener {
                listener(item)
            }
        }
    }
}