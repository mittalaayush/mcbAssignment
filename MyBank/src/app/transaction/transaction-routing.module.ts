import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DisplayTransactionComponent } from './display-transaction/display-transaction.component';
import { RegisterTransactionComponent } from './register-transaction/register-transaction.component';
import { TransactionComponent } from './transaction.component';


const routes: Routes = [
  {
      path: '', component: TransactionComponent,
      children: [
          { path: '', component: RegisterTransactionComponent },
          { path: 'display', component: DisplayTransactionComponent }
      ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TransactionRoutingModule { }
