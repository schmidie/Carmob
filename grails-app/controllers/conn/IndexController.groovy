package conn

class IndexController {
 def authenticationService
    
    def index() {
        if (!authenticationService.isLoggedIn(request)) {
            redirect(action:"login")
        }
    }
    
    def login() {
        if (authenticationService.isLoggedIn(request)) {
            redirect(action:"index")
        }
    }
    
    def signup() {
        if (authenticationService.isLoggedIn(request)) {
            redirect(action:"index")
        }
    }
}
