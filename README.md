# Simple Flexible Snackbar Builder - Kotlin
A minimal way to create snackbars using the Builder Pattern. 
You can create both Native Snackbars and [Top Snackbars](https://github.com/AndreiD/TSnackBar) 

How to use:

```kotlin
//Create Top Snackbar
val snack = findViewById(R.id.your_coordinator_layout_id) // can be a Frame Layout too
SFSnackbar.Builder(this, snack, SFSnackbar.SnackType.TOP)
            .backgroundColor(Color.YELLOW)
            .setText("This is the first snack, got it?", Color.BLACK)
            .setActionWithColor("GOTCHA", Color.BLUE, listener)
            .build()
            .show()
            
// Without action
SFSnackbar.Builder(this, snack, SFSnackbar.SnackType.TOP)
            .backgroundColorResource(R.color.secondsnackcolor)
            .setTextWithColor(R.string.secondsnackbartext, Color.BLUE)
            .textAlignment(SFSnackbar.Alignment.CENTER)
            .setDuration(SFSnackbar.Duration.LENGTH_LONG)
            .build()
            .show()
```

or

```kotlin
//Create Regular Snackbar
SFSnackbar.Builder(this, snack, SFSnackbar.SnackType.BOTTOM)
            .backgroundColor(Color.BLACK)
            .setText("Something is done successfully", Color.YELLOW)
            .setAction("OKAY")
            .setDuration(SFSnackbar.Duration.LENGTH_SHORT) // this has no effect
            .build()
            .show()
```

How it looks:
<p/>
<img src="https://github.com/mustafatunc/simple_flexible_snackbar_builder/blob/master/app/snackbars_gif.gif" width="300">

<p/>
<p/>

Class Diagram (Not exactly)
<p/>
<img src="https://github.com/mustafatunc/simple_flexible_snackbar_builder/blob/master/app/snackclass.jpg" width="500">
<p/>
<p/>



