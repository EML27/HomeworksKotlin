package com.example.homeworkskotlin

object GameListGenerator {
    fun getGamesList(): List<Game> {
        val resList = ArrayList<Game>()

        resList.add(Game(R.drawable.fifa20pic, "FIFA 20", "Simulator"))
        resList.add(Game(R.drawable.rdr2pic, "Red Dead Redemption", "RPG"))
        resList.add(Game(R.drawable.re2pic, "Resident Evil", "Shooter"))
        resList.add(Game(R.drawable.dl2, "Dying light", "Shooter"))
        resList.add(Game(R.drawable.kalibrpic, "Kalibr", "Shooter"))
        resList.add(Game(R.drawable.borderlandspic, "Borderlands", "Shooter"))
        resList.add(Game(R.drawable.chernobylitepic, "Chernobylite", "Simulator"))
        resList.add(Game(R.drawable.codmwpic, "Call of Duty", "Shooter"))
        resList.add(Game(R.drawable.dspic, "Death Stranding", "Kojima's game"))
        resList.add(Game(R.drawable.medievil, "Medievil", "Rogue like"))
        resList.add(Game(R.drawable.outerworldspic, "The Outer Worlds", "RPG"))
        resList.add(Game(R.drawable.postalpic, "Postal 2", "Simulator"))
        resList.add(Game(R.drawable.tcgrbpic, "Ghost Recon: Breakpoint", "Shooter"))
        resList.add(Game(R.drawable.cyberpunkpic, "Cyberpunk", "RPG"))
        return resList

    }
}