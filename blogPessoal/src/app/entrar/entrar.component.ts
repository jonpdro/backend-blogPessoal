import { environment } from './../../environments/environment.prod';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserLogin } from '../model/userLogin';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-entrar',
  templateUrl: './entrar.component.html',
  styleUrls: ['./entrar.component.css']
})
export class EntrarComponent implements OnInit {

  userLogin: UserLogin = new UserLogin()

  constructor(
    private auth: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  entrar() {
    this.auth.entrar(this.userLogin).subscribe((resp: UserLogin)=>{
      this.userLogin = resp

      environment.id = this.userLogin.id
      environment.nome = this.userLogin.nome
      environment.username = this.userLogin.username
      environment.token = this.userLogin.token
      environment.email = this.userLogin.email
      environment.admin = this.userLogin.admin
      environment.fotoPerfil = this.userLogin.fotoPerfil

      this.router.navigate(['/home'])
    }, erro =>{
      if(erro.status == 500){
        alert('Usuário ou senha estão incorretos!')
      }
    })
  }

}
