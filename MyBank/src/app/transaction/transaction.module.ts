import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TransactionRoutingModule } from './transaction-routing.module';
import { RegisterTransactionComponent } from './register-transaction/register-transaction.component';
import { DisplayTransactionComponent } from './display-transaction/display-transaction.component';
import { ReactiveFormsModule } from '@angular/forms';
import { TransactionComponent } from './transaction.component';


@NgModule({
  declarations: [
    RegisterTransactionComponent,
    DisplayTransactionComponent,
    TransactionComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    TransactionRoutingModule
  ]
})
export class TransactionModule { }
