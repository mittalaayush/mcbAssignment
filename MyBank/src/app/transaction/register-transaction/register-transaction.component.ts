import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AccountService } from 'src/app/services/account.service';
import { AlertService } from 'src/app/services/alert.service';
import { first } from 'rxjs/operators';

import * as customerinfo from '../../customer.json';

@Component({
  selector: 'app-register-transaction',
  templateUrl: './register-transaction.component.html',
  styleUrls: ['./register-transaction.component.scss']
})
export class RegisterTransactionComponent implements OnInit {
  form: FormGroup;
  loading = false;
  submitted = false;
  regionList: any=['Port Louis','Curepipe','Vacoas','Port Mathurin']
  currencyList:any=['AED','EUR','CHF','MUR','USD']
  
  constructor(
      private formBuilder: FormBuilder,
      private route: ActivatedRoute,
      private router: Router,
      private accountService: AccountService,
      private alertService: AlertService
  ) { }

  ngOnInit() {
      
      this.form = this.formBuilder.group({
          transactionType: ['', Validators.required],
          customerNumber: ['', Validators.required],
          customerAddress: ['', Validators.required],
          customerPhoneNumber: ['', Validators.required],
          amount: ['', Validators.required],
          currency: ['', Validators.required],
          beneficiaryBank: ['', Validators.required],
          beneficiaryACNumber: ['', Validators.required],
          paymentDetails: ['', Validators.required],
          cardDetail: ['', Validators.required],
          region: ['', Validators.required],
          customerName: ['', [Validators.required]]
      });
      
  }
  customer:any=null
  // convenience getter for easy access to form fields
  get f() { return this.form.controls; }
  customeDetailData(inputData:string){
      var products= (customerinfo as any).default;
      this.customer=products.responseXML.getCustomerInfoResponse.getCustomerInfoResult.CUST_INFO;
      
      
      if(this.customer.CUST_NO==inputData){
          console.log("user found");
          this.form.patchValue({
              customerName:this.customer.SHORT_NAME,
              customerAddress:this.customer.STREET_ADDR,
              customerPhoneNumber:this.customer.CONTACT_INFO_V7.CONTACT_INFO_V7.PHONE_LIST_V7.PHONE_LIST_ITEM_V7.PHONE
          })  
          }else{
              console.log("user not found");
              this.form.patchValue({
                  customerName:'',
                  customerAddress:'',
                  customerPhoneNumber:''
              }) 
          }
  }

  onSubmit() {
      this.submitted = true;

      // reset alerts on submit
      this.alertService.clear();

      // stop here if form is invalid
      if (this.form.invalid) {
          return;
      }

      this.loading = true;
      this.accountService.createTransaction(this.form.value)
          .pipe(first())
          .subscribe(
              data => {
                  this.alertService.success('Transaction successfully submited', { keepAfterRouteChange: true });
                  this.router.navigate(['/transaction/display', { relativeTo: this.route }]);
              },
              error => {
                  this.alertService.error(error);
                  this.loading = false;
              });
  }
}
