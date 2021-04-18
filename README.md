
# Aplicação de Gerenciamento de pedidos

Aplicação desenvolvida para desafio técnico.

## Pré Requisitos
O projeto foi desenvolvido com as tecnologias:

- JDK 11;
- Maven 3.6.2 (ou Maven Wrapper presente na aplicação)
- Banco de dados PostgreSQL 13.2

## Configuração:

É necessário configurar as propriedades abaixno no arquivo **src/main/resources/application.properties**:

- spring.datasource.url=jdbc:postgresql://localhost:5432/**NOME_DO_BANCO**
- spring.datasource.username=**USUÁRIO**
- spring.datasource.password=**SENHA**

> O banco de dados deve ser criado previamente, > para que as migrações realizadas no start da > aplicações sejam realizadas com sucesso.

## Execução:
Para a execução do projeto basta utilizar o Maven para a compilação, utilizando o comando:

mvn clean package install   
Ou:

./mvnw clean package install   
Para o S.O Windows:

mvnw.cmd clean package install
> Os 2 últimos exemplos servem apenas se você estiver utilizando o Maven Wrapper

> Após a execução do comando Maven, o artefato de execução do projeto estará > disponível na pasta **target** da aplicação com o nome **senior_pedidos-0.0.1-SNAPSHOT.jar**

## Endpoints
Os endpoints disponíveis na IDE são:

### /produtos

#### Listagem
O endpoint de listagem dos produtos é o http://localhost:8080/produtos

A listagem pode ser ordenada, e paginada com os atributos:

| Atributo | Tipo | Descrição | Exemplo |    
|--|--|--|--|    
| page | int | Define qual é a página atual da lista de produtos. A primeira página é definida pelo número 0. | 0    
| size | int | Define quantos elementos serão listados na página atual | 5    
| sort | string | Define qual será o atributo base para a ordenação, seguido do tipo da ordenação, podendo ser ASC (crescente) ou DESC (decrescente) | price,DESC


#### GET
O endpoint para a consulta de um produto é o http://localhost:8080/produtos/*{id}*

> Onde *{id}* deve conter o ID de um produto válido.

#### POST
O endpoint de criação de um produto é o  http://localhost:8080/produtos/

A requisição **POST** deve enviar um *body* como o abaixo:

	{ 
		"name":  "Produto Teste", 
		"price":  123.456, 
		"active":  true, 
	}  
Todos os atributos são obrigatórios, e estão descritos abaixo:

| Atributo | Tipo | Descrição | Exemplo |    
|--|--|--|--|    
| name | string | Define o nome do produto | Smartphone Apple Iphone 12    
| price | double | Define o preço do produto | 6589.90    
| active | boolean | Define se o produto estará ativo ou não | false

#### PUT
O endpoint de edição de um produto é o  http://localhost:8080/produtos/*{id}*

> Onde *{id}* deve conter o ID de um produto válido.

A requisição **PUT** deve enviar um *body* como o abaixo:

	{
		"name":  "Produto Teste",
		"price":  123.456,
		"active":  true
	}  
Todos os atributos são obrigatórios, e estão descritos abaixo:

| Atributo | Tipo | Descrição | Exemplo |    
|--|--|--|--|    
| name | string | Define o nome do produto | Smartphone Apple Iphone 12    
| price | double | Define o preço do produto | 6589.90    
| active | boolean | Define se o produto estará ativo ou não | false

#### DELETE
O endpoint de exclusão de um produto é o  http://localhost:8080/produtos/*{id}*

> Onde *{id}* deve conter o ID de um produto válido.


### /serviços

#### Listagem
O endpoint de listagem dos serviços é o http://localhost:8080/servicos

A listagem pode ser ordenada, e paginada com os atributos:

| Atributo | Tipo | Descrição | Exemplo |    
|--|--|--|--|    
| page | int | Define qual é a página atual da lista de serviços. A primeira página é definida pelo número 0. | 0    
| size | int | Define quantos elementos serão listados na página atual | 5    
| sort | string | Define qual será o atributo base para a ordenação, seguido do tipo da ordenação, podendo ser ASC (crescente) ou DESC (decrescente) | price,DESC


