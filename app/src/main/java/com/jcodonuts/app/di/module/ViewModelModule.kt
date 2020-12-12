package com.jcodonuts.app.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jcodonuts.app.di.module.factory.ViewModelFactory
import com.jcodonuts.app.di.scope.ApplicationScope
import com.jcodonuts.app.di.scope.ViewModelKey
import com.jcodonuts.app.ui.zzexample.article.ArticleViewModel
import com.jcodonuts.app.ui.zzexample.detail.DetailViewModel
import com.jcodonuts.app.ui.auth.forgot_password.ForgotPasswordViewModel
import com.jcodonuts.app.ui.auth.login.LoginViewModel
import com.jcodonuts.app.ui.main.base.MainViewModel
import com.jcodonuts.app.ui.main.cart.CartViewModel
import com.jcodonuts.app.ui.main.home.HomeViewModel
import com.jcodonuts.app.ui.main.notification.NotificationViewModel
import com.jcodonuts.app.ui.main.profile.ProfileViewModel
import com.jcodonuts.app.ui.auth.register.RegisterViewModel
import com.jcodonuts.app.ui.payment.linkaja.LinkajaViewModel
import com.jcodonuts.app.ui.payment.topup.TopupViewModel
import com.jcodonuts.app.ui.main.menu_search.MenuSearchViewModel
import com.jcodonuts.app.ui.pickup.PickupViewModel
import com.jcodonuts.app.ui.product_detail.ProductDetailViewModel
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
    @ViewModelKey(LinkajaViewModel::class)
    internal abstract fun providesLinkajaViewModel(viewModel: LinkajaViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TopupViewModel::class)
    internal abstract fun providesTopupViewModel(viewModel: TopupViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductDetailViewModel::class)
    internal abstract fun providesProductDetailViewModel(viewModel: ProductDetailViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PickupViewModel::class)
    internal abstract fun providesPickupViewModel(viewModel: PickupViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MenuSearchViewModel::class)
    internal abstract fun providesMenuSearchViewModel(viewModel: MenuSearchViewModel) : ViewModel








    @Binds
    @IntoMap
    @ViewModelKey(ArticleViewModel::class)
    internal abstract fun providesArticleViewModel(viewModel: ArticleViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    internal abstract fun providesDetailViewModel(viewModel: DetailViewModel) : ViewModel

}