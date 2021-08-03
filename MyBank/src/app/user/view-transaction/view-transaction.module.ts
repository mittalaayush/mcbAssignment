import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ViewtransactionRoutingModule } from './view-transaction-routing.module';
import { ViewTransactionComponent } from './view-transaction.component';
import { ListComponent } from './list.component';
import { FormsModule } from '@angular/forms';
import { NgbdSortableHeader} from './list.component';
@NgModule({
    imports: [
        CommonModule,
        ReactiveFormsModule,
        ViewtransactionRoutingModule,
        NgbModule,
        FormsModule
    ],
    declarations: [
        ViewTransactionComponent,
        ListComponent,
        NgbdSortableHeader
    ],
    exports:[ListComponent],
    bootstrap:[ListComponent]
    
})
export class ViewtransactionModule { }