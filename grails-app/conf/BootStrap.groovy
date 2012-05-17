package conn

class BootStrap {

    def init = { servletContext ->

        new Trip()
            .addToConnections(new Connection(transMean:new TransportationMean(name:'Fahrrad'),start:'Hermannstr 100',end:'Hermannplatz'))
            .addToConnections(new Connection(transMean:new TransportationMean(name:'Auto'),start:'Hermannstr 100',end:'Hermannplatz'))
            .save()

    }
    def destroy = {
    }
}
