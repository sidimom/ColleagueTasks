package com.example.colleaguetasks.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.example.colleaguetasks.databinding.FragmentColleagueTasksBinding
import com.example.colleaguetasks.presentation.presenter.ColleagueTasksPresenter
import com.example.colleaguetasks.presentation.view.ColleagueTasksView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ColleagueTasksFragment : MvpAppCompatFragment(), ColleagueTasksView {

    private val presenter by moxyPresenter { ColleagueTasksPresenter() }

    private var bindingNull: FragmentColleagueTasksBinding? = null
    private val binding
        get() = bindingNull!!

    private var colleagueId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingNull = FragmentColleagueTasksBinding.inflate(
            inflater,
            container,
            false)

        getArgColleagueId()
        presenter.viewIsReady(colleagueId)

        return binding.root
    }

    private fun getArgColleagueId() {
        colleagueId = ColleagueTasksFragmentArgs.fromBundle(requireArguments()).colleagueId
    }

    override fun onDestroyView() {
        bindingNull = null
        super.onDestroyView()
    }

    override fun setUser(userName: String) {
        binding.tvUserName.text = userName
    }

    override fun setTodo1(todoId: Int, todoName: String) {
        binding.btnTodo1.text = todoName
        binding.btnTodo1.setOnClickListener { navigateToMainFragment(todoName) }
    }

    override fun setTodo2(todoId: Int, todoName: String) {
        binding.btnTodo2.text = todoName
        binding.btnTodo2.setOnClickListener { navigateToMainFragment(todoName) }
    }

    private fun navigateToMainFragment(todoName: String) {
        NavHostFragment
            .findNavController(this)
            .navigate(
                ColleagueTasksFragmentDirections.actionColleagueTasksFragmentToMyTasksFragment(
                    binding.tvUserName.text.toString(),
                    todoName
                )
            )
    }

    override fun showLoading() {
        binding.btnTodo1.isEnabled = false
        binding.btnTodo2.isEnabled = false
        binding.pbCircular.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.btnTodo1.isEnabled = true
        binding.btnTodo2.isEnabled = true
        binding.pbCircular.visibility = View.GONE
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }
}