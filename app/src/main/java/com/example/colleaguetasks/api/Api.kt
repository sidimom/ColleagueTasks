package com.example.colleaguetasks.api

import com.example.colleaguetasks.api.model.Todo
import com.example.colleaguetasks.api.model.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Single<User>

    @GET("todos/{id}")
    fun getTodos(@Path("id") id: Int): Single<Todo>
}