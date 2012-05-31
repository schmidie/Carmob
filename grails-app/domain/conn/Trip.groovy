package conn

import groovy.time.*


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
        def minutes=0
        
        connections.each(){
            TimeDuration tmp = TimeCategory.minus(it.end_time, it.start_time)
            minutes += tmp.minutes
            //dTime += it.end_time.time - it.start_time.time
        }
        minutes
    }
}
