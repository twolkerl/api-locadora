# API Locadora

API para locadoras. A API permite a criação de
usuários (clientes), listagem de filmes disponíveis, locação de
um filme, devolução de um filme, pesquisa de filme pelo título e outras funções.

### Banco de Dados Utilizado:

- MySQL

## Métodos disponíveis na API:

### Criar novo usuário

#### Descrição:

Este método permite a criação de um novo usuário(cliente).

#### Especificações:

Método:  `POST`

Endpoint:  `localhost:8080/usuarios`

Retorno:  `Retornará o objeto do novo usuário (não mostrando a senha) com seu ID gerado.`

JSON Exemplo: 

```json
{
	"email" : "john@teste.com",
	"senha" : "teste123",
	"nomeCompleto": "John Teste"
}
```

---

### Exlcuir usuário

#### Descrição:

Este método permite excluir um usuário(cliente) que não esteja com uma locação de filmes em andamento.

#### Especificações:

Método:  `DELETE`

Endpoint:  `localhost:8080/usuarios`

---

### Listar todos usuários

#### Descrição:

Método para listagem de usuários.

#### Especificações:

Método:  `GET`

Endpoint:  `localhost:8080/usuarios`

Retorno:  `Retornará uma listagem de usuários, omitindo o campo de senha.`

---

### Pesquisar usuário por filtro

#### Descrição:

Método para pesquisar usuários através dos filtros informados.

#### Especificações:

Método:  `GET`

Endpoint:  `localhost:8080/usuarios/filtro`

Retorno:  `Retornará uma listagem de usuários através dos filtros informados, omitindo o campo de senha.`

Query Params: 

|  Tipo | Nome  |
| ------------ | ------------ |
|  Long | idUsuario  |
|  String | nomeCompleto  |
|  String | email  |

---

### Salvar novo filme

#### Descrição:

Este método permite o cadastro de um novo filme.

#### Especificações:

Método:  `POST`

Endpoint:  `localhost:8080/filmes`

Retorno:  `Retornará o objeto do novo filme com seu ID gerado.`

JSON Exemplo: 

```json
{
	"titulo" : "Star Wars, Episódio V: O Império Contra-Ataca",
	"diretor" : "Irvin Kershner"
}
```

---

### Exlcuir filme

#### Descrição:

Este método permite excluir um filme que não esteja alugado.

#### Especificações:

Método:  `DELETE`

Endpoint:  `localhost:8080/filmes`

---

### Listar todos filmes

#### Descrição:

Método para listagem de filmes.

#### Especificações:

Método:  `GET`

Endpoint:  `localhost:8080/filmes`

Retorno:  `Retornará uma listagem contendo todos os filmes e seus dados.`

---

### Pesquisar filmes por filtro

#### Descrição:

Método para pesquisar filmes através dos filtros informados. Os filtros são opcionais, portanto se for informado apenas o "título" por exemplo, o critério da busca será feito apenas por este.

#### Especificações:

Método:  `GET`

Endpoint:  `localhost:8080/filmes/filtro`

Retorno:  `Retornará uma listagem de filmes através dos filtros informados.`

Query Params: 

|  Tipo | Nome  |
| ------------ | ------------ |
|  Long | idFilme  |
|  String | titulo  |
|  String | diretor  |

---


### Salvar cópia de um filme no estoque

#### Descrição:

Este método permite o cadastro de uma nova cópia de um filme em estoque, sendo necessário informar apenas o identificador do filme.

#### Especificações:

Método:  `POST`

Endpoint:  `localhost:8080/inventario`

Retorno:  `Retornará o objeto da cópia do filme salvo com seu ID gerado.`

JSON Exemplo: 

```json
{
	"idFilme" : 1
}
```

---

### Pesquisar cópias do filme pelo ID do filme

#### Descrição:

Método para pesquisar cópias do filme em estoque através do ID do filme.

#### Especificações:

Método:  `GET`

Endpoint:  `localhost:8080/inventario/{idFilme}`

Retorno:  `Retornará uma listagem das cópias em estoque correspondentes ao ID do filme informado.`

Path Variable:  `idFilme`

---

### Alugar filmes

#### Descrição:

Método para realizar a locação de um ou mais filmes. Será necessário informar os IDs dos filmes (um ou mais) e ID do usuário que está realizando a locação.

#### Especificações:

Método:  `PUT`

Endpoint:  `localhost:8080/inventario/alugar`

Retorno: ` Retornará uma listagem das cópias dos filmes que foram alugados ao cliente.`

 JSON Exemplo:  

```json
{
	"idsFilme" : [1,2,3],
	"idUsuario" : 1
}
```

---

### Receber filmes que estavam alugados

#### Descrição:

Método para realizar a devolução de todos os filmes que estavam alugados para o usuário informado.

#### Especificações:

Método:  `PUT`

Endpoint:  `localhost:8080/inventario/receber-todos`

Query Params: 

|  Tipo | Nome  |
| ------------ | ------------ |
|  Long | idUsuario  |

---

### Receber UM filme que estava alugado

#### Descrição:

Método para realizar a devolução de um filme que estava alugado para o usuário informado.

#### Especificações:

Método:  `PUT`

Endpoint:  `localhost:8080/inventario/receber`

Query Params: 

|  Tipo | Nome  |
| ------------ | ------------ |
|  Long | idUsuario  |
|  Long | idFilme  |

---

### Obter quantidade de filmes em estoque

#### Descrição:

Método para obter a quantidade em estoque do filme informado.

#### Especificações:

Método:  `GET`

Endpoint:  `localhost:8080/inventario/qtd-filme`

Retorno:  `Retornará a quantidade em estoque do filme de id informado.`

Query Params: 

|  Tipo | Nome  |
| ------------ | ------------ |
|  Long | idFilme  |

---

### Obter quantidade de filmes disponíveis em estoque

#### Descrição:

Método para obter a quantidade disponível (não alugados) em estoque do filme informado.

#### Especificações:

Método:  `GET`

Endpoint:  `localhost:8080/inventario/qtd-filme-disponivel`

Retorno:  `Retornará a quantidade disponível (não alugados) em estoque do filme de id informado para locação.`

Query Params: 

|  Tipo | Nome  |
| ------------ | ------------ |
|  Long | idFilme  |

---


### Excluir cópia do filme em estoque

#### Descrição:

Método para excluir uma cópia em estoque do filme (não pode estar alugado) informado.

#### Especificações:

Método: `DELETE`

Endpoint:  `localhost:8080/inventario`