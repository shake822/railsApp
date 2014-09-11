package com.shake

import groovy.transform.ToString

@ToString
class User {
    String userName
    String account
    String password
    String email
    Date createDate
    //    static transients = ['upperCaseName'] 与数据库无关联的字段
    static constraints = {
        email(email:true)
        account(size:6..20)
        password(size:6..20)
        userName(nullable: true)
    }
}
