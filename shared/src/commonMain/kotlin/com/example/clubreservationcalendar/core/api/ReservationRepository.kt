package com.example.clubreservationcalendar.core.api

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.firestore.firestore
import dev.gitlive.firebase.firestore.where
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable


class ReservationRepository {

    /**
     * set -> insert
     * update -> update
     */

    private val COLLECTION_PATH_RESERVATIONS = "reservations"

    suspend fun addReservation(
        comment: String
    ): Resource<Boolean> {
        val db = Firebase.firestore
        val reservation = Reservation(
            comment = comment,
        )
        if (comment.isNotBlank()) {
            return try {
                db.collection(COLLECTION_PATH_RESERVATIONS)
                    .document("1231323")
                    .set(Reservation.serializer(), reservation, encodeDefaults = true)
                Resource.Success(true);
            } catch (e: Exception) {
                Resource.Failure(Exception(e.message))
            }
        }
        return Resource.Failure(Exception("failed to add"))
    }

}

@Serializable
data class ReservationComment(
    var reservationId: String = "",
    var comment: String = "",
)

@Serializable
data class Reservation(
    var comment: String = "",
)

