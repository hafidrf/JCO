package com.jcodonuts.app.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jcodonuts.app.di.module.factory.ViewModelFactory
import com.jcodonuts.app.di.scope.ApplicationScope
import com.jcodonuts.app.di.scope.ViewModelKey
import com.jcodonuts.app.ui.article.ArticleViewModel
import com.jcodonuts.app.ui.detail.DetailViewModel
import com.jcodonuts.app.ui.forgot_password.ForgotPasswordViewModel
import com.jcodonuts.app.ui.login.LoginViewModel
import com.jcodonuts.app.ui.main.MainViewModel
import com.jcodonuts.app.ui.mainCart.CartViewModel
import com.jcodonuts.app.ui.mainHome.HomeViewModel
import com.jcodonuts.app.ui.mainNotification.NotificationViewModel
import com.jcodonuts.app.ui.mainProfile.ProfileViewModel
import com.jcodonuts.app.ui.register.RegisterViewModel
import com.jcodonuts.app.ui.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @ApplicationScope
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory):ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    internal abstract fun providesSplashViewModel(viewModel: SplashViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun providesMainViewModel(viewModel: MainViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun providesHomeViewModel(viewModel: HomeViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel::class)
    internal abstract fun providesCartViewModel(viewModel: CartViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NotificationViewModel::class)
    internal abstract fun providesNotificationViewModel(viewModel: NotificationViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    internal abstract fun providesProfileViewModel(viewModel: ProfileViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun providesLoginViewModel(viewModel: LoginViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ForgotPasswordViewModel::class)
    internal abstract fun providesForgotViewModel(viewModel: ForgotPasswordViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    internal abstract fun providesRegisterViewModel(viewModel: RegisterViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ArticleViewModel::class)
    internal abstract fun providesArticleViewModel(viewModel: ArticleViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    internal abstract fun providesDetailViewModel(viewModel: DetailViewModel) : ViewModel

}