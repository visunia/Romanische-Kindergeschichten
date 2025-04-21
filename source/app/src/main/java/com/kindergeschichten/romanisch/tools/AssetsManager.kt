package com.us.babyeducation.assets


import android.app.Activity
import android.content.Context
import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.AudioManager
import android.media.MediaPlayer
import android.text.TextUtils
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import java.io.IOException
import java.io.InputStream
import java.text.Normalizer
import java.util.*
import kotlin.collections.ArrayList


class AssetsManager {
    companion object {
        private lateinit var instance: AssetsManager

        fun getInstance(context: Activity): AssetsManager {
            if (!::instance.isInitialized)
                instance = AssetsManager(context)
            return instance
        }

        val PICTURE_FOLDER = "Pictures"
    }


    fun isValidExpensionFile(): Boolean {
        return true
    }

    var assetManager: AssetManager? = null
    lateinit var snackbar: Snackbar

    private constructor(appcontext: Activity) {

        try {
            val context: Context = appcontext.createPackageContext(appcontext.getPackageName(), 0)
            // using AssetManager class we get assets
            assetManager = context.assets
            snackbar = Snackbar.make(
                appcontext.findViewById(android.R.id.content),
                "Playing sound",
                Snackbar.LENGTH_INDEFINITE
            );
        } catch (e: java.lang.Exception) {
            Log.d("IOException", "IOException occurred")
        }

    }

    fun getAppPic(folderName: String?, phonePitureName: String?): InputStream? {
        if (TextUtils.isEmpty(phonePitureName)) return null
        val path = "$PICTURE_FOLDER/$folderName/$phonePitureName"

        var inputStream: InputStream? = null
        try {
            inputStream = assetManager?.open(path)
            return inputStream
        } catch (e: IOException) {
            e.printStackTrace()

        }
        return null
    }

