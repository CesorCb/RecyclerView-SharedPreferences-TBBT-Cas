package com.cesor.android.ttbtcastsp

/****
 * Project: Users SP
 * From: com.cesor.android.userssp
 * Created by: César Castro on 22/06/2022 at 14:33.
 ***/
data class Actor(val id: Int, val name: String, val lastName: String, val role: String, val imgUrl: String){

    fun getFullName(): String = "$name $lastName"
    fun getActorRole(): String = "as $role"
}
