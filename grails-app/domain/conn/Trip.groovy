package conn

// entity
class Trip {
    
    // attributes
    String name
    Collection connections
    // String start
    // String end
    // We shoud get start, end, etc. from the connections!
    
    // relations
    static hasMany = [
        connections:Connection
    ]
    
    // constraints
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
