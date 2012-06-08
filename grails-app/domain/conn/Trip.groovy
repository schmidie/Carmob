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
    
    def getStart(){
        def start
        def start_time
        connections.each(){
            if(it.start_time < start_time){
                start_time = it.start_time
                start = it.start
            }
        }
        start
    }
    def getEnd(){
        def end
        def end_time
        connections.each(){
            if(it.end_time > end_time){
                end_time = it.end_time
                end = it.end
            }
        }
        end
    }
    def getStartTime(){
        def start_time
        connections.each(){
            if(it.start_time < start_time){
                start_time = it.start_time
            }
        }
        start_time
    }
    def getEndTime(){
        def end_time
        connections.each(){
            if(it.end_time > end_time){
                end_time = it.end_time
            }
        }
        end_time
    }
    
    
    // methods
    def duration() {
        def endminutes=0
        def startminutes=0
        
        connections.each(){
            endminutes = it.end_time.get(Calendar.MINUTE) + (it.end_time.get(Calendar.HOUR)*60)
            startminutes = it.start_time.get(Calendar.MINUTE) + (it.end_time.get(Calendar.HOUR)*60)
            
//            TimeDuration tmp = TimeCategory.minus(it.end_time, it.start_time)
//            minutes += tmp.minutes
//            //dTime += it.end_time.time - it.start_time.time
        }
        return endminutes-startminutes
    }
}
