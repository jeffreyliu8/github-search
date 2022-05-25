package com.askjeffreyliu.githubsearch.adapter
/*
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.askjeffreyliu.githubsearch.databinding.ListItemBinding
import com.askjeffreyliu.githubsearch.model.QueryItem
import com.bumptech.glide.Glide


class ItemAdapter(private val listener: (QueryItem) -> Unit) :
    RecyclerView.Adapter<ItemAdapter.MyViewHolder>() {

    private var mList: List<QueryItem>? = null

    fun updateList(list: List<QueryItem>?) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) =
        holder.bind(mList!![position], listener)

    inner class MyViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: QueryItem, listener: (QueryItem) -> Unit) = with(itemView) {
            binding.nameTextView.text = item.fullName
            binding.descriptionTextView.text = item.description
            binding.starTextView.text = "★" + item.stargazersCount
            //Picasso.get().load(item.owner.avatarUrl).into(binding.avatarImageView)
            Glide.with(this).load(item.owner.avatarUrl).into(binding.avatarImageView)
            setOnClickListener {
                listener(item)
            }
        }
    }
}
*/