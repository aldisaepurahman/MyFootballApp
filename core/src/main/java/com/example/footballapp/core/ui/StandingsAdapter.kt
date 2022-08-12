package com.example.footballapp.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.footballapp.core.R
import com.example.footballapp.core.databinding.ItemLeagueStandingsBinding
import com.example.footballapp.core.domain.model.Standings

class StandingsAdapter(listStandings: List<Standings>) : RecyclerView.Adapter<StandingsAdapter.LeagueTableViewHolder>() {

    private val listTeamStandings = ArrayList<Standings>()

    init {
        listTeamStandings.addAll(listStandings)
    }

    fun setData(standings: List<Standings>?) {
        if (standings == null) return
        val diffResult = DiffUtil.calculateDiff(setupCallback(this.listTeamStandings, standings))

        listTeamStandings.clear()
        listTeamStandings.addAll(standings)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueTableViewHolder =
        LeagueTableViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_league_standings, parent, false)
        )

    override fun onBindViewHolder(holder: LeagueTableViewHolder, position: Int) {
        val data = listTeamStandings[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listTeamStandings.size

    inner class LeagueTableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemLeagueStandingsBinding.bind(itemView)

        fun bind(data: Standings) {
            with(binding) {
                tvRankItem.text = data.intRank

                Glide.with(itemView.context)
                    .load(data.strTeamBadge)
                    .into(ivLogoTableItem)

                tvTeamNameItem.text = data.strTeam
                tvPlayedTableItem.text = data.intPlayed ?: "0"
//                tvWinTableItem.text = data.intWin ?: "0"
//                tvDrawTableItem.text = data.intDraw ?: "0"
//                tvLoseTableItem.text = data.intLoss ?: "0"
                tvGoalDifferenceTableHeader.text = data.intGoalDifference ?: "0"
                tvPointsTableItem.text = data.intPoints ?: "0"
            }
        }
    }

    companion object {
        fun setupCallback(oldList: List<Standings>, newList: List<Standings>) = object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = oldList.size

            override fun getNewListSize(): Int = newList.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                oldList[oldItemPosition].strTeam == newList[newItemPosition].strTeam

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}