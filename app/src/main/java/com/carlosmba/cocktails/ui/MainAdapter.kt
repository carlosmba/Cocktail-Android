package com.carlosmba.cocktails.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carlosmba.cocktails.R
import com.carlosmba.cocktails.base.BaseViewHolder
import com.carlosmba.cocktails.data.model.Cocktail
import com.carlosmba.cocktails.databinding.CocktailRowBinding

class MainAdapter(private val context : Context, private val listCocktail : List<Cocktail>, private val itemClickListener : OnCocktailClickListener) : RecyclerView.Adapter<BaseViewHolder<*>>(){

    interface OnCocktailClickListener{
        fun onCocktailClick(item : Cocktail, position : Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(LayoutInflater.from(context).inflate(R.layout.cocktail_row,parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        if(holder is MainViewHolder){
            holder.bind(listCocktail[position], position)
        }

    }

    override fun getItemCount(): Int = listCocktail.size


    inner class MainViewHolder(itemView : View) : BaseViewHolder<Cocktail>(itemView){
        private val binding = CocktailRowBinding.bind(itemView)
        override fun bind(item: Cocktail, position: Int) {
            Glide.with(context).load(item.image).centerCrop().into(binding.imgCocktail)
            binding.tvTitle.text = item.name
            binding.tvDescription.text = item.description
            itemView.setOnClickListener { itemClickListener.onCocktailClick(item, position) }
        }

    }

}