import { environment } from 'src/environments/environment.prod';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  foto = environment.fotoPerfil

  constructor(
    private router: Router
  ) { }

  ngOnInit() {
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
