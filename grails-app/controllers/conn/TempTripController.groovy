package conn

import org.springframework.dao.DataIntegrityViolationException

/**
 *  ! comment here - The temporary trip controller
 */
class TempTripController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    /**
     *  ! comment here
     */
    def index() {
        redirect action: 'list', params: params
    }

    /**
     *  ! comment here
     */
    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [tempTripInstanceList: TempTrip.list(params), tempTripInstanceTotal: TempTrip.count()]
    }

    /**
     *  ! comment here
     */
    def create() {
		switch (request.method) {
		case 'GET':
        	[tempTripInstance: new TempTrip(params)]
			break
		case 'POST':
	        def tempTripInstance = new TempTrip(params)
	        if (!tempTripInstance.save(flush: true)) {
	            render view: 'create', model: [tempTripInstance: tempTripInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'tempTrip.label', default: 'TempTrip'), tempTripInstance.id])
	        redirect action: 'show', id: tempTripInstance.id
			break
		}
    }

    /**
     *  ! comment here
     */
    def show() {
        def tempTripInstance = TempTrip.get(params.id)
        if (!tempTripInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'tempTrip.label', default: 'TempTrip'), params.id])
            redirect action: 'list'
            return
        }

        [tempTripInstance: tempTripInstance]
    }

    /**
     *  ! comment here
     */
    def edit() {
	
        switch (request.method) {
            
            case 'GET':
                def tempTripInstance = TempTrip.get(params.id)
	        if (!tempTripInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tempTrip.label', default: 'TempTrip'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [tempTripInstance: tempTripInstance]
		break
		
            case 'POST':
	        def tempTripInstance = TempTrip.get(params.id)
	        if (!tempTripInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tempTrip.label', default: 'TempTrip'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (tempTripInstance.version > version) {
	                tempTripInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'tempTrip.label', default: 'TempTrip')] as Object[],
	                          "Another user has updated this TempTrip while you were editing")
	                render view: 'edit', model: [tempTripInstance: tempTripInstance]
	                return
	            }
	        }

	        tempTripInstance.properties = params

	        if (!tempTripInstance.save(flush: true)) {
	            render view: 'edit', model: [tempTripInstance: tempTripInstance]
	            return
	        }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'tempTrip.label', default: 'TempTrip'), tempTripInstance.id])
	        redirect action: 'show', id: tempTripInstance.id
		break
	}
    }

    /**
     *  ! comment here
     */
    def delete() {
        
        def tempTripInstance = TempTrip.get(params.id)
        
        if (!tempTripInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tempTrip.label', default: 'TempTrip'), params.id])
            redirect action: 'list'
            return
        }

        try {
            tempTripInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'tempTrip.label', default: 'TempTrip'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tempTrip.label', default: 'TempTrip'), params.id])
            redirect action: 'show', id: params.id
        }
        
    }

} // eoc