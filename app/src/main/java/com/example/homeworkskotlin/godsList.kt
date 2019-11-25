package com.example.homeworkskotlin

object godsList {
    private var list = ArrayList<God>()

    init {
        list.add(God("Zeus", "Lightnings and thunder"))
        list.add(God("Poseidon", "Water"))
        list.add(God("Hades", "Underground"))
        list.add(God("Hera", "Hating husband"))
        list.add(God("Athena", "War and knowledge"))
        list.add(God("Aphrodite", "Love"))
        list.add(God("Hephaestus", "Fire and iron"))
        list.add(God("Hermes", "Trade"))
        list.add(God("Ares", "War"))
        list.add(God("Apollo", "Landing on Moon"))
    }

    fun size(): Int = list.size
    fun getData(): ArrayList<God> = list

    fun addGod(god: God) {
        list.add(god)
    }

    fun addGod(name: String, power: String) {
        list.add(God(name, power))
    }

    fun addGod(name: String, power: String, position: Int) {
        list.add(position, God(name, power))
    }

    // иронично
    fun deleteGod(god: God) {
        list.remove(god)
    }
}
