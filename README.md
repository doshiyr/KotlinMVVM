# Kotlin MVVM

MVVM(Model View ViewModel) sample in Kotlin using the components ViewModel, LiveData and Retrofit.

## MVVM Pattern (Model View ViewModel)
<img src="assets/AndroidMVVM.png" height="360">

## MVVM Code

### View Model
```kotlin
class BoredViewModel(val context: Application) : AndroidViewModel(context) {

    private val _isLoading : MutableLiveData<Boolean> = MutableLiveData()
    val isLoading : LiveData<Boolean> get() = _isLoading

    private val _boredActivity : MutableLiveData<BoredActivity> = MutableLiveData()
    val boredActivity : LiveData<BoredActivity> get() = _boredActivity
    
    private val _isError : MutableLiveData<String> = MutableLiveData()
    val isError: LiveData<String> get() = _isError

    val boredRepository = BoredRepository()

    init {
        getBoredActivity()
    }
    
    fun getBoredActivity(){
        _isLoading.value = true
        boredRepository.getActivity().enqueue(object : Callback<BoredActivity>{
            override fun onResponse(call: Call<BoredActivity>, response: Response<BoredActivity>) {
                _isLoading.value = false
                if(response.isSuccessful){
                    _boredActivity.value = response.body()
                } else {
                    _isError.value = context.getString(R.string.str_something_wrong)
                }
            }
            
            override fun onFailure(call: Call<BoredActivity>, t: Throwable) {
                _isLoading.value = false
                _isError.value = context.getString(R.string.str_something_wrong)
            }
        })
    }

}
```

### Activity
```kotlin
class MainActivity : AppCompatActivity() {

    lateinit var viewModel: BoredViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(BoredViewModel::class.java)
        viewModel.isLoading.observe(this, Observer {
            // Show/Hide Loader
        })
        viewModel.isError.observe(this, Observer {
            // Show/Hide Error
        })
        viewModel.boredActivity.observe(this, Observer {boredActivity ->
            // Update your UI
        })
        btnNewActivity.setOnClickListener {
            viewModel.getBoredActivity()
        }
    }
}
```
 
## Demo

<img src="assets/kotlinmvvm.gif" height="480"> 
 
## Screenshots

<img src="assets/screenshot.jpg" height="480">  <img src="assets/screenshot_loading.jpg" height="480">  <img src="assets/screenshot_error.jpg" height="480">

## Dependencies

- Retrofit 2 - [Version: 2.8.1](https://square.github.io/retrofit/)
- AndroidX - [Version: 1.2.0](https://mvnrepository.com/artifact/androidx)
- Arch Lifecycle - [Version: 2.2.0](https://developer.android.com/jetpack/androidx/releases/lifecycle)

> Any questions or comment is appreciated. If you find this useful, consider giving it a star.
> Create an issue for any questions.