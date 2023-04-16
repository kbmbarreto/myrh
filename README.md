# MYRH - Cadastro de recrutadores

<p>Sistema simples em Java Swing, utilizado para cadastrar os contatos dos recrutadores que me oferecem vagas de emprego. As vezes, encontrá-los no LinkedIn se torna uma tarefa um pouco árdua, este sistema simples acaba com este problema. Também me dá oinformações sobre em quais períodos venho recebendo mais ou menos ofertas de emprego.</p>

## Ferramentas utilizadas

- Java 8
- Oracle MySQL

## Preparação do ambiente
Para rodar o projeto, utlize a IDE que você mais se identifique **(no meu caso, para desenvolvimento Java Swing, utilizo o Eclipse IDE)**, em seguida, crie o banco de dados com o script a seguir, em seu MySQL:

**Script para criação do banco de dados**:

````tsql
create schema myrh;

use myrh;

CREATE TABLE recrutadores (
  id INT NOT NULL AUTO_INCREMENT,
  data_contato DATE NOT NULL,
  nome VARCHAR(50) NOT NULL,
  telefone VARCHAR(20) NOT NULL,
  email VARCHAR(50) NOT NULL,
  empresa VARCHAR(50) NOT NULL,
  meio_de_contato VARCHAR(20) NOT NULL,
  PRIMARY KEY (id)
);
````

Em seguida, basta alterar as informações na classe `ConnectionFactory.java` para que se conecte com sucesso em seu ambiente de banco de dados.

## Tutoriais úteis para configurar sua estação de trabalho.

- [Configurar variáveis de ambiente JAVA](https://mauriciogeneroso.medium.com/configurando-java-4-como-configurar-as-vari%C3%A1veis-java-home-path-e-classpath-no-windows-46040950638f)
- [Uma breve introdução do Java Swing](https://www.alura.com.br/artigos/como-criar-interface-grafica-swing-java)
