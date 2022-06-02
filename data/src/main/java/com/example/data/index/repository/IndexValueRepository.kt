package com.example.data.index.repository

import com.example.data.index.entities.IndexValue

interface IndexValueRepository {

    suspend fun addIndexValue(indexValue: IndexValue)
}