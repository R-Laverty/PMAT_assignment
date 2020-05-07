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

    fun getMatches(userDetails: User) {
        //Based on Gender
        //SELECT first_name,last_name,DOB, gender, sexuality, hobbies_1,hobbies_2,hobbies_3
        // FROM tbl_user_info,tbl_user_attraction,tbl_user_hobby
        //  WHERE tbl_user_attraction.sexuality = 'Straight';

        //Based on Hobbies
        // SELECT first_name,last_name,DOB, gender, sexuality, hobbies_1,hobbies_2,hobbies_3
        // FROM tbl_user_info,tbl_user_attraction,tbl_user_hobby
        // WHERE tbl_user_attraction.hobbies_1 = 'Cooking';

        //get matches should perform a database lookup for all users with at least 2? matching hobbies
        // as well as users desired partners sexuality then return them in a list
    }

    fun updateDatabase(userDetails: User) {
        //update database should update the database with the most up to date user details
    }
}
