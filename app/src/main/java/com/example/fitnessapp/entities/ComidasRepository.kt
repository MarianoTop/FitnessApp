package com.example.fitnessapp.entities

class ComidasRepository {

    var comidas : MutableList<Comida> = mutableListOf()
    var ingredientes : MutableList<String> = mutableListOf()

    init {
        ingredientes.add("Ingrediente1")
        ingredientes.add("Ingrediente2")
        ingredientes.add("Ingrediente3")
        ingredientes.add("Ingrediente4")
        ingredientes.add("Ingrediente5")
        ingredientes.add("Ingrediente6")
        ingredientes.add("Ingrediente7")
        ingredientes.add("Ingrediente8")

        comidas.add(Comida("1", "Ensalada de pasta con mozzarella y atún", "https://i.blogs.es/2355f7/ensalada-fusilli2/1366_2000.jpg", 100.0, ingredientes, 0))
        comidas.add(Comida("2", "Pechugas de pollo jugosas al horno", "https://i.blogs.es/ae7fdb/pollo/1366_2000.jpg", 100.0, ingredientes, 0))
        comidas.add(Comida("3", "Ensalada de arroz salvaje", "https://i.blogs.es/33fb5e/ensalada-de-arroz-salvaje/1366_2000.jpg", 100.0, ingredientes, 0))
        comidas.add(Comida("4", "Ensalada hummus", "https://i.blogs.es/58df55/ensalada-hummus/1366_2000.jpg", 100.0, ingredientes, 0))
        comidas.add(Comida("5", "Ensalada de garbanzos, aguacate y queso feta", "https://i.blogs.es/7c5fc6/ensalada-de-garbanzos-aguacate-y-queso-feta/1366_2000.jpg", 100.0, ingredientes, 0))
        comidas.add(Comida("6", "Ensalada al estilo asiático de tofu, arroz y brócoli", "https://i.blogs.es/b88b27/ensalada-asiatica-tofu-arroz-brocoli-300118-0001/1366_2000.jpg", 100.0, ingredientes, 0))
        comidas.add(Comida("7", "Ensalada niçoise o nizarda", "https://i.blogs.es/7f40a4/ensaladanicoise/1366_2000.jpg", 100.0, ingredientes, 0))
        comidas.add(Comida("8", "Ensalada de queso a la plancha con sandía y migas de alcaparras", "https://i.blogs.es/b3c591/ensalada-de-queso-a-la-plancha-con-sandia/1366_2000.jpg", 100.0, ingredientes, 0))
        comidas.add(Comida("9", "Omelette Jamon y Queso", "https://i.ytimg.com/vi/nLmOuGI3bu0/maxresdefault.jpg", 100.0, ingredientes, 0))
    }

}