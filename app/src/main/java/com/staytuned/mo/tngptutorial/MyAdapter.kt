package com.staytuned.mo.tngptutorial

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.staytuned.mo.tngptutorial.networking.RedditNewsResponse
import kotlinx.android.synthetic.main.reddit_list_item.view.*

class MyAdapter(val list: ArrayList<RedditNewsResponse.RedditChildrenResponse>,val activity: FragmentActivity?) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.reddit_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.author.text = list.get(position).data.author
        Glide.with(activity!!).load(list.get(position).data.thumbnail).into(holder.image)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val author = view.tv_name
        val image = view.iv_avatar

    }

}
