# 💸 API de Controle de Gastos Pessoais

Este projeto é uma API REST desenvolvida com o objetivo de **demonstrar boas práticas de documentação com Swagger (OpenAPI)**, além de ser uma base simples para o gerenciamento de despesas e usuários.

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Swagger / OpenAPI
- Maven

## 📄 Documentação da API

A documentação interativa da API está disponível via Swagger UI.

Após iniciar o projeto, acesse:

http://localhost:8080/swagger-ui/index.html


## 🧰 Funcionalidades

- ✅ Cadastro de usuários
- ✅ Cadastro de despesas por usuário
- ✅ Listagem de despesas
- ✅ Filtro de despesas por usuário
- ✅ Exclusão de despesas
- ✅ Categorias pré-definidas com uso de `enum`
- ✅ Documentação clara com Swagger


## 📥 Como Executar

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/seu-repo.git
```

2. Configure o application.properties com os dados do seu banco MySQL:

```
    spring.datasource.url=jdbc:mysql://localhost:3306/controle_gastos
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    
    spring.jpa.hibernate.ddl-auto=update
```
3. Execute o projeto:

✅ Via IDE:

Abra o projeto na IDE de sua preferência (como IntelliJ, Eclipse ou VS Code) e execute a classe ControledeGastosApplication.java.

💡 No meu caso, utilizei o IntelliJ IDEA, mas sinta-se à vontade para usar a IDE que preferir.

📌 Exemplos de Requisições

    📬 POST /usuarios
    {
        "nome": "João da Silva",
        "email": "joao@email.com"
    }

    📬 POST /despesas
    {
        "descricao": "Supermercado",
        "valor": 150.00,
        "categoria": "ALIMENTACAO",
        "dataDespesa": "2024-04-01"
    }

## 🎯 Objetivo
Mais do que entregar funcionalidades, este projeto busca mostrar como uma API bem documentada com Swagger facilita a comunicação entre desenvolvedores, tornando o desenvolvimento mais eficiente, colaborativo e menos propenso a erros.

## 👤 Autor
Feito por **Warley Ramires**

🔗 [LinkedIn](https://www.linkedin.com/in/warley-ramires/)  
🔗 [GitHub](https://github.com/warleyramires)
