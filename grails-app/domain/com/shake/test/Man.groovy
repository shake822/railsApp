package com.shake.test

import org.codehaus.groovy.grails.orm.hibernate.cfg.JoinTable;

class Man {
	String name
	Wife myWife
	static hasMany = [children:Child]
	static mapping ={
		table 'good_man'
		name column:'man_name'
		myWife column:'man_wife_id'
		//children column:'children_id'
		children joinTable:[
			name:'man_child',key:'man_Id',column:'Child_id']
	}
	static constraints = {
	}
}
