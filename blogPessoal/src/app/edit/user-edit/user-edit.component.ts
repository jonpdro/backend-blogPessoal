import { AlertasService } from './../../service/alertas.service';
import { environment } from 'src/environments/environment.prod';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from './../../model/user';
import { AuthService } from './../../service/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {

  user: User = new User()
  idUser: number
  confirmarSenha: string

  constructor(
    private authService: AuthService,
    private route: ActivatedRoute,
    private router: Router,
    private alert: AlertasService
  ) { }

  ngOnInit() {
    if (environment.token == '') {
      this.router.navigate(['/entrar'])
    }

    this.idUser = this.route.snapshot.params['id']
    this.findByIdUser(this.idUser)
  }

  findByIdUser(id: number) {
    this.authService.getByIdUser(id).subscribe((resp: User) => {
      this.user = resp
    })
  }

  confirmaSenha(event: any) {
    this.confirmarSenha = event.target.value
  }

  atualizar() {
    if (this.user.senha != this.confirmarSenha) {
      this.alert.showAlertWarning('As senhas estão incorretas!')
    } else {
      this.authService.atualizar(this.user).subscribe((resp: User) => {
        this.user = resp
        this.router.navigate(['/home'])
        this.alert.showAlertSucess('O usuário foi atualizado com sucesso! Faça login novamente.')

        environment.id = 0
        environment.nome = ''
        environment.username = ''
        environment.token = ''
        environment.email = ''
        environment.admin = ''
        environment.fotoPerfil = ''
        environment.fotoCapa = ''

        this.router.navigate(['/entrar'])
      })
    }

  }

}
