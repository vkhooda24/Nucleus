package com.vkhooda24.nucleus

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vkhooda24.nucleus.R
import kotlinx.coroutines.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GlobalScope.launch {
            launch {
                delay(500)
                println("Launch 1")
            }

            launch {
                delay(1000)
                println("Launch 2")
            }
//            withContext(Dispatchers.Default) {
//                delay(8000)
//                println("With context")
//            }

            println(withContext(Dispatchers.Unconfined) {
                delay(8000)
                "With context output"
            })

            val output = async {
                "Async output"
            }.await()
            println(output)

            launch {
                delay(8000)
                println("Launch 3")
            }

            launch {
                delay(8000)
                println("Launch 4")
            }

            launch {
                println("Launch Start")
            }
        }
    }

}