package com.capstone_ch2_ps374.realone.di

import com.capstone_ch2_ps374.realone.api.AddressApiService
import com.capstone_ch2_ps374.realone.api.AdressApiConfig
import com.capstone_ch2_ps374.realone.api.ApiConfig
import com.capstone_ch2_ps374.realone.api.ApiService
import com.capstone_ch2_ps374.realone.domain.usecase.ValidateAddressEmpty
import com.capstone_ch2_ps374.realone.domain.usecase.ValidateEmail
import com.capstone_ch2_ps374.realone.domain.usecase.ValidateEmpty
import com.capstone_ch2_ps374.realone.domain.usecase.ValidatePassword
import com.capstone_ch2_ps374.realone.domain.usecase.ValidateRepeatedPassword
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideValidateEmail(): ValidateEmail {
        return ValidateEmail()
    }

    @Provides
    fun provideValidatePassword(): ValidatePassword {
        return ValidatePassword()
    }

    @Provides
    fun provideValidateRepeatedPassword(): ValidateRepeatedPassword {
        return ValidateRepeatedPassword()
    }

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return Firebase.auth
    }

    @Provides
    fun provideApi(): ApiService {
        return ApiConfig.getApiService()
    }

    @Provides
    fun provideValidateEmpty(): ValidateEmpty {
        return ValidateEmpty()
    }

    @Provides
    fun provideApiAddress(): AddressApiService {
        return AdressApiConfig.getApiService()
    }

    @Provides
    fun provideValidateAddressEmpty(): ValidateAddressEmpty {
        return ValidateAddressEmpty()
    }
}