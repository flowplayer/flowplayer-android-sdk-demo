package com.flowplayer.player.demo

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.flowplayer.android.chromecast.ChromecastManager
import com.flowplayer.player.demo.demos.*


class MainActivity : AppCompatActivity(), ItemAdapter.OnItemClickListener {
    private var selectedMediaType = Constants.MEDIA_TYPE_FLOWPLAYER

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = ArrayList<Item>()
        items.add(Item("FlowplayerView Activity", "A FlowplayerView inside an Activity", Intent(this, FlowplayerViewActivity::class.java)))
        items.add(Item("FlowplayerView AppCompatActivity", "A FlowplayerView inside an AppCompatActivity", Intent(this, FlowplayerViewAppCompatActivity::class.java)))
        items.add(Item("FlowplayerSupportFragment AppCompatActivity", "A FlowplayerSupportFragment added programmatically inside an AppCompatActivity", Intent(this, FlowplayerSupportFragmentActivity::class.java)))
        items.add(Item("FlowplayerSupportFragment XML AppCompatActivity", "A FlowplayerSupportFragment added via XML inside an AppCompatActivity", Intent(this, FlowplayerSupportFragmentXmlActivity::class.java)))
        items.add(Item("FlowplayerChromecastFragment Activity", "A FlowplayerSupportFragment which cast to a connected Chromecast device", Intent(this, FlowplayerChromecastFragmentActivity::class.java)))

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = ItemAdapter(items, this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activity_main, menu)
        ChromecastManager.getInstance().setMediaRouteButton(this, menu, R.id.media_route_menu_item)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.item_settings) {
            showMediaTypeDialog()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClicked(item: Item) {
        startActivity(item.clickIntent.putExtra(Constants.EXTRA_MEDIA_TYPE, selectedMediaType))
    }

    private fun showMediaTypeDialog() {
        val mediaTypes = arrayOf(Constants.MEDIA_TYPE_FLOWPLAYER, Constants.MEDIA_TYPE_DASH, Constants.MEDIA_TYPE_SS, Constants.MEDIA_TYPE_HLS, Constants.MEDIA_TYPE_MP4)
        val dialog = AlertDialog.Builder(this)
                .setTitle("Select media type")
                .setItems(mediaTypes) { dialog, which ->
                    selectedMediaType = mediaTypes[which]
                }
                .create()

        dialog.show()
    }
}
