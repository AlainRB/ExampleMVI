package cu.xetid.examplemvi.data.api

import cu.xetid.examplemvi.data.model.TodoTask

interface RestApi {
    suspend fun getTodoTasks(): List<TodoTask>
}