package com.sun.quickquotes.di

import com.sun.quickquotes.data.repository.*
import com.sun.quickquotes.data.source.AuthorDataSource
import com.sun.quickquotes.data.source.CollectionDataSource
import com.sun.quickquotes.data.source.QuoteDataSource
import com.sun.quickquotes.data.source.TagDataSource
import com.sun.quickquotes.data.source.local.AuthorLocalDataSource
import com.sun.quickquotes.data.source.local.CollectionLocalDataSource
import com.sun.quickquotes.data.source.local.QuoteLocalDataSource
import com.sun.quickquotes.data.source.local.TagLocalDataSource
import com.sun.quickquotes.data.source.remote.AuthorRemoteDataSource
import com.sun.quickquotes.data.source.remote.QuoteRemoteDataSource
import com.sun.quickquotes.data.source.remote.TagRemoteDataSource
import org.koin.dsl.module

val repositoryModule = module {

    single<AuthorDataSource.Local> { AuthorLocalDataSource(get()) }
    single<AuthorDataSource.Remote> { AuthorRemoteDataSource(get()) }
    single<AuthorRepository> { AuthorRepositoryImpl(get(), get()) }

    single<CollectionDataSource.Local> { CollectionLocalDataSource(get()) }
    single<CollectionRepository> { CollectionRepositoryImpl(get()) }

    single<QuoteDataSource.Local> { QuoteLocalDataSource(get()) }
    single<QuoteDataSource.Remote> { QuoteRemoteDataSource(get()) }
    single<QuoteRepository> { QuoteRepositoryImpl(get(), get()) }

    single<TagDataSource.Local> { TagLocalDataSource(get()) }
    single<TagDataSource.Remote> { TagRemoteDataSource(get()) }
    single<TagRepository> { TagRepositoryImpl(get(), get()) }
}
