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
## 스키마
schema.graphqls
```graphql
type Mutation{
    writePosting(inputPosting: InputPosting!): ResponsePosting #@MutationMappping이랑 메서드명이 같아야됨 + 메게변수 이름도 같아야됨 + 타입은 이름 달라도됨
}

type Query{
    getPostings:[ResponsePosting] #@QueryMappping이랑 메서드명이 같아야됨 + 메게변수 이름도 같아야됨 + 타입은 이름 달라도됨
    getPosting(id:ID!): ResponsePosting
}
```
InputPosting.graphqls
```graphql
input InputPosting{
    title: String!
    content: String!
}
```
ResponsePosting.graphqls
```graphql
type ResponsePosting{
    id: ID!
    title: String!
    content: String!
}
```
