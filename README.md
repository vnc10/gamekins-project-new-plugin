# Projeto Gamekins

Esta é uma aplicação Spring Boot para gerenciar estudantes, cursos, matérias e matrículas.

## Estrutura do Projeto

O projeto está organizado nos seguintes diretórios:

- **`dev/`**: Contém o arquivo `docker-compose.yml` para configurar o ambiente de desenvolvimento, incluindo o banco de dados MySQL.
- **`src/main/java/gamekins/project/`**: Contém o código-fonte principal da aplicação.
    - **`controller/`**: Esta camada é o primeiro ponto de contato para as requisições dos usuários, responsável por receber, validar e delegar tarefas para a camada de serviço. Ela lida com as requisições e respostas HTTP, garantindo que os dados fluam corretamente entre o cliente e a aplicação.
    - **`domain/`**: Contém os principais modelos de negócio da aplicação, que são o núcleo das regras de negócio. Estes modelos definem a estrutura fundamental dos dados e são responsáveis por garantir a consistência e a integridade das informações.
        - **`dto/`**: Contém os Objetos de Transferência de Dados (DTOs), que são responsáveis por transferir dados entre as camadas de controller e de serviço. Os DTOs são usados para moldar os dados de uma forma que seja apropriada para a interface do usuário ou outros serviços externos, evitando assim a exposição direta dos modelos de domínio.
    - **`mapper/`**: Esta camada é responsável por converter objetos de domínio em DTOs e vice-versa. Essa separação de responsabilidades torna o código mais modular e fácil de manter, pois a lógica de conversão fica centralizada em um único lugar.
    - **`repository/`**: Contém as interfaces de acesso a dados, que são responsáveis por toda a comunicação com o banco de dados. Esta camada abstrai os detalhes de persistência de dados, permitindo que a camada de serviço permaneça agnóstica ao tipo de banco de dados utilizado.
    - **`service/`**: É aqui que reside a principal lógica de negócio da aplicação. A camada de serviço coordena as regras de negócio, manipula os modelos de domínio e utiliza os repositórios para persistir e recuperar dados. Ela é responsável por garantir que todas as operações sejam realizadas de forma correta e consistente.
- **`src/main/resources/`**: Contém os recursos da aplicação.
    - **`application.properties`**: Arquivo de configuração da aplicação.
    - **`db/migration/`**: Contém os scripts de migração de banco de dados do Flyway.
- **`src/test/`**: Contém os testes da aplicação.

## Começando

Para começar a usar este projeto, você precisará ter o seguinte instalado:

- Java 21
- Maven
- Docker

### Compilando e Executando o Projeto

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/seu-usuario/gamekins-project.git
    cd gamekins-project
    ```
2.  **Inicie o banco de dados:**
    ```bash
    docker-compose -f dev/docker-compose.yml up -d
    ```
3.  **Execute a aplicação:**
    ```bash
    ./mvnw spring-boot:run
    ```
A aplicação estará disponível em `http://localhost:8080`.

## Endpoints da API

Os seguintes endpoints da API estão disponíveis:

### Cursos

- `GET /api/courses`: Obtém todos os cursos.
- `GET /api/courses/{id}`: Obtém um curso pelo ID.
- `POST /api/courses`: Cria um novo curso.
- `PUT /api/courses/{id}`: Atualiza um curso.
- `DELETE /api/courses/{id}`: Deleta um curso.

### Estudantes

- `GET /api/students`: Obtém todos os estudantes.
- `GET /api/students/{id}`: Obtém um estudante pelo ID.
- `POST /api/students`: Cria um novo estudante.
- `PUT /api/students/{id}`: Atualiza um estudante.
- `DELETE /api/students/{id}`: Deleta um estudante.

### Matérias

- `GET /api/subjects`: Obtém todas as matérias.
- `GET /api/subjects/{id}`: Obtém uma matéria pelo ID.
- `POST /api/subjects`: Cria uma nova matéria.
- `PUT /api/subjects/{id}`: Atualiza uma matéria.
- `DELETE /api/subjects/{id}`: Deleta uma matéria.

### Matrículas

- `GET /api/enrollments`: Obtém todas as matrículas.
- `GET /api/enrollments/{id}`: Obtém uma matrícula pelo ID.
- `POST /api/enrollments`: Cria uma nova matrícula.
- `PUT /api/enrollments/{id}`: Atualiza uma matrícula.
- `DELETE /api/enrollments/{id}`: Deleta uma matrícula.