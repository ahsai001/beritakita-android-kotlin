package com.ahsailabs.beritakita_kotlin.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ahsailabs.beritakita_kotlin.R
import com.ahsailabs.beritakita_kotlin.ui.home.models.News
import com.squareup.picasso.Picasso

/**
 * Created by ahmad s.
 */

class NewsAdapter(private val modelList: List<News>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val rootView: View = LayoutInflater.from(parent.context).inflate(
            R.layout.news_itemview,
            parent,
            false
        )
        return NewsViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val (summary, photo, createdAt, _, title, createdBy) = modelList[position]
        holder.tvTitle.text = title
        holder.tvSummary.text = summary
        holder.tvDate.text = createdAt
        holder.tvUser.text = createdBy

        Picasso.get().load(photo).into(holder.ivPhoto)
        setViewClickable(holder, holder.itemView)
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


    //give this class new power : clickable itemview
    interface OnChildViewClickListener<News> {
        fun onClick(view: View?, dataModel: News, position: Int)
        fun onLongClick(view: View?, dataModel: News, position: Int)
    }

    private var onChildViewClickListener: OnChildViewClickListener<News>? = null

    fun setOnChildViewClickListener(onChildViewClickListener: OnChildViewClickListener<News>?) {
        this.onChildViewClickListener = onChildViewClickListener
    }


    fun setViewClickable(viewHolder: NewsViewHolder, view: View) {
        view.setOnClickListener { view ->
            if (onChildViewClickListener != null) {
                val position = viewHolder.adapterPosition
                onChildViewClickListener!!.onClick(view, modelList[position], position)
            }
        }
    }

    fun setViewLongClickable(viewHolder: NewsViewHolder, view: View) {
        view.setOnLongClickListener(OnLongClickListener { view ->
            if (onChildViewClickListener != null) {
                val position = viewHolder.adapterPosition
                onChildViewClickListener!!.onLongClick(view, modelList[position], position)
                return@OnLongClickListener true
            }
            false
        })
    }
}