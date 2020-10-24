package com.example.colleaguetasks.presentation.view

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface MyTasksView: MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToColleagueTasks(colleagueId: Int)
}