package com.example.logic

import java.io.Serializable

class User (firstName:String?, surname:String?, email:String?, dobDay:Int?, dobMonth:Int?, dobYear:Int?, age:Int?): Serializable {
    var mfirstName = firstName
    var msurname = surname
    var memail = email
    var mdobDay = dobDay
    var mdobMonth = dobMonth
    var mdobYear = dobYear
    var mage = age
}