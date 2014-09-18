package com.shake.test

import sun.font.Type1Font.T1DisposerRecord;
import grails.transaction.Transactional

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
		t1.save()
		HasOne2 t2 = new HasOne2()
		t2.save()
		t2.one =t1
		
	}
	
	def testHasOne1(){
		println "hasOne1"
		HasOne2 t2 = new HasOne2()
		HasOne1 t1 = new HasOne1()
		t2.one =t1
		t2.save()
	}
}
