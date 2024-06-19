package ru.paramonov.cryptocurrencytracker.di


import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.paramonov.cryptocurrencytracker.data.workers.ChildWorkerFactory
import ru.paramonov.cryptocurrencytracker.data.workers.RefreshDataWorker
import ru.paramonov.cryptocurrencytracker.di.annotation.WorkerKey

@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    fun bindRefreshWorkerFactory(
        worker: RefreshDataWorker.Factory
    ): ChildWorkerFactory
}