# weather_forecast

**Multi modeles dialog**

<img width="537" alt="Screen Shot 2021-08-01 at 20 52 02" src="https://user-images.githubusercontent.com/6103507/127777888-88651f16-0a41-41c4-8378-a702bb6e39fa.png">


**Scenario**

<img width="874" alt="Screen Shot 2021-08-01 at 23 10 09" src="https://user-images.githubusercontent.com/6103507/127777960-a4a83ce4-c98a-4dc3-abbb-b4ae2df53a08.png">

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
* Dagger Hilt injection: https://developer.android.com/training/dependency-injection/hilt-android

**Local Development**

Requirement:

gradle:4.2.2

android studio:4.2.2

Use the gradlew build command or use "Import Project" in Android Studio.

./gradlew clean build - Build the entire example and execute unit and integration tests plus lint check.

./gradlew installDebug - Install the debug apk on the current connected device.


**Check list of items the candidate has done.**

1. Programming language:Kotlin.

2. Design app's architecture (MVVM, Clean architecture)???
3. Apply LiveData mechanism???
4. UI should be looks like in attachment. 

5. Write Unit Tests???
6. Ui test

7. Exception handling???
8. Caching handling (retrofit cache)
9. Secure Android app from: 

* DecompileAPK ??? (proguard)
* Data transmission via network (retrofit public key pinning)









