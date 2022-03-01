Dafiti Challenge - Spring Boot Application
------------------------------------------

### Introducão

Visando atender os requisitos do desafio desenvolvi uma aplicação utilizando [Spring](https://spring.io/) para a criação de toda a API Back-end do nosso projeto utilizando o Maven como gerenciador de pacotes da nossa aplicação. Já no Front-End utilizamos o [ReactJs](https://reactjs.org/), utilizando o NodeJs como gerenciador de pacotes. Também foi criada uma documentação de cada método via Postman, visto que a facilidade de se criar uma documentação utilizando o postman é algo muito flexível a ágil, você pode acessar pela seguinte url: [https://documenter.getpostman.com/view/14599945/UVkqrEwa](https://documenter.getpostman.com/view/14599945/UVkqrEwa)

### Por onde começar

Deixamos duas opções para auxiliar na executação da aplicação

#### Acessando aplicação

Para facilicade dos testes a aplicação está rodando em tempo real atráves da hospedagem Heroku, que é uma plataforma que nos permite realizar o deploy de aplicações sem custo algum, para acessar basta realizar os request no link

[https://challenge-dafiti.herokuapp.com](https://challenge-dafiti.herokuapp.com)

#### Instalação

Para que a aplicação execute de forma correta você deverá ter instalado em sua máquina a versão mais recente do Java, além disto tambem irá precisar instalar o maven que é o responsável por carregar todas as dependencias da nossa aplicação, caso tenha necessidade você pode acessar a documentação do [Maven](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)

#### Clonando o projeto

Após a intalação do maven você precisará clonar ou baixar este repositório em sua máquina local, caso deseje você pode utilizar o comando:

`git clone git@github.com:Gabriel438/Dafiti.git`

#### Instalando dependencias

Em seguida abra a pasta do projeto, nesta etapa iremos realizar a instalação de todas as dependencias utilizando o Maven, para isso abra um Prompt de comando, e execute:

`mvn install`

Deverá realizar toda a instalação, e pronto, caso necessário execute novamente a linha de comando para garantir que todos os pacotes funcionaram corretamente.

#### Executando Aplicação

Pronto, o projeto está pronto para executar em sua máquina local, para isso você deve ainda em seu prompt de comando executar a seguinte linha de comando:

`mvn spring-boot:run`

Pronto! a sua aplicação deve iniciar, por padrão ela irá iniciar na porta :8080, mas é possível alterar seguindo a documentação do Spring [Server Port](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.server.server.port).

### Testes

Para facilitar um pouco a verificação dos métodos da nossa aplicação foi desenvolvido alguns testes para validar a respostar dos endpoints, caso deseje realizar os testes é necessário executar a seguinte linha de comando

`mvn test`
