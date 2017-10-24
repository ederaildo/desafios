
# Desafio 2 IDwall - Crawlers

## Sobre

Implementação do desafio 2 da IDWall sobre recuperação de Crawlers.

## Autor

Ederaildo Fontes

## Tecnologias utilizadas

* Java 8
* Maven 3.5.0
* Spring Boot
* Thymeleaf
* Telegram Bot
* Apache Common CLI
* Spring Tool Suite IDE

## Pre Requisitos

Para instalacao e execuçãoo será necessário:

* Java 8
* Maven 3.4.* ou maior

## Instalação

Executar o comando:

```
mvn clean install
```

## Execução Parte 1

Executar o comando:

```
mvn exec:java -Dsubreddits=[Lista de subreddits]
```

A opção `-Dsubreddits` deve receber uma lista dos nomes de subreddits separados por vírgula. Por exemplo: 

```
mvn exec:java -Dsubreddits=askreddit;worldnews;cats
```

O output será mostrado assim:


```
# subreddit ....: worldnews
# upvotes ......: 55756
# titulo .......: Ex-Governor of Puerto Rico tweets photo of surgeons operating by cellphone light after Trump dubs aide efforts a 10
# link .........: http://www.nydailynews.com/news/national/puerto-rico-gov-slams-trump-high-marks-relief-efforts-article-1.3583112
# link comments.: /r/worldnews/comments/78aay3/exgovernor_of_puerto_rico_tweets_photo_of/


# subreddit ....: worldnews
# upvotes ......: 39343
# titulo .......: Czech President holds up machine gun marked 'for journalists' during press conference
# link .........: https://thinkprogress.org/czech-president-holds-up-machine-gun-marked-for-journalists-095d31fe562a/
# link comments.: /r/worldnews/comments/781zvi/czech_president_holds_up_machine_gun_marked_for/


# subreddit ....: worldnews
# upvotes ......: 33263
# titulo .......: The U.S. Air Force is preparing to put nuclear-armed bombers back on 24-hour ready alert, a status not seen since the Cold War ended in 1991.
# link .........: http://www.defenseone.com/threats/2017/10/exclusive-us-preparing-put-nuclear-bombers-back-24-hour-alert/141957/
# link comments.: /r/worldnews/comments/783av6/the_us_air_force_is_preparing_to_put_nucleararmed/
```

## Execução Parte 2

A parte 2 do desafio foi construída em cima de um webcontainer Spring Boot para receber o comando foi registrado um BOT no Telegram.

Para executar é necessário acessar o browser nessa URL: 

```
http://localhost:8080/
```

O campo **Comando** da página deve receber o seguinte comando:

```
/NadaPraFazer [Lista de subreddits]
```

Devendo receber uma lista dos nomes de subreddits separados por vírgula. Por exemplo: 

```
/NadaPraFazer -Dsubreddits=askreddit;worldnews;cats
```

Após a execução do comando será enviada uma lista de threads para cada subreddits, sendo mostrados somente as threads com upvotes maiores que 5000.

Essa lista será enviada para o canal do Telegram [@nadaprafazerIDwall](https://t.me/nadaprafazerIDwall)


## Contributors

**Ederaildo Fontes**

* [github/ederaildo](https://github.com/ederaildo)
* [linkedin/ederaildo](https://www.linkedin.com/in/ederaildo/)

## Licença
