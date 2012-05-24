package conn

class Trip {
    
    Collection connections
    
    //relations
    static hasMany = [connections:Connection]
    
    //attributes
    String name
    //String start
    //String end
    //we shoud get start end , etc. from the connections
    
    //constraints
    static constraints = {
        name (blank:false)
    }
    
    // methods
    def duration() {
        def dTime = 0
        connections.each(){
            dTime += it.end_time.time - it.start_time.time
        }
        dTime
    }
}
