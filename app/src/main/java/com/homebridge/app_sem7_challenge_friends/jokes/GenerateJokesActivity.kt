package     com.homebridge.app_sem7_challenge_friends.jokes

import      android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import      androidx.activity.enableEdgeToEdge
import      androidx.appcompat.app.AppCompatActivity
import      androidx.core.view.ViewCompat
import      androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import      com.homebridge.app_sem7_challenge_friends.R
import com.homebridge.app_sem7_challenge_friends.api.ApiResponse
import com.homebridge.app_sem7_challenge_friends.api.JokeApi
import com.homebridge.app_sem7_challenge_friends.db.AppDatabase
import com.homebridge.app_sem7_challenge_friends.favorite.JokeAdapter
import com.homebridge.app_sem7_challenge_friends.models.Joke
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GenerateJokesActivity : AppCompatActivity(), JokeAdapter.OnItemClickListener {
    private lateinit var btnGenerateJokes: Button
    private lateinit var etResult: EditText
    private lateinit var rvjokes: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?): Unit {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_generate_jokes)

        setSupportActionBar(findViewById(R.id.generate_joke_activity_toolbar))

        btnGenerateJokes = findViewById(R.id.generate_button)
        etResult = findViewById(R.id.results_number_text_input)
        rvjokes = findViewById(R.id.jokes_recycler_view)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

    }

    override fun onResume() {
        super.onResume()
        btnGenerateJokes.setOnClickListener {
            val jokes = etResult.text.toString().toInt()
            loadJokes(jokes) { jokes ->
                rvjokes.adapter = JokeAdapter(jokes, this)
                rvjokes.layoutManager = LinearLayoutManager(this@GenerateJokesActivity)
            }
        }
    }

    fun loadJokes(jokes: Int, onComplete: (List<Joke>) -> Unit) {
        var count = jokes

        val retrofit = Retrofit.Builder()
            .baseUrl("https://v2.jokeapi.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val JokeService: JokeApi = retrofit.create(JokeApi::class.java)

        val request = JokeService.getJoke(count)

        request.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val jokeApiJokes: ApiResponse = response.body()!!
                    val jokeList = mutableListOf<Joke>()

                    jokeApiJokes.jokes?.forEach {
                        jokeList.add(it.toJoke())
                    }

                    onComplete(jokeList)
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(
                    this@GenerateJokesActivity,
                    "Error loading jokes",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun onItemClick(joke: Joke) {
        val dao = AppDatabase.getInstance(this).getDao()
        dao.insertOne(joke)

        Toast.makeText(this, "Joke "+joke.setup+" added to favorites", Toast.LENGTH_SHORT).show()
    }
}