package kr.ac.tukorea.whereareu.presentation.nok.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.ac.tukorea.whereareu.databinding.ItemMeaningfulListBinding
import kr.ac.tukorea.whereareu.domain.home.MeaningfulPlace

class MeaningfulListRVA: ListAdapter<MeaningfulPlace, MeaningfulListRVA.MeaningfulListViewHolder>
    (object : DiffUtil.ItemCallback<MeaningfulPlace>(){
    override fun areItemsTheSame(oldItem: MeaningfulPlace, newItem: MeaningfulPlace): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MeaningfulPlace, newItem: MeaningfulPlace): Boolean {
        return oldItem.address == newItem.address
    }

}) {

    inner class MeaningfulListViewHolder(private val binding: ItemMeaningfulListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(meaningfulPlace: MeaningfulPlace){
            binding.addressTv.text = meaningfulPlace.address
            binding.placeNameTv.text = meaningfulPlace.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningfulListViewHolder {
        return MeaningfulListViewHolder(
            ItemMeaningfulListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MeaningfulListViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}