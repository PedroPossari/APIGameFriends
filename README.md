# 🎮 GameFriends API

API RESTful desenvolvida com Spring Boot para autenticação de usuários (com suporte a login via Google), cadastro e gerenciamento de jogos, avaliações (reviews) e favoritos.

---

## 📚 Sumário

- [📦 Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [📋 Pré-requisitos](#-pré-requisitos)
- [🚀 Como Executar Localmente](#-como-executar-localmente)
- [🔐 Autenticação](#-autenticação)
- [📁 Endpoints](#-endpoints)
    - [👤 AuthController](#-authcontroller)
    - [🎮 JogoController](#-jogocontroller)
- [🛠️ Estrutura do Projeto](#️-estrutura-do-projeto)
- [🧪 Testes](#-testes)
- [📄 Licença](#-licença)

---

## 📦 Tecnologias Utilizadas

- Java 17
- Spring Boot 2.7.6
- Spring Security
- JWT (Token de autenticação)
- Google OAuth 2.0
- Spring Data JPA
- PostgreSQL
- Lombok
- Swagger/OpenAPI
- Spring Mail
- Hibernate Validator

---

## 📋 Pré-requisitos

- Java 17+
- Maven 3.8+
- PostgreSQL configurado
- Conta de desenvolvedor Google (para OAuth2)

---

## 🚀 Como Executar Localmente

1. Clone o repositório:

    ```bash
    git clone https://github.com/seu-usuario/GameFriends.git
    cd GameFriends


## Configure seu application.properties (ou application.yml):

    spring.datasource.url=jdbc:postgresql://localhost:5432/gamefriends
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    
    jwt.secret=sua-chave-secreta
    jwt.expiration=3600000
    
    google.clientId=seu-client-id-do-google
    spring.mail.username=seu-email
    spring.mail.password=sua-senha

## Execute a aplicação com o Maven:

    ./mvnw spring-boot:run

## 🔐 Autenticação
- Login via email/senha com JWT

- Login via conta Google com ID Token

## 📁 Endpoints
### 👤 AuthController
| Método | Endpoint               | Descrição                   |
| ------ | ---------------------- | --------------------------- |
| POST   | `/auth`                | Login com e-mail e senha    |
| POST   | `/auth/google`         | Login com conta Google      |
| POST   | `/auth/register`       | Registro de novo usuário    |
| GET    | `/auth/Usuario-logado` | Retorna o usuário logado    |
| PUT    | `/auth/senha`          | Atualiza a senha            |
| PUT    | `/auth/atualizar`      | Atualiza dados do usuário   |
| DELETE | `/auth/deletar`        | Desativa a conta do usuário |

### 🎮 JogoController
| Método | Endpoint                 | Descrição                                   |
| ------ | ------------------------ | ------------------------------------------- |
| GET    | `/jogos`                 | Lista todos os jogos com paginação e filtro |
| GET    | `/jogos/{idJogo}`        | Busca um jogo por ID                        |
| POST   | `/jogos`                 | Cria um novo jogo                           |
| PUT    | `/jogos/{idJogo}`        | Atualiza um jogo existente                  |
| DELETE | `/jogos/{idJogo}`        | Deleta um jogo                              |
| POST   | `/jogos/review`          | Cria uma review para um jogo                |
| PUT    | `/jogos/review`          | Atualiza uma review existente               |
| PUT    | `/jogos/favoritos`       | Marca/desmarca um jogo como favorito        |
| GET    | `/jogos/favoritos`       | Lista os jogos favoritos do usuário         |
| GET    | `/jogos/usuario/reviews` | Lista todas as reviews feitas pelo usuário  |

## 🛠️ Estrutura do Projeto
    GameFriends
    ├── controller/
    │   ├── AuthController.java
    │   └── JogoController.java
    ├── dto/
    │   ├── Usuario/
    │   └── Jogos/
    ├── entity/
    ├── security/
    ├── service/
    ├── exception/
    ├── config/
    └── GameFriendsApplication.java

## 🧪 Testes
### O projeto possui suporte para testes com:

    spring-boot-starter-test

    spring-security-test

### Execute os testes com:
    ./mvnw test