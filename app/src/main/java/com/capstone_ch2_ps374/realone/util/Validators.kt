package com.capstone_ch2_ps374.realone.util

import java.util.regex.Pattern

fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
    val pattern = Pattern.compile(emailRegex)
    return pattern.matcher(email).matches()
}