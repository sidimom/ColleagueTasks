package com.example.colleaguetasks.presentation.presenter

import com.example.colleaguetasks.presentation.view.MyTasksView
import com.example.colleaguetasks.utils.Const.Companion.COLLEAGUE_NUMBER
import moxy.InjectViewState
import moxy.MvpPresenter
import kotlin.random.Random

@InjectViewState
class MyTasksPresenter: MvpPresenter<MyTasksView>() {

    fun getRandomTodo(){
        val todoId = Random.nextInt(1, COLLEAGUE_NUMBER)
        viewState.navigateToColleagueTasks(todoId)
    }
}