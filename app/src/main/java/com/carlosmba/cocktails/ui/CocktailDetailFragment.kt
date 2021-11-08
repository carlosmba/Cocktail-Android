package com.carlosmba.cocktails.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.carlosmba.cocktails.AppDatabase
import com.carlosmba.cocktails.data.DataSource
import com.carlosmba.cocktails.data.model.Cocktail
import com.carlosmba.cocktails.databinding.FragmentCocktailDetailBinding
import com.carlosmba.cocktails.domain.RepositoryImpl
import com.carlosmba.cocktails.ui.viewmodel.MainViewModel
import com.carlosmba.cocktails.ui.viewmodel.VMFactory


class CocktailDetailFragment : Fragment() {
    private var _binding : FragmentCocktailDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel : MainViewModel by activityViewModels(){
        VMFactory(RepositoryImpl(DataSource(AppDatabase.getDatabase(requireActivity().applicationContext))))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCocktailDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val drink = requireArguments().getParcelable<Cocktail>("drink")!!

        Glide.with(requireActivity()).load(drink.image).centerCrop().into(binding.imgCocktailDetail)
        binding.textView.text = drink.name
        binding.tvDescription.text = drink.description
        if(drink.alcoholic == "Non_Alcoholic"){
            binding.tvAlcoholic.text = "Bebida sin Alcohol"
        }else{
            binding.tvAlcoholic.text = "Bebida con Alcohol"
        }

        binding.checkBoxFavorite.setOnCheckedChangeListener { _, b ->
            if(b){
                viewModel.saveCocktailFavorite(drink)
            }

        }
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}