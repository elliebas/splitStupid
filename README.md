# SplitStupid 
SplitStupid is an application designed for splitting the expenses.

## Swagger
Swagger is available at ```/swagger-ui/```

#### Request body example
```$xslt
[
  {
    "participant": "Good",
    "participation": 900
  },
  {
    "participant": "Bad",
    "participation": 0
  },
 {
    "participant": "Ugly",
    "participation": 0
  }
]
```
#### Response example
```$xslt
----------------------------------------------
[Expense[Bad : 0.0], Expense[Good : 900.0], Expense[Ugly : 0.0]]
Number of participants: 3
Total expenses: 900.0
Average expense: 300.0
----------------------------------------------
Bad pays to Good 300.0
Ugly pays to Good 300.0
```