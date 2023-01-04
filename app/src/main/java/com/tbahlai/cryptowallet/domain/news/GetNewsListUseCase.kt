package com.tbahlai.cryptowallet.domain.news

import com.tbahlai.cryptowallet.data.models.RequestResult
import com.tbahlai.cryptowallet.data.repositories.NewsRepository
import com.tbahlai.cryptowallet.domain.models.ApiError
import com.tbahlai.cryptowallet.domain.models.New
import javax.inject.Inject

class GetNewsListUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke() : RequestResult<List<New>, ApiError> {
        return newsRepository.getNewsList()
    }
}