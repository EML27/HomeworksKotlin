package com.example.homeworkskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_recycler.*

class RecyclerActivity : AppCompatActivity() {

    private var adapter: GameAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        adapter = GameAdapter(GameLIstGenerator().getGamesList()) { game ->
            startActivity(
                GamePageActivity.createIntent(
                    this,
                    game.name,
                    game.type,
                    game.picSrcId
                )
            )
        }

        rv_games.adapter=adapter


    }


}
