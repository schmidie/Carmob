package conn

import org.springframework.dao.DataIntegrityViolationException

/**
 *  ! comment here - The angel controller
 */
class AngleController {

    static allowedMethods = [
        create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST'
    ]

    /**
     *  Redirection to the index with a list of all angels.
     */
    def index() {
        redirect action: 'list', params: params
    }

    /**
     *  Lists all angels.
     */
    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [angleInstanceList: Angle.list(params), angleInstanceTotal: Angle.count()]
    }

    /**
     *  Creats a new angel.
     */
    def create() {
        switch (request.method) {
            
            case 'GET':
                [angleInstance: new Angle(params)]
		break
            
            case 'POST':
	        def angleInstance = new Angle(params)
                if (!angleInstance.save(flush: true)) {
	            render view: 'create', model: [angleInstance: angleInstance]
	            return
	        }
                flash.message = message(code: 'default.created.message', args: [message(code: 'angle.label', default: 'Angle'), angleInstance.id])
	        //redirect action: 'show', id: angleInstance.id
                redirect(controller: "TripSearch", action: "index")
                break
	}
    }
    
    /**
     *  Shows all angels.
     */
    def show() {
        def angleInstance = Angle.get(params.id)
    
        if (!angleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'angle.label', default: 'Angle'), params.id])
            redirect action: 'list'
            return
        }

        [angleInstance: angleInstance]
    }

    /**
     *  Change the values of an angel.
     */
    def edit() {
	switch (request.method) {
            
            case 'GET':
	        def angleInstance = Angle.get(params.id)
	        
                if (!angleInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'angle.label', default: 'Angle'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [angleInstance: angleInstance]
		break
            
            case 'POST':
	        def angleInstance = Angle.get(params.id)
	    
                if (!angleInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'angle.label', default: 'Angle'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (angleInstance.version > version) {
	                angleInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'angle.label', default: 'Angle')] as Object[],
	                          "Another user has updated this Angle while you were editing")
	                render view: 'edit', model: [angleInstance: angleInstance]
	                return
	            }
	        }

	        angleInstance.properties = params

	        if (!angleInstance.save(flush: true)) {
	            render view: 'edit', model: [angleInstance: angleInstance]
	            return
	        }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'angle.label', default: 'Angle'), angleInstance.id])
	        redirect action: 'show', id: angleInstance.id
			break
	}
    }
    
    /**
     *  Delete an angel.
     */
    def delete() {
        def angleInstance = Angle.get(params.id)
        if (!angleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'angle.label', default: 'Angle'), params.id])
            redirect action: 'list'
            return
        }

        try {
            angleInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'angle.label', default: 'Angle'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'angle.label', default: 'Angle'), params.id])
            redirect action: 'show', id: params.id
        }
    }
    
    /**
     *  Creates new "predefined" angels.
     */
    def addNew(){
        new Angle(name:"Carmeq (Berlin)", address:"Helmholtzstr., Berlin",  
                  post_code:"10587", city:"Berlin",  vicinity:"Berlin").save()

        // Wolfsburg
        new Angle(name:"Carmeq - Autovision (Wolfsburg)", address:"Forum AutoVision, Wolfsburg 11",  
                  post_code:"38442", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
             
        new Angle(name:"VW TE (Hopfengarten)", address:"Mozartstraße, Wolfsburg-Fallersleben",  
                  post_code:"38442", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
            
        new Angle(name:"VW TE (Rübenkamp)", address:"Mozartstraße, Wolfsburg-Fallersleben",  
                  post_code:"38442", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
            
        new Angle(name:"VW AutoUni/Mobile/LifeCampus", address:"Hageberg Brücke Sandkamp, Wolfsburg",  
                  post_code:"38440", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
            
        new Angle(name:"VW FE (LKW Wache)", address:"VW LKW Waage, Wolfsburg",  
                  post_code:"38440", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
            
        new Angle(name:"VW FE (Fussgänger Wache)", address:"Saarstraße, Wolfsburg ",  
                  post_code:"38440", city:"Wolfsburg",  vicinity:"Wolfsburg").save()

        new Angle(name:"VW HMI (Fa. Volke)", address:"AutoMuseum, Wolfsburg ",  
                  post_code:"38446", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
            
        new Angle(name:"VW Isenbüttel", address:"Kl. Moorweg, Isenbüttel ",  
                  post_code:"38550", city:"Isenbüttel",  vicinity:"Isenbüttel").save()
            
        new Angle(name:"VW Prüfgelände Ehra(GEHT NICHT)", address:"",  
                  post_code:"38468", city:"Ehra-Lessien",  vicinity:"Ehra-Lessien").save()
            
        // Ingolstadt
        new Angle(name:"Carmeq (Ingolstadt)", address:"Gewerbegebiet, Gaimersheim ",  
                  post_code:"85080", city:"Gaimersheim",  vicinity:"Gaimersheim").save()
            
        new Angle(name:"Audi TE", address:"Audi - Tor 10, Ingolstadt",  
                  post_code:"85055", city:"Ingolstadt",  vicinity:"Ingolstadt").save()
            
        new Angle(name:"Audi Forum", address:"Scherzerstraße, Ingolstadt ",  
                  post_code:"85057", city:"Ingolstadt",  vicinity:"Ingoltstadt").save()
            
        // Stuttgart
        new Angle(name:"Carmeq (Stuttgart)", address:"Schlossplatz, Stuttgart ",  
                  post_code:"70173", city:"Stuttgart",  vicinity:"Stuttgart").save()
            
        new Angle(name:"Porsche Weissach", address:"Weissach (Württ) Marktplatz ",  
                  post_code:"71287", city:"Weissach",  vicinity:"Weissbach").save()
            
        // Prag
        new Angle(name:"e4t Prag(GEHT NICHT)", address:"Novodvorská 994/138",  
                  post_code:"147 00", city:"Praha 4-Branîk",  vicinity:"Prag").save()
            
        new Angle(name:"Skoda Werk(GEHT NICHT)", address:"Mladá Boleslav",  
                  post_code:"", city:"CZ",  vicinity:"Prag").save()
    }

} // eoc
