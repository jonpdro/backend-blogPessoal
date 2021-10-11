import { Postagem } from './../model/postagem';
import { Observable } from 'rxjs';
import { environment } from './../../environments/environment.prod';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PostagemService {

  constructor(
    private http: HttpClient
  ) { }

  token = {
    headers: new HttpHeaders().set('Authorization', environment.token)
  }

  getAllPostagens(): Observable<Postagem[]> {
    return this.http.get<Postagem[]>('https://blogpessoaljp.herokuapp.com/postagens', this.token)
  }

  getByIdPostagem(id: number): Observable<Postagem> {
    return this.http.get<Postagem>(`https://blogpessoaljp.herokuapp.com/postagens/${id}`, this.token)
  }

  getByTituloPostagem(titulo: string): Observable<Postagem[]> {
    return this.http.get<Postagem[]>(`https://blogpessoaljp.herokuapp.com/postagens/titulo/${titulo}`, this.token)
  }

  postPostagem(postagem: Postagem): Observable<Postagem> {
    return this.http.post<Postagem>('https://blogpessoaljp.herokuapp.com/postagens', postagem, this.token)
  }

  putPostagem(postagem: Postagem): Observable<Postagem> {
    return this.http.put<Postagem>('https://blogpessoaljp.herokuapp.com/postagens', postagem, this.token)
  }

  deletePostagem(id: number) {
    return this.http.delete(`https://blogpessoaljp.herokuapp.com/postagens/${id}`, this.token)
  }
}
