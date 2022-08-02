## Projeto final da disciplina Desenvolvimento API Restful com Spring Boot

Residência em Desenvolvimento Web FullStack - Serratec [Parque Tecnológico Região Serrana] - Firjan/Senai


## 🛠️ Ferramentas utilizadas no projeto:

* [Eclipse](https://www.eclipse.org/) - IDE usada
* [Maven](https://maven.apache.org/) - Gerente de Dependência
* [Postman](https://www.postman.com/) - Testar a API
* [Swagger](https://swagger.io/) - Consumo e visualização de serviços de uma API REST
* [Spring Boot](https://spring.io/projects/spring-boot) - Framework Java



## Detalhes do projeto:

Criação de API, utilizando SpringBoot.

### E-commerce

Uma empresa varejista deseja cria um software que controle todo o seu estoque de produtos, clientes, funcionários e suas vendas, as vendas serão feitas pela internet, a seguir são descritos os requisitos e os dados que a empresa julga serem necessários para o funcionamento do sistema.

O sistema deve ser capaz de armazenar informações sobre os produtos da empresa, sendo eles: código, nome, descrição, valor unitário, data de cadastro, quantidade em estoque e data de validade.
Ao cadastrar o produto no sistema, os funcionários da empresa devem ser capazes de associá-lo à uma categoria. Cada produto só poderá pertencer à uma categoria.
Se o produto já estiver cadastrado o sistema ira atualizar somente a quantidade de produto em estoque.
Para a categoria do produto deverá ser armazenado as seguintes informações: código, nome e descrição.
Para os clientes o sistema deverá armazenas as seguintes informações: código, nome completo, nome de usuário, e-mail, cpf, data de nascimento, endereço e telefone.
Para os funcionários o sistema deverá armazenar as seguintes informações: código, nome e cpf(cpf é único)
Através do sistema o cliente pode comprar um ou mais produtos, lembrando que o mesmo não pode comprar mais do que possui em estoque, assim que o cliente fizer a compra o sistema deverá enviar um e-mail informando os qual o produto foi comprado, quantidade e o valor.
A empresa varejista também deseja visualizar qual os 5 produtos mais vendido e o valor total desses produtos, então será um relatório com o nome do produto, quantidade vendida e o valor total.

Lembre-se que em todas as classes: cliente, funcionário, produtos e categoria deverá possuir o crud(get,put,delete e post).

EXTRA 1: O sistema deverá enviar um email para o dono da empresa varejista, quando os produtos estiverem com 5 ou menos em seu estoque.

EXTRA 2: [MUITO IMPORTANTE] - Fazer segurança da API utilizando o JWT  

DESAFIO - Fazer uma classe Endereço vincular ao cliente, através dessa classe desse ser consumido um serviço do CORREIO para recuperar o endereço pelo CEP.





