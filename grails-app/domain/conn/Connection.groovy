package conn

class Connection {

 // attributes
    TransportationMean transMean
    String start
    String end
    Date start_time
    Date end_time
    //Integer distance
    
    // constraints
    static constraints = {
        
        //trip (nullable:false)
        transMean    (nullable:false)    
        start        (blank:false)
        end          (blank:false)
        start_time   (nullable:false)
        end_time     (nullable:false)
        
        //distance     (nullable:true)
    }
    
    static mapping = {
        end column: 'end_connection'
    }
    
    def getDuration(){

        Calendar cal_start = Calendar.getInstance();
        cal_start.setTime(start_time);
        Calendar cal_end = Calendar.getInstance();
        cal_end.setTime(end_time);
        
        long milliseconds1 = cal_start.getTimeInMillis();
        long milliseconds2 = cal_end.getTimeInMillis();
        long diff = milliseconds2 - milliseconds1;
        long diffMinutes = diff / (60 * 1000);
        
        diffMinutes
    }
}
