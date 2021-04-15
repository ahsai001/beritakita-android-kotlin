package com.ahsailabs.beritakita_kotlin.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ahsailabs.beritakita_kotlin.R
import com.ahsailabs.beritakita_kotlin.ui.home.models.News

/**
 * Created by ahmad s.
 */

class NewsAdapter(private val modelList: List<News>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val rootView: View = LayoutInflater.from(parent.context).inflate(R.layout.news_itemview, parent, false)
        return NewsViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val (summary, _, createdAt, _, title, createdBy) = modelList[position]
        holder.tvTitle.text = title
        holder.tvSummary.text = summary
        holder.tvDate.text = createdAt
        holder.tvUser.text = createdBy
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        internal val tvSummary: TextView = itemView.findViewById(R.id.tvSummary)
        internal val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        internal val tvUser: TextView = itemView.findViewById(R.id.tvUser)
        internal val ivPhoto: ImageView = itemView.findViewById(R.id.ivPhoto)
        internal val llTextPanel: LinearLayout = itemView.findViewById(R.id.llTextPanel)
    }
}