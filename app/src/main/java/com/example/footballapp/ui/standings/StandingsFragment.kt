package com.example.footballapp.ui.standings

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballapp.core.data.Result
import com.example.footballapp.core.domain.model.League
import com.example.footballapp.core.ui.StandingsAdapter
import com.example.footballapp.databinding.FragmentStandingsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class StandingsFragment : Fragment() {

    private var _binding: FragmentStandingsBinding? = null

    private val binding get() = _binding!!

    private val standingsViewModel: StandingsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStandingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            standingsViewModel.leagues.observe(viewLifecycleOwner, Observer(::setSpinnerLeague))

            setLeagueStandings()
        }
    }

    private fun setLeagueStandings() {
        val standingsAdapter = StandingsAdapter()
        with(binding.rvLeagueTable) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = standingsAdapter
        }

        standingsViewModel.standings.observe(viewLifecycleOwner) { standings ->
            if (standings != null) {
                when (standings) {
                    is Result.Loading -> {
                        binding.rvLeagueTable.visibility = View.GONE
                        binding.progressBarLeagueTable.visibility = View.VISIBLE
                        binding.textStandingsNone.visibility = View.GONE
                    }
                    is Result.Success -> {
                        binding.rvLeagueTable.visibility = View.VISIBLE
                        binding.progressBarLeagueTable.visibility = View.GONE
                        binding.textStandingsNone.visibility = View.GONE
                        standingsAdapter.setData(standings.data)
                    }
                    is Result.Error -> {
                        binding.rvLeagueTable.visibility = View.GONE
                        binding.progressBarLeagueTable.visibility = View.GONE
                        binding.textStandingsNone.apply {
                            visibility = View.VISIBLE
                            text = standings.message
                                ?: getString(com.example.footballapp.R.string.no_standings)
                        }
                    }
                }
            }
        }

    }

    private fun setSpinnerLeague(league: List<League>?) {
        val leagueName = ArrayList<String>()
        val leagueId = ArrayList<String>()
        league?.forEach {
            leagueName.add(it.name)
            leagueId.add(it.leagueId)
        }

        with(binding) {
            ArrayAdapter(
                requireContext(),
                R.layout.simple_spinner_item,
                leagueName
            ).also { adapter ->
                adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
                spinnerLeague.adapter = adapter
            }

            spinnerLeague.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    standingsViewModel.switchLeague(leagueId[position])
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