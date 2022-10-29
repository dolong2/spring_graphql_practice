# spring_graphql_practice
spring으로 graphql을 연습하는 레포

## graphql설정
yml
```yml
spring:
  graphql:
    schema:
      file-extensions: .graphqls
      locations: classpath:graphql/**/
    graphiql:
      enabled: true
      printer:
        enabled: true
```
.graphqlconfig
```json
{
  "name": "Graphql-Example",
  "schemaPath": "src/main/resources/graphql/schema.graphqls",
  "extensions": {}
}
```
