package com.example.clubreservationcalendar.core.data

import android.content.Context
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

actual class ImageStorage(
    private val context: Context
) {
    actual suspend fun saveImage(bytes: ByteArray): String {
        return withContext(Dispatchers.IO) {
            val fileName = UUID.randomUUID().toString() + ".jpg"

            context.openFileOutput(fileName, Context.MODE_PRIVATE).use { outputStream ->
                outputStream.write(bytes)
            }
            fileName
        }
    }

    actual suspend fun getImage(fileName: String): ByteArray? {
        return withContext(Dispatchers.IO) {
            context.openFileInput(fileName).use { inputStream ->
                inputStream.readBytes()
            }
        }
    }

    actual suspend fun deleteImage(fileName: String) {
        return withContext(Dispatchers.IO) {
            context.deleteFile(fileName)
        }
    }

    actual suspend fun saveImageToFireStore(bytes: ByteArray): String {
        TODO("Not yet implemented")
    }
}