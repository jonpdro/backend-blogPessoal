import { User } from './../model/user';
import { TemaService } from 'src/app/service/tema.service';
import { Postagem } from './../model/postagem';
import { environment } from 'src/environments/environment.prod';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { PostagemService } from '../service/postagem.service';
import { Tema } from '../model/tema';
import { AuthService } from '../service/auth.service';
import { AlertasService } from '../service/alertas.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  postagem: Postagem = new Postagem()
  listarPostagem: Postagem[]

  tema: Tema
  listarTemas: Tema[]
  idTema: number

  user: User = new User()
  fotoUser = environment.fotoPerfil
  idUser = environment.id


  constructor(
    private router: Router,
    private postagemService: PostagemService,
    private temaService: TemaService,
    public authService: AuthService,
    private alertas: AlertasService
  ) { }

  ngOnInit() {
    if (environment.token == '') {
      this.router.navigate(['/entrar'])
    }

    this.getAllPostagens()
    this.getAllTemas()
  }

  getAllPostagens() {
    this.postagemService.getAllPostagens().subscribe((resp: Postagem[]) => {
      this.listarPostagem = resp
    })
  }

  getAllTemas() {
    this.temaService.getAllTema().subscribe((resp: Tema[]) => {
      this.listarTemas = resp
    })
  }

  findByIdTema() {
    this.temaService.getByIdTema(this.idTema).subscribe((resp: Tema) => {
      this.tema = resp
    })
  }

  publicar() {
    this.tema.id = this.idTema
    this.postagem.tema = this.tema

    this.user.id = this.idUser
    this.postagem.user = this.user

    let novaRota = '/home'

    this.postagemService.postPostagem(this.postagem).subscribe((resp: Postagem) => {
      this.postagem = resp
      this.alertas.showAlertSucess('Postagem realizada com sucesso!')
      this.postagem = new Postagem()
      this.router.navigate(['/']).then(() => { this.router.navigate([novaRota]); })
    })

  }

  sair() {
    this.router.navigate(['/entrar'])
    environment.id = 0
    environment.nome = ''
    environment.username = ''
    environment.token = ''
    environment.email = ''
    environment.admin = ''
    environment.fotoPerfil = ''
  }
}
