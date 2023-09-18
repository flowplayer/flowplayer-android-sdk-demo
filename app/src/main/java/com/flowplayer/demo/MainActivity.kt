package com.flowplayer.demo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.flowplayer.demo.activity.*

class MainActivity : AppCompatActivity(), ItemAdapter.OnItemClickListener {
    private var selectedMediaType = Constants.MEDIA_TYPE_FLOWPLAYER_NO_ADS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "OnCreate")

        setContentView(R.layout.activity_main)

        val items = ArrayList<Item>()
        items.add(
            Item(
                "FlowplayerView Activity",
                "A FlowplayerView inside an Activity",
                Intent(this, FlowplayerViewActivity::class.java)))
        items.add(
            Item(
                "FlowplayerView AppCompatActivity",
                "A FlowplayerView inside an AppCompatActivity",
                Intent(this, FlowplayerViewAppCompatActivity::class.java)))
        items.add(
            Item(
                "FlowplayerSupportFragment AppCompatActivity",
                "A FlowplayerSupportFragment added programmatically inside an AppCompatActivity",
                Intent(this, FlowplayerSupportFragmentActivity::class.java)))
        items.add(
            Item(
                "FlowplayerSupportFragment XML AppCompatActivity",
                "A FlowplayerSupportFragment added via XML inside an AppCompatActivity",
                Intent(this, FlowplayerSupportFragmentXmlActivity::class.java)))
        items.add(
            Item(
                "FlowplayerSupportFragment Picture in Picture Activity",
                "A Picture in Picture example using FlowplayerSupportFragment",
                Intent(this, FlowplayerSupportFragmentPiPActivity::class.java)))

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = ItemAdapter(items, this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activity_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_settings) {
            showMediaTypeDialog()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClicked(item: Item) {
        startActivity(item.clickIntent.putExtra(Constants.EXTRA_MEDIA_TYPE, selectedMediaType))
    }

    private fun showMediaTypeDialog() {
        val mediaTypes =
            arrayOf(
                Constants.MEDIA_TYPE_FLOWPLAYER_NO_ADS,
                Constants.MEDIA_TYPE_FLOWPLAYER_PRE_POST_ADS,
                Constants.MEDIA_TYPE_FLOWPLAYER_PRE_AD_SKIPPABLE,
                Constants.MEDIA_TYPE_DASH,
                Constants.MEDIA_TYPE_SS,
                Constants.MEDIA_TYPE_HLS,
                Constants.MEDIA_TYPE_MP4,
                Constants.MEDIA_TYPE_DRM_DASH,
                Constants.MEDIA_TYPE_DASH_LIVESTREAM)
        val dialog =
            AlertDialog.Builder(this)
                .setTitle("Select media type")
                .setItems(mediaTypes) { _, which -> selectedMediaType = mediaTypes[which] }
                .create()

        dialog.show()
    }
}
