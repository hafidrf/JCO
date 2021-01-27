package com.jcodonuts.app.data.repository


import com.jcodonuts.app.data.remote.api.JcoUserApi
import com.jcodonuts.app.data.remote.helper.ErrorNetworkHandler
import com.jcodonuts.app.data.remote.model.req.LoginReq
import com.jcodonuts.app.data.remote.model.req.RegisterReq
import com.jcodonuts.app.data.remote.model.res.LoginRes
import com.jcodonuts.app.data.remote.model.res.RegisterRes
import io.reactivex.Single
import javax.inject.Inject

interface AuthRepository {
    fun login(body:LoginReq): Single<LoginRes>
    fun register(body:RegisterReq): Single<RegisterRes>
}

class AuthRepositoryImpl @Inject constructor(
    private val service: JcoUserApi
): AuthRepository, BaseRepository() {

    override fun login(body: LoginReq): Single<LoginRes> {
        return composeSingle { service.login(body) }
            .compose(ErrorNetworkHandler())
    }

    override fun register(body: RegisterReq): Single<RegisterRes> {
        return composeSingle { service.register(body) }
            .compose(ErrorNetworkHandler())
    }
}