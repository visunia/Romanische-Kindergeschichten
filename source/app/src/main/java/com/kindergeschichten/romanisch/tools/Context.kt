package com.kindergeschichten.romanisch.tools

import android.app.Activity
import android.app.UiModeManager
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kindergeschichten.romanisch.R
import com.kindergeschichten.romanisch.data.Story
import com.kindergeschichten.romanisch.ui.bottomsheet.PdfViewer
import com.kindergeschichten.romanisch.ui.custom.Theme
import java.io.IOException
import java.util.Locale
import java.util.Random


fun AppCompatActivity.openPdfDocument(documentType: PdfViewer.DocumentType) {
    PdfViewer(documentType).show(supportFragmentManager, "")
}


fun ImageView.setDrawableByName(context: Context, resourceName: String) {
    val resourceId = context.resources.getIdentifier(resourceName, "drawable", context.packageName)
    if (resourceId != 0) {
        this.setImageResource(resourceId)
    } else {
        // Handle the case where the resource is not found
        //this.setImageResource(R.drawable.default_image) // Replace with your default image
    }
}


fun Context.loadStoriesFromAssets(): List<Story> {
    // Load JSON from assets
    val fileName: String = "stories_1.2"
    val json = assets.open(fileName).bufferedReader().use { it.readText() }

    // Use Gson to parse JSON into an array of Story objects
    val gson = Gson()
    val storyArrayType = object : TypeToken<List<Story>>() {}.type
    return gson.fromJson(json, storyArrayType)
}


fun AppCompatActivity.showParentCheckDialog( checkPassed:()->Unit) {
    val layout = layoutInflater.inflate(R.layout.parent_check_2, null)
    val dialog: AlertDialog = AlertDialog
        .Builder(this)
        .setView(layout)
        .setCancelable(true).show()

    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    val imgClose = layout.findViewById<ImageView>(R.id.imgClose)
    imgClose.setOnClickListener {
        dialog.dismiss()
    }

    //
    val numberOne = getRandomNumber()
    val numberTwo = getRandomNumber()
    val answer = numberOne + numberTwo
    //


    //
    val tvQuestion = layout.findViewById<TextView>(R.id.tvQuestion)

    tvQuestion.text = "$numberOne + $numberTwo = ?"

    val tvAnswer = layout.findViewById<TextView>(R.id.tvAnswer)
    val tv0 = layout.findViewById<TextView>(R.id.tv0)
    val tv1 = layout.findViewById<TextView>(R.id.tv1)
    val tv2 = layout.findViewById<TextView>(R.id.tv2)
    val tv3 = layout.findViewById<TextView>(R.id.tv3)
    val tv4 = layout.findViewById<TextView>(R.id.tv4)
    val tv5 = layout.findViewById<TextView>(R.id.tv5)
    val tv6 = layout.findViewById<TextView>(R.id.tv6)
    val tv7 = layout.findViewById<TextView>(R.id.tv7)
    val tv8 = layout.findViewById<TextView>(R.id.tv8)
    val tv9 = layout.findViewById<TextView>(R.id.tv9)
    val tvBackspace = layout.findViewById<ImageButton>(R.id.tvbackspace)
    val tvDone = layout.findViewById<ImageButton>(R.id.tvDone)
    //

    val onclick = View.OnClickListener { view ->
        when (view.id) {
            R.id.tv0, R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5, R.id.tv6, R.id.tv7, R.id.tv8, R.id.tv9 -> {

                tvAnswer.text = tvAnswer.text.toString() + (view as TextView).text.toString()
            }

            R.id.tvDone -> {
                val userInput = tvAnswer.text.toString().toInt()
                if (userInput == answer)
                    checkPassed()
                else
                    this.showMessege("Failed")
                dialog.dismiss()
            }

            R.id.tvbackspace -> {
                tvAnswer.text = tvAnswer.text.toString().backSpace()
            }

        }


    }


    tv0.setOnClickListener(onclick)
    tv1.setOnClickListener(onclick)
    tv2.setOnClickListener(onclick)
    tv3.setOnClickListener(onclick)
    tv4.setOnClickListener(onclick)
    tv5.setOnClickListener(onclick)
    tv6.setOnClickListener(onclick)
    tv7.setOnClickListener(onclick)
    tv8.setOnClickListener(onclick)
    tv9.setOnClickListener(onclick)
    tvBackspace.setOnClickListener(onclick)

    tvDone.setOnClickListener(onclick)


}

fun getRandomNumber():Int
{
    return Random().nextInt(20) + 1
}


