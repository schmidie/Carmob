package conn

// entity
class Connection {

    // attributes
    TransportationMean transMean
    String start
    String end
    Date start_time
    Date end_time
    Boolean regular
    Integer distance
    String area
    
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
        distance     (nullable:true)
        area         (blank:false)
        
        // belongsTo nullable:true
        // hasMany nullable:true
    }
    
}