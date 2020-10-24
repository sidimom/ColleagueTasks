package com.example.colleaguetasks.presentation.presenter

import com.example.colleaguetasks.api.NetworkService
import com.example.colleaguetasks.presentation.view.ColleagueTasksView
import com.example.colleaguetasks.utils.Const.Companion.TODO_NUMBER
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import java.util.concurrent.atomic.AtomicInteger
import kotlin.random.Random

@InjectViewState
class ColleagueTasksPresenter: MvpPresenter<ColleagueTasksView>() {

    private val disposables = CompositeDisposable()
    private val requestsCount = AtomicInteger()

    fun viewIsReady(colleagueId: Int){
        viewState.showLoading()

        requestsCount.incrementAndGet()
        disposables.add(NetworkService.getInstance().api
            .getUser(colleagueId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { 
                if (requestsCount.decrementAndGet() < 1) 
                    viewState.hideLoading()
            }
            .subscribe({ viewState.setUser(it.name) },
                { viewState.showError(it.toString()) })
        )
        
        val todoId1 = getRandomInt()
        requestsCount.incrementAndGet()
        disposables.add(NetworkService.getInstance().api
            .getTodos(todoId1 + (colleagueId - 1) * TODO_NUMBER)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                if (requestsCount.decrementAndGet() < 1)
                    viewState.hideLoading()
            }
            .subscribe({ viewState.setTodo1(it.id, it.title) },
                { viewState.showError(it.toString()) })
        )

        val todoId2 = getRandomInt(todoId1)
        requestsCount.incrementAndGet()
        disposables.add(NetworkService.getInstance().api
            .getTodos(todoId2 + (colleagueId - 1) * TODO_NUMBER)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                if (requestsCount.decrementAndGet() < 1)
                    viewState.hideLoading()
            }
            .subscribe({ viewState.setTodo2(it.id, it.title) },
                { viewState.showError(it.toString()) })
        )
    }

    private fun getRandomInt(exceptionalInt: Int = 0): Int {
        var todoId: Int
        do {
            todoId = Random.nextInt(1, TODO_NUMBER)
        } while (todoId == exceptionalInt)

        return todoId
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}