package conn

import groovy.time.*

// entity-type
class Trip {
    
    // attributes
    String      name
    Collection  connections
    Boolean     temp
    
    // relations
    static hasMany = [
        connections:Connection
    ]
    
    // constraints
    static constraints = {
        name (blank:false)
    }
    
    // methods
    
    /**
     *  @return the time between two sites in minutes
     */
    def minuteDiff(start, end){
        
        Calendar cal_start = Calendar.getInstance()
        cal_start.setTime(start)
        
        Calendar cal_end   = Calendar.getInstance()
        cal_end.setTime(end)
        
        long milliseconds1 = cal_start.getTimeInMillis()
        long milliseconds2 = cal_end.getTimeInMillis()
        
        long diff = milliseconds2 - milliseconds1
        long diffMinutes = (diff / (60 * 1000))
        
        return (diffMinutes)
    }    

    /**
     *  @return the time until a trip starts
     */
    def nowToTrip(){
        def start_tmp = this.getStartTime()

        minuteDiff(new Date(),start_tmp)
    }
    
    /**
     *  @return the duration of a trip
     */
    def duration() {
        def start_tmp = this.getStartTime()
        def end_tmp = this.getEndTime()
        
        minuteDiff(start_tmp,end_tmp)
    }
    
    /**
     *  @return the starting connection of a trip
     */
    def getStart(){
        def start = 0
        def start_time = new Date(Long.MAX_VALUE)
        
        connections.each(){
            if(it.start_time.getTime() < start_time.getTime()){
                start_time = it.start_time
                start = it.start
            }
        }
        
        return (start)
    }
    
    /**
     *  @return the ending connection of a trip
     */
    def getEnd(){
        def end = 0
        def end_time = new Date(Long.MIN_VALUE)
        
        connections.each(){
            if(it.end_time.getTime() > end_time.getTime()){
                end_time = it.end_time
                end = it.end
            }
        }
        
        return (end)
    }
    
    /**
     *  @return the starttime of a trip
     */
    def getStartTime(){
        def start_time = new Date(Long.MAX_VALUE)
        connections.each(){
            if(it.start_time.getTime() < start_time.getTime()){
                start_time = new Date(it.start_time.getTime())
            }
        }
        
        return (start_time)
    }
    
    /**
     *  @return the endtime of a trip
     */
    def getEndTime(){
        def end_time = new Date(Long.MIN_VALUE)
        connections.each(){
            if(it.end_time.getTime() > end_time.getTime()){
                end_time = new Date(it.end_time.getTime())
            }
        }
        
        return (end_time)
    }
    
} // eoc
