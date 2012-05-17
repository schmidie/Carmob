package conn

class Connection {

    static belongsTo = Trip
    static hasMany = [trips:Trip]
    
    TransportationMean transMean
    String start
    String end
    
    Date start_time
    Date end_time
    
    Integer distance
    
    static constraints = {
        
        distance (nullable:true)
        
        //belongsTo nullable:true
        //hasMany nullable:true
        
        transMean (nullable:false)
        
        start (blank:false)
        end (blank:false)
        
        start_time (nullable:false)   // we can calculate with the average speed the end time (e.g. bikes)
        end_time (nullable:true)
    }
}
