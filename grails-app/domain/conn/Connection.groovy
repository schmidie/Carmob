package conn

// entity
class Connection {

    // attributes
    TransportationMean transMean
    String start
    String end
    Calendar start_time
    Calendar end_time
    Boolean regular
    Integer distance
    String area
    //committest
    // relations
    static belongsTo = Trip
    static hasMany = [
        trips : Trip
    ]
    
    // constraints
    static constraints = {
        transMean    (nullable:false)    
        start        (blank:false)
        end          (blank:false)
        start_time   (nullable:false)   // we can calculate with the average speed the end time (e.g. bikes)
        end_time     (nullable:true)
        regular      (nullable:true)
        distance     (nullable:false)
        area         (blank:false)
        
        // belongsTo nullable:true
        // hasMany nullable:true
    }
    
}