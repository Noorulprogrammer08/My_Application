package com.mallowtech.myapplication.commonFiles

import android.util.Patterns
import com.mallowtech.myapplication.R

object HRFunctions {
    fun isValidUsername(user: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(user).matches()
    }

    fun checkPassword(password: String?): Int? {
        var errorPassword: Int? = null
        when {
            password.isNullOrEmpty() -> errorPassword = R.string.password_empty
//            password?.length != 6 -> errorPassword = R.string.password_length
            password?.firstOrNull { it.isDigit() } == null -> errorPassword =
                R.string.password_digit
            password?.filter { it.isLetter() }
                ?.firstOrNull { it.isUpperCase() } == null -> errorPassword =
                R.string.password_uppercase
            password?.firstOrNull { !it.isLetterOrDigit() } == null -> errorPassword =
                R.string.password_special_character
        }
        errorPassword?.let {
            return it
        }
        return null
    }

    fun <T1, T2, T3>
            ifNotNull(value1: T1?, value2: T2?, value3: T3?, allNotNull: (T1, T2, T3) -> (Unit)) {
        if (value1 != null && value2 != null && value3 != null) {
            allNotNull(value1, value2, value3)
        }
    }

    fun <T1, T2>
            ifBothNotNull(value1: T1?, value2: T2?, bothNotNull: (T1, T2) -> (Unit)) {
        if (value1 != null && value2 != null ) {
            bothNotNull(value1, value2)
        }
    }

}