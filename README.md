# Simple Snackbar Builder - Kotlin
A minimal way to create snackbars using the Builder Pattern. 
You can create both Native Snackbars and [Top Snackbars](https://github.com/AndreiD/TSnackBar) 

How to use:

```kotlin
//Create Top Snackbar
val snack = findViewById(R.id.your_coordinator_layout_id) // can be a Frame Layout too
SSnackbar.Builder(this, snack, SSnackbar.SnackType.TOP)
            .backgroundColor(Color.YELLOW)
            .setText("This is the first snack, got it?", Color.BLACK)
            .setActionWithColor("GOTCHA", Color.BLUE, listener)
            .build()
            .show()
            
// Without action
SSnackbar.Builder(this, snack, SSnackbar.SnackType.TOP)
            .backgroundColorResource(R.color.secondsnackcolor)
            .setTextWithColor(R.string.secondsnackbartext, Color.BLUE)
            .textAlignment(SSnackbar.Alignment.CENTER)
            .setDuration(SSnackbar.Duration.LENGTH_LONG)
            .build()
            .show()
```

or

```kotlin
//Create Regular Snackbar
SSnackbar.Builder(this, snack, SSnackbar.SnackType.BOTTOM)
            .backgroundColor(Color.BLACK)
            .setText("Something is done successfully", Color.YELLOW)
            .setAction("OKAY")
            .setDuration(SSnackbar.Duration.LENGTH_SHORT) // this has no effect
            .build()
            .show()
```

How it looks:
<p/>
<img src="https://github.com/mustafatunc/simple_snackbar_builder/blob/master/app/snackbars_gif.gif" width="300">

<p/>
<p/>

Class Diagram (Not exactly)
<p/>
<img src="https://github.com/mustafatunc/simple_snackbar_builder/blob/master/app/snackclass.jpg" width="500">
<p/>
<p/>

Don't rely on it that much, but if you want to add this to your project this is how:

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    ...
    implementation 'com.github.mustafatunc:simple_snackbar_builder:0.1.1'
}
```

As I develop, it'll be more reliable.

