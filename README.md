## Sobre o repositório

Repositório dedicado ao aprendizado de SpringBoot comm Maven e Java 21, este repositório NÃO deve ser levado a sério, apenas fiz com o intuito de praticar o inicio e a configuração de um projeto Spring.

## Como rodar o projeto:

Caso vá rodar localmente, sem docker, certifique-se de ter o Maven instalado, rode:

    mvn -version

Caso for utilizar docker para rodar o projeto e o banco de dados, certifique-se de ter o docker instalado:

    docker version

ou
    
    docker --version

Estando tudo certo com o docker, rode:

    docker compose up -d --build

`OBS: Certifique-se de estar com o arquivo .env com as variaveis de ambiente devidamente configuradas.`

## Acesse o Swagger para ter mais detalhes sobre a API

    http://localhost:xxxx/swagger-ui/index.html#/user-controller

`OBS: Troque o xxxx pela porta utilizada do spring server`