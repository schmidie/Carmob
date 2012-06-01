package conn

class User {
     
    Collection trips
    //Collection transMeans
    
    // relations
    static hasMany = [
        trips:Trip
    ]

    String login
    String password
    String email
    int status // must be set to AuthenticationService.STATUS_NEW at init
    
    Boolean fahrrad
    Boolean auto
    Boolean bahn_card_100
    Boolean bahn_card_50
    
    
    static constraints = {
        fahrrad nullable:true
        auto nullable:true
        bahn_card_100 nullable:true
        bahn_card_50 nullable:true
    }
}
