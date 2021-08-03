import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './account/auth.guard';
import { HomeComponent } from './home/home.component';


const routes: Routes = [
  {  path: '', component: HomeComponent},
  { path: 'account', loadChildren: () => import('./account/account.module').then(x => x.AccountModule) },
  { path: 'home', component: HomeComponent},
  { path: 'user', loadChildren: () => import('./user/user.module').then(x => x.UserModule),
   canActivate:[AuthGuard]  },
  { path: 'transaction', loadChildren: () => import('./transaction/transaction.module').then(x => x.TransactionModule),
  canActivate:[AuthGuard]  },
  { path: 'mytransaction', loadChildren: () => import('./user/view-transaction/view-transaction.module').then(x => x.ViewtransactionModule),
   canActivate:[AuthGuard]  },
  //  { path: 'mytransaction', loadChildren: () => import('./viewtransaction/viewtransaction.module').then(x => x.ViewtransactionModule),canActivate:[AuthGuard]  },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 
}
