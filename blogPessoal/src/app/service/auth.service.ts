import { UserLogin } from './../model/userLogin';
import { User } from './../model/user';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient
  ) { }

  entrar(userLogin: UserLogin): Observable<UserLogin> {
    return this.http.post<UserLogin>('https://blogpessoaljp.herokuapp.com/usuarios/entrar', userLogin)
  }

  cadastrar(user: User): Observable<User> {
    return this.http.post<User>('https://blogpessoaljp.herokuapp.com/usuarios/cadastrar', user)
  }

  logado() {
    let ok: boolean = false

    if (environment.token != '') {
      ok = true
    }
    
    return ok
  }

  adm() {
    let ok: boolean = false

    if (environment.admin == 'adm') {
      ok = true
    }

    return ok
  }
}
