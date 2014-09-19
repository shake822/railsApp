package com.shake.test


class Author {
    String authorName
    static hasMany = [books:Book]
    static constraints = {
    }
}
