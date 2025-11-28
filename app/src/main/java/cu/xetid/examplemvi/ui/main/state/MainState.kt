package cu.xetid.examplemvi.ui.main.state

import cu.xetid.examplemvi.data.model.TodoTask

sealed class MainState {
    object Inactive : MainState()
    object Loading : MainState()
    data class LoadTasks(val todoTasks: List<TodoTask>) : MainState()
    data class Error(val message: String?) : MainState()
}