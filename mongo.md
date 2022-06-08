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

- procurar um usuário pelo nome

```
    db.usuarios.find({nome: "Fabrizio"})
```

- procurar um usuário pelo nome com letra b

```
    db.usuarios.find({nome: { $regex: /a/ } })
```

- procurar um usuário pelo salário (usando operador maior que)

```
    db.usuarios.find({salario: { $gte: 1000 } })
```

- funções limit e skip para limitar o número de documentos retornados e para ignorar alguns documentos

```
    db.usuarios.find().skip(1).limit(10)
```


- update (apenas um registro)

```
    db.usuarios.updateOne( {nome: "Fabrizio Mongo SH"}, { $set: { email: "alterado@g.com"}  } )
```

- delete (apenas um registro)

```
    db.usuarios.deleteOne({nome: "Fabrizio Mongo SH"})
```



