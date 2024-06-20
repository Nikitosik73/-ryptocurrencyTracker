package ru.paramonov.cryptocurrencytracker.presentation.screens.deteilcoin.terminal.component

import ru.paramonov.cryptocurrencytracker.data.network.model.graphic.Bar
import ru.paramonov.cryptocurrencytracker.presentation.screens.deteilcoin.terminal.TimeFrame

sealed class TerminalViewState {

    object Initial : TerminalViewState()

    object Loading : TerminalViewState()

    data class Error(val messageResId: Int) : TerminalViewState()

    data class ContentTerminal(val bars: List<Bar>, val timeFrame: TimeFrame) : TerminalViewState()
}