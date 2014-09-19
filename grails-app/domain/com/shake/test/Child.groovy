package com.shake.test

class Child {
    String name
    Man father
    
    static mapping={father column:'my_father'}
    
    static constraints = {
    }
}
