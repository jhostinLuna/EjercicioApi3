package com.jhostinluna.heroes.data.repositories

import javax.inject.Inject

class DataRepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSourceInterface
) {
}