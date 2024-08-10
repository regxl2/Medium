package com.example.medium.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.medium.domain.repository.TokenRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TokenRepositoryImpl@Inject constructor(@ApplicationContext private val context: Context): TokenRepository{
    private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    companion object{
        private val JWT_TOKEN = stringPreferencesKey("jwt_token")
    }
    override fun getTokenFlow(): Flow<String?> {
        return context.datastore.data.map {
            preferences ->  preferences[JWT_TOKEN]
        }
    }

    override suspend fun setToken(token: String) {
        context.datastore.edit {
            settings -> settings[JWT_TOKEN] = token
        }
    }

}