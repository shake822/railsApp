package shake

import groovy.transform.ToString


@ToString
class Person {
	String name
	int age
	Date dateOfBirth
	Address homeAddress
	Address workAddress
	static embedded = ['homeAddress', 'workAddress']
}

class Address {
	String streetName
	String streetCode
}
