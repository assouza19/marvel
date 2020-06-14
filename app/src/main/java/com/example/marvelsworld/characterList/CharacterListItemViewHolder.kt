package com.example.marvelsworld.characterList

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelsworld.R
import com.example.marvelsworld.response.Result
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_user.view.*

class CharacterListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(result: Result) {
        itemView.name.text = result.name
        itemView.name.setOnClickListener {
            Toast.makeText(
                itemView.context,
                "Você clicou em ".plus(itemView.name.text),
                Toast.LENGTH_LONG
            ).show()
        }
        itemView.picture.setOnClickListener {
            Toast.makeText(
                itemView.context,
                "Você clicou em ".plus(itemView.name.text),
                Toast.LENGTH_LONG
            ).show()
        }
        Picasso.get()
            .load(result.thumbnail.path.plus(".").plus(result.thumbnail.extension))
            .error(R.drawable.ic_round_account_circle)
            .fit()
            .centerCrop()
            .into(itemView.picture, object : Callback {
                override fun onSuccess() {}

                override fun onError(e: Exception?) {}
            })
    }


}