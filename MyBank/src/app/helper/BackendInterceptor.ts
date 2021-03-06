import { Injectable } from '@angular/core';
import { HttpRequest, HttpResponse, HttpHandler, HttpEvent, HttpInterceptor, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { delay, mergeMap, materialize, dematerialize } from 'rxjs/operators';

// array in local storage for registered users
let users = JSON.parse(localStorage.getItem('users')) || [];
let transactions = JSON.parse(localStorage.getItem('transactions')) || [];

@Injectable()
export class FakeBackendInterceptor implements HttpInterceptor {
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const { url, method, headers, body } = request;

        // wrap in delayed observable to simulate server api call
        return of(null)
            .pipe(mergeMap(handleRoute))
            .pipe(materialize()) // call materialize and dematerialize to ensure delay even if an error is thrown (https://github.com/Reactive-Extensions/RxJS/issues/648)
            .pipe(delay(500))
            .pipe(dematerialize());

        function handleRoute() {
            switch (true) {
                case url.endsWith('/user/login') && method === 'POST':
                    return login();
                case url.endsWith('/user/register') && method === 'POST':
                    return register();
                case url.endsWith('/transaction/create') && method === 'POST':
                    return createTransaction();
                case url.endsWith('/users') && method === 'GET':
                    return getUsers();
                case url.endsWith('/transaction/all') && method === 'GET':
                    return getAllTransaction();
                case url.match(/\/users\/\d+$/) && method === 'GET':
                    return getUserById();
                case url.match(/\/users\/\d+$/) && method === 'PUT':
                    return updateUser();
                case url.match(/\/users\/\d+$/) && method === 'DELETE':
                    return deleteUser();
                default:
                    // pass through any requests not handled above
                    return next.handle(request);
            }    
        }

        // route functions

        function login() {
            const { username, password } = body;
            // const user = users.find(x => x.username === username && x.password === password);
            // if (!user) return error('Username or password is incorrect');
            return next.handle(request);
            // return ok({
            //     id: user.id,
            //     username: user.username,
            //     firstName: user.firstName,
            //     lastName: user.lastName,
            //     userType:user.userType,
            //     token: 'fake-jwt-token'
            // })
        }

        function register() {
            const user = body

            if (users.find(x => x.username === user.username)) {
                return error('Username "' + user.username + '" is already taken')
            }

            // user.id = users.length ? Math.max(...users.map(x => x.id)) + 1 : 1;
            users.push(user);
            localStorage.setItem('users', JSON.stringify(users));
            return next.handle(request);
            // return ok();
        }
        
        function createTransaction() {
            const transaction = body

            
            if(isNaN(Number(transaction.customerPhoneNumber)))
            {
                return error("Phone Number should be number only");
            }
            if(isNaN(Number(transaction.cardDetail)))
            {
                return error("Card Detail should be number only");
            }
            if(!/^[a-zA-Z]+$/.test(transaction.beneficiaryBank)){
                return error("Beneficiary Bank should contain only letter");
            }
            if(isNaN(Number(transaction.beneficiaryACNumber)))
            {
                return error("Beneficiary Account Number should be number only");
            }
            
            if(!/^[a-zA-Z]+$/.test(transaction.paymentDetails)){
                return error("Special character are not allowed for payment detail");
            }
            

            transaction.reference = getReferenceNumber();
            transactions.push(transaction);
            localStorage.setItem('transactions', JSON.stringify(transactions));
            localStorage.setItem('transaction', JSON.stringify(transaction));
            return ok();
        }
        function getReferenceNumber(){
            var perfix='CUS';
            var date=new Date();
            
            var reference=perfix+'-'+date.getDate()+date.getMonth()+date.getFullYear()+'-'+Math.floor(Math.random() * 100000);
            return reference;
        }

        function getUsers() {
            if (!isLoggedIn()) return unauthorized();
            return ok(users);
        }
        function getAllTransaction() {
            if (!isLoggedIn()) return unauthorized();
            return ok(transactions);
        }

        function getUserById() {
            if (!isLoggedIn()) return unauthorized();

            const user = users.find(x => x.id === idFromUrl());
            return ok(user);
        }

        function updateUser() {
            if (!isLoggedIn()) return unauthorized();

            let params = body;
            let user = users.find(x => x.id === idFromUrl());

            // only update password if entered
            if (!params.password) {
                delete params.password;
            }

            // update and save user
            Object.assign(user, params);
            localStorage.setItem('users', JSON.stringify(users));

            return ok();
        }

        function deleteUser() {
            if (!isLoggedIn()) return unauthorized();

            users = users.filter(x => x.id !== idFromUrl());
            localStorage.setItem('users', JSON.stringify(users));
            return ok();
        }

        // helper functions

        function ok(body?) {
            return of(new HttpResponse({ status: 200, body }))
        }

        function error(message) {
            return throwError({ error: { message } });
        }

        function unauthorized() {
            return throwError({ status: 401, error: { message: 'Unauthorised' } });
        }

        function isLoggedIn() {
            return headers.get('Authorization') === 'Bearer fake-jwt-token';
        }

        function idFromUrl() {
            const urlParts = url.split('/');
            return parseInt(urlParts[urlParts.length - 1]);
        }
    }
}

export const BackendProvider = {
    // use fake backend in place of Http service for backend-less development
    provide: HTTP_INTERCEPTORS,
    useClass: FakeBackendInterceptor,
    multi: true
};