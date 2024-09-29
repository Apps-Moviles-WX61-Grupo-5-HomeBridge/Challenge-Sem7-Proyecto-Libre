package     com.homebridge.app_sem7_challenge_friends.favorite

import      android.os.Bundle
import      androidx.activity.enableEdgeToEdge
import      androidx.appcompat.app.AppCompatActivity
import      androidx.recyclerview.widget.LinearLayoutManager
import      androidx.recyclerview.widget.RecyclerView
import      com.homebridge.app_sem7_challenge_friends.R
import com.homebridge.app_sem7_challenge_friends.db.AppDatabase
import com.homebridge.app_sem7_challenge_friends.models.Joke


class FavoriteJokesActivity : AppCompatActivity(), JokeAdapter.OnItemClickListener
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

    override fun onResume(){
        super.onResume()

        this.loadJokes { jokes ->
            this.m_Favorites.adapter = JokeAdapter(jokes, this)
            this.m_Favorites.layoutManager = LinearLayoutManager(this@FavoriteJokesActivity)
        }
    }

    private fun loadJokes(onComplete: (List<Joke>) -> Unit){
        val dao = AppDatabase.getInstance(this).getDao()

        onComplete(dao.getAll())
    }
    override fun onItemClick(joke: Joke){
        val dao = AppDatabase.getInstance(this).getDao()

        dao.delete(joke)

        this.loadJokes { jokes ->
            this.m_Favorites.adapter = JokeAdapter(jokes, this)
            this.m_Favorites.layoutManager = LinearLayoutManager(this@FavoriteJokesActivity)
        }
    }
}