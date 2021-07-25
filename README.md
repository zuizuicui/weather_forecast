# weather_forecast

**Multi model structure**
https://developer.android.com/training/dependency-injection/dagger-multi-module

Apply on code:

<img width="703" alt="Screen Shot 2021-07-25 at 15 40 51" src="https://user-images.githubusercontent.com/6103507/126893130-8e95e8db-ebcf-4ba6-821b-34d5aa668c6d.png">


**Clean architect structure**
https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html

Apply on code:

<img width="710" alt="Screen Shot 2021-07-25 at 16 42 36" src="https://user-images.githubusercontent.com/6103507/126894778-19593515-63e9-4de5-b1ff-cd27dd9a5a3f.png">

Flow of control apply with coroutine

<img width="548" alt="Screen Shot 2021-07-25 at 16 42 50" src="https://user-images.githubusercontent.com/6103507/126894789-4de91dde-ba78-4c2e-b69e-dcc5a7c16677.png">


**Kotlin coroutine best practices**
https://developer.android.com/kotlin/coroutines/coroutines-best-practices

Applied:
* Suspend functions should be safe to call from the main thread
* The ViewModel should create coroutines
* Don't expose mutable types
* The data and business layer should expose suspend functions and Flows
* Inject TestCoroutineDispatcher in tests
* Watch out for exceptions

**Libs used:**
* Retrofit public key pining
* Retrofit cache
* Retrofit mock test
* Data binding https://developer.android.com/topic/libraries/data-binding
* Kotlin coroutine https://kotlinlang.org/docs/coroutines-overview.html
* Dagger injection: https://developer.android.com/training/dependency-injection/dagger-android

**Local Development**

**CHECK OUT CODE MASTER BRANCH**

Use the gradlew build command or use "Import Project" in Android Studio.

./gradlew clean build - Build the entire example and execute unit and integration tests plus lint check.
./gradlew installDebug - Install the debug apk on the current connected device.


**Check list of items the candidate has done.**

1. Programming language:Kotlin.

2. Design app's architecture (MVVM, Clean architecture) 
3. Apply LiveData mechanism 
4. UI should be looks like in attachment. 

5. Write Unit Tests 


7. Exception handling 
8. Caching handling
9. Secure Android app from: 

* DecompileAPK  
* Data transmission via network









