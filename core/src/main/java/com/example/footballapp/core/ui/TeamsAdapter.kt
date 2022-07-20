package com.example.footballapp.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.footballapp.core.R
import com.example.footballapp.core.databinding.TeamCardLayoutBinding
import com.example.footballapp.core.domain.model.Team

class TeamsAdapter(private val onClick: (Team) -> Unit, listTeam: List<Team>) : RecyclerView.Adapter<TeamsAdapter.GridViewHolder>() {
    private var listTeam = ArrayList<Team>()

    init {
        this.listTeam.addAll(listTeam)
    }

    fun setData(newList: List<Team>?) {
        if (newList == null) return
        val diffResult = DiffUtil.calculateDiff(setupCallback(this.listTeam, newList))

        listTeam.clear()
        listTeam.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder =
        GridViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.team_card_layout, parent, false))

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val data = listTeam[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listTeam.size

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = TeamCardLayoutBinding.bind(itemView)

        fun bind(data: Team) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.logo)
                    .into(teamLogo)
                teamName.text = data.name

                root.setOnClickListener {
                    onClick(data)
                }
            }
        }
    }

    companion object {
        fun setupCallback(oldList: List<Team>, newList: List<Team>) = object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = oldList.size

            override fun getNewListSize(): Int = newList.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                oldList[oldItemPosition].teamId == newList[newItemPosition].teamId

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}
