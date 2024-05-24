package com.jhostinluna.heroes.data.network

import com.jhostinluna.heroes.BuildConfig
import com.jhostinluna.heroes.core.extensions.toMD5
import java.time.Instant

class SecurityAuthentication {
    val publicKey: String get() =  BuildConfig.MARVEL_PUBLIC_KEY
    private val privateKey: String = BuildConfig.MARVEL_PRIVATE_KEY
    var ts: Long? = null
    init {
        ts = Instant.now().epochSecond
    }
    fun getHashValue(): String = "$ts$privateKey$publicKey".toMD5()
}
