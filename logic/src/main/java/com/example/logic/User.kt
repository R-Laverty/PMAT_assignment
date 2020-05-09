package com.example.logic

import sun.security.util.Password
import java.io.Serializable

class User (username:String?,firstName:String?, surname:String?, email:String?, dobDay:Int?, dobMonth:Int?,
            dobYear:Int?, age:Int?, gender:String?, sexuality:String?, password: String?,
            bio:String?, hobbie1:String?, hobbie2:String?, hobbie3:String?, DOB: String?, Rtype: String?, liked_matches: String?, disliked_matches: String?): Serializable {
    var musername = username
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
    var mbio = bio
    var mhobbie1 = hobbie1
    var mhobbie2 = hobbie2
    var mhobbie3 = hobbie3
    var mDOB = DOB
    var mRtype = Rtype
    var mliked_matches = liked_matches
    var mdisliked_matches = disliked_matches
}