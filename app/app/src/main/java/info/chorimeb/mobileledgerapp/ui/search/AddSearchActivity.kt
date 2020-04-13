package info.chorimeb.mobileledgerapp.ui.search

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import info.chorimeb.mobileledgerapp.R

class AddSearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_search)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_search, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.maxWidth = Integer.MAX_VALUE

        return true
    }
}
