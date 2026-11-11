# SAGSA – Sistema Automatizado de Geração de Situações de Aprendizagem

## 1. Sobre o projeto

O SAGSA (Sistema Automatizado de Geração de Situações de Aprendizagem) é um projeto desenvolvido com o objetivo de auxiliar o processo de elaboração de atividades educacionais, utilizando recursos de automação para organizar informações e apoiar o trabalho dos docentes.

O sistema está sendo desenvolvido no contexto do curso Técnico em Desenvolvimento de Sistemas do SENAI, com foco na aplicação de conceitos de desenvolvimento de software, integração com banco de dados e construção de aplicações utilizando arquitetura REST.

Este repositório contém o desenvolvimento do back-end da aplicação, responsável pelo processamento das requisições, gerenciamento dos dados e implementação das regras de negócio.

## 2. Objetivo

Desenvolver uma aplicação capaz de centralizar e organizar informações relacionadas ao processo de criação de situações de aprendizagem, oferecendo uma estrutura que permita a comunicação entre os componentes do sistema e o gerenciamento dos dados utilizados pela aplicação.

Entre os objetivos técnicos estão:

- Implementar uma API REST para comunicação entre cliente e servidor.
- Realizar operações de persistência e consulta de dados.
- Organizar o código utilizando separação de responsabilidades.
- Implementar mecanismos de autenticação e controle de acesso.
- Aplicar boas práticas de desenvolvimento com Java e Spring Boot.

## 3. Tecnologias utilizadas

As principais tecnologias e ferramentas utilizadas no desenvolvimento são:

| Tecnologia | Aplicação |
|---|---|
| Java | Linguagem de programação principal |
| Spring Boot | Estrutura para desenvolvimento da aplicação |
| Spring Data JPA | Persistência e manipulação de dados |
| Spring Security | Configuração de segurança e controle de acesso |
| MySQL | Sistema gerenciador de banco de dados |
| Maven | Gerenciamento de dependências e compilação |
| JWT | Mecanismo de autenticação baseado em tokens, conforme implementação do projeto |

## 4. Arquitetura do projeto

O back-end segue uma organização em camadas, buscando separar as responsabilidades e facilitar a manutenção e evolução do código.

A estrutura principal está organizada da seguinte maneira:

| Pacote | Responsabilidade |
|---|---|
| `configs` | Configurações gerais da aplicação e segurança |
| `controllers` | Recebimento e tratamento das requisições HTTP |
| `models` | Definição das entidades e representação dos dados |
| `repositories` | Acesso e persistência das informações no banco de dados |
| `services` | Implementação das regras de negócio |
| `security` | Componentes relacionados à autenticação e autorização |
| `exceptions` | Tratamento e gerenciamento de exceções |

Essa divisão permite manter a lógica de negócio independente das operações de acesso aos dados e da comunicação HTTP.

## 5. Banco de dados

A aplicação utiliza o MySQL como sistema gerenciador de banco de dados, com integração realizada por meio do Spring Data JPA.

A camada de persistência é responsável por estabelecer a comunicação entre as entidades Java e as tabelas do banco de dados.

O mapeamento das entidades é realizado utilizando os recursos do Jakarta Persistence (JPA), permitindo representar os relacionamentos e as estruturas de dados da aplicação.

As configurações de conexão são definidas no arquivo de propriedades da aplicação e devem ser ajustadas conforme o ambiente de execução.

## 6. API REST

A comunicação entre o cliente e o servidor ocorre por meio de requisições HTTP, utilizando os métodos definidos pelo padrão REST.

Entre as operações desenvolvidas estão:

- Cadastro e gerenciamento de usuários.
- Operações relacionadas ao gerenciamento de alunos.
- Consulta e gerenciamento de turmas.
- Autenticação de usuários.

Os endpoints devem ser consultados nos respectivos controllers da aplicação, que definem os caminhos, métodos HTTP e parâmetros aceitos.

## 7. Segurança

A aplicação utiliza Spring Security para configurar os mecanismos de proteção dos recursos disponibilizados pela API.

A estrutura de segurança contempla componentes responsáveis pela autenticação dos usuários, identificação das requisições e controle de acesso aos endpoints.

Também são utilizados componentes específicos para integração com o processo de autenticação e gerenciamento das informações dos usuários.

As credenciais de acesso ao banco de dados e os segredos utilizados pela aplicação não devem ser disponibilizados publicamente no repositório.

## 8. Instalação e execução

### Requisitos

Para executar o projeto, são necessários:

- JDK compatível com a versão configurada no projeto.
- Apache Maven.
- MySQL Server.
- IDE de desenvolvimento Java, como Visual Studio Code ou IntelliJ IDEA.

### Configuração

Antes de iniciar a aplicação, é necessário configurar a conexão com o banco de dados no arquivo `application.properties`, localizado em:

`src/main/resources/application.properties`

As informações de conexão devem corresponder ao ambiente em que a aplicação será executada.

### Execução

Acesse o diretório do back-end:

```bash
cd api_aptec-ds
```

Compile e verifique o projeto:

```bash
mvn clean install
```

Inicie a aplicação:

```bash
mvn spring-boot:run
```

Após a inicialização, a API ficará disponível localmente na porta configurada para o servidor, sendo a porta padrão do projeto 8080.

## 9. Testes e validação

A validação da aplicação envolve a compilação do projeto, a inicialização dos componentes do Spring Boot e a verificação das operações disponibilizadas pela API.

Os testes devem contemplar o comportamento dos endpoints, a persistência das informações, a autenticação e o tratamento de situações de erro.

## 10. Desenvolvimento

O projeto está sendo desenvolvido de forma incremental, com implementação e organização das funcionalidades em etapas, utilizando o Git e o GitHub para controle de versão e acompanhamento das alterações realizadas.

## 11. Autoria

**Desenvolvimento:**
- Victoria Batista
- Netally Lopes Gonçalves

**Instituição:** SENAI

**Curso:** Técnico em Desenvolvimento de Sistemas

**Projeto:** SAGSA – Sistema Automatizado de Geração de Situações de Aprendizagem
