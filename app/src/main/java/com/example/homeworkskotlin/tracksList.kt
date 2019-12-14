package com.example.homeworkskotlin

object tracksList {
    private var list = ArrayList<Track>()

    init {
        list.add(
            Track(
                "Let's go",
                "Stuck in the sound",
                R.raw.letsgo_sits,
                R.drawable.stuckinthesound_cover
            )
        )
        list.add(Track("Delta", "C2C", R.raw.delta_c2c, R.drawable.c2c_cover))
        list.add(
            Track(
                "The greatest view",
                "Flume",
                R.raw.thegreatestview_flume,
                R.drawable.flume_cover
            )
        )
        list.add(
            Track(
                "Lonely boy",
                "The black keys",
                R.raw.lonelyboy_tbk,
                R.drawable.theblackkeys_cover
            )
        )
        list.add(
            Track(
                "How you like me now",
                "The heavy",
                R.raw.howyoulikemenow_theheavy,
                R.drawable.theheavy_cover
            )
        )
        list.add(
            Track(
                "Jumpsuit",
                "Twenty one pilots",
                R.raw.jumpsuit_top,
                R.drawable.twentyonepilots_cover
            )
        )
        list.add(
            Track(
                "I feel like I'm drowning",
                "Two feet",
                R.raw.ifeellikeimdrowning_twofeet,
                R.drawable.twofeet_cover
            )
        )
    }

    fun getData() = list

    fun getPosition(track: Track) =
        list.indexOf(track)

    fun getTrackByNumber(num: Int) =
        list[num]

}
