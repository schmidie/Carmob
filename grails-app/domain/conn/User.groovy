package conn

//entity
class User {
     
    // attributes
    String login
    String password
    String email
    int    status     // must be set to AuthenticationService.STATUS_NEW at init
    
    String surName
    String lastName
    
    Boolean hasFahrrad  = false
    Boolean hasAuto     = false
    Boolean hasBCard50  = false
    Boolean hasBCard100 = false
    
    Boolean useFahrrad  = false
    Boolean useTaxi     = false
    Boolean useDBahn    = false
    Boolean useSBahn    = false
    Boolean useUBahn    = false
    Boolean useBus      = false
    
    Collection trips
    
    // relations
    static hasMany = [
        trips:Trip
    ]
   
    // constraints
    static constraints = {
        surName     nullable:true
        lastName    nullable:true
        hasFahrrad  nullable:true
        hasAuto     nullable:true
        hasBCard50  nullable:true
        hasBCard100 nullable:true
        useFahrrad  nullable:true
        useTaxi     nullable:true
        useDBahn    nullable:true
        useSBahn    nullable:true
        useUBahn    nullable:true
        useBus      nullable:true
    }
    
}