package cu.xetid.examplemvi.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cu.xetid.examplemvi.data.repository.MainRepository
import cu.xetid.examplemvi.ui.main.state.MainState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repository: MainRepository) : ViewModel() {

    private val _mainState = MutableStateFlow<MainState>(MainState.Inactive)

    val mainState: StateFlow<MainState>
        get() = _mainState

    fun onFetchTodoTasks() {
        viewModelScope.launch {
            _mainState.value = MainState.Loading

            _mainState.value = try {
                MainState.LoadTasks(repository.getTodoTasks())
            } catch (e: Exception) {
                MainState.Error(e.message)
            }
        }
    }
    // Si tuvieras más intenciones, añadirías más funciones:
    // fun onAddTask(task: TodoTask) { ... }
    // fun onDeleteTask(taskId: String) { ... }
}