package cu.xetid.examplemvi.data.repository

import cu.xetid.examplemvi.data.api.RestApi

class MainRepository(private val restApi: RestApi) {
    suspend fun getTodoTasks() = restApi.getTodoTasks()
}