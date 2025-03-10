
# Agendador de notificações

Esse projeto foi desenvolvido para um desafio técnico visando apresentar as habilidades de desenvolverdor Java backend e tem como escopo o cadastro de notificações para posterior envio.


## Rodando localmente

Clone o projeto

```bash
  git clone https://link-para-o-projeto
```

Entre no diretório do projeto

```bash
  docker-compose up --build
```



## Documentação da API

#### Cadastra notificações pendentes

```http
  POST /agendamento
```

| Body   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `emailDestinatario` | `string` | **Obrigatório**. Email do destinatário da`notificação |
| `telefoneDestinatario` | `string` | **Obrigatório**. Telefone do destinatário da notificação|
| `mensagem` | `string` | **Obrigatório**. Mensagem da notificação |
| `dataHoraEvento` | `LocalDateTime` | **Obrigatório**. Data hora do evento no formato dd-MM-yyy hh:mm:ss |

#### Retorna uma notificação por id

```http
  GET /notificacao/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. O ID do item que você quer buscar |

#### Cancela uma notificação por id

```http
  DELETE /notificacao/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. O ID do item que você quer cancelar |


## Rodando os testes

Para rodar os testes, rode o seguinte comando

```bash
  mvn test
```