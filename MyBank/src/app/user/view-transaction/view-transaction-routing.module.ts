import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ViewTransactionComponent } from './view-transaction.component';
import { ListComponent } from './list.component';


const routes: Routes = [
    {
        path: '', component: ViewTransactionComponent,
        children: [
            { path: '', component: ListComponent }
        ]
    }
];


@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ViewtransactionRoutingModule { }