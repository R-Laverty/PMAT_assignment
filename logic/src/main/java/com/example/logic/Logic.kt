package com.example.logic

fun validateSignIn(username: String, password: String): Boolean {
    //TODO validate sign in return true if sign in is valid
    return true
}

fun validateEmail(email: String):Boolean{
    if (email.contains('@') && email.contains('.')) {
        return true
    }
    return false
}

fun validateAgeAndDOB(dayOfBirth: Int, monthOfBirth: Int, yearOfBirh: Int, age: Int, currentYear: Int): Boolean{
    val calcAge = currentYear-yearOfBirh
    if (age > 17){
        if (calcAge-1 == age || calcAge+1 == age){
            return true
        }
    }
    return false
}
