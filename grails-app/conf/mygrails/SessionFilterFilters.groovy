package mygrails

class SessionFilterFilters {
    
    def filters = {
        loginCheck(controller:'*', action:'*' ) {
            before = {
                if (!session.user ) {
                    if(!(controllerName.equals('login') || controllerName.equals('assets'))){
//                        render(view:"/login")
                        return true
                    }
                }
                return true
            }
            //            after = { Map model -> println "filter after" }
            //            afterView = { Exception e -> println "filter afterView" }
        }
    }
}
