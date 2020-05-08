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
                    userDetails.mfirstName = rs.getString("first_name")
                    userDetails.msurname = rs.getString("last_name")
                    userDetails.memail = rs.getString("email")
                    userDetails.mDOB = rs.getString("DOB").toString()
                    userDetails.mbio = rs.getString("BIO")
                    userDetails.mhobbie1 = rs.getString("hobbies_1")
                    userDetails.mhobbie2 = rs.getString("hobbies_2")
                    userDetails.mhobbie3 = rs.getString("hobbies_3")
                    userDetails.mgender = rs.getString("gender")
                    userDetails.msexuality = rs.getString("sexuality")
                    userDetails.mRtype = rs.getString("type_of_relationship")
                }
                con.close()
                stmt.close()
                rs.close()
            }
        }catch (e: java.lang.Exception){}
        return userDetails
    }

    fun validateSignIn(username: String, password: String): Boolean {
        // validate sign in should check the database for the entered username and password and return
        // true if the user is found
        //TODO validate sign in return true if sign in is valid and user exists
        return true
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
            val con = connectionclass()
            if (con != null) {

                //High priority matches
                var stringQuery =
                    "SELECT * FROM tbl_user_info, tbl_user_hobby, tbl_user_attraction WHERE " +
                            "tbl_user_info.user_id = tbl_user_hobby.fk_user_id " +
                            "AND tbl_user_info.user_id = tbl_user_attraction.fk_user_id " +
                            "AND (tbl_user_hobby.hobbies_1 = '$hobbie1'OR tbl_user_hobby.hobbies_2 = '$hobbie1' OR tbl_user_hobby.hobbies_3 = '$hobbie1')" +
                            "AND (tbl_user_hobby.hobbies_1 = '$hobbie2'OR tbl_user_hobby.hobbies_2 = '$hobbie2' OR tbl_user_hobby.hobbies_3 = '$hobbie2')" +
                            "AND (tbl_user_hobby.hobbies_1 = '$hobbie3'OR tbl_user_hobby.hobbies_2 = '$hobbie3' OR tbl_user_hobby.hobbies_3 = '$hobbie3')"
                var stmt: Statement = con.createStatement()
                var rs: ResultSet = stmt.executeQuery(stringQuery)
                while (rs.next()){
                    var match = User(rs.getString("first_name"),rs.getString("last_name"),
                        rs.getString("email"), null, null, null,
                        null, rs.getString("gender"),rs.getString("sexuality"),
                        null, rs.getString("BIO"), rs.getString("hobbies_1"),
                        rs.getString("hobbies_2"), rs.getString("hobbies_3"), rs.getString("DOB"),
                        rs.getString("type_of_relationship"))
                    highPriomatches = highPriomatches.plus(match)
                }

                //medium prio matches
                stringQuery =
                    "SELECT * FROM tbl_user_info, tbl_user_hobby, tbl_user_attraction " +
                        "WHERE tbl_user_info.user_id = tbl_user_hobby.fk_user_id " +
                        "AND tbl_user_info.user_id = tbl_user_attraction.fk_user_id " +
                        "AND (tbl_user_hobby.hobbies_1 = '${userDetails.mhobbie1}'OR tbl_user_hobby.hobbies_2 = '${userDetails.mhobbie1}' OR tbl_user_hobby.hobbies_3 = '${userDetails.mhobbie1}')" +
                        "AND ((tbl_user_hobby.hobbies_1 = '${userDetails.mhobbie2}'OR tbl_user_hobby.hobbies_2 = '${userDetails.mhobbie2}' OR tbl_user_hobby.hobbies_3 = '${userDetails.mhobbie2}')" +
                        "OR (tbl_user_hobby.hobbies_1 = '${userDetails.mhobbie3}'OR tbl_user_hobby.hobbies_2 = '${userDetails.mhobbie3}' OR tbl_user_hobby.hobbies_3 = '${userDetails.mhobbie3}'))"

                stmt = con.createStatement()
                rs = stmt.executeQuery(stringQuery)
                while (rs.next()){
                    var match = User(rs.getString("first_name"),rs.getString("last_name"),
                        rs.getString("email"), null, null, null,
                        null, rs.getString("gender"),rs.getString("sexuality"),
                        null, rs.getString("BIO"), rs.getString("hobbies_1"),
                        rs.getString("hobbies_2"), rs.getString("hobbies_3"), rs.getString("DOB"),
                        rs.getString("type_of_relationship"))
                    medPriomatches = medPriomatches.plus(match)
                }

                //low Prio matches
                stringQuery =
                    "SELECT * FROM tbl_user_info, tbl_user_hobby, tbl_user_attraction WHERE " +
                            "tbl_user_info.user_id = tbl_user_hobby.fk_user_id " +
                            "AND tbl_user_info.user_id = tbl_user_attraction.fk_user_id " +
                            "AND ((tbl_user_hobby.hobbies_1 = '${userDetails.mhobbie1}'OR tbl_user_hobby.hobbies_2 = '${userDetails.mhobbie1}' OR tbl_user_hobby.hobbies_3 = '${userDetails.mhobbie1}')" +
                            "OR (tbl_user_hobby.hobbies_1 = '${userDetails.mhobbie2}'OR tbl_user_hobby.hobbies_2 = '${userDetails.mhobbie2}' OR tbl_user_hobby.hobbies_3 = '${userDetails.mhobbie2}')" +
                            "OR (tbl_user_hobby.hobbies_1 = '${userDetails.mhobbie3}'OR tbl_user_hobby.hobbies_2 = '${userDetails.mhobbie3}' OR tbl_user_hobby.hobbies_3 = '${userDetails.mhobbie3}'))"

                stmt = con.createStatement()
                rs = stmt.executeQuery(stringQuery)
                while (rs.next()){
                    var match = User(rs.getString("first_name"),rs.getString("last_name"),
                        rs.getString("email"), null, null, null,
                        null, rs.getString("gender"),rs.getString("sexuality"),
                        null, rs.getString("BIO"), rs.getString("hobbies_1"),
                        rs.getString("hobbies_2"), rs.getString("hobbies_3"), rs.getString("DOB"),
                        rs.getString("type_of_relationship"))
                    lowPriomatches = lowPriomatches.plus(match)

                    con.close()
                    stmt.close()
                    rs.close()
                }
            }
        }catch (e: java.lang.Exception){}
        lowPriomatches = lowPriomatches.filter { !medPriomatches.contains(it) && !highPriomatches.contains(it) }
        medPriomatches = medPriomatches.filter { !highPriomatches.contains(it) }
        allmatches = allmatches.plus(lowPriomatches)
        allmatches = allmatches.plus(medPriomatches)
        allmatches = allmatches.plus(highPriomatches)

        return allmatches
    }

    fun updateDatabase(userDetails: User) {
        //update database should update the database with the most up to date user details
        try{
            val con = connectionclass()
            if (con != null){
                var stringQuery = "BEGIN; " +
                        "INSERT INTO tbl_user_info(first_name, last_name, password, email) VALUES ('${userDetails.mfirstName}','${userDetails.msurname}','${userDetails.mpassword}','${userDetails.memail}');" +
                        "INSERT INTO tbl_user_hobby(hobbies_1, hobbies_2, hobbies_3) VALUES ('${userDetails.mhobbie1}', '${userDetails.mhobbie2}', '${userDetails.mhobbie3}'); " +
                        "INSERT INTO tbl_user_attraction(gender, sexuality) VALUES('${userDetails.mgender}','${userDetails.msexuality}');" +
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
}
