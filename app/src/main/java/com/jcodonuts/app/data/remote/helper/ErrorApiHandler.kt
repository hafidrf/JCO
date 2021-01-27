package com.jcodonuts.app.data.remote.helper

import com.google.gson.Gson
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException

object ErrorApiHandler {

    /**
     * Handle error from http code and wrap to single object Failure
     * @param e throwable
     * @return failure
     */
    fun handleErrorOnNext(e: Throwable): Failure {
        return if (e is HttpException) {
            val responseBody = e.response()!!.errorBody()
            Failure(e.code(), getErrorMessage(responseBody))
        } else {
            Failure(StatusCode.UNKNOWN_ERROR, "Something wrong")
        }
    }

    /**
     * get error message from API
     * @param responseBody responseBody
     * @return error message
     */
    private fun getErrorMessage(responseBody: ResponseBody?): String {
        return try {
            val errorModel = Gson().fromJson(responseBody?.string(), ErrorModel::class.java)
            errorModel.error
//            val jsonObject = JSONObject(responseBody?.string()?:"")
//            jsonObject.getString("status_message")
        } catch (e: Exception) {
            e.message ?: "Something wrong"
        }
    }
}
