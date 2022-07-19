package com.example.footballapp.core.di

import androidx.room.Room
import com.example.footballapp.core.data.FootballRepository
import com.example.footballapp.core.data.source.local.LocalDataSource
import com.example.footballapp.core.data.source.local.room.TeamDatabase
import com.example.footballapp.core.data.source.remote.RemoteDataSource
import com.example.footballapp.core.data.source.remote.network.ApiService
import com.example.footballapp.core.domain.repository.IFootballRepository
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<TeamDatabase>().teamDao() }
    single {
        val codeMaster: ByteArray = SQLiteDatabase.getBytes("t7aBady3".toCharArray())
        val factory = SupportFactory(codeMaster)
        Room.databaseBuilder(
            androidContext(),
            TeamDatabase::class.java, "team.db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }
}

val networkModule = module {
    val hostname = "www.thesportsdb.com"
    single {
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/NnupxxWETrHvZNGEnih0b/uMtCHadqgOzzx/jlJnBKI=")
            .build()

        OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            connectTimeout(120, TimeUnit.SECONDS)
            readTimeout(120, TimeUnit.SECONDS)
            certificatePinner(certificatePinner)
        }.build()
    }
    single {
        val retrofit = Retrofit.Builder().apply {
            baseUrl("https://www.thesportsdb.com/api/v1/json/2/")
            addConverterFactory(GsonConverterFactory.create())
            client(get())
        }.build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IFootballRepository> {
        FootballRepository(get(), get())
    }
}