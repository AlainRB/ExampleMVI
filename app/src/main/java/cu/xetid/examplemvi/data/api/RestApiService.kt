package cu.xetid.examplemvi.data.api

import cu.xetid.examplemvi.data.model.TodoTask
import retrofit2.http.GET

interface RestApiService {
    @GET("todos")
    suspend fun listTodo(): List<TodoTask>
}