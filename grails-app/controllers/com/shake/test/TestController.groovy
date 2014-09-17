package com.shake.test

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
        def node1 = Nose1.load(1)
        node1.noseName="nose1"
        face1.nose=node1
        face1.save()
    }
}
