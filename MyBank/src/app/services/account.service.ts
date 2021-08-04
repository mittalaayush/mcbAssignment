import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { map } from 'rxjs/operators';

import { environment } from 'src/environments/environment';
import { User,Transaction} from '../models';

@Injectable({ providedIn: 'root' })
export class AccountService {
    private userSubject: BehaviorSubject<User>;
    private transactionSubject: BehaviorSubject<Transaction>;
    public user: Observable<User>;
    public transaction:Observable<Transaction>;

    constructor(
        private router: Router,
        private http: HttpClient
    ) {
        this.userSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('user')));
        this.transactionSubject = new BehaviorSubject<Transaction>(JSON.parse(localStorage.getItem('transaction')));
        this.user = this.userSubject.asObservable();
        this.transaction=this.transactionSubject.asObservable();
    }

    public get userValue(): User {
        return this.userSubject.value;
    }
    public get transactionValue(): Transaction {
        return this.transactionSubject.value;
    }

    login(username, password) {
        // return this.http.post<User>(`${environment.apiUrl}/users/authenticate`, { username, password })
        return this.http.post<User>(`${environment.backendUrl}/user/login`, { username, password })            
            .pipe(map(user => {
                if(user.userResponseMessage === 'Success'){
                    user.token = 'fake-jwt-token';
                    console.log("user data "+JSON.stringify(user));
                    localStorage.setItem('user', JSON.stringify(user));
                    this.userSubject.next(user);
                return user;
                } else {
                    localStorage.removeItem('user');
                    this.userSubject.next(null);
                    this.error(user.userResponseMessage);
                    user = {userResponseMessage : user.userResponseMessage};
                    return user;
                }
            }));

    }

    logout() {
        // remove user from local storage and set current user to null
        localStorage.removeItem('user');
        localStorage.removeItem('transaction');
        this.userSubject.next(null);
        this.router.navigate(['/account/login']);
    }

    register(user: User) {
        console.log("creating user "+JSON.stringify(user));
        // return this.http.post(`${environment.apiUrl}/users/register`, user);
        return this.http.post(`${environment.backendUrl}/user/register`, user);
    }

    error(message: String) {
        return throwError({ error: { message } });
    }

    createTransaction(transaction: Transaction) {
        return this.http.post(`${environment.apiUrl}/transaction/create`, transaction);
    }

    getAll() {
        return this.http.get<User[]>(`${environment.apiUrl}/users`);
    }
    getAllTransaction() {
        return this.http.get<Transaction[]>(`${environment.apiUrl}/transaction/all`);
    }

    getById(id: string) {
        return this.http.get<User>(`${environment.apiUrl}/users/${id}`);
    }

    update(id, params) {
        return this.http.put(`${environment.apiUrl}/users/${id}`, params)
            .pipe(map(x => {
                // update stored user if the logged in user updated their own record
                if (id == this.userValue.id) {
                    // update local storage
                    const user = { ...this.userValue, ...params };
                    localStorage.setItem('user', JSON.stringify(user));

                    // publish updated user to subscribers
                    this.userSubject.next(user);
                }
                return x;
            }));
    }

    delete(id: string) {
        return this.http.delete(`${environment.apiUrl}/users/${id}`)
            .pipe(map(x => {
                // auto logout if the logged in user deleted their own record
                if (id == this.userValue.id) {
                    this.logout();
                }
                return x;
            }));
    }
}