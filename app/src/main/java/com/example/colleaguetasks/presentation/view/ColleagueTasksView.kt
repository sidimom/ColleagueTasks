package com.example.colleaguetasks.presentation.view

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ColleagueTasksView: MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setUser(userName: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setTodo1(todoId: Int, todoName: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setTodo2(todoId: Int, todoName: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showLoading()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun hideLoading()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showError(error: String)
}