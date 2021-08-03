import { Component, OnInit } from '@angular/core';
import { Transaction } from 'src/app/models';
import { AccountService } from 'src/app/services/account.service';

@Component({
  selector: 'app-display-transaction',
  templateUrl: './display-transaction.component.html',
  styleUrls: ['./display-transaction.component.scss']
})
export class DisplayTransactionComponent implements OnInit {

  transaction: Transaction;

    constructor(private accountService: AccountService) {
        this.transaction = this.accountService.transactionValue;
    }

  ngOnInit(): void {
  }

}
