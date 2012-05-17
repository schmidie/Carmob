package conn

class Trip {
    static hasMany = [connections:Connection]
    //String start
    //String end
    //we shoud get start end , etc. from the connections
    String name
    static constraints = {
        name (blank:false)
    }
}
