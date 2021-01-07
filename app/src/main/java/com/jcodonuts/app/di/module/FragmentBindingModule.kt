package com.jcodonuts.app.di.module

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.jcodonuts.app.di.module.factory.InjectingFragmentFactory
import com.jcodonuts.app.di.scope.FragmentKey
import com.jcodonuts.app.ui.auth.change_password.ChangePasswordFragment
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
import com.jcodonuts.app.ui.delivery.DeliveryFragment
import com.jcodonuts.app.ui.edit_profile.EditProfileFragment
import com.jcodonuts.app.ui.hot_promo.HotPromoFragment
import com.jcodonuts.app.ui.language.LanguageFragment
import com.jcodonuts.app.ui.payment.linkaja.LinkajaFragment
import com.jcodonuts.app.ui.payment.topup.TopupFragment
import com.jcodonuts.app.ui.main.menu_search.MenuSearchFragment
import com.jcodonuts.app.ui.main.wishlist.WishlistFragment
import com.jcodonuts.app.ui.payment.choose_payment.ChoosePaymentFragment
import com.jcodonuts.app.ui.payment.payment_detail.PaymentDetailFragment
import com.jcodonuts.app.ui.pickup.PickupFragment
import com.jcodonuts.app.ui.product_detail.ProductDetailFragment
import com.jcodonuts.app.ui.splash.SplashFragment
import com.jcodonuts.app.ui.tracking.TrackingFragment
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
    @FragmentKey(WishlistFragment::class)
    abstract fun bindWishlistFragment(fragment: WishlistFragment): Fragment

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
    @FragmentKey(TopupFragment::class)
    abstract fun bindTopupFragment(fragment: TopupFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(ProductDetailFragment::class)
    abstract fun bindProductDetailFragment(fragment: ProductDetailFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(PickupFragment::class)
    abstract fun bindPickupFragment(fragment: PickupFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(DeliveryFragment::class)
    abstract fun bindDeliveryFragment(fragment: DeliveryFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(MenuSearchFragment::class)
    abstract fun bindMenuSearchFragment(fragment: MenuSearchFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(ChoosePaymentFragment::class)
    abstract fun bindChoosePaymentFragment(fragment: ChoosePaymentFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(PaymentDetailFragment::class)
    abstract fun bindPaymentDetailFragment(fragment: PaymentDetailFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(TrackingFragment::class)
    abstract fun bindTrackingFragment(fragment: TrackingFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(HotPromoFragment::class)
    abstract fun bindHotPromoFragment(fragment: HotPromoFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(ChangePasswordFragment::class)
    abstract fun bindChangePasswordFragment(fragment: ChangePasswordFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(EditProfileFragment::class)
    abstract fun bindEditProfileFragment(fragment: EditProfileFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(LanguageFragment::class)
    abstract fun bindLanguageFragment(fragment: LanguageFragment): Fragment









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