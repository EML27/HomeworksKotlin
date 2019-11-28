package com.example.homeworkskotlin

object creaturesList {
    private var list = ArrayList<Creature>()

    init {
        list.add(
            Creature(
                R.drawable.aries_pic,
                "Minotaur",
                "Angry as a bull",
                ArrayList<Int>().apply {
                    add(R.drawable.mino1)
                    add(R.drawable.mino2)
                    add(R.drawable.mino3)
                })
        )
        list.add(
            Creature(
                R.drawable.hydra_pic,
                "Hydra",
                "Many heads lol",
                ArrayList<Int>().apply {
                    add(R.drawable.hydra1)
                    add(R.drawable.hydra2)
                })
        )
        list.add(
            Creature(
                R.drawable.medusa_pic,
                "Medusa",
                "Your actual ex",
                ArrayList<Int>().apply {
                    add(R.drawable.medusa1)
                    add(R.drawable.medusa2)
                })
        )
    }

    fun getData() = list


}
