package com.me.instafeed

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.me.instafeed.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var randAdapter: RandAdapter
    private lateinit var trendAdapter: TrendAdapter

    private lateinit var binding: ActivityMainBinding

    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()

        setupObservers()
    }

    private fun setupViewModel() {
        viewModel = MainViewModel(GiphyRepository())
    }

    private fun setupListeners() {
        binding.recyclerViewRandom.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val linearLayoutManager =
                    binding.recyclerViewRandom.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (!recyclerView.canScrollVertically(1)) {
                        if (linearLayoutManager != null) {
                            // Last item reached
                            viewModel.getRandom()
                            isLoading = true;
                        }
                    }
                }
            }
        })
    }

    private fun setupObservers() {
        viewModel.resultRandLiveData.observe(this) { result ->
            if (result != null) {
                binding.recyclerViewRandom.visibility = View.VISIBLE
                binding.recyclerViewTrending.visibility = View.VISIBLE
                binding.progressBar.visibility = View.INVISIBLE

                if (binding.recyclerViewRandom.adapter == null) {
                    setupRandomAdapter(result.body().data)
                } else {
                    randAdapter.addGif(result.body().data)
                }

                isLoading = false;

                //binding.recyclerViewRandom.scrollToPosition(
                //    (binding.recyclerViewRandom.adapter?.itemCount ?: 0) - 1)

                setupListeners()
            }
        }

        viewModel.resultTrendLiveData.observe(this) { result ->
            if (result != null) {
                binding.recyclerViewRandom.visibility = View.VISIBLE
                binding.recyclerViewTrending.visibility = View.VISIBLE
                binding.progressBar.visibility = View.INVISIBLE

                if (binding.recyclerViewTrending.adapter == null)
                    setupTrendingAdapter(result.body().data)
                trendAdapter.addGif(result.body().data)
            }
        }

        for (i in 1..3) {
            viewModel.getRandom()
        }

        viewModel.getTrending()
    }

    fun setupRandomAdapter(data: DataResponse) {
        binding.recyclerViewRandom.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        randAdapter = RandAdapter(this, arrayListOf(data)) { item ->
            val shareIntent = ShareCompat.IntentBuilder.from(this@MainActivity)
                .setType("text/plain")
                .setText(item.url)
                .intent
            if (shareIntent.resolveActivity(packageManager) != null) {
                startActivity(shareIntent)
            }
        }

        val dividerHorizontal = DividerItemDecoration(
            binding.recyclerViewRandom.getContext(),
            DividerItemDecoration.VERTICAL
        )

        ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.divider
        )?.let {
            dividerHorizontal.setDrawable(
                it
            )
        }

        binding.recyclerViewRandom.addItemDecoration(dividerHorizontal)
        binding.recyclerViewRandom.adapter = randAdapter
    }

    fun setupTrendingAdapter(data: ArrayList<DataResponse>) {
        binding.recyclerViewTrending.setLayoutManager(
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        )

        trendAdapter = TrendAdapter(this, data)

        binding.recyclerViewTrending.adapter = trendAdapter
    }
}