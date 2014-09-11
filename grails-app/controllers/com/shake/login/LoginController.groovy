package com.shake.login

import grails.transaction.Transactional

import com.shake.User


@Transactional(readOnly = true)
class LoginController {
    
    def index() {
        render(view:"/login")
    }
    def login(){
        String account = params["account"]
        String password = params["password"]
        User user = User.find{
            account =="$account" && password=="$password"
        }
        if(user ==null){
            flash.message = "用户名密码错误"
            render(view:"/login")
            return
        }
        session.user = user
        render "登录成功"
    }
    def logout(){
        session.invalidate()
        render(view:"/login")
    }
}
