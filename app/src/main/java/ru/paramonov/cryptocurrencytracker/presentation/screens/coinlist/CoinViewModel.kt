package ru.paramonov.cryptocurrencytracker.presentation.screens.coinlist

import androidx.lifecycle.ViewModel
import ru.paramonov.cryptocurrencytracker.domain.usecase.GetCoinInfoListUseCase
import ru.paramonov.cryptocurrencytracker.domain.usecase.GetCoinInfoUseCase
import ru.paramonov.cryptocurrencytracker.domain.usecase.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    /*
        Создаём блок инициализации для того,
        чтобы в момент создания viewmodel
        данные подгружались автоматически
        и не нужно было вызывать метод loadData()
        в ручную
     */
    init {
        loadDataUseCase()
    }
}