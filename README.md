
# MS-Order API

O Microsserviço Order possui a funcionalidade de gerar pedidos de itens e enviar esses pedidos para um microsserviço de histórico, que possui uma relação de todos os pedidos realizados por data.






## Configurações Iniciais

Inicializar o RabbitMQ no Docker

```bash
  docker-compose -f docker-compose.yml up
```

Fila usada para a mensageria:
    
## Funcionalidades

- CRUD básico de Pedidos;
- Filtragem de Pedidos por CPF;
- Ordenação de ordem crescente e decrescente para o valor Total do Pedidos.



## Melhorias

- Implementação do Logback;
- Padrão Builder Pattern para Testes Unitários.


## Cobertura de Testes
## Documentação da API

#### Criar um Pedido

```http
  POST /api/pedidos
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `cpf` | `string` | **Obrigatório**. CPF do pedido |
| `items` | `array` | **Obrigatório**. Itens do pedido |
| `cep` | `string` | **Obrigatório**. CEP para busca do endereço |
| `number` | `string` | **Obrigatório**. Número da casa |

#### Lista todos os Pedidos

```http
  GET /api/pedidos
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `cpf` | `string` | **Opcional**. CPF do pedido a ser buscado |


#### Lista Pedido por ID

```http
  GET /api/pedidos/{id}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id` | `long` | **Obrigatório (Path)**. ID do Pedido a ser buscado|


#### Atualizar Pedido

```http
  PUT /api/pedidos/{id}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id` | `long` | **Obrigatório (Path)**. ID do Pedido a ser atualizado |
| `cpf` | `string` | **Obrigatório**. CPF do pedido (Novo) |
| `cep` | `string` | **Obrigatório**. CEP para busca do endereço (Novo) |
| `number` | `string` | **Obrigatório**. Número da casa (Novo) |

#### Atualizar Item

```http
  PATCH /api/itens/{id}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id` | `long` | **Obrigatório (Path)**. ID do Item a ser atualizado |
| `name` | `string` | **Obrigatório**. Nome do Item (Novo) |
| `creationDate` | `string` | **Obrigatório**. Data de criação do Item (Novo) |
| `validationDate` | `string` | **Obrigatório**. Data de validade do Item (Novo) |
| `value` | `string` | **Obrigatório**. Valor do Item (Novo) |
| `description` | `string` | **Obrigatório**. Descrição do item (Novo) |

#### Deletar Pedido

```http
  PATCH /api/itens/{id}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id` | `long` | **Obrigatório (Path)**. ID do Pedido a ser deletado |





## MS-History
O Microsserviço History possui a funcionalidade de listar um histórico de pedidos efetuados, em relação a sua data de criação.
## Funcionalidades
- Listagem de todo o Histórico de Pedidos;
- Filtragem do Histórico por Data.
## Cobertura de Testes

## Documentação da API
#### Listar todo o Histórico

```http
  GET /api/pedidos
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `startDate` | `string` | **Opcional**. Data de início dos Pedidos |
| `endDate` | `string` | **Opcional**. Data de fim dos Pedidos |

## Autores

- [@solanokruger](https://github.com/solanokruger)

