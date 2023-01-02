package com.tbahlai.cryptowallet.common

import androidx.lifecycle.ViewModel
import com.tbahlai.cryptowallet.common.utils.stop
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

abstract class BaseViewModel<State: ScreenState, Action, Event> : ViewModel() {

    val state: StateFlow<State>
        get() = _state.asStateFlow()
    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState())

    val events: Flow<Event>
        get() = _events
    private val _events = MutableSharedFlow<Event>()

    protected abstract fun initialState(): State

    protected abstract fun handleAction(action: Action)

    protected fun updateState(copyBlock: State.() -> State) {
        _state.value = _state.value.run(copyBlock)
    }

    protected fun setData(copyBlock: State.() -> State) {
        _state.value = _state.value.run(copyBlock).apply {
            (this as? BaseState)?.let {
                loading = loading.stop()
                error = null
            }
        }
    }

    protected fun setError(copyBlock: State.() -> State) {
        _state.value = _state.value.run(copyBlock).apply {
            (this as? BaseState)?.let { loading = loading.stop() }
            (this as? RefreshState)?.let { refresh = refresh?.stop() }
        }
    }

    protected suspend fun sendEvent(event: Event) {
        _events.subscriptionCount.first { it > 0 }
        _events.emit(event)
    }

    fun <T> CoroutineScope.collectState(
        transform: (State) -> T,
        distinctUntilChanged: Boolean = true,
        consumer: suspend (T) -> Unit,
    ) {
        state
            .map(transform::invoke)
            .run { if (distinctUntilChanged) distinctUntilChanged() else this }
            .onEach(consumer::invoke)
            .launchIn(this)
    }

    fun CoroutineScope.collectEvents(
        consumer: suspend (Event) -> Unit,
    ) {
        events
            .onEach(consumer::invoke)
            .launchIn(this)
    }
}
