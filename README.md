
# Magic Project

## Descrição
O **Magic Project** é uma aplicação de gerenciamento de cartas e decks, desenvolvida com Java e utilizando o framework Spring Boot. O projeto oferece funcionalidades como criação e gerenciamento de usuários, autenticação com tokens JWT, e controle de cartas e decks para jogadores.

O objetivo é facilitar o gerenciamento de cartas e decks em jogos de cartas, permitindo que os usuários gerenciem seus próprios baralhos e coleções de cartas.

## Funcionalidades
- **Autenticação e Autorização**: O projeto inclui autenticação baseada em JWT, protegendo as rotas e garantindo que apenas usuários autorizados possam acessar certos recursos.
  - **Classes relacionadas**: `SecurityConfig.java`, `SecurityFilter.java`, `JwtTokenService.java`
  
- **Gerenciamento de Usuários**: Os usuários podem se cadastrar e fazer login no sistema, com diferentes permissões baseadas em seus papéis (como `UsuarioRole.java`).
  - **Classes relacionadas**: `Usuario.java`, `UsuarioRepository.java`, `UsuarioService.java`, `UsuarioController.java`

- **Gerenciamento de Cartas**: Os usuários podem criar e gerenciar cartas, incluindo o tipo de carta (definido por `CardType.java`) e as informações associadas a elas.
  - **Classes relacionadas**: `Card.java`, `CardRepository.java`, `CardService.java`, `CardController.java`
  
- **Gerenciamento de Decks**: Os usuários podem criar e organizar seus próprios decks de cartas, adicionando e removendo cartas conforme necessário.
  - **Classes relacionadas**: `Deck.java`, `DeckRepository.java`, `DeckService.java`, `DeckController.java`
  
## Instalação
Para configurar o projeto localmente, siga as etapas abaixo:

1. Clone o repositório:
   ```bash
   git clone https://github.com/willyantomaz/magic
   ```

2. Acesse o diretório do projeto:
   ```bash
   cd magic
   ```

3. Certifique-se de que o JDK 17 e o Maven estão instalados. Para instalar as dependências, execute:
   ```bash
   ./mvnw install
   ```

4. Antes de rodar o projeto, execute o comando para subir o conteiner do RabbitMQ:
   ```
   docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:management
   ```

5. Para rodar a aplicação, use o comando:
   ```bash
   ./mvnw spring-boot:run
   ```

6. O projeto estará acessível via:
   ```
   http://localhost:8080
   ```

## Estrutura do Projeto
- **src/main/java/br/com/unicesumar/magic**: Contém os pacotes principais do projeto:
  - **auth**: Configurações de segurança e filtros para autenticação JWT.
  - **config**: Configurações do Spring, como o `RestTemplateConfig.java`.
  - **controller**: Controladores REST para gerenciar cartas, decks e usuários.
  - **dto**: Objetos de transferência de dados (DTOs) utilizados nas interações entre o cliente e o servidor.
  - **entity**: Entidades representando os dados principais, como `Card`, `Deck` e `Usuario`.
  - **repository**: Interfaces que gerenciam a persistência de dados no banco de dados.
  - **service**: Classes de serviços que contêm a lógica de negócios, como criação de tokens JWT, gerenciamento de cartas e decks.

## Uso
Após iniciar a aplicação, você pode realizar as seguintes ações:
- Criar usuários e realizar login via rotas de autenticação.
- Adicionar e gerenciar cartas.
- Criar decks personalizados e organizá-los com suas cartas.
