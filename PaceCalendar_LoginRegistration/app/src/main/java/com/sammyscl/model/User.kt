package com.sammyscl.model

import java.util.*

class User {

    var name: String? = null
    var surname: String?=null
    var userType: String? = null
    var email: String? = null
    private var password: String? = null
    val created_at: String? = null
    private var newPassword: String? = null
    private var token: String? = null


    fun setPassword(password: String) {
        this.password = password
    }

    fun setNewPassword(newPassword: String) {
        this.newPassword = newPassword
    }

    fun setToken(token: String) {
        this.token = token
    }

    fun setUsername(string: String?) {

    }

    fun setFullName(string: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun setSessionExpiryDate(date: Date) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