fun Context.showMessege(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun String.backSpace(): String {
    var str = this
    if (!str.isNullOrEmpty()) {
        str = str.substring(0, str.length - 1)
    }
    return str
}




fun Context.showExitDialog(message:Int,result: () -> Unit,retain:()->Unit) {
    // Inflate the custom layout
    val customView = LayoutInflater.from(this).inflate(R.layout.dialog_exit, null)

    // Find the buttons in the custom view
    val btnYes = customView.findViewById<TextView>(R.id.btnYes)
    val btnNo = customView.findViewById<TextView>(R.id.btnNo)
    val textViewMessage = customView.findViewById<TextView>(R.id.tvMessage)
    textViewMessage.text = getString(message)

    val dialog = AlertDialog.Builder(this,R.style.WrapContentDialog)
        .setView(customView)  // Set the custom view to the dialog
        .create()

    // Set actions for the buttons
    btnYes.setOnClickListener {
        result() // Call the lambda function passed
        dialog.dismiss() // Dismiss the dialog
    }

    btnNo.setOnClickListener {
        retain()
        dialog.dismiss() // Dismiss the dialog
    }

    // Show the dialog
    dialog.show()



}


fun Context.showStoryDetails(
    story: Story,
    storyType:String,
    callback: (action: Int) -> Unit
) {
    val alertDialog: AlertDialog
    val builder =
        AlertDialog.Builder(this)
    val view: View =
        LayoutInflater.from(this).inflate(R.layout.dialog_table_item, null)
    builder.setView(view)

    alertDialog = builder.create()

    val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
    val tableLayout = view.findViewById<TableLayout>(R.id.tableLayout)

    tvTitle.text = story.getStoryName(storyType)
    val spokenBy = story.getStorySpeakerName(storyType)
    // The object to display
    val data = mapOf(
        "Autor" to story.author,
        "Geschrieben von" to story.writtenBy,
        "Übersetzt von" to story.translatedBy,
        "Gezeichnet von" to story.illustrator,
        "Originalgeschichte" to story.originalStory,
        "Veröffentlicht von" to story.publishedBy,
        "Fotograf" to story.photographer,
        "Übersetzt von (Deutsch)" to story.translatedByGerman,
        "Erzählt von" to spokenBy
    )


    val nonNullFields = data.filterValues { !it.isNullOrEmpty() }

    // Dynamically populate rows
    nonNullFields.forEach { (key, value) ->
        val tableRow = TableRow(this)

        val keyTextView = TextView(this).apply {
            text = key
            setPadding(16, 16, 16, 16)
            setTextColor(getColor(R.color.day_night_foreground)) // Key text color
            //setBackgroundResource(R.drawable.table_cell_border) // Apply border
        }

        val valueTextView = TextView(this).apply {
            text = value.toString()
            setPadding(16, 16, 16, 16)
            setTextColor(getColor(R.color.day_night_foreground)) // Value text color
           // setBackgroundResource(R.drawable.table_cell_border) // Apply border
        }

        tableRow.addView(keyTextView)
        tableRow.addView(valueTextView)

        tableLayout.addView(tableRow)
    }

    view.findViewById<View>(R.id.imgClose).setOnClickListener {
        alertDialog.dismiss()
        callback(0)
    }


    alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    alertDialog.show()

}


fun Activity.showInfoSupporter() {
    val alertDialog: AlertDialog
    val builder = AlertDialog.Builder(this)
    val view: View = LayoutInflater.from(this).inflate(R.layout.dialog_info_supporter, null)
    builder.setView(view)


    alertDialog = builder.create()


    view.findViewById<View>(R.id.imgClose).setOnClickListener {
        alertDialog.dismiss()
    }
    alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    alertDialog.show()

}

fun Context.openUrl(url: Int) {
    try {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(url)))
        startActivity(browserIntent)
    } catch (ex: Exception) {
        Toast.makeText(this, "No Application to Perform this action", Toast.LENGTH_SHORT).show()
    }
}

fun Activity.handleDarkTheme()
{
    val preferenceManager = PreferenceManager.getInstance(this)
    if(preferenceManager.themeMode == Theme.Dark)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    else if(preferenceManager.themeMode == Theme.Light)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    else{
        if(isSystemDarkThemeEnabled())
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}

fun Context.isSystemDarkThemeEnabled(): Boolean {
    val uiModeManager = getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
    return uiModeManager.nightMode == UiModeManager.MODE_NIGHT_YES ||
            (uiModeManager.nightMode == UiModeManager.MODE_NIGHT_AUTO &&
                    (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES)
}


fun Activity.shareByEmail() {
    val EMAIL_SUBJECT: String = getResources().getString(R.string.app_name)

    val EMAIL_BODY: String =
        getResources().getString(R.string.email_body) + "\n\n" + getResources().getString(R.string.playstore_base_url) + getPackageName()


    val intent = Intent(Intent.ACTION_SEND)
    intent.setType("text/plain")
    intent.putExtra(Intent.EXTRA_SUBJECT, EMAIL_SUBJECT)
    intent.putExtra(Intent.EXTRA_TEXT, EMAIL_BODY)
    startActivity(Intent.createChooser(intent, ""))
}


fun Context.rateApp() {
    try {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=" + getPackageName())
            )
        )
    } catch (ex: Exception) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())
            )
        )
    }
}


fun Locale.getSimplifiedName(): String {
    return try {
        language.substringBefore("_")
    } catch (ex: Exception) {
        language
    }
}



@Throws(IOException::class)
fun Context.readJsonAsset(fileName: String): String {
    val inputStream = assets.open(fileName)
    val size = inputStream.available()
    val buffer = ByteArray(size)
    inputStream.read(buffer)
    inputStream.close()
    return String(buffer, Charsets.UTF_8)
}


fun Context.openInPlayStore(appPackageName: String) {
    try {
        startActivity(
            Intent(
                Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")
            )
        )
    } catch (anfe: ActivityNotFoundException) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?Id=$appPackageName")
            )
        )
    }
}

fun Activity.openRomanisch() {
    try {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.romontsch_url)))
        startActivity(browserIntent)
    } catch (ex: Exception) {
        Toast.makeText(this, "No Application to Perform this action", Toast.LENGTH_SHORT).show()
    }
}

fun Activity.openWebsite() {
    try {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.website)))
        startActivity(browserIntent)
    } catch (ex: java.lang.Exception) {
        Toast.makeText(this, "No Application to Perform this action", Toast.LENGTH_SHORT).show()
    }
}



