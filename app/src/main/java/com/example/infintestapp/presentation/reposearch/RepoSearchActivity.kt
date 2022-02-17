package com.example.infintestapp.presentation.reposearch

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.infintestapp.R
import com.example.infintestapp.app.InfinTestApp
import com.example.infintestapp.databinding.ActivityRepoSearchBinding
import com.example.infintestapp.domain.models.Item
import javax.inject.Inject

class RepoSearchActivity : AppCompatActivity() {


    @Inject
    lateinit var mViewModelFactory: RepoSearchViewModelFactory
    private lateinit var mViewModel: RepoSearchViewModel
    private lateinit var binding: ActivityRepoSearchBinding
    private lateinit var adapter: ReposListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (applicationContext as InfinTestApp).appComponent.inject(this)


        initRecyclerView()
        initViewModel()

        binding.etvSearch.addTextChangedListener {
            val string = binding.etvSearch.text.toString()
            mViewModel.filterString.value = string
        }

        binding.imgBtnClearSearch.setOnClickListener {
            binding.etvSearch.text?.clear()
        }

    }

    private fun initViewModel() {
        mViewModel = ViewModelProvider(this, mViewModelFactory).get(RepoSearchViewModel::class.java)

        mViewModel.listToDraw.observe(this, {
            if (!it.isNullOrEmpty()) {
                Log.d("LIST", it.toString())
                adapter.list = it
            } else {
                adapter.list.clear()
                adapter.notifyDataSetChanged()
            }
        })

        mViewModel.loading.observe(this, {
            if (it == true) {
                binding.loader.visibility = View.VISIBLE
                binding.rvRepositories.visibility = View.INVISIBLE
            } else {
                binding.loader.visibility = View.GONE
                binding.rvRepositories.visibility = View.VISIBLE
            }
        })

        mViewModel.filterString.observe(this, {
            mViewModel.getRepositoriesList()
        })

        mViewModel.errorLoading.observe(this, {
            if (it != null)
                Toast.makeText(this, resources.getString(R.string.error), Toast.LENGTH_SHORT).show()
        })
    }

    private fun initRecyclerView() {
        adapter = ReposListAdapter(object : ReposListAdapter.OnAdapterItemClickListener {
            override fun onClick(repo: Item) {
                val intent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(repo.htmlUrl))
                startActivity(intent)
            }

            override fun loadMore(lastItemIndex: Int) {
                mViewModel.getMoreRepositoriesList(lastItemIndex)
            }

        }, this)

        binding.rvRepositories.layoutManager = LinearLayoutManager(this)
        binding.rvRepositories.adapter = adapter
    }
}