package com.jcodonuts.app.data.repository


import com.jcodonuts.app.data.remote.api.JcoUserApi
import com.jcodonuts.app.data.remote.helper.ErrorNetworkHandler
import com.jcodonuts.app.data.remote.model.req.ChangePsswordReq
import com.jcodonuts.app.data.remote.model.req.LoginReq
import com.jcodonuts.app.data.remote.model.req.RegisterReq
import com.jcodonuts.app.data.remote.model.res.LoginRes
import com.jcodonuts.app.data.remote.model.res.RegisterRes
import com.jcodonuts.app.data.remote.model.res.SuccessRes
import io.reactivex.Single
import javax.inject.Inject

interface AuthRepository {
    fun login(body:LoginReq): Single<LoginRes>
    fun register(body:RegisterReq): Single<RegisterRes>
    fun logout(): Single<SuccessRes>
    fun changePassword(body:ChangePsswordReq): Single<SuccessRes>
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

    override fun logout(): Single<SuccessRes> {
        return composeSingle { service.logout() }
                .compose(ErrorNetworkHandler())
    }

    override fun changePassword(body: ChangePsswordReq): Single<SuccessRes> {
        return composeSingle { service.changePassword(body) }
            .compose(ErrorNetworkHandler())
    }
}