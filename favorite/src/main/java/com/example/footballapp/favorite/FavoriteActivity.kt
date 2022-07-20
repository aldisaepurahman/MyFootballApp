package com.example.footballapp.favorite

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.footballapp.core.domain.model.Team
import com.example.footballapp.core.ui.TeamsAdapter
import com.example.footballapp.favorite.di.favoriteModule
import com.example.footballapp.favorite.databinding.ActivityFavoriteBinding
import com.example.footballapp.ui.detail.DetailTeamActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFavoriteBinding

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        supportActionBar?.title = "Favorite"
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, com.example.footballapp.R.color.teal_700)))

        val teamsAdapter = TeamsAdapter({ team ->
            val intent = Intent(this, DetailTeamActivity::class.java)
            intent.putExtra(DetailTeamActivity.TEAM, team)
            startActivity(intent)
        }, listOf(Team(teamId = "-1")))
        with(binding.rvTeamsFavorite) {
            layoutManager = GridLayoutManager(applicationContext, 2)
            setHasFixedSize(true)
            adapter = teamsAdapter
        }

        favoriteViewModel.favorites.observe(this) { teams ->
            teamsAdapter.setData(teams)
            binding.textFavoriteNone.visibility = if (teams.isNotEmpty()) View.GONE else View.VISIBLE
        }
    }
}