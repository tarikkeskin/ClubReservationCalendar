package com.example.clubreservationcalendar.core.data

expect class ImageStorage {
    suspend fun saveImage(bytes: ByteArray): String
    suspend fun getImage(fileName: String): ByteArray?
    suspend fun deleteImage(fileName: String)
    suspend fun saveImageToFireStore(bytes: ByteArray): String
}