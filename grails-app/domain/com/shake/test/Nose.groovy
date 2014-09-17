package com.shake.test

class Nose {
    String noseName
    static belongsTo = [bodyface:Face]
    static constraints = {
    }
    //    static mapping={ bodyface column : 'face_id' }
}
