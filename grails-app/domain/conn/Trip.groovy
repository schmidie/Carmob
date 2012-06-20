package conn

import groovy.time.*


// entity
class Trip {
    
    // attributes
    String name
    // We shoud get start, end, etc. from the connections!
    Collection connections
    Boolean temp
    
    // relations
    static hasMany = [
        connections:Connection
    ]
    
    // constraints
    static constraints = {
        name (blank:false)
    }
    
    def nowToTrip(){
        def start_tmp = this.getStartTime()
        minuteDiff(new Date(),start_tmp)

    }
    
    def getStart(){
        def start = 0
        def start_time = new Date(Long.MAX_VALUE)
        connections.each(){
            if(it.start_time.getTime() < start_time.getTime()){
                start_time = it.start_time
                start = it.start
            }
        }
        start
        //new Date()
    }
    def getEnd(){
        def end
        def end_time = new Date(Long.MIN_VALUE)
        connections.each(){
            if(it.end_time.getTime() > end_time.getTime()){
                end_time = it.end_time
                end = it.end
            }
        }
        end
    }
    def getStartTime(){
        def start_time = new Date(Long.MAX_VALUE)
        connections.each(){
            if(it.start_time.getTime() < start_time.getTime()){
                start_time = new Date(it.start_time.getTime())
            }
        }
        start_time
    }
    def getEndTime(){
        def end_time = new Date(Long.MIN_VALUE)
        connections.each(){
            if(it.end_time.getTime() > end_time.getTime()){
                end_time = new Date(it.end_time.getTime())
            }
        }
        end_time
        
    }
    
    def minuteDiff(start, end){
        Calendar cal_start = Calendar.getInstance()
        cal_start.setTime(start)
        Calendar cal_end = Calendar.getInstance()
        cal_end.setTime(end)
        
        long milliseconds1 = cal_start.getTimeInMillis()
        long milliseconds2 = cal_end.getTimeInMillis()
        long diff = milliseconds2 - milliseconds1
        long diffMinutes = (diff / (60 * 1000))
        
        diffMinutes
    }
    
    // methods
    def duration() {
        
        def start_tmp = this.getStartTime()
        def end_tmp = this.getEndTime()
        
        minuteDiff(start_tmp,end_tmp)
        
    }
}
