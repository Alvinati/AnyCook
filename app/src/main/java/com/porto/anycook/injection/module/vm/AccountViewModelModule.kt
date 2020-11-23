package com.porto.anycook.injection.module.vm

import androidx.lifecycle.ViewModel
import com.porto.anycook.injection.module.ViewModelModule
import com.porto.anycook.injection.vmf.ViewModelKey
import com.porto.anycook.ui.main.account.AccountViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
interface AccountViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    fun bindAccountViewModel(accountViewModel: AccountViewModel) : ViewModel

}