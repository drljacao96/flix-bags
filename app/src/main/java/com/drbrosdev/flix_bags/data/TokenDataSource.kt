package com.drbrosdev.flix_bags.data

import androidx.datastore.core.DataStore
import com.drbrosdev.flix_bags.Token
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import java.io.IOException

class TokenDataSource(
    private val dataStore: DataStore<Token>
) {

    val tokenFlow = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(Token.getDefaultInstance())
            } else {
                throw exception
            }
        }

    suspend fun getTokenSnapshot() = dataStore.data.first()

    suspend fun updateToken(token: String) {
        dataStore.updateData {
            it.toBuilder()
                .setToken(token)
                .build()
        }
    }

    suspend fun clearToken() {
        dataStore.updateData {
            it.toBuilder()
                .clear()
                .build()
        }
    }

}