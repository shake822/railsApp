package com.shake.test

class Airport {
	String name
	static hasMany = [flights:Flight]
	static constraints = {
	}
	static mapping = {
		flights  lazy: false , batchSize: 2
	}
}
