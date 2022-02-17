package com.example.infintestapp.presentation.reposearch

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.infintestapp.Consts
import com.example.infintestapp.R
import com.example.infintestapp.domain.models.Item
import com.example.infintestapp.helpers.LoadMore


class ReposListAdapter(var listener: OnAdapterItemClickListener?, val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list: MutableList<Any> = arrayListOf<Any>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is Item -> 1
            is LoadMore -> 2
            else -> throw IllegalStateException()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> RepositoriesViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_repos, parent, false)
            )
            2 -> LoaderViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.bottom_loader_recycler, parent, false)
            )
            else -> throw IllegalStateException()
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val repo = list[position]
        if (holder is RepositoriesViewHolder && repo is Item) {

            holder.repoName.text = repo.fullName
            holder.author.text = repo.owner?.login
            holder.language.text = repo.language

            Glide.with(context)
                .load(repo.owner?.avatarUrl)
                .into(holder.imgAuthor)

        } else if (holder is LoaderViewHolder && repo is LoadMore) {
            val pageNumber = (list.size / Consts.PER_PAGE)+1
            listener?.loadMore(pageNumber)
        }
    }


    inner class RepositoriesViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val repoName: TextView
        val author: TextView
        val language: TextView
        val imgAuthor: ImageView

        init {

            repoName = itemview.findViewById<TextView>(R.id.tvRepoName)
            author = itemview.findViewById<TextView>(R.id.tvAuthorName)
            language = itemview.findViewById<TextView>(R.id.tvLanguage)
            imgAuthor = itemview.findViewById<ImageView>(R.id.imgAuthor)

            itemView.setOnClickListener {
                val item = list[absoluteAdapterPosition] as Item
                listener?.onClick(item)
            }
        }
    }

    inner class LoaderViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
    }


    interface OnAdapterItemClickListener {
        fun onClick(repo: Item)
        fun loadMore(lastItemIndex: Int)
    }
}