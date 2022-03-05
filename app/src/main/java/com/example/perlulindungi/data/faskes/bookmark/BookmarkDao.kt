package com.example.perlulindungi.data.faskes.bookmark

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM Bookmark")
    fun getAll(): List<Bookmark>

    @Insert
    fun insertBookmark(vararg bookmarks: Bookmark)

    @Delete
    fun deleteBookmark(bookmark: Bookmark)
}