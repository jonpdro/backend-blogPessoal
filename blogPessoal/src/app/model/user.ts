import { Postagem } from './postagem';

export class User {
    public id: number
    public nome: string
    public username: string
    public email: string
    public senha: string
    public dataNascimento: Date
    public fotoPerfil: string
    public fotoCapa: string
    public admin: string
    public postagem: Postagem[]
}