package conn

class Connection {
    static belongsTo = Trip
    static hasMany = [trips:Trip]
    
    TransportationMean transMean
    String start
    String end
    
    Date start_time
    Date end_time
    
    static constraints = {
        transMean nullable:false
        start blank:false
        end blank:false
    }
}
