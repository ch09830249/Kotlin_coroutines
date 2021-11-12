package com.example.myapplication2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Test 1
//        CoroutineScope(Dispatchers.Main).launch{
//            Log.i("Kenny","${Thread.currentThread().name} current job") //main thread
//            long_time_job1()
//        }

//        //Test2
//        CoroutineScope(Dispatchers.Main).launch{
//            val time = measureTimeMillis {
//                val one = async{ doSomeThing1() }
//                val two = async { doSomeThing2() }
//                Log.i("Kenny", "${one.await()+two.await()}")
//            }
//            Log.i("Kenny", "Complete in $time ms")
//        }

        //test3
        CoroutineScope(Dispatchers.Unconfined).launch{
            Log.v("Kenny","Unconfined      : I'm working in thread ${Thread.currentThread().name}")
            delay(1000)
            Log.v("Kenny","Unconfined      : After delay in thread ${Thread.currentThread().name}")
        }

        //test4
//        CoroutineScope(Dispatchers.Default).launch{
//            Log.v("Kenny","Default      : I'm working in thread ${Thread.currentThread().name}")
//            Log.v("Kenny","Default      : After delay in thread ${Thread.currentThread().name}")
//        }
//        CoroutineScope(Dispatchers.IO).launch{
//            Log.v("Kenny","IO      : I'm working in thread ${Thread.currentThread().name}")
//            Log.v("Kenny","IO      : After delay in thread ${Thread.currentThread().name}")
//        }


    }

    suspend fun long_time_job1(){
        return withContext(Dispatchers.IO){
            delay(1000)
            Log.i("Kenny","${Thread.currentThread().name} do task long_time_job1")
            long_time_job2()
        }
    }

    suspend fun long_time_job2(){
        return withContext(Dispatchers.Default){
            delay(1000)
            Log.i("Kenny","${Thread.currentThread().name} do task long_time_job2")
        }
    }

    suspend fun doSomeThing1(): Int{
        delay(1000)
        return 19
    }

    suspend fun doSomeThing2(): Int{
        delay(1000)
        return 23
    }
}