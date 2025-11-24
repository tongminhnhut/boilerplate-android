package com.tongminhnhut.android_compose.core.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseService @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {
    /** Auth **/
    fun currentUser() = auth.currentUser
    fun uid() = auth.currentUser?.uid

    /** Firestore references **/
    fun collection(name: String): CollectionReference = firestore.collection(name)
    fun document(path: String): DocumentReference = firestore.document(path)

    /** Firestore basic operations with suspend **/

    suspend fun getDocument(ref: DocumentReference): Map<String, Any>? {
        val doc = ref.get().await()
        return if (doc.exists()) doc.data else null
    }

    suspend fun setDocument(ref: DocumentReference, data: Any) {
        ref.set(data).await()
    }

    suspend fun updateDocument(ref: DocumentReference, data: Map<String, Any>) {
        ref.update(data).await()
    }

    suspend fun addDocument(ref: CollectionReference, data: Any): String {
        return ref.add(data).await().id
    }

    suspend fun deleteDocument(ref: DocumentReference) {
        ref.delete().await()
    }

    /**
     * SignIn with Google
     */
    suspend fun firebaseSignInWithGoogle(idToken: String): FirebaseUser {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        return auth.signInWithCredential(credential).await().user
            ?: throw Exception("Firebase User null")
    }

    fun signOut() {
        auth.signOut()
    }


    /** Listen realtime changes **/
    fun listenDocument(ref: DocumentReference) = callbackFlow {
        val listener = ref.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            trySend(snapshot?.data)
        }

        awaitClose { listener.remove() }
    }

    fun listenCollection(ref: CollectionReference) = callbackFlow {
        val listener = ref.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            val list = snapshot?.documents?.map { it.data } ?: emptyList()
            trySend(list)
        }

        awaitClose { listener.remove() }
    }
}