def tm1 = new conn.TransportationMean(name:'Fahrrad', average_Speed:20)
def tm2 = new conn.TransportationMean(name:'Auto', average_Speed:50)
def c1 = new conn.Connection(transMean:tm1,start:'Hermannstr 100',end:'Hermannplatz')
def c2 = new conn.Connection(transMean:tm2,,start:'Hermannplatz',end:'Hermannstr 100')

t1 = new conn.Trip()
    .addToConnections(c1)
    .addToConnections(c2)
