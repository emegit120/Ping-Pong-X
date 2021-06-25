package br.com.pingpongx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.pingpongx.databinding.ActivityPlayerBinding
import br.com.pingpongx.model.LastGame

class PlayerActivity : AppCompatActivity() {


    private lateinit var binding: ActivityPlayerBinding

    private val LAST_GAME_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayerBinding.inflate(layoutInflater)

        binding.btStart.setOnClickListener{
            nextScreen()
        }


        setContentView(binding.root)
    }

    private fun nextScreen() {
        val nextScreenIntent = Intent(this, MainActivity::class.java)
        nextScreenIntent.putExtra(MainActivity.PLAYER1, binding.etPlayer1.text.toString())
        nextScreenIntent.putExtra(MainActivity.PLAYER2, binding.etPlayer2.text.toString())
        startActivityForResult(nextScreenIntent, LAST_GAME_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LAST_GAME_REQUEST_CODE && resultCode == RESULT_OK) {

            val player1Name = data?.getStringExtra("PLAYER1_NAME")
            val player2Name = data?.getStringExtra("PLAYER2_NAME")
            val player1Score = data?.getIntExtra("PLAYER1_SCORE", 0)
            val player2Score = data?.getIntExtra("PLAYER2_SCORE", 0)

            //binding.tvLastGame.text = "$player1Name $player1Score - $player2Score $player2Name"

            val lastGame = data?.getParcelableExtra<LastGame>("LAST_GAME")
            binding.tvLastGame?.text = "${lastGame?.player1Name} ${lastGame?.player1Score} - ${lastGame?.player2Score} ${lastGame?.player2Name}"
        }
    }
}