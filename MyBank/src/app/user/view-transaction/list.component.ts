import { Component, OnInit,Directive, EventEmitter, Input, Output, QueryList, ViewChildren } from '@angular/core';
import { first } from 'rxjs/operators';
import { AccountService } from '../../services/account.service';
import { Transaction } from '../../models';

export type SortColumn = keyof Transaction | '';
export type SortDirection = 'asc' | 'desc' | '';
const rotate: {[key: string]: SortDirection} = { 'asc': 'desc', 'desc': '', '': 'asc' };

const compare = (v1: string | number, v2: string | number) => v1 < v2 ? -1 : v1 > v2 ? 1 : 0;

export interface SortEvent {
  column: SortColumn;
  direction: SortDirection;
}

@Directive({
  selector: 'th[sortable]',
  host: {
    '[class.asc]': 'direction === "asc"',
    '[class.desc]': 'direction === "desc"',
    '(click)': 'rotate()'
  }
})
export class NgbdSortableHeader {

  @Input() sortable: SortColumn = '';
  @Input() direction: SortDirection = '';
  @Output() sort = new EventEmitter<SortEvent>();
  
  rotate() {
    this.direction = rotate[this.direction];
    this.sort.emit({column: this.sortable, direction: this.direction});
  }
}

@Component({ templateUrl: 'list.component.html' })
export class ListComponent implements OnInit {
    transactions:Transaction[] = null;
    page = 1;
  pageSize = 4;
  collectionSize;
  viewTransaction:Transaction[]

    constructor(private accountService: AccountService) {}

    ngOnInit() {
        this.accountService.getAllTransaction()
            .pipe(first())
            .subscribe(transactions => {
                this.transactions = transactions;
                this.collectionSize=this.transactions.length;
                console.log("value of collection is "+this.transactions.length);
                //this.viewTransaction=this.transactions;
                this.refreshTxn();
            }
                );
                
                
    }

    refreshTxn() {
       
        this.viewTransaction = this.transactions
          .map((txn, i) => ({id: i + 1, ...txn}))
          .slice((this.page - 1) * this.pageSize, (this.page - 1) * this.pageSize + this.pageSize);
      }
      @ViewChildren(NgbdSortableHeader) headers: QueryList<NgbdSortableHeader>;

      onSort({column, direction}: SortEvent) {
    
        // resetting other headers
        this.headers.forEach(header => {
          if (header.sortable !== column) {
            header.direction = '';
          }
        });
    
        // sorting countries
        if (direction === '' || column === '') {
            this.viewTransaction = this.transactions;
        } else {
          this.viewTransaction = [...this.transactions].sort((a, b) => {
            const res = compare(a[column], b[column]);
            return direction === 'asc' ? res : -res;
          });
        }
      }


    
}

