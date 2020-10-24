package com.example.colleaguetasks.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.example.colleaguetasks.databinding.FragmentMyTasksBinding
import com.example.colleaguetasks.presentation.presenter.MyTasksPresenter
import com.example.colleaguetasks.presentation.view.MyTasksView
import com.example.colleaguetasks.utils.Const.Companion.APP_PREFERENCES_KEY
import com.example.colleaguetasks.utils.Const.Companion.TODO_KEY
import com.example.colleaguetasks.utils.Const.Companion.USER_KEY
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class MyTasksFragment : MvpAppCompatFragment(), MyTasksView {

    private val presenter by moxyPresenter { MyTasksPresenter() }

    private var bindingNull: FragmentMyTasksBinding? = null
    private val binding
        get() = bindingNull!!

    private lateinit var pref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingNull = FragmentMyTasksBinding.inflate(inflater, container, false)

        //set argument data in SharedPreferences
        setPref()
        //set user and task to TextView
        setUserTodo()

        binding.btnRandomTodo.setOnClickListener { presenter.getRandomTodo() }

        return binding.root
    }

    private fun setUserTodo() {
        pref.getString(USER_KEY, null)?.let {
            binding.tvUserName.text = it
            binding.tvUserName.visibility = View.VISIBLE
        }

        pref.getString(TODO_KEY, null)?.let {
            binding.tvTodoName.text = it
            binding.tvTodoName.visibility = View.VISIBLE
        }
    }

    private fun setPref() {
        context?.let {
            pref = it.getSharedPreferences(APP_PREFERENCES_KEY, Context.MODE_PRIVATE)
            arguments?.let {arg ->
                if (arg.getString("userName") != null){
                    pref.edit()
                        .putString(USER_KEY, MyTasksFragmentArgs.fromBundle(arg).userName).apply()
                }
                if (arg.getString("todoName") != null) {
                    pref.edit()
                        .putString(TODO_KEY, MyTasksFragmentArgs.fromBundle(arg).todoName).apply()
                }
            }
        }
    }

    override fun onDestroyView() {
        bindingNull = null
        super.onDestroyView()
    }

    override fun navigateToColleagueTasks(colleagueId: Int) {
        NavHostFragment
            .findNavController(this)
            .navigate(
                MyTasksFragmentDirections.actionMyTasksFragmentToColleagueTasksFragment(colleagueId)
            )
    }
}