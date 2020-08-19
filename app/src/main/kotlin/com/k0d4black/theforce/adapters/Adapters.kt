package com.k0d4black.theforce.adapters

import com.k0d4black.theforce.R
import com.k0d4black.theforce.models.CharacterPresentation
import com.k0d4black.theforce.models.FavoritePresentation
import com.k0d4black.theforce.models.FilmPresentation
import com.k0d4black.theforce.models.SpeciePresentation
import me.ibrahimyilmaz.kiel.adapterOf

internal inline fun createSpeciesAdapter() = adapterOf<SpeciePresentation> {
    diff(
        areItemsTheSame = { old, new -> old === new },
        areContentsTheSame = { old, new -> old.name == new.name }
    )
    register(
        layoutResource = R.layout.item_specie,
        viewHolder = ::SpecieViewHolder,
        onBindBindViewHolder = { specieViewHolder, _, speciePresentation ->
            specieViewHolder.binding.species = speciePresentation
        }
    )
}

internal inline fun createFilmsAdapter() = adapterOf<FilmPresentation> {
    diff(
        areItemsTheSame = { old, new -> old === new },
        areContentsTheSame = { old, new -> old.title == new.title }
    )
    register(
        layoutResource = R.layout.item_film,
        viewHolder = ::FilmViewHolder,
        onBindBindViewHolder = { viewHolder, _, item ->
            viewHolder.binding.film = item
        }
    )
}

internal inline fun createSearchResultAdapter(noinline onClick: (CharacterPresentation) -> Unit) =
    adapterOf<CharacterPresentation> {
        diff(
            areItemsTheSame = { old, new -> old === new },
            areContentsTheSame = { old, new -> old.url == new.url }
        )
        register(
            layoutResource = R.layout.item_search,
            viewHolder = ::SearchedCharacterViewHolder,
            onBindBindViewHolder = { viewHolder, _, character ->
                viewHolder.binding.searchedCharacter = character
                viewHolder.binding.moreInfoArrowImageButton.setOnClickListener {
                    onClick(character)
                }
            }
        )
    }

internal inline fun createFavoritesAdapter(noinline onClick: (FavoritePresentation) -> Unit) =
    adapterOf<FavoritePresentation> {
        diff(
            areItemsTheSame = { old, new -> old === new },
            areContentsTheSame = { old, new ->
                old.characterPresentation.name == new.characterPresentation.name &&
                        old.characterPresentation.birthYear == new.characterPresentation.birthYear &&
                        old.characterPresentation.heightInCm == new.characterPresentation.heightInCm
            }
        )
        register(
            layoutResource = R.layout.item_favorite,
            viewHolder = ::FavoriteViewHolder,
            onBindBindViewHolder = { vh, _, favoritePresentation ->
                vh.binding.favorite = favoritePresentation
                vh.binding.favCard.setOnClickListener {
                    onClick(favoritePresentation)
                }
            }
        )
    }
