package conn

//entity-type
class User {
     
    // attributes
    String  login
    String  password
    String  email
    int     status      // must be set to AuthenticationService.STATUS_NEW at init
    
    String  surName
    String  lastName
    
    Boolean hasFahrrad  = false
    Boolean hasAuto     = false
    Boolean hasBCard50  = false
    Boolean hasBCard100 = false
    
    Collection trips
    
    // mapping - deployment with Heroku/Postgres doesn't allow user table
    static mapping = {
        table 'accounts'
    }
    
    // relations
    static hasMany = [
        trips:Trip
    ]
   
    // constraints
    static constraints = {

        surName     (nullable:true)
        lastName    (nullable:true)
        hasFahrrad  (nullable:true)
        hasAuto     (nullable:true)
        hasBCard50  (nullable:true)
        hasBCard100 (nullable:true)

    }
    
    // methods
    
    /**
     *  @return the next trip of a user
     */
    def getNextTrip(){
        def next_trp
        def start_tmp = new Date(Long.MAX_VALUE)

        if(trips){

            trips.each(){
                if(it.getStartTime() < start_tmp){
                    start_tmp = it.getStartTime() 
                    next_trp  = it
                }
            }
            
        }
        
        return (next_trp)
    }
    
    /**
     *  @return the time until a trip starts
     */
    def getTimeToNextTrip(){
        
        long diffMinutes = 0
        def start_tmp = new Date(Long.MAX_VALUE)

        if(trips){
            
            trips.each(){
                if(it.getStartTime() < start_tmp)
                    start_tmp = it.getStartTime()
            }

            Calendar cal_start = Calendar.getInstance();
            cal_start.setTime(new Date());
            
            Calendar cal_end = Calendar.getInstance();
            cal_end.setTime(start_tmp);

            long milliseconds1 = cal_start.getTimeInMillis()
            long milliseconds2 = cal_end.getTimeInMillis()
            long diff = milliseconds2 - milliseconds1
            
            diffMinutes = (diff / (60 * 1000))
        }
        
        return (diffMinutes)
    }
    
} // eoc