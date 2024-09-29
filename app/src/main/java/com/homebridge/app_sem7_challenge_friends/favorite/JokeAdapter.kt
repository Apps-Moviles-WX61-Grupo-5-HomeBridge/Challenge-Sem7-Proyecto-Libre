package     com.homebridge.app_sem7_challenge_friends.favorite

import      android.view.LayoutInflater
import      android.view.View
import      android.view.ViewGroup
import      android.widget.ImageButton
import      android.widget.TextView
import      androidx.recyclerview.widget.RecyclerView.Adapter
import      androidx.recyclerview.widget.RecyclerView.ViewHolder
import      com.homebridge.app_sem7_challenge_friends.R
import com.homebridge.app_sem7_challenge_friends.models.Joke
import com.squareup.picasso.Picasso


class JokeAdapter(private val jokes: List<Joke>, private val clickListener: OnItemClickListener) : Adapter<JokeAdapter.JokeViewHolder>()
{
    inner class JokeViewHolder(itemView: View): ViewHolder(itemView)
    {
        private val tvCategory: TextView    = itemView.findViewById(R.id.joke_category_text_view)
        private val tvContent: TextView     = itemView.findViewById(R.id.joke_setup)
        private val tvContenttwo: TextView  = itemView.findViewById(R.id.joke_delivery)
        private val favorite: ImageButton   = itemView.findViewById(R.id.save_to_favorites_button)

        fun bind(joke: Joke, clickListener: OnItemClickListener) {
            tvCategory.text = joke.category
            tvContent.text  = joke.setup
            tvContenttwo.text = joke.delivery

            favorite.setOnClickListener {
                clickListener.onItemClick(joke)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_joke, parent, false)
        return JokeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return jokes.size
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        holder.bind(jokes[position], clickListener)
    }

    interface OnItemClickListener {
        fun onItemClick(joke: Joke)
    }
}