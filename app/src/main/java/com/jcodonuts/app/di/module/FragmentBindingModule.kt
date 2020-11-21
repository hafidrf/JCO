package com.jcodonuts.app.di.module

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.jcodonuts.app.di.module.factory.InjectingFragmentFactory
import com.jcodonuts.app.di.scope.FragmentKey
import com.jcodonuts.app.ui.zzexample.article.ArticleFragment
import com.jcodonuts.app.ui.zzexample.detail.DetailFragment
import com.jcodonuts.app.ui.auth.forgot_password.ForgotPasswordFragment
import com.jcodonuts.app.ui.auth.login.LoginFragment
import com.jcodonuts.app.ui.main.base.MainFragment
import com.jcodonuts.app.ui.main.cart.CartFragment
import com.jcodonuts.app.ui.main.home.HomeFragment
import com.jcodonuts.app.ui.main.notification.NotificationFragment
import com.jcodonuts.app.ui.main.profile.ProfileFragment
import com.jcodonuts.app.ui.auth.register.RegisterFragment
import com.jcodonuts.app.ui.payment.linkaja.LinkajaFragment
import com.jcodonuts.app.ui.splash.SplashFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FragmentBindingModule {

    @Binds
    @IntoMap
    @FragmentKey(SplashFragment::class)
    abstract fun bindSplashFragment(splashFragment: SplashFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(MainFragment::class)
    abstract fun bindMainFragment(mainFragment: MainFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(HomeFragment::class)
    abstract fun bindHomeFragment(homeFragment: HomeFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(CartFragment::class)
    abstract fun bindCartFragment(cartFragment: CartFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(NotificationFragment::class)
    abstract fun bindNotificationFragment(notificationFragment: NotificationFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(ProfileFragment::class)
    abstract fun bindProfileFragment(profileFragment: ProfileFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(LoginFragment::class)
    abstract fun bindLoginFragment(fragment: LoginFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(ForgotPasswordFragment::class)
    abstract fun bindForgotPasswordFragment(fragment: ForgotPasswordFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(RegisterFragment::class)
    abstract fun bindRegisterFragment(fragment: RegisterFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(LinkajaFragment::class)
    abstract fun bindLinkajaFragment(fragment: LinkajaFragment): Fragment









    @Binds
    @IntoMap
    @FragmentKey(ArticleFragment::class)
    abstract fun bindArticleFragment(articleFragment: ArticleFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(DetailFragment::class)
    abstract fun bindDetailFragment(detailFragment: DetailFragment): Fragment

    @Binds
    abstract fun bindFragmentFactory(factory: InjectingFragmentFactory): FragmentFactory
}