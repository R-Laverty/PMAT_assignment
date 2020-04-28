package com.example.logic

import sun.security.util.Password
import java.io.Serializable

class User (firstName:String?, surname:String?, email:String?, dobDay:Int?, dobMonth:Int?, dobYear:Int?, age:Int?, gender:String?, sexuality:String?, password: String?): Serializable {
    var mfirstName = firstName
    var msurname = surname
    var memail = email
    var mdobDay = dobDay
    var mdobMonth = dobMonth
    var mdobYear = dobYear
    var mage = age
    var mgender = gender
    var msexuality = sexuality
    var mpassword = password//TODO encrypt this
}