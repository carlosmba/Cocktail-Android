package com.carlosmba.cocktails.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.carlosmba.cocktails.AppDatabase
import com.carlosmba.cocktails.R
import com.carlosmba.cocktails.data.DataSource
import com.carlosmba.cocktails.databinding.FragmentFavoriteBinding
import com.carlosmba.cocktails.databinding.FragmentMainBinding
import com.carlosmba.cocktails.domain.RepositoryImpl
import com.carlosmba.cocktails.ui.viewmodel.MainViewModel
import com.carlosmba.cocktails.ui.viewmodel.VMFactory
import com.carlosmba.cocktails.vo.Resource


class FavoriteFragment : Fragment() {
    private var _binding : FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel : MainViewModel by activityViewModels(){
        VMFactory(RepositoryImpl(DataSource(AppDatabase.getDatabase(requireActivity().applicationContext))))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllCocktailFavorite.observe(viewLifecycleOwner){ result ->
            when(result){
                is Resource.Loading -> {}
                is Resource.Success -> {
                    Log.i("favorite", "${result.data}")
                }
                is Resource.Failure -> {
                    Toast.makeText(requireActivity(), "Ocurrio un error al obtener los datos: ${result.exception.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


}