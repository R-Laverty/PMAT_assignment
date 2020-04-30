package com.example.logic

fun SignIn(username: String, password: String, userDetails:User): User {
    //Sign in should find the user from the database and then update userdetails
    //TODO get user details from database and update user details
    return userDetails
}

fun validateSignIn(username: String, password: String): Boolean{
    // validate sign in should check the database for the entered username and password and return
    // true if the user is found
    //TODO validate sign in return true if sign in is valid and user exists
    return true
}

fun validateEmail(email: String):Boolean{
    //validate email does basic validation to check weather an email is an email and returns false
    // if it isnt
    if (email.contains('@') && email.contains('.')) {
        return true
    }
    return false
}

fun validateAgeAndDOB(dayOfBirth: Int, monthOfBirth: Int, yearOfBirh: Int, age: Int,
                      currentYear: Int): Boolean{
    //validate age and DOB checks weather the entered year of birth corroborates with the entered age
    //as well as ensuring the user is 18 or over and returns true if it is
    val calcAge = currentYear-yearOfBirh
    if (age > 17){
        if (calcAge-1 == age || calcAge+1 == age){
            return true
        }
    }
    return false
}

fun getMatches(userDetails: User){
    //get matches should perform a database lookup for all users with at least 2? matching hobbies
    // as well as users desired partners sexuality then return them in a list
}

fun updateDatabase(userDetails: User){
    //update database should update the database with the most up to date user details
}