    fun getAppPic(phonePitureName: String): InputStream? {
        var phonePitureName = phonePitureName
        if (TextUtils.isEmpty(phonePitureName)) return null
        phonePitureName = phonePitureName.replace(".gif", ".jpg")
        val path = "App_Pictures/$phonePitureName"
        var inputStream: InputStream? = null
        try {
            inputStream = assetManager?.open(path)
            return inputStream
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return inputStream
    }

    fun getAwardPic(phonePitureName: String): InputStream? {


        if (TextUtils.isEmpty(phonePitureName)) return null
        val path = "$PICTURE_FOLDER/Awards/$phonePitureName"

        var inputStream: InputStream? = null
        try {
            inputStream = assetManager?.open(path)
            return inputStream
        } catch (e: IOException) {
            e.printStackTrace()

        }
        return null

    }


    fun getPhoneCategoryPictureAvailable(phonePitureName: String): InputStream? {
        if (TextUtils.isEmpty(phonePitureName)) return null
        val path = "Pictures_Category/Phone/$phonePitureName"
        var inputStream: InputStream? = null
        try {
            inputStream = assetManager?.open(path)
            return inputStream
        } catch (e: IOException) {
            e.printStackTrace()

        }
        return null
    }

    fun getTabletCategoryPictureAvailable(phonePitureName: String): InputStream? {
        if (TextUtils.isEmpty(phonePitureName)) return null
        val path = "Pictures_Category/Tablet/$phonePitureName"
        var inputStream: InputStream? = null
        try {
            inputStream = assetManager?.open(path)
            return inputStream
        } catch (e: IOException) {
            e.printStackTrace()

        }
        return null
    }


    fun getPicture(folderName: String?, phonePitureName: String?): AssetFileDescriptor? {
        val path = "$folderName/$phonePitureName"
        return assetManager?.openFd(path)
    }

    fun getPicture( phonePitureName: String?): InputStream? {

        return  try {
            val folderName = "pictures"
            val path = "$folderName/$phonePitureName"
             assetManager?.open(path)
        }catch (ex:Exception){
            null
        }

    }


    var mediaPlayer: MediaPlayer? = null

    fun normalize(file: String): String {

        var s = Normalizer.normalize(file, Normalizer.Form.NFD)
        s = s.replace("[\\p{InCombiningDiacriticalMarks}]".toRegex(), "")
        s = s.replace("[\\p{InCombiningDiacriticalMarks}]".toRegex(), "")
        if (s.substring(0, 2) == "l‘" || s.substring(0, 2) == "l\'") {
            s = "l'" + s.substring(3)
        }
        s = s.replace("?", "")
        s = s.replace("‘", "\'")
        if (s.substring(s.length - 1) == "!") {
            s = s.substring(0, s.length - 1)
        }
        s = s.replace("!", "")
        return s
    }

    fun play(file: String) {

        //if (playingSound) return

        playingSound = true
        snackbar.show()

        val targetFile = getTargetFile(file)
        if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
            stop()
        }
        try {
            var fd: AssetFileDescriptor?
            fd = openFd(targetFile)
            initMedia()
            mediaPlayer?.setAudioStreamType(AudioManager.STREAM_MUSIC)
            fd?.apply {
                mediaPlayer?.setDataSource(this.fileDescriptor, this.startOffset, this.length)
                mediaPlayer?.prepare()
                mediaPlayer?.setOnCompletionListener {
                    playingSound = false
                    //stop()
                    playingSound = false
                    snackbar.dismiss()
                    onCompletePlaying?.CompletedPlaying()
                }
            }

            //mediaPlayer.start();
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getTargetFile(word: String): String {
        if (word == null) return ""
        //var foldername = word.substring(0, 1).uppercase(Locale.getDefault())

        val path = "sounds/"  + normalize(word)


        try {
            return if(openStream(path)!=null)
                path
                else
                    ""
        } catch (e: IOException) {
            e.printStackTrace()

        }
        return ""
    }

    fun openFd(file: String): AssetFileDescriptor? {
        return try {
            assetManager?.openFd(file)
        } catch (ex: java.lang.Exception) {
            null
        }
    }

    fun initMedia() {
        if (mediaPlayer == null) mediaPlayer = MediaPlayer() else mediaPlayer?.reset()
        mediaPlayer?.setOnCompletionListener {
            try {
                onCompletePlaying?.CompletedPlaying()
            } catch (ex: java.lang.Exception) {
            }
        }
        mediaPlayer?.setOnErrorListener { mp, what, extra -> true }
        mediaPlayer?.setOnPreparedListener { mediaPlayer -> mediaPlayer.start() }
    }

    interface OnCompletePlaying {
        fun CompletedPlaying();
    }

    private var onCompletePlaying: OnCompletePlaying? = null
    fun setOnCompletedPlayingListener(onCompletePlaying: OnCompletePlaying) {
        this.onCompletePlaying = onCompletePlaying
    }

    fun removeOnCompletedPlayingListener() {
        onCompletePlaying = null
        snackbar?.dismiss()
        mediaPlayer?.stop()

    }

    fun getLessonPictureAvailable(phonePitureName: String?): InputStream? {
        if (phonePitureName.isNullOrEmpty()) return null
        val path = "Pictures_Lessons/Phone/$phonePitureName"
        var inputStream: InputStream? = null
        try {
            inputStream = assetManager?.open(path)
            return inputStream
        } catch (e: IOException) {
            e.printStackTrace()

        }
        return null
    }

    fun getTabletLessonPictureAvailable(phonePitureName: String?): InputStream? {
        if (phonePitureName.isNullOrEmpty()) return null
        val path = "Pictures_Lessons/Tablet/$phonePitureName"
        var inputStream: InputStream? = null
        try {
            inputStream = assetManager?.open(path)
            return inputStream
        } catch (e: IOException) {
            e.printStackTrace()

        }
        return null
    }

    fun isSoundAvailable(word: String?): Boolean {
        if (word == null) return false
        var foldername = word.substring(0, 1).uppercase(Locale.getDefault())
        if (!Character.isLetter(foldername[0])) {
            foldername = "_Sonstige"
        }
        val path = "Sounds/" + foldername + "/" + normalize(word) + ".mp3"

        val file = "Sounds/" + foldername + "/" + normalize(word) + ".m4a";
        var inputStream: InputStream? = null
        try {
            inputStream =openStream(path)
            if (inputStream == null)
                inputStream = openStream(file)
            return inputStream != null
        } catch (e: IOException) {
            e.printStackTrace()

        }
        return false
    }

    fun openStream(filePath:String): InputStream? {
        return try {
            assetManager?.open(filePath)
        }catch (ex:java.lang.Exception){
            null
        }
    }

    @JvmField
    var playingSound: Boolean = false

    fun stop() {
        try {
            playingSound = false
            if (mediaPlayer != null) {
                mediaPlayer!!.stop()
                mediaPlayer!!.release()
                mediaPlayer = null
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    fun  fd(name:String):AssetFileDescriptor?
    {
        return   assetManager?.openFd(name)
    }

    fun checkIfDocumentAvailaable(path: String): InputStream? {


        var inputSteam: InputStream? = null
        try {
            inputSteam = assetManager?.open(path)
            return inputSteam
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return inputSteam
    }

    fun getPdfFileFromPath(path: String): InputStream? {


        var inputSteam: InputStream? = null
        try {
            inputSteam = assetManager?.open(path)
            return inputSteam
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return inputSteam
    }

    fun getDocumentsList(locale: String): ArrayList<String> {
        val  path = "Documents/$locale/"
        val  result =  ArrayList<String>()
        assetManager?.list(path)?.forEach {
            result.add(it)
        }
        return result
    }
}