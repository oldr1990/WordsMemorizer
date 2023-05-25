package com.github.wordsmemorizer.network

sealed interface Response<T>{
    class Success<T>(val data: T):Response<T>
    class Error<T>(val exception: java.lang.Exception): Response<T>
}