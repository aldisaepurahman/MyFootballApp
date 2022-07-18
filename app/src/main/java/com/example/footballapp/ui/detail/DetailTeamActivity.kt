package com.example.footballapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.footballapp.R
import com.example.footballapp.core.domain.model.Team
import com.example.footballapp.databinding.ActivityDetailTeamBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailTeamActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityDetailTeamBinding.inflate(layoutInflater)
    }
    private val detailTeamViewModel: DetailTeamViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        val team = intent.getParcelableExtra<Team>(TEAM)
        showDetailTeam(team)
    }

    private fun showDetailTeam(team: Team?) {
        team?.let {
            Glide.with(this@DetailTeamActivity)
                .load(team.logo)
                .into(binding.ivLogoDetail)

            binding.tvTeamNameDetail.text = team.name
            binding.tvLeagueName.text = team.leagueName ?: getString(R.string.no_league_name)
            binding.tvStadiumTeam.text = team.stadium ?: getString(R.string.no_stadium_name)
            binding.tvDescriptionTeam.text = team.description ?: getString(R.string.no_description)
            binding.tvFormedYear.text = team.formedYear ?: getString(R.string.no_formed_year)

            var favorite = team.isFavorite
            setButtonFavorite(favorite)
            binding.fabFavorite.setOnClickListener {
                favorite = !favorite
                detailTeamViewModel.setFavoriteTeam(team, favorite)
                setButtonFavorite(favorite)
            }
        }
    }

    private fun setButtonFavorite(favorite: Boolean) {
        binding.fabFavorite.setImageDrawable(
            ContextCompat.getDrawable(
                this, if (favorite) {
                    R.drawable.ic_favorite
                } else {
                    R.drawable.ic_not_favorite
                }
            )
        )
    }

    companion object {
        const val TEAM = "team"
    }
}