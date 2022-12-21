package com.example.mvvmarc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.doodleandroid.RecyclerAdaptor

import com.example.mvvmarc.api.ApiInterface
import com.example.mvvmarc.api.ApiUtilities
import com.example.mvvmarc.databinding.ActivityMainBinding
import com.example.mvvmarc.model.JokesModel
import com.example.mvvmarc.model.Meme
import com.example.mvvmarc.repository.JokeRepository
import com.example.mvvmarc.viewModel.JokesViewModel
import com.example.mvvmarc.viewModel.JokesViewModelFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

import io.reactivex.rxjava3.core.Observable

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable





class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var jokesViewModel: JokesViewModel
    private lateinit var list:ArrayList<Meme>
    private lateinit var recyclerView: RecyclerView
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list=ArrayList()
        recyclerView=findViewById(R.id.rvList)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager=LinearLayoutManager(this)

//        simpleObs()
//        createObs()
        netWorkCallObs()
    }

    private fun simpleObs(){
        val list= listOf("Apple","Man","Sun")
        val observable=Observable.fromIterable(list)
        observable.subscribe(object :io.reactivex.rxjava3.core.Observer<String>{
            override fun onSubscribe(d: Disposable) {
                println("on sub call")
                println(d.toString())
            }

            override fun onNext(t: String) {
                println(t)

            }

            override fun onError(e: Throwable) {
                println(e.message)
            }

            override fun onComplete() {
                println("complete")
            }
        }   )
    }
    private fun netWorkCallObs(){
        val apiInterface=ApiUtilities.rxGetInstance().create(ApiInterface::class.java)
        compositeDisposable.add(apiInterface.getJokes().observeOn(AndroidSchedulers.mainThread()).subscribe {
                onNxt->displayData(onNxt)
        })




    }

    private fun displayData(onNxt: JokesModel) {
        for (x in onNxt.data.memes){
            list.add(x)
        }
        println(onNxt.toString())
                    val adaptor=RecyclerAdaptor(list)
            recyclerView.adapter=adaptor

    }

    private fun createObs(){
        val observable=Observable.create<String>{
            it.onNext("Helo")
            it.onNext("There")
            it.onComplete()

        }
        observable.subscribe(object :io.reactivex.rxjava3.core.Observer<String>{
            override fun onSubscribe(d: Disposable) {
                println("on sub call")
                println(d.toString())
            }

            override fun onNext(t: String) {
                println(t)

            }

            override fun onError(e: Throwable) {
                println(e.message)
            }

            override fun onComplete() {
                println("complete")
            }
        }   )
    }

private fun mvvm(){

    val apiInterface=ApiUtilities.getInstance().create(ApiInterface::class.java)
    val repository=JokeRepository(apiInterface)
    jokesViewModel=ViewModelProvider(this,JokesViewModelFactory(repository)).get(JokesViewModel::class.java)
    jokesViewModel.jokes.observe(this) {
        for (x in it.data.memes) {
            list.add(x)
        }
        val adaptor=RecyclerAdaptor(list)
        recyclerView.adapter=adaptor


    }
    binding.pname="binded Data"

}
}


