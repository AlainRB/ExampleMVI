package cu.xetid.examplemvi.data.api

import cu.xetid.examplemvi.data.model.TodoTask

class RestApiImpl(private val restApiService: RestApiService) : RestApi {

    override suspend fun getTodoTasks(): List<TodoTask> {
        return restApiService.listTodo()
    }


}