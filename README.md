<div>
<p align="center">
   <img src=".github/logo.svg" alt="Oracle-One" width="500"/>
</p>
</div>
<p align="center">
  <a aria-label="Completed" href="https://www.oracle.com/br/education/oracle-next-education/">
    <img src="https://img.shields.io/badge/Oracle-Alura-0A3871?logo="></img>
  </a>
  <a href="https://github.com/luizlcezario/OracleOne_Challenge_HotelOne/commits/master">
    <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/luizlcezario/OracleOne_Challenge_HotelOne?color=0A3871">
  </a>

  <a href="https://github.com/luizlcezario/OracleOne_Challenge_HotelOne/stargazers">
    <img alt="Stargazers" src="https://img.shields.io/github/stars/luizlcezario/OracleOne_Challenge_HotelOne?color=0A3871&logo=github">
  </a>
</p>

<div align="center">
  <sub>Challange Hotel Alura of Oracle One. Made with ❤︎
  </sub>
</div>

# :pushpin: Index

- [What is this Repo?](#sparkles_What-is-this-Repo?)
- [How to test](#clipboard_How-to-test)
- [Technologies](#computer_Technologies)
- [Find a Bug? Or somenthing need to change?](#bug_Issues?)

# :sparkles: What is this Repo?

This is a challenge proposed in the oracle one next education course, in this challenge we have to make create the logical part of a interface for a administration of a hotel for this we use a JDBC wiht postgreSQl, java Swing and other.

to make all simple I take the [original repo](https://github.com/Alquimistas-AluraLatam/PT-hotel-alura) and implement a package manager the grandle and make a docker compose to start the Database more simple.

# :clipboard: How to test

to start you need to setup the database with docker running:
`$> docker-compose up`
if you want to change the password of the database or something like this please verify [.env]("https://github.com/luizlcezario/OracleOne_Challenge_HotelOne/blob/main/postgres.env) and change in the [java file](https://github.com/luizlcezario/OracleOne_Challenge_HotelOne/blob/main/HotelAluraFix/HotelAluraFix/src/main/java/src/db/DatabaseConnection.java)

Finally to see if is working enter in the correct folder
`$> cd ./HotelAluraFix/HotelAluraFix`

And then use grandlew to run
`$> ./gradlew run`

## :computer: Technologies

- [Java](https://www.java.com/pt-BR/)
- [Java Swing](https://docs.oracle.com/javase/tutorial/uiswing/)
- [Docker](https://www.docker.com)
- [PostgreSql](https://www.postgresql.org)
- [JDBC](https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/)

# :bug: Issues?

Please feel free **to create a new issue** with its title and description on the issues page of the [BenchMark](https://github.com/luizlcezario/OracleOne_Challenge_HotelOne/issues) Repository. If you have already found the solution to the problem, **I would love to review your pull request**!

Give ⭐️ if you like this project, this will help me!
