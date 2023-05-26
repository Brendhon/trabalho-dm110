# Trabalho DM110 (Purchase)

Aplicação utilizada como trabalho final da disciplina DM110 (Desenvolvimento JavaEE) na **Pós em Dispositivos Móveis e Computação em Nuvem** pelo **[INATEL](https://inatel.br/home/)**.

---

## 💻 Tecnologias

As seguintes tecnologias foram utilizadas na construção do projeto:

- **[Java](https://www.java.com/pt-BR/)**
- **[Maven](https://maven.apache.org/)**
- **[Wildfly](https://www.wildfly.org/)**
- **[Hsqldb](http://hsqldb.org/)**

### Utilitários
- IDE:  **[Ecplise](https://www.eclipse.org/)**

---
## ⚙️ Como executar o projeto

### 💡 Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
**[Git](https://git-scm.com)** e **[Java 11](https://www.java.com/pt-BR/)**.<br>

```bash

# Clone este repositório
$ git clone https://github.com/Brendhon/trabalho-dm110.git

# Acesse a pasta do projeto

# Instale as dependências
$ mvn clean install

```

#### Configurando o Eclipse

Configurar o Eclipse para usar o JDK 11:
- Acessar Window → Preferences → Installed JREs → Add → Standard JVM.
- Adicionar o JDK 11 e deixar ele marcado.

Configurar o JBoss Tools no Eclipse:
- Acessar o Menu Help → Eclipse Marketplace...
- Buscar por JBoss Tools
- Clicar para instalar e seguir os passos de instalação.
- Aguardar o processo de instalação.
- Reiniciar o Eclipse.

Configurar o Wildfly no Eclipse:
- Acessar o Menu Window → Preferences → Server → Runtime Environments → Add...
- Selecionar o Wildfly 24.x e informar o caminho de instalação do Wildfly.
- Clicar em Finish.
- Clicar em OK.
- Reiniciar o Eclipse.
- Acessar o Menu Window → Show View → Other... → Server → Servers.
- Clicar com o botão direito do mouse em cima do servidor Wildfly e selecionar a opção Start.
- Aguardar o servidor subir.
- Acesse a aplicação em http://127.0.0.1:8080/trabalho-web/ e verifique se a aplicação está rodando

#### Configurando HSQLDB
- Baixe o HSQLDB no site oficial: http://hsqldb.org/;
- Descompacte o arquivo baixado em uma pasta de sua preferência;
- Execute o jar com o comando `java -jar hsqldb.jar`.
- Na tela inicial, criar uma nova configuração com os seguintes dados:
  - Setting Name: DM110.
  - Type: HSQL Database Engine Standalone.
  - Driver: org.hsqldb.jdbc.JDBCDriver.
  - URL: `jdbc:hsqldb:file:$caminho_completo_do_arquivo_do_banco` (por exemplo:jdbc:hsqldb:file:/home/roberto/dm110-database/dm110.db).
  - User: dm110
  - Password: senhadm110
- Criar a tabela PURCHASE_ENTITY com o seguinte comando:
  ```sql
  CREATE TABLE PURCHASE_ENTITY (
    INVOICE_CODE VARCHAR(255) PRIMARY KEY,
    ORDER_ITEM VARCHAR(255),
    CPF VARCHAR(14),
    DATE_TIME TIMESTAMP,
    VALUE DOUBLE
  );
  ```
- Clique em `Execute SQL`, com isso a tabela PURCHASE_ENTITY será criada.
- Agora crie a tabela de auditoria:
  ```sql
  CREATE TABLE AUDIT_ENTITY (
    ID BIGINT IDENTITY PRIMARY KEY,
    REGISTER_CODE VARCHAR(255),
    OPERATION VARCHAR(255),
    CREATION_DATE TIMESTAMP
  );
  ```

Configurando o módulo do HSQLDB no Wildfly:
- Subir o servidor Wildfly.
- Execute o arquivo `jboss-cli.bat` que está na pasta `bin` do Wildfly.
- Execute o comando `connect`.
- Execute o comando `module add --name=br.inatel.dm110.org.hsqldb --dependencies=javax.transaction.api --export-dependencies=javax.api --resources={HSQLDBHome}/lib/hsqldb.jar`.
- Verificar a estrutura e o conteúdo criados dentro do diretório `{WildFlyHome}/modules/br/inatel/dm110/org/hsqldb/main`.


Configurando o driver do HSQLDB no Wildfly:
- Subir o servidor Wildfly.
- Acessar o console de administração do Wildfly em http://127.0.0.1:9990/
- Acessar o menu Configuration → Subsystems → Connector → JDBC Drivers.
- Click no botão `Add (+)`.
- Informar os seguintes dados:
  - Driver Name: HSQLDBDriver
  - Driver Module Name: br.inatel.dm110.org.hsqldb
  - Driver Class Name: org.hsqldb.jdbc.JDBCDriver
- Clicar em `Add`.

Criando o datasource do HSQLDB no Wildfly:
- Subir o servidor Wildfly.
- Acessar o console de administração do Wildfly em http://127.0.0.1:9990/
- Acessar o menu Configuration → Subsystems → Connector → Datasources.
- Clicar no botão Add (+) e selecionar Add Datasource.
- No passo Choose Template, escolha Custom e clique em Next.
- No passo Attributes, informe os seguintes dados:
  - Name: PurchaseDS
  - JNDI Name: java:/PurchaseDS
- No passo JDBC Driver, escolher o Driver Name no combo: HSQLDBDriver
- No passo Connection, informe os seguintes dados:
  - Connection URL: (a mesma URL utilizada quando o banco foi criado) Exemplo: jdbc:hsqldb:file:/home/roberto/dm110-database/dm110.db
  - User Name: dm110
  - Password: senhadm110
- No passo Test Connection, rolar o scroll para baixo e clicar no botão Test Connection e verificar se a conexão foi bem sucedida.
- Clique em Next, confirmar as informações e depois em Finish.
- Talvez seja necessário reiniciar o Wildfly.

#### Configurando o Message Driven Bean (MDB)

Configuração da Fila JMS no WildFly
- Subir o servidor Wildfly.
- Execute o arquivo `jboss-cli.bat` que está na pasta `bin` do Wildfly.
- Execute o comando `connect`.
- Execute o comando `jms-queue add --queue-address=dm110queue --durable=true --entries=["java:/jms/queue/dm110queue"]`.
- Acessar o console de administração do Wildfly em http://127.0.0.1:9990/
- Acessar o menu Configuration → Subsystems → Messaging → Category → Server → default → Destinations.
- Clicar no botão View e selecionar JMS Queue
- Visualizar a fila criada.
- Talvez seja necessário reiniciar o Wildfly.

**Após isso a aplicação já estará pronta para ser executada.**

## Observações  
- Caso tenha alteração no JNDI Name, será necessário alterar persistence.xml que se encontra dentro do `trabalho-ejb/src/main/resources/META-INF`.
- Em alguns casos pode ocorrer da aplicação se localizar no endereço http://127.0.0.1:8080/trabalho-web/ e outras vezes em  http://127.0.0.1:8080/trabalho-web-1.0/
- Foi anexado junto ao trabalho a collection do postman para testes: `Trabalho DM110.postman_collection.json`
- Para verificar se os registros de auditoria estão sendo salvos, basta acessar a pasta `hsqldb` e executar o comando `java -jar hsqldb.jar` e executar o comando SQL `select * from AUDIT_ENTITY;`

---

## Diagrama de Classes

<img style="margin-right: 30px" src="UML.jfif" width="1200px;" alt="Avatar"/><br>

---

## 👥 Autor

<h4>
<img style="border-radius: 5%; margin-right: 30px" src="https://avatars.githubusercontent.com/Brendhon" width="120px;" alt="Avatar"/><br>
Brendhon Moreira
</h4>


[![Linkedin Badge](https://img.shields.io/badge/-Brendhon-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/brendhon-moreira)](https://www.linkedin.com/in/brendhon-moreira)

<h4>
<img style="border-radius: 5%; margin-right: 30px" src="https://media.licdn.com/dms/image/C4E03AQFgY64gotkX3Q/profile-displayphoto-shrink_200_200/0/1638415089910?e=1690416000&v=beta&t=typf7RbhoblMyt_50ppy2FOfZzD6-IO9Dj7wfPu8yBg" width="120px;" alt="Avatar Aguinaldo"/><br>
Aguinaldo Feliciano de Souza
</h4>


[![Linkedin Badge](https://img.shields.io/badge/-Aguinaldo-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/aguinaldo-fs)](https://www.linkedin.com/in/aguinaldo-fs)

<h4>
<img style="border-radius: 5%; margin-right: 30px" src="https://avatars.githubusercontent.com/u/36972044?v=4" width="120px;" alt="120px"/><br>
Luiz Fhelipy
</h4>

[![Linkedin Badge](https://img.shields.io/badge/-Luiz-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/lf-teixeira/)](https://www.linkedin.com/in/lf-teixeira/)

---
## 📝 License
[![License](https://img.shields.io/github/license/Brendhon/Pokedex?style=plastic)](http://badges.mit-license.org)

- **[MIT license](https://choosealicense.com/licenses/mit/)**
