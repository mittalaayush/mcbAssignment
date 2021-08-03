import { Component } from '@angular/core';
import { User } from './models';
import { AccountService } from './services/account.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  user: User;

  ngOnInit(){
  this.user = {
          id: '1',
          username: 'aayush',
          password: 'ds',
          firstName: 'Aayush',
          lastName: 'Mittal',
          token: "dsdd",
          userType: "Yes"
      }
  }

  constructor(private accountService: AccountService) {
      this.accountService.user.subscribe(x => this.user = x);
  }

  logout() {
      this.accountService.logout();
  }
}
