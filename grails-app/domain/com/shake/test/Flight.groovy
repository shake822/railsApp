package com.shake.test

class Flight {
	String number
	Location destination
	static belongsTo = [airport: Airport]
	static constraints = {
	}
	static fetchMode = [destination: 'eager']
}
