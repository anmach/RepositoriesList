package com.example.allegrorepositorieslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.allegrorepositorieslist.model.RepoBaseModel

class ReposAdapter(private val repositories : List<RepoBaseModel>,
                   private val onItemClicked: (RepoBaseModel) -> Unit)
    : RecyclerView.Adapter<ReposAdapter.ViewHolder>()  {

    class ViewHolder(rowView: View, onItemClicked: (Int) -> Unit) : RecyclerView.ViewHolder(rowView) {
        var nameTextView: TextView = rowView.findViewById(R.id.itemTitle)
        var descriptionTextView: TextView = rowView.findViewById(R.id.itemDescription)
        init {
            itemView.setOnClickListener{
                onItemClicked(adapterPosition)
            }
        }

        fun bindData(repo: RepoBaseModel){
            nameTextView.setText(repo.name)
            descriptionTextView.setText(repo.description)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val contactView = inflater.inflate(R.layout.recycler_view_row, parent, false)
        return ViewHolder(contactView) {
            onItemClicked(repositories[it])
        }
    }

    override fun onBindViewHolder(viewHolder: ReposAdapter.ViewHolder, position: Int) {
        viewHolder.bindData(repositories[position])
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

}