package com.example.logic

class User(firstName:String?, surname:String?, email:String?, dobDay:Int?, dobMonth:Int?, dobYear:Int?, age:Int?) {
        val mfirstName = firstName
        val msurname = surname
        val memail = email
        val dateOfBirth = "$dobDay/$dobMonth/$dobYear"
        val mage = age
}