#### GET
O endpoint para a consulta de um serviço é o http://localhost:8080/servicos/*{id}*

> Onde *{id}* deve conter o ID de um serviço válido.

#### POST
O endpoint de criação de um serviço é o  http://localhost:8080/servicos/

A requisição **POST** deve enviar um *body* como o abaixo:

	{
		"name":  "serviço Teste",
		"price":  123.456,
		"active":  true
	}  
Todos os atributos são obrigatórios, e estão descritos abaixo:

| Atributo | Tipo | Descrição | Exemplo |    
|--|--|--|--|    
| name | string | Define o nome do serviço | Smartphone Apple Iphone 12    
| price | double | Define o preço do serviço | 6589.90    
| active | boolean | Define se o serviço estará ativo ou não | false

#### PUT
O endpoint de edição de um serviço é o  http://localhost:8080/servicos/*{id}*

> Onde *{id}* deve conter o ID de um serviço válido.

A requisição **PUT** deve enviar um *body* como o abaixo:

    {
		"name":  "serviço Teste",
		"price":  123.456,
		"active":  true
	}  
Todos os atributos são obrigatórios, e estão descritos abaixo:

| Atributo | Tipo | Descrição | Exemplo |    
|--|--|--|--|    
| name | string | Define o nome do serviço | Smartphone Apple Iphone 12    
| price | double | Define o preço do serviço | 6589.90    
| active | boolean | Define se o serviço estará ativo ou não | false

#### DELETE
O endpoint de exclusão de um serviço é o  http://localhost:8080/servicos/*{id}*

> Onde *{id}* deve conter o ID de um serviço válido.


### /pedidos

#### Listagem
O endpoint de listagem dos pedidos é o http://localhost:8080/pedidos

A listagem pode ser ordenada, e paginada com os atributos:

| Atributo | Tipo | Descrição | Exemplo |    
|--|--|--|--|    
| page | int | Define qual é a página atual da lista de pedidos. A primeira página é definida pelo número 0. | 0    
| size | int | Define quantos elementos serão listados na página atual | 5    
| sort | string | Define qual será o atributo base para a ordenação, seguido do tipo da ordenação, podendo ser ASC (crescente) ou DESC (decrescente) | total,DESC


#### GET
O endpoint para a consulta de um pedido é o http://localhost:8080/pedidos/*{id}*

> Onde *{id}* deve conter o ID de um pedido válido.

#### POST
O endpoint de criação de um pedido é o  http://localhost:8080/pedidos/

A requisição **POST** deve enviar um *body* como o abaixo:

	{
        "status": "OPENED",
        "discount": 10,
        "items": [
            {"id" : 3}
        ]
    } 

Todos os atributos são obrigatórios, e estão descritos abaixo:

| Atributo | Tipo | Descrição | Exemplo |    
|--|--|--|--|    
| status | string | Define o status do pedido | OPENED    
| discount | double | Define o desconto em porcentagem do total do pedido | 25.5    
| items | Array[Produtos/Serviços] | Define a lista de items a serem incluídos no pedido | [{"id" : 3}, {"id" : 5}, {"id" : 19}]

#### PUT
O endpoint de edição de um pedido é o  http://localhost:8080/pedidos/*{id}*

> Onde *{id}* deve conter o ID de um pedido válido.

A requisição **PUT** deve enviar um *body* como o abaixo:

    {
        "status": "OPENED",
        "discount": 10,
        "items": [
            {"id" : 3}
        ]
    }

Todos os atributos são obrigatórios, e estão descritos abaixo:

| Atributo | Tipo | Descrição | Exemplo |    
|--|--|--|--|    
| status | string | Define o status do pedido | OPENED    
| discount | double | Define o desconto em porcentagem do total do pedido | 25.5    
| items | Array[Produtos/Serviços] | Define a lista de items a serem incluídos no pedido | [{"id" : 3}, {"id" : 5}, {"id" : 19}]

#### DELETE
O endpoint de exclusão de um pedido é o  http://localhost:8080/pedidos/*{id}*

> Onde *{id}* deve conter o ID de um pedido válido.