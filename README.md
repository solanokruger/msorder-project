
# MS-Order API

O Microsserviço Order possui a funcionalidade de gerar pedidos de itens e enviar esses pedidos para um microsserviço de histórico, que possui uma relação de todos os pedidos realizados por data.






## Configurações Iniciais

Inicializar o RabbitMQ no Docker

Acessar filas pelo endereço

http://localhost:15672/#/queues

Usuário e senha padrão: admin

```bash
  docker-compose -f docker-compose.yml up
```

#### Fila criada para a mensageria:

![queue](https://user-images.githubusercontent.com/89487480/214903988-c9f5441f-84bb-49d2-abae-cde869bfa237.png)

## Funcionalidades

- CRUD básico de Pedidos;
- Filtragem de Pedidos por CPF;
- Ordenação de ordem crescente e decrescente para o valor Total do Pedidos.



## Melhorias

- Implementação do Logback;
- Padrão Builder Pattern para Testes Unitários.


## Cobertura de Testes
#### Pacotes removidos da cobertura de testes:

![code_coverage_excluded](https://user-images.githubusercontent.com/89487480/214903363-b9564402-6cc6-4600-b671-ff1e7b3bca7c.png)

#### Cobertura total de Testes

![code_coverage_msorder](https://user-images.githubusercontent.com/89487480/215087407-7238aa6e-1c7a-4055-bcb2-93e9998392e0.png)

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

#### Pacotes removidos da cobertura de testes:

![excluded_from_mshistory](https://user-images.githubusercontent.com/89487480/214904443-1b12d5c4-9711-44f1-87f3-3174bf486d2d.png)

#### Cobertura total de Testes

![code_coverage_mshistory](https://user-images.githubusercontent.com/89487480/215087605-801ddfce-be31-4541-9199-746a62fc91a1.png)

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

