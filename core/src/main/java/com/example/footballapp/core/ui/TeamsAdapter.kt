package com.example.footballapp.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.footballapp.core.R
import com.example.footballapp.core.databinding.TeamCardLayoutBinding
import com.example.footballapp.core.domain.model.Team

class TeamsAdapter(private val onClick: (Team) -> Unit) : RecyclerView.Adapter<TeamsAdapter.GridViewHolder>() {
    private var listTeam = ArrayList<Team>()

    fun setData(newList: List<Team>?) {
        if (newList == null) return
        listTeam.clear()
        listTeam.addAll(newList)
        notifyDataSetChanged()
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
}
