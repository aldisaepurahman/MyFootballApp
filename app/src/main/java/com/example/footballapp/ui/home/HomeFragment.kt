package com.example.footballapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.footballapp.R
import com.example.footballapp.core.data.Result
import com.example.footballapp.core.domain.model.League
import com.example.footballapp.core.ui.TeamsAdapter
import com.example.footballapp.databinding.FragmentHomeBinding
import com.example.footballapp.ui.detail.DetailTeamActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        homeViewModel.leagues.observe(viewLifecycleOwner, Observer(::setSpinnerLeague))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val teamsAdapter = TeamsAdapter{ team ->
                val intent = Intent(activity, DetailTeamActivity::class.java)
                intent.putExtra(DetailTeamActivity.TEAM, team)
                startActivity(intent)
            }
            with(binding.rvTeams) {
                layoutManager = GridLayoutManager(requireContext(), 2)
                setHasFixedSize(true)
                adapter = teamsAdapter
            }

            homeViewModel.teams.observe(viewLifecycleOwner) { teams ->
                if (teams != null) {
                    when (teams) {
                        is Result.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.rvTeams.visibility = View.GONE
                        }
                        is Result.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.rvTeams.visibility = View.VISIBLE
                            teamsAdapter.setData(teams.data)
                        }
                        is Result.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.rvTeams.visibility = View.VISIBLE
                            binding.textNone.visibility = View.VISIBLE
                            binding.textNone.text = teams.message ?: getString(R.string.warning_teams)
                        }
                    }
                }
            }
        }
    }

    private fun setSpinnerLeague(league: List<League>?) {
        val leagueName = ArrayList<String>()
        for (i in league?.indices!!) {
            leagueName.add(league[i].name)
        }

        with(binding) {
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                leagueName
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerLeague.adapter = adapter
            }

            spinnerLeague.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    homeViewModel.switchLeague(leagueName[position])
                    Toast.makeText(requireContext(), "You choose + ${leagueName[position]}", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}