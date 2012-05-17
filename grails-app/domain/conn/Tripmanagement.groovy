package conn


class Tripmanagement {
    static hasMany = [trips:Trip]
    
    def scratcherService = new ScratcherService()
    
        
    def transportation_mean_collection = [
        new TransportationMean(name:"Fahrrad", average_speed:17).save(),
        new TransportationMean(name:"Auto", average_speed:50).save(),
        new TransportationMean(name:"Taxi", average_speed:50).save()
    ]
    
    static constraints = {
        hasMany nullable:true
    }


}
