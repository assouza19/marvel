package com.example.marvelsworld.characterList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelsworld.R
import com.example.marvelsworld.response.Result

class CharacterListAdapter : RecyclerView.Adapter<CharacterListItemViewHolder>() {

    var users = emptyList<Result>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                CharacterListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_user, parent, false)

        return CharacterListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterListItemViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size
}