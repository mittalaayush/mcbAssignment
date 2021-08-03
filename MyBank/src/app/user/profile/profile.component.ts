import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models';
import { AccountService } from 'src/app/services/account.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  user: User;

    constructor(private accountService: AccountService) {
        this.user = this.accountService.userValue;
    }

  ngOnInit(): void {
  }

}
