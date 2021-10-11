import { AlertasService } from './../service/alertas.service';
import { AuthService } from './../service/auth.service';
import { User } from './../model/user';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cadastrar',
  templateUrl: './cadastrar.component.html',
  styleUrls: ['./cadastrar.component.css']
})
export class CadastrarComponent implements OnInit {

  user: User = new User()
  confirmarSenha: string
  tipoUsuario: string

  constructor(
    private authService: AuthService,
    private router: Router,
    private alert: AlertasService
  ) { }

  ngOnInit() {
    window.scroll(0, 0)

  }

  confirmSenha(event: any) {
    this.confirmarSenha = event.target.value
  }

  tipoUser(event: any) {
    this.tipoUsuario = event.target.value
  }

  cadastrar() {
    if (this.tipoUsuario == "admin") {
      this.user.admin = "adm"
    } else {
      this.user.admin = "user"
    }

    if (this.user.senha != this.confirmarSenha) {
      this.alert.showAlertDanger('As senhas estão incorretas!')
    } else {
      this.authService.cadastrar(this.user).subscribe((resp: User) => {
        this.user = resp

        this.router.navigate(['/entrar'])

        this.alert.showAlertSucess('O usuário foi cadastrado com sucesso!')
      })
    }
  }
}

