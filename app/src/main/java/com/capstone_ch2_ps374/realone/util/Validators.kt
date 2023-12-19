package com.capstone_ch2_ps374.realone.util

import android.util.Patterns
import java.util.regex.Pattern

object Validators{
    fun isNotValidEmail(email: String): Boolean {
        return !Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}