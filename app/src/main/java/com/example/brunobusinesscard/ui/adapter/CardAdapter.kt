package com.example.brunobusinesscard.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.brunobusinesscard.R
import com.example.brunobusinesscard.data.Card
import com.example.brunobusinesscard.databinding.ItemCardsBinding

class CardAdapter : ListAdapter<Card, CardAdapter.ViewHolder>(DiffCallBack()) {

    var listenerShare: (View) -> Unit = {}
    var listenerEdit: (Card) -> Unit = {}
    var listenerDelete: (Card) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCardsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemCardsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Card) {
            binding.tvName.text = item.nome
            binding.tvEmail.text = item.email
            binding.tvPhone.text = item.telefone
            binding.tvCompany.text = item.empresa
            binding.cdView.setOnClickListener {
                listenerShare(it)
            }
            binding.IVMore.setOnClickListener { showPopup(item) }
        }

        private fun showPopup(item: Card) {
            val ivMore = binding.IVMore
            val popUpMenu = PopupMenu(ivMore.context, ivMore)
            popUpMenu.menuInflater.inflate(R.menu.popup_menu, popUpMenu.menu)
            popUpMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_edit -> listenerEdit(item)
                    R.id.action_delete -> listenerDelete(item)
                }
                return@setOnMenuItemClickListener true
            }
            popUpMenu.show()
        }
    }
}

class DiffCallBack : DiffUtil.ItemCallback<Card>() {
    override fun areItemsTheSame(oldItem: Card, newItem: Card) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Card, newItem: Card) = oldItem.id == newItem.id
}