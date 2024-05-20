package com.jhostinluna.heroes.core.di

import com.jhostinluna.heroes.BuildConfig
import com.jhostinluna.heroes.data.network.CharacterApiService
import com.jhostinluna.heroes.data.network.RemoteDataSourceImpl
import com.jhostinluna.heroes.data.repositories.DataRepositoryImp
import com.jhostinluna.heroes.data.repositories.RemoteDataSourceInterface
import com.jhostinluna.heroes.domain.interfaces.DataRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {
    @Provides
    fun getApiRetrofitService(okHttpClient: OkHttpClient): CharacterApiService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(CharacterApiService::class.java)

    @Provides
    fun arcgisAMBOkHttpClient() : OkHttpClient {
        val logging = HttpLoggingInterceptor()

        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient: OkHttpClient =
            OkHttpClient().newBuilder()
                .writeTimeout(2, TimeUnit.MINUTES)
                .callTimeout(2, TimeUnit.MINUTES)
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(logging)
                .addInterceptor(Interceptor { chain ->
                    val originalRequest: Request = chain.request()
                    val builder: Request.Builder = originalRequest.newBuilder()
                    val newRequest: Request = builder.build()
                    chain.proceed(newRequest)
                }).build()

        return okHttpClient
    }
}
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindDataRepository(dataRepositoryImp: DataRepositoryImp): DataRepositoryInterface

}
@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteRepoModule {
    @Binds
    abstract fun bindDataSource(dataSourceImpl: RemoteDataSourceImpl): RemoteDataSourceInterface
}