package com.tbahlai.cryptowallet.presentation.home.mappers

import com.tbahlai.cryptowallet.domain.models.New
import com.tbahlai.cryptowallet.presentation.home.models.UiNew
import javax.inject.Inject

class NewToUiNewMapper @Inject constructor(
) {

    fun map(new: New) : UiNew {
        return UiNew(
            id = new.id,
            sourceName = new.sourceName,
            title = new.title,
            publishedDate = new.publishedDate
        )
    }
}