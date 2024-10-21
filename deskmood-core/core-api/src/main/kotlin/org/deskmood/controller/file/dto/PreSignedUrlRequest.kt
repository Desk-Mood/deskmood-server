package org.deskmood.controller.file.dto

data class PreSignedUrlRequest(
    val fileName: String,
    val fileType: String
)
