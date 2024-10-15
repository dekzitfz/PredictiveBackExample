package id.adiandrea.predictivebackexample

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import id.adiandrea.predictivebackexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //setup list
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = ListAdapter(generateSampleData())

    }

    private fun generateSampleData(): MutableList<Pokemon> {
        val data = mutableListOf<Pokemon>()
        data.add(Pokemon(id = 1, name = "Bulbasaur", imageURL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"))
        data.add(Pokemon(id = 25, name = "Pikachu", imageURL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png"))
        data.add(Pokemon(id = 6, name = "Charizard", imageURL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png"))
        return data
    }
}