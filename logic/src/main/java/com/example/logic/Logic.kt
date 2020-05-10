package com.example.logic

import java.io.Serializable
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

class Logic: Serializable {

    private var url = "jdbc:jtds:sqlserver://amorappserver.database.windows.net:1433;DatabaseName=AmorAppDB;" +
            "user=pmatamor@amorappserver;password=Qwertyuiop@1;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;"


    fun connectionclass(): Connection?{
        var connection: Connection? = null
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver")
            val ConnectionURL = "jdbc:jtds:sqlserver://amorappserver.database.windows.net:1433;DatabaseName=AmorAppDB;user=pmatamor@amorappserver;password=Qwertyuiop@1;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=100;"
            connection = DriverManager.getConnection(ConnectionURL)
        }catch (e: java.sql.SQLException){

        }catch (e: java.lang.ClassNotFoundException){

        }
        return connection
    }

    fun SignIn(username: String, password: String, userDetails: User): User {
        //Sign in should find the user from the database and then update userdetails
        try {
            val con = connectionclass()
            if (con != null){
                val stringQuery = "SELECT * FROM tbl_user_info, tbl_user_hobby, tbl_user_attraction " +
                        "WHERE tbl_user_info.email = '$username' " +
                        "AND tbl_user_info.password = '$password' " +
                        "AND tbl_user_info.user_id = tbl_user_hobby.fk_user_id " +
                        "AND tbl_user_info.user_id = tbl_user_attraction.fk_user_id"
                val stmt: Statement = con.createStatement()
                val rs: ResultSet = stmt.executeQuery(stringQuery)
                if (rs.next()){
                    userDetails.musername = rs.getString("user_id")
                    userDetails.mfirstName = rs.getString("first_name")
                    userDetails.msurname = rs.getString("last_name")
                    userDetails.memail = rs.getString("email")
                    userDetails.mDOB = rs.getString("DOB").toString()
                    userDetails.mage = rs.getString("age").toInt()
                    userDetails.mbio = rs.getString("BIO")
                    userDetails.mhobbie1 = rs.getString("hobbies_1")
                    userDetails.mhobbie2 = rs.getString("hobbies_2")
                    userDetails.mhobbie3 = rs.getString("hobbies_3")
                    userDetails.mgender = rs.getString("gender")
                    userDetails.msexuality = rs.getString("sexuality")
                    userDetails.mRtype = rs.getString("type_of_relationship")
                    userDetails.mliked_matches = rs.getString("liked_matches")
                    userDetails.mdisliked_matches = rs.getString("disliked_matches")
                }
                con.close()
                stmt.close()
                rs.close()
            }
        }catch (e: java.lang.Exception){}
        return userDetails
    }

    fun validateEmail(email: String): Boolean {
        //validate email does basic validation to check weather an email is an email and returns false
        // if it isnt
        if (email.contains('@') && email.contains('.')) {
            return true
        }
        return false
    }

    fun validateAgeAndDOB(
        dayOfBirth: Int, monthOfBirth: Int, yearOfBirh: Int, age: Int,
        currentYear: Int
    ): Boolean {
        //validate age and DOB checks weather the entered year of birth corroborates with the entered age
        //as well as ensuring the user is 18 or over and returns true if it is
        val calcAge = currentYear - yearOfBirh
        if (age > 17) {
            if (calcAge == age || calcAge + 1 == age) {
                return true
            }
        }
        return false
    }

    fun getMatches(userDetails: User): List<User> {

        var lowPriomatches: List<User> = listOf()
        var medPriomatches: List<User> = listOf()
        var highPriomatches: List<User> = listOf()
        var allmatches: List<User> = listOf()

        var hobbie1 = userDetails.mhobbie1
        var hobbie2 = userDetails.mhobbie2
        var hobbie3 = userDetails.mhobbie3

        try {
            val con: Connection? = connectionclass()
            if (con != null) {
                //low Prio matches
                val stringQuery =
                    "SELECT * " +
                            "FROM tbl_user_info, tbl_user_hobby, tbl_user_attraction " +
                            "WHERE tbl_user_info.user_id = tbl_user_hobby.fk_user_id " +
                            "AND tbl_user_info.user_id = tbl_user_attraction.fk_user_id " +
                            "AND ((tbl_user_hobby.hobbies_1 = '$hobbie1'OR tbl_user_hobby.hobbies_2 = '$$hobbie1' OR tbl_user_hobby.hobbies_3 = '$hobbie1')" +
                            "OR (tbl_user_hobby.hobbies_1 = '$hobbie2'OR tbl_user_hobby.hobbies_2 = '$hobbie2' OR tbl_user_hobby.hobbies_3 = '$hobbie2')" +
                            "OR (tbl_user_hobby.hobbies_1 = '$hobbie3'OR tbl_user_hobby.hobbies_2 = '$hobbie3' OR tbl_user_hobby.hobbies_3 = '$hobbie3'))"

                val stmt: Statement = con.createStatement()
                val rs: ResultSet = stmt.executeQuery(stringQuery)

                while (rs.next()) {
                    val match = User(
                        rs.getString("fk_user_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"), null, null, null,
                        rs.getString("age").toInt(),
                        rs.getString("gender"),
                        rs.getString("sexuality"),
                        null,
                        rs.getString("BIO"),
                        rs.getString("hobbies_1"),
                        rs.getString("hobbies_2"),
                        rs.getString("hobbies_3"),
                        rs.getString("DOB"),
                        rs.getString("type_of_relationship"), null, null
                    )

                    lowPriomatches = lowPriomatches.plus(match)
                }
                con.close()
                stmt.close()
                rs.close()
            }
        }catch (e: java.lang.Exception){}
        lowPriomatches = lowPriomatches.filter { !medPriomatches.contains(it) || !highPriomatches.contains(it) }
        medPriomatches = medPriomatches.filter { !highPriomatches.contains(it) }
        allmatches = allmatches.plus(lowPriomatches)
        allmatches = allmatches.plus(medPriomatches)
        allmatches = allmatches.plus(highPriomatches)

        var likedMatches = matchesStringToList(userDetails.mliked_matches)
        var dislikedMatches = matchesStringToList(userDetails.mdisliked_matches)

        return allmatches.filter { !likedMatches.contains("${it.mfirstName} ${it.msurname}") ||
                !dislikedMatches.contains("${it.mfirstName} ${it.msurname}")}
    }

    fun updateDatabase(userDetails: User) {
        //update database should update the database with the most up to date user details
        try{
            var con: Connection? = connectionclass()
            val userID = generateUserID(userDetails, con)
            con = connectionclass()
            if (con != null){
                var stringQuery = "BEGIN; " +
                        "INSERT INTO tbl_user_info(user_id,first_name, last_name, password, email, BIO, age) VALUES ('$userID','${userDetails.mfirstName}','${userDetails.msurname}','${userDetails.mpassword}','${userDetails.memail}','${userDetails.mbio}',${userDetails.mage});" +
                        "INSERT INTO tbl_user_hobby(fk_user_id,hobbies_1, hobbies_2, hobbies_3) VALUES ('$userID','${userDetails.mhobbie1}', '${userDetails.mhobbie2}', '${userDetails.mhobbie3}'); " +
                        "INSERT INTO tbl_user_attraction(fk_user_id,gender, sexuality) VALUES('$userID','${userDetails.mgender}','${userDetails.msexuality}');" +
                        "END;"

                val stmt = con.createStatement()
                stmt.executeQuery(stringQuery)

                con.close()
                stmt.close()
            }
        }catch (e: java.lang.Exception){}
    }

    fun formatForDatabase(name: String): String{
        var mName = name.trim(' ')
        mName = mName.toLowerCase()
        var mNameA = mName.toCharArray()
        mNameA[0] = mNameA[0].toUpperCase()
        mName = ""
        for(char in mNameA){
            mName = "$mName$char"
        }
        return mName
    }

    fun ValidateGender(gender: String): String{
        val mGender = gender.toUpperCase()
        if (mGender == "M" || mGender == "MALE"){
            return "M"
        }else if (mGender == "F" || mGender == "FEMALE"){
            return "F"
        }else{
            return "/"
        }
    }

    fun generateUserID(userDetails: User, con: Connection?): String{
        val firstname  = userDetails.mfirstName
        val surname = userDetails.msurname
        var idNumber = 0
        var userId: String = ""
        if (firstname != null && surname != null){
            userId = "${firstname.toCharArray()[0]}${surname.toCharArray()[0]}"
        }
        if (con!=null){
            try {
                var tmpIDNo = 0
                val stmt = con.createStatement()
                while (idNumber == null) {
                    var tmpID = "$userId$tmpIDNo"
                    var stringQuery = "SELECT user_id" +
                            "FROM tbl_user_info" +
                            "WHERE user_id =  $tmpID"

                    val rs: ResultSet = stmt.executeQuery(stringQuery)
                    if (rs.getString("user_id") == null){
                        idNumber = tmpIDNo
                    }else{
                        tmpIDNo++
                    }
                }
                con.close()
                stmt.close()
            }catch (e: java.lang.Exception){}
        }
        return "$userId$idNumber"
    }

    fun likeMatch(userDetails: User, likedMatchFname:String?, likedMatchsurname: String?): User{
        //updates the database with the disliked match and userdetails
        userDetails.mliked_matches = "${userDetails.mliked_matches},$likedMatchFname $likedMatchsurname"
        val con = connectionclass()
        if (con != null){
            try {
                val stringQuery = "UPDATE tbl_user_attraction " +
                        "SET liked_matches = '${userDetails.mliked_matches}'" +
                        "WHERE fk_user_id = '${userDetails.musername}'"
                val stmt = con.createStatement()
                stmt.executeQuery(stringQuery)
                con.close()
                stmt.close()
            }catch (e: java.lang.Exception){}
        }
        return userDetails
    }

    fun dislikeMatch(userDetails: User, dislikedMatchFname:String?, dislikedMatchsurname: String?): User{
        //updates the database with the disliked match and userdetails
        userDetails.mdisliked_matches = "${userDetails.mdisliked_matches},$dislikedMatchFname $dislikedMatchsurname"
        val con = connectionclass()
        if (con != null){
            try {
                val stringQuery = "UPDATE tbl_user_attraction " +
                        "SET disliked_matches = '${userDetails.mdisliked_matches}'" +
                        "WHERE fk_user_id = '${userDetails.musername}'"
                val stmt = con.createStatement()
                stmt.executeQuery(stringQuery)
                con.close()
                stmt.close()
            }catch (e: java.lang.Exception){}
        }
        return userDetails
    }

    fun matchesStringToList(matches: String?):List<String>{
        val tmpMatches = matches
        if (tmpMatches != null){
            val matchesList = tmpMatches.split(',')
            return matchesList
        }
        return listOf()
    }

    fun matchesListToString(matches: List<String>): String?{
        var stringMatches = ""
        for (match in matches){
            stringMatches = "$stringMatches,$match"
        }
        return stringMatches
    }
}
