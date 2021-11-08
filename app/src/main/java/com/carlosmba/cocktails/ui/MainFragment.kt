package com.carlosmba.cocktails.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.carlosmba.cocktails.AppDatabase
import com.carlosmba.cocktails.R
import com.carlosmba.cocktails.data.DataSource
import com.carlosmba.cocktails.data.model.Cocktail
import com.carlosmba.cocktails.databinding.FragmentMainBinding
import com.carlosmba.cocktails.domain.RepositoryImpl
import com.carlosmba.cocktails.ui.viewmodel.MainViewModel
import com.carlosmba.cocktails.ui.viewmodel.VMFactory
import com.carlosmba.cocktails.vo.Resource


class MainFragment : Fragment(), MainAdapter.OnCocktailClickListener {


    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel : MainViewModel by activityViewModels(){
        VMFactory(RepositoryImpl(DataSource(AppDatabase.getDatabase(requireActivity().applicationContext))))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        setupObserver()
        setupSearch()
        binding.btnFavorite.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_favoriteFragment)
        }


    }

    private fun setupSearch(){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setSearchText(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun setupObserver(){
        viewModel.fetchCocktailList.observe(viewLifecycleOwner){ result->
            when(result){
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Log.i("main", "${result.data}")
                    binding.recyclerView.adapter = MainAdapter(requireActivity(), result.data, this)
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireActivity(), "Ocurrio un error al obtener la lista: ${result.exception.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setupRecycler(){
        binding.recyclerView.also{
            it.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            it.addItemDecoration(DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCocktailClick(item: Cocktail, position: Int) {
        val bundle = Bundle().also{
            it.putParcelable("drink", item)
        }
        findNavController().navigate(R.id.action_mainFragment_to_cocktailDetailFragment, bundle)
    }

}