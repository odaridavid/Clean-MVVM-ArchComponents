package com.k0d4black.theforce.feature.charactersearchresults.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.k0d4black.theforce.feature.charactersearchresults.R
import com.k0d4black.theforce.feature.charactersearchresults.model.CharacterSearchResultPresentation

class CharacterSearchResultViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(characterSearchResultPresentation: CharacterSearchResultPresentation) {
        with(view) {
            findViewById<TextView>(R.id.character_birth_year_text_view).apply {
                text = characterSearchResultPresentation.birthYear
            }

            findViewById<TextView>(R.id.character_name_text_view).apply {
                text = characterSearchResultPresentation.name
            }
        }
    }
}
