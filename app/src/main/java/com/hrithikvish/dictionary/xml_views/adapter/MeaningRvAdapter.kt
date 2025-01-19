package com.hrithikvish.dictionary.xml_views.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hrithikvish.dictionary.databinding.WordMeaningRvItemBinding
import com.hrithikvish.dictionary.domain.model.Meaning

class MeaningRvAdapter : RecyclerView.Adapter<MeaningRvAdapter.MeaningViewHolder>() {

    private var meanings = mutableListOf<Meaning>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitWordItemAndNotify(meaningList: List<Meaning>) {
        meanings.clear()
        meanings.addAll(meaningList)
        notifyDataSetChanged()
    }

    inner class MeaningViewHolder(val binding: WordMeaningRvItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningViewHolder {
        val binding = WordMeaningRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MeaningViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MeaningViewHolder, position: Int) {
        val meaning = meanings[position]
        with(holder.binding) {
            this.meaning = RvItemMeaning("${position+1}.", meaning)
        }
    }

    override fun getItemCount(): Int = meanings.size
}

data class RvItemMeaning(
    val index: String,
    val meaning: Meaning
)