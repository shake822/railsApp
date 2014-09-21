package com.shake.test

import grails.transaction.Transactional
import shake.Person

@Transactional(readOnly = true)
class TestController {

	def index() {
		println "indexx"
	}

	def testFaceNose(){
		println "testFaceNose"
		def face = new Face()

		face.faceName="face"
		def node = new Nose()
		node.noseName="nose"
		face.nose=node
		face.save()
	}

	def testGetFace(){
		println "testGetFace"
		def face = Face.get(1)
		println face.nose.noseName
		println "end"
	}

	def testOneToOne(){
		println "testOneToOne"
		def face1 = new Face1()
		face1.faceName="face"
		def node1 =Nose1.load(1)
		node1.noseName="nose2"
		face1.nose=node1
		face1.save()
	}
	/**
	 * belongsTo单独使用A,B belongsToA 会在B表中加入a_id
	 * 如果A 中有B作为属性，则a表中有b_id
	 * 如果A中有B作为属性，且B belongsToA 则a表中有b_id而b表中没有a_id
	 * 只有belongsTo的时候才能形成对象关联，保存一个都保存
	 * @return
	 */
	def testBelongsTo(){
		println "testBelongsTo"
		B b = new B()
		A a = new A()
		a.b = b
		a.save()
	}
	def testBelongsTo1(){
		println "testBelongsTo1"
		B b = B.get(1)
		A a = new A()
		a.b = b
		a.save()
	}
	@Transactional
	def testHasOne(){
		println "hasOne1"
		HasOne1 t1 = new HasOne1()
		t1.two=new HasOne2()
		t1.save()

		HasOne1 t11 = new HasOne1()
		t11.two = t1.two
		t11.save()
		//        HasOne2 t2 = new HasOne2()
		//        t2.one =t1
		//        t2.save()
	}

	@Transactional
	def testBelongTo1(){
		println "testBelongTo1"
		if("insertAuthor".equals(params.op)){
			Author author = new  Author()
			author.authorName="赵群齐"
			author.save()
		}else  if("insertBook".equals(params.op)){
			Book book = new Book()
			book.bookName = "大神的世界"
			book.save()
		}else  if("insert".equals(params.op)){
			Author author = Author.find{"author_name=='赵群齐'"}
			println author
			Book book = new Book()
			book.bookName = "你行的骚年"
			book.addToAuthors(author)
			book.save()
		}else  if("insertOther".equals(params.op)){
			//只会保存Book,因为Book属于Author但Author不属于Book
			Book book = new Book()
			book.bookName = "我是你的新书"
			book.addToAuthors(new Author(authorName:'莫莫'))
			book.save()
		}else  if("insertBelongTo".equals(params.op)){
			//两边都带belongsto这种模式是错误的
			E e = new E(eName:'E')
			e.addToF(new F(fName:'F'))
			e.save()
		}else  if("insertHasMany".equals(params.op)){
			A1 a1 = new A1()
			C c = new C()
			a1.addToCs(c)
			a1.save()
		}else  if("deleteHasMany".equals(params.op)){
			A1 a1 = A1.get(1)
			a1.delete()
		}
		else  if("insert".equals(params.op)){
			Author author = Author.find{"author_name=='赵群齐'"}
			Book book = new Book()
			book.bookName = "无谓的教"
			book.authors = [author]book.save()
		}else if("delete".equals(params.op)){
			Author author =  Author.find{"author_name == '赵群齐'"}
			author.delete()
		}else if("query".equals(params.op)){
			Author author =  Author.find{"author_name == '赵群齐'"}
			author.books.each  { bookIte -> println bookIte }
		}else{
			println "nothing"
		}
	}
	@Transactional
	def testSql(){
		println "testSql"
		if("initData".equals(params.op)){
			Location l1 = new Location(city:"深圳",country:"中国")
			l1.save()
			Location l2 = new Location(city:"西安",country:"中国")
			l2.save()
			Airport airport = new Airport(name:'宝安国际机场')
			.addToFlights(new Flight(number:'001号',destination:l1))
			.addToFlights(new Flight(number:'002号',destination:l2)).save()
		}else  if("lazy".equals(params.op)){
			def airport = Airport.findByName("宝安国际机场")
			println airport.flights.size()
			airport.name="我要边"
			println airport.isDirty()
			if(airport.isDirty()){
				def modifiedFieldNames = airport.getDirtyPropertyNames()
				for (fieldName in modifiedFieldNames) {
					def currentValue = airport."$fieldName"
					def oparamsriginalValue = airport.getPersistentValue(fieldName)
					println "$fieldName from $originalValue change to $currentValue"
				}
			}

			//			for (flight in airport.flights) {
			//				println    flight.destination.city
			//			}
		}else  if("updateAirport".equals(params.op)){
			def airport = Airport.findByName("宝安国际机场")
			airport.name="变变变"
			airport.save(flush:true)
		}else  if("query".equals(params.op)){
			def persons = Person.findByName("12")
			println persons

			def queryWhere = Person.where{
				name=='12' && name =='123'
			}
			//whereAny
			queryWhere = queryWhere.where { age > avg(age) }
			//				name=='12' && name =='123'
			queryWhere = Person.where {
				age > avg(age).of { name == "Simpson" } && name == "Homer"
			}
			queryWhere = Person.where {
				age < property(age).of { name == "Simpson" }
			}
			println "---------------"
			def p2 = queryWhere.find()
		}else{
			println "Nothing"
		}
	}
	def showFamily(){
		def mans = Man.list
		flash.mans = mans
		render(view:"/test/man")
	}
}
