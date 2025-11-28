package cu.xetid.examplemvi.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.R
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import cu.xetid.examplemvi.data.api.RestApiImpl
import cu.xetid.examplemvi.data.api.RetrofitBuilder
import cu.xetid.examplemvi.data.model.TodoTask
import cu.xetid.examplemvi.databinding.ActivityMainBinding
import cu.xetid.examplemvi.ui.main.intent.MainIntent
import cu.xetid.examplemvi.ui.main.state.MainState
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModels {
        MainViewModelFactory(
            RestApiImpl(RetrofitBuilder.apiService)
        )
    }
    private var mainAdapter = MainAdapter(listOf())
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
                ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
                    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                    insets
                }

        setupUI()
        setupClicks()
        observeViewModel()
    }

    private fun setupClicks() {

        binding.buttonTasks.setOnClickListener {
            lifecycleScope.launch {
                mainActivityViewModel.userIntent.send(MainIntent.FetchTodoTasks)
            }
        }
    }

    private fun setupUI() {
        binding.recyclerviewTasks.layoutManager = LinearLayoutManager(this)

        binding.recyclerviewTasks.run {
            adapter = mainAdapter
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            mainActivityViewModel.mainState.collect { mainState ->
                when (mainState) {
                    is MainState.Loading -> binding.progressbar.visibility = View.VISIBLE

                    is MainState.LoadTasks -> {
                        binding.progressbar.visibility = View.GONE
                        renderList(mainState.todoTasks)
                    }

                    is MainState.Error -> {
                        binding.recyclerviewTasks.visibility = View.GONE
                        Toast.makeText(
                            applicationContext,
                            "Error: ${mainState.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    MainState.Inactive -> {
                        binding.recyclerviewTasks.visibility = View.VISIBLE
                        binding.progressbar.visibility = View.GONE
                        Toast.makeText(
                            applicationContext,
                            "Inactive",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun renderList(listTodoTask: List<TodoTask>) {
        mainAdapter.setTodoTasks(listTodoTask)
        mainAdapter.notifyDataSetChanged()
    }
}