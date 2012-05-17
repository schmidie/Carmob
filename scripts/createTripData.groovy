def tm1 = new conn.TransportationMean(name:'Fahrrad', average_Speed:20).save()
def tm2 = new conn.TransportationMean(name:'Auto', average_Speed:50).save()
def c1 = new conn.Connection(transMean:tm1,start:'Hermannstr 100',end:'Hermannplatz').save()
def c2 = new conn.Connection(transMean:tm2,,start:'Hermannplatz',end:'Hermannstr 100').save()

t1 = new conn.Trip()
    .addToConnections(c1)
    .addToConnections(c2)
    .save()