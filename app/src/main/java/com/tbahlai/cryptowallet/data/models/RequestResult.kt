package com.tbahlai.cryptowallet.data.models

import com.tbahlai.cryptowallet.data.utils.StatusCode
import java.io.IOException
import java.io.Serializable
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind.AT_MOST_ONCE
import kotlin.contracts.contract

sealed class RequestResult<out S, out E> : Serializable {

    data class Success<out S> internal constructor(
        val data: S
    ) : RequestResult<S, Nothing>()

    sealed class Failure<out E> : RequestResult<Nothing, E>() {
        data class HttpFailure<out E> internal constructor(
            val code: Int,
            val value: E?,
        ) : Failure<E>() {
            fun isHttpClientError(): Boolean = code in StatusCode.HTTP_CLIENT_FAILURE_RANGE
            fun isHttpServerError(): Boolean = code in StatusCode.HTTP_SERVER_FAILURE_RANGE
        }

        data class NetworkFailure internal constructor(
            val error: IOException
        ) : Failure<Nothing>()

        data class UnknownFailure internal constructor(
            val error: Throwable
        ) : Failure<Nothing>()
    }

    inline val dataOrNull: S?
        get() = when (this) {
            is Success -> data
            else -> null
        }

    @OptIn(ExperimentalContracts::class)
    inline fun <R : Any?> map(transform: (data: S) -> R): RequestResult<R, E> {
        contract {
            callsInPlace(transform, AT_MOST_ONCE)
        }

        return when (this) {
            is Success -> success(transform.invoke(data))
            is Failure -> this
        }
    }

    @OptIn(ExperimentalContracts::class)
    inline fun <R : Any?> mapFailure(transform: (failure: E) -> R): RequestResult<S, R> {
        contract {
            callsInPlace(transform, AT_MOST_ONCE)
        }

        return when (this) {
            is Success -> this
            is Failure.HttpFailure -> httpFailure(code, value?.let(transform))
            is Failure.NetworkFailure -> this
            is Failure.UnknownFailure -> this
        }
    }

    @OptIn(ExperimentalContracts::class)
    inline fun <R, E> flatMap(transform: (data: S) -> RequestResult<R, E>): RequestResult<R, E> {
        contract {
            callsInPlace(transform, AT_MOST_ONCE)
        }

        @Suppress("UNCHECKED_CAST")
        return when (this) {
            is Success -> transform.invoke(data)
            is Failure -> this as RequestResult<R, E>
        }
    }

    @OptIn(ExperimentalContracts::class)
    inline fun <R> fold(
        onSuccess: (data: S) -> R,
        onFailure: (failure: Failure<E>) -> R
    ): R {
        contract {
            callsInPlace(onSuccess, AT_MOST_ONCE)
            callsInPlace(onFailure, AT_MOST_ONCE)
        }

        return when (this) {
            is Success -> onSuccess.invoke(data)
            is Failure -> onFailure.invoke(this)
        }
    }

    @OptIn(ExperimentalContracts::class)
    inline fun onSuccess(block: (data: S) -> Unit): RequestResult<S, E> {
        contract {
            callsInPlace(block, AT_MOST_ONCE)
        }

        if (this is Success) {
            block.invoke(data)
        }
        return this
    }

    @OptIn(ExperimentalContracts::class)
    inline fun onFailure(block: (Failure<E>) -> Unit): RequestResult<S, E> {
        contract {
            callsInPlace(block, AT_MOST_ONCE)
        }

        if (this is Failure) {
            block.invoke(this)

        }
        return this
    }

    companion object {
        /**
         * Returns a new [Success] with given [value].
         */
        fun <S : Any?> success(value: S): Success<S> = Success(value)

        /**
         * Returns a new [Failure.HttpFailure] with given [code] and optional [error].
         */
        fun <E> httpFailure(
            code: Int,
            error: E? = null
        ): Failure.HttpFailure<E> {
            checkHttpFailureCode(code)
            return Failure.HttpFailure(code, error)
        }

        /**
         * Returns a new [Failure.NetworkFailure] with given [error].
         */
        fun networkFailure(error: IOException): Failure.NetworkFailure =
            Failure.NetworkFailure(error)

        /**
         * Returns a new [Failure.UnknownFailure] with given [error].
         */
        fun unknownFailure(error: Throwable): Failure.UnknownFailure = Failure.UnknownFailure(error)

        private fun checkHttpFailureCode(code: Int) {
            require(code !in StatusCode.HTTP_SUCCESS_RANGE) {
                "Status code '$code' is a successful HTTP response. If you mean to use a ${StatusCode.OK} code " +
                    "+ error string to indicate an API error, use the RequestResult.apiFailure() factory."
            }
            require(code in StatusCode.HTTP_FAILURE_RANGE) {
                "Status code '$code' is not a HTTP failure response. Must be a 4xx or 5xx code."
            }
        }
    }
}
