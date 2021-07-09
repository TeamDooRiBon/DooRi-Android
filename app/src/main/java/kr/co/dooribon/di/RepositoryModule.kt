package kr.co.dooribon.di

import kr.co.dooribon.api.repository.HomeRepository

/**
 * api 통신시 Repository를 주입할 때 사용
 */
class RepositoryModule {

    fun provideHomeRepository() = HomeRepository()
}