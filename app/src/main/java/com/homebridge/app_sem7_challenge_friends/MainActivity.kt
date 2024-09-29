package     com.homebridge.app_sem7_challenge_friends

import android.content.Intent
import      android.os.Bundle
import android.widget.Button
import      androidx.activity.enableEdgeToEdge
import      androidx.appcompat.app.AppCompatActivity
import      androidx.core.view.ViewCompat
import      androidx.core.view.WindowInsetsCompat
import com.homebridge.app_sem7_challenge_friends.favorite.FavoriteJokesActivity
import com.homebridge.app_sem7_challenge_friends.jokes.GenerateJokesActivity


public final class MainActivity : AppCompatActivity()
{
    protected final override fun onCreate(savedInstanceState: Bundle?): Unit {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        this.goToJokes()
        this.goToFavorites()
    }

    private final fun goToJokes(): Unit {
        val button: Button = findViewById(R.id.jokes_button)
        button.setOnClickListener {
            val intent = Intent(this, GenerateJokesActivity::class.java)
            startActivity(intent)
        }
    }
    private final fun goToFavorites(): Unit {
        val button: Button = findViewById(R.id.favorites_button)
        button.setOnClickListener {
            val intent = Intent(this, FavoriteJokesActivity::class.java)
            startActivity(intent)
        }
    }
}