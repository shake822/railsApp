package com.shake.test

class Face1 {
    String faceName
    static hasOne=[nose:Nose1]
    static constraints = { nose unique: false }
    static mapping={ nose column:'nose1_id' }
}
