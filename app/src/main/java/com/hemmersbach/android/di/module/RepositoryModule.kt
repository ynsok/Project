package com.hemmersbach.android.di.module

import com.hemmersbach.android.database.Dao
import com.hemmersbach.android.repository.Repository
import com.hemmersbach.android.network.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(
        apiService: ApiService,
        dao: Dao
    ) = Repository(apiService, dao)
}