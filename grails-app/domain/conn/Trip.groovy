package conn

class Trip {
    static hasMany = [connections:Connection]
    
    String start
    String end
    
    static constraints = {
    }
}
