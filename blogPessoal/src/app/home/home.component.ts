import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  foto = environment.fotoPerfil

  constructor(
    private router: Router
  ) { }

  ngOnInit() {

    if (environment.token == '') {
      // alert('Sua sessão expirou, faça login novamente.')
      this.router.navigate(['/entrar'])
    }

  }

}
