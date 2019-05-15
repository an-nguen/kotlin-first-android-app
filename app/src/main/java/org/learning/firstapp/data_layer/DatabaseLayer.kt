package org.learning.firstapp.data_layer

interface DatabaseLayer<T, K> {
    fun select() : ArrayList<T>
    fun create(item: T)
    fun update(id: K, item: T)
    fun delete(id: K)
}