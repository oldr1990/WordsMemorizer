package com.github.wordsmemorizer.network.oxford

import javax.inject.Inject

class OxfordRepository @Inject constructor(private val api: OxfordApi) {

    suspend fun searchWord(search: String){
         try {
            val a = api.searchWord(search)
             println(a.toString())
        } catch (e: Exception){
            println(e)
        }
    }

}