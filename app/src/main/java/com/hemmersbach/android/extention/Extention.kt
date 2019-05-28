package com.hemmersbach.android.extention

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.hemmersbach.android.result.Result
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import java.util.concurrent.TimeUnit
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty1

suspend fun <T : Any> safeApi(call: suspend () -> Response<T>): Result<T> {
    try {
        val response = call.invoke()
        // HTTP 200
        return if (response.isSuccessful) Result.Success(response.body()!!)
        // HTTP != 200
        else return Result.Error(response.message().toString())
        // Network Exception
    } catch (e: Exception) {
        return Result.Exception(e)
    }
}

fun ViewGroup.inflate(): LayoutInflater = LayoutInflater.from(context)

fun <T : Any> getData(isConnection: Boolean, remoteData: suspend () -> Result<T>) {
    if (isConnection) GlobalScope.launch {
        remoteData()
        Log.i("ext", remoteData.toString())
    }
}

fun <T> LiveData<T>.liveObserver(
    lifecycleOwner: LifecycleOwner,
    showOnUI: (receivedItem: T) -> Unit
) =
    this.observe(lifecycleOwner, Observer { it?.let { it1 -> showOnUI(it1) } })

fun Context.showAnimation(view: View, anim: Int) =
    AnimationUtils.loadAnimation(this, anim).also { view.animation = it }.start()

fun <T> List<T>.midElement(): T =
    if (this.isNotEmpty()) this[size / 2] else throw NoSuchElementException("List is empty")

val list = listOf(1, 2)
val midElement = list.midElement()
val fold = list.fold(2, { acc: Int, i: Int ->
    // println(" acc = $acc   i = $i")
    if (i != 2) acc - i else acc + i
})

fun <T> LiveData<T>.liveObserverExt(
    lifecycleOwner: LifecycleOwner,
    showOnUI: (showOnUI: T) -> Unit
) =
    this.observe(lifecycleOwner, Observer { it?.let { it1 -> showOnUI(it1) } })

fun Context.isPortait() =
    this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

val repeatFun: String.(Int) -> String = { times -> this.repeat(times) }
val twoParameters: (String, Int) -> String = repeatFun

fun transformation(f: (String, Int) -> String): String = f("dom ", 2)

/*fun <T> LiveData<T>.mergeLiveData(vararg liveData: LiveData<T>) {}*/

fun <T> merge(liveDatas: List<LiveData<T>>): LiveData<T> {
    val mediatorLiveData: MediatorLiveData<T> = MediatorLiveData()
    liveDatas.forEach { t: LiveData<T> ->
        t.value?.let { mediatorLiveData.value = it }
        mediatorLiveData.addSource(t) { mediatorLiveData.value = it }
    }
    return mediatorLiveData
}

fun ImageView.init(url: String) = Picasso.get().load(url).into(this)
val sum: Int.(Int) -> Int = { this.plus(it) }
val minus: Int.(Int) -> Int = { this.minus(it) }
fun compare(x: (Int, Int) -> Int, y: (Int, Int) -> Int, z: Int, k: Int): Int =
    if (z > k) x(z, k)
    else y(z, k)

fun Context.showToast(message: CharSequence, duaration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, duaration).show()

fun AppCompatActivity.setupActionBar(toolbar: androidx.appcompat.widget.Toolbar) {
    this.setSupportActionBar(toolbar)
    kotlin.with(supportActionBar!!) {
        setDisplayHomeAsUpEnabled(true)
        setDisplayShowTitleEnabled(false)
    }
}

inline fun instantSearch(emits: () -> Observable<String>) =
    emits().debounce(250, TimeUnit.MILLISECONDS).distinctUntilChanged()
        .filter { true }

fun <T> asynRx(
    heavyFunction: () -> T,
    succesRespond: (succesRespond: T?) -> Unit,
    errorRespond: (errorRespond: Throwable?) -> Unit
): Disposable? {
    val observable = Single.create<T> { emitter -> emitter.onSuccess(heavyFunction()) }

    return observable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ t: T -> succesRespond(t) }, { t -> errorRespond(t) })
}

/*
@RequiresPermission(value = android.Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.isConnection() =
    (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo?.isConnected
        ?: false
*/

fun Context.showSnackBar(
    view: View,
    message: CharSequence,
    duration: Int = Snackbar.LENGTH_SHORT,
    actionMessage: CharSequence,
    action: () -> Unit
) = Snackbar.make(view, message, duration).setAction(actionMessage) { action() }

fun isMultipleOf(number: Int, multipleOf: Int): Boolean {
    return number % multipleOf == 0
}

fun <T> ArrayList<T>.filterOnCondition(condition: (T) -> Boolean): ArrayList<T> {
    val result = arrayListOf<T>()
    for (item in this) {
        if (condition(item)) {
            result.add(item)
        }
    }
    return result
}

fun ImageButton.showImage(image: Int) =
    this.setImageDrawable(ContextCompat.getDrawable(this.context, image))

fun functionText(function: String.() -> String) = println(function("Hello There"))

class Person(val name: String, var age: Int) {
    fun presents() = "I'm $name, and I'm $age years old"
    fun greet(other: String) = "Hi, $other, I'm $name"
}

fun <T> printProperty(instance: T, property: KProperty1<T, *>) =
    println("${property.name} = ${property.get(instance)}")

fun <T> incrementProperty(instance: T, property: KMutableProperty1<T, Int>) {
    val value = property.get(instance)
    return property.set(instance, value + 3)
}