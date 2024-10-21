package com.example.nogizaka5th

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {


    private lateinit var tvName: TextView
    private lateinit var tvReference: TextView
private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        supportActionBar?.title = "Profile"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        webView= findViewById<WebView>(R.id.webView) as WebView



        val ivProfile: ImageView = findViewById(R.id.ivProfile)
        tvName = findViewById(R.id.tvName)
        val tvBirthplace: TextView = findViewById(R.id.tvBirthplace)
        val tvBirthday: TextView = findViewById(R.id.tvBirthday)
        val tvNickname: TextView = findViewById(R.id.tvNickname)
        val tvJapaneseName: TextView = findViewById(R.id.tvJapaneseName)
        val tvTriviaContent: TextView = findViewById(R.id.tvTrivia)
        tvReference = findViewById(R.id.tvReference)

        val name = intent.getStringExtra("EXTRA_NAME") ?: return


        val names = resources.getStringArray(R.array.data_name)
        val cities = resources.getStringArray(R.array.data_city)
        val birthdays = resources.getStringArray(R.array.nogi_birthdays)
        val photos = resources.obtainTypedArray(R.array.data_photo)
        val nickname = resources.getStringArray(R.array.data_nick)
        val kanji = resources.getStringArray(R.array.data_kanji)
        val trivia = resources.getStringArray(R.array.data_trivia)
        val reference = resources.getStringArray(R.array.references)
        val videoIds = resources.getStringArray(R.array.video)

        val index = names.indexOf(name)


        if (index != -1) {
            tvName.text = names[index]
            tvBirthplace.text = "Birthplace: " + cities[index]
            tvBirthday.text = "Birthday: " + birthdays[index]
            tvNickname.text = "Nickname: " + nickname[index]
            tvJapaneseName.text = kanji[index]
            tvReference.text = reference[index]
            val memberTrivia = trivia[index].split("; ")
            tvTriviaContent.text = memberTrivia.joinToString("\n")
            val photoResId = photos.getResourceId(index, -1)
            if (photoResId != -1) {
                ivProfile.setImageResource(photoResId)
            }
        } else {

            tvName.text = "Profile not found"
            tvBirthplace.text = "City not available"
            tvBirthday.text = "Birthday not available"
        }
        val videoId = videoIds.getOrNull(index) ?: return
        val videoUrl = "https://www.youtube.com/embed/$videoId"
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl(videoUrl)

        photos.recycle()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {

                val characterName = tvName.text
                val characterReference = tvReference.text

                val shareText = "$characterName is one of 5th Generation of Nogizaka46! \nRead more in $characterReference"
                val intent = Intent(Intent.ACTION_SEND).apply {
                    putExtra(Intent.EXTRA_TEXT, shareText)
                    type = "text/plain"
                }

                startActivity(Intent.createChooser(intent, "Share with"))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
