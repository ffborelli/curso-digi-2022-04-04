# Aula Mongo

## Trazer todos documentos que possuam a letra ‘a’ no nome

```
    {nome: { $regex: /a/ }}
```

## Trazer todos maiores de idade

```
    {idade: {$gte: 18}}
```

## Combinar filtros usando vírgulas dentro do documento passado por parâmetro

```
    {nome: "Fabrizio", idade: {$gte: 18}}
```

## Outros operadores

- $eq: exatamente igual (=)
- $ne: diferente (<> ou !=)
- $gt: maior do que (>)
- $lt: menor do que (<)
- $lte: menor ou igual a (<=)
- $in: o valor está contido em um array de possibilidades, como em um OU. Ex{idade: {$in: [10,12] }}

# Mongosh

- Mudar de banco de dados

```
    use fabrizio_borelli;
```

- Inserir um usuário:

```
    db.usuarios.insert({ nome: "Fabrizio Mongo SH", email : "sh@sh.com" })
```

- Inserir vários usuários:

```
    db.usuarios.insert(
        [
            { nome: "Fabrizio Mongo SH 1", email : "sh1@sh.com" },
            { nome: "Fabrizio Mongo SH 2", email : "sh2@sh.com" },
            { nome: "Fabrizio Mongo SH 3", email : "sh3@sh.com" }
        ]  
    )
```

- procurar um usuário pelo nome

```
    db.usuarios.find({nome: "Fabrizio Mongo SH"})
```

- procurar um usuário pelo nome com letra b

```
    db.usuarios.find({nome: { $regex: /SH/ } })
```

- procurar um usuário pelo salário (usando operador maior que)

```
    db.usuarios.find({salario: { $gte: 1000 } })
```

- funções limit e skip para limitar o número de documentos retornados e para ignorar alguns documentos

```
    db.usuarios.find().skip(1).limit(10)
```


- update apenas um registro

```
    db.usuarios.updateOne( {nome: "Fabrizio Mongo SH"}, { $set: { email: "alterado@g.com"}  } )
```

- update vários registros

Alterar todos os documentos que contenham SH

```
    db.usuarios.updateMany( {nome: /SH/}, { $set: { email: "alterado@g.com"}  } )
```

- delete (apenas um registro)

```
    db.usuarios.deleteOne({nome: "Fabrizio Mongo SH"})
```


# No Spring (camada repository) : 

@Query("{'nome' : ?0 }")
List<T> getByNome(  String nome );

@Query("{'nome' : :#{#nome} }")
List<T> getByNome( @Param("nome") String nome );
