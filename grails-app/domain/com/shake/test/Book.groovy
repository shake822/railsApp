package com.shake.test

import groovy.transform.ToString

@ToString
class Book {
    String bookName
    
    static belongsTo = [author:Author]
}
