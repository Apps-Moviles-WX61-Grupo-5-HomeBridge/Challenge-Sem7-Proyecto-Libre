package     com.homebridge.app_sem7_challenge_friends.favorite

import      android.os.Bundle
import      androidx.activity.enableEdgeToEdge
import      androidx.appcompat.app.AppCompatActivity
import      androidx.recyclerview.widget.LinearLayoutManager
import      androidx.recyclerview.widget.RecyclerView
import      com.homebridge.app_sem7_challenge_friends.R



public final class FavoriteJokesActivity : AppCompatActivity(), JokeAdapter.OnItemClickListener
{
    private lateinit var m_Favorites: RecyclerView

    protected final override fun onCreate(savedInstanceState: Bundle?): Unit {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_favorite_jokes)

        setSupportActionBar(findViewById(R.id.favorite_jokes_activity_toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        this.m_Favorites = findViewById(R.id.favorites_recycler_view)
    }

    protected final override fun onResume(): Unit {
        super.onResume()

        this.loadJokes { jokes ->
            this.m_Favorites.adapter = JokeAdapter(jokes, this)
            this.m_Favorites.layoutManager = LinearLayoutManager(this@FavoriteJokesActivity)
        }
    }

    private final fun loadJokes(
        onComplete: (List<Joke>) -> Unit
    ): Unit {
        //  Code here the DAO thingy.
    }
    public final override fun onItemClick(joke: Joke): Unit {
        //  Code here the DAO thingy.
    }
}