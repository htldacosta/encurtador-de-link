# 🚀 Encurtador de Links com Spring Boot e MongoDB

Um serviço de API REST simples, mas robusto, para encurtamento de URLs, construído com Java 21, Spring Boot 3 e MongoDB. O projeto inclui expiração automática de links usando o recurso TTL (Time-To-Live) do MongoDB.

---

## 🛠️ Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 3.5.7**
* **Spring Web:** Para a criação dos endpoints REST.
* **Spring Data MongoDB:** Para integração com o banco de dados NoSQL MongoDB.
* **Spring Boot Validation:** Para validação de entrada nos DTOs.
* **Springdoc-OpenAPI (Swagger):** Para documentação interativa da API.
* **Maven:** Gerenciador de dependências.
* **JUnit 5, MockMvc & Flapdoodle (MongoDB Embutido):** Para testes de integração.

---

## ✨ Funcionalidades

* **Encurtar URL:** Cria um ID curto e aleatório para uma URL longa.
* **Redirecionamento:** Redireciona o usuário (via HTTP 302 Found) do link curto para a URL original.
* **Expiração Automática:** Links expiram automaticamente após um tempo configurado (padrão de 1 minuto) usando o índice TTL do MongoDB.
* **Validação de Entrada:** A API não aceita URLs inválidas ou em branco.

---

## 📖 Endpoints da API

A documentação completa e interativa da API está disponível via Swagger.

* `POST /shorten-url`: Cria uma nova URL encurtada.
* `GET /{id}`: Redireciona para a URL original.

---

## 🏃 Como Rodar (Em Breve com Docker)

*(Por enquanto, esta seção descreve como rodar localmente. Atualizaremos com Docker Compose.)*

**1. Pré-requisitos:**
* Java 21
* Maven
* MongoDB rodando em `mongodb://localhost:27017`

**2. Clone o repositório:**
```bash
git clone <url-do-seu-repositorio>
cd encurtador-de-link