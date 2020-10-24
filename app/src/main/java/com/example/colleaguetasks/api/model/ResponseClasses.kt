package com.example.colleaguetasks.api.model

data class User(var id: Int,
                var name: String,
                var username: String,
                var email: String)

data class Todo(var userId: Int,
                var id: Int,
                var title: String,
                var completed: Boolean)