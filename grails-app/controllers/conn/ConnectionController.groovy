package conn

import org.springframework.dao.DataIntegrityViolationException

/**
 *  ConnectionController Class generated with scaffolding
 *  Methods for create, list, show, edit and delete Connections
 */
class ConnectionController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    /**
     *  Redirection to the index with a list of all connections.
     */
    def index() {
        redirect action: 'list', params: params
    }

    /**
     *  Lists all connctions.
     */
    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [connectionInstanceList: Connection.list(params), connectionInstanceTotal: Connection.count()]
    }

    /**
     *  Creats a new connection.
     */
    def create() {
	switch (request.method) {
        
            case 'GET':
        	[connectionInstance: new Connection(params)]
		break

            case 'POST':
	        def connectionInstance = new Connection(params)
	        if (!connectionInstance.save(flush: true)) {
	            render view: 'create', model: [connectionInstance: connectionInstance]
	            return
	        }
		flash.message = message(code: 'default.created.message', args: [message(code: 'connection.label', default: 'Connection'), connectionInstance.id])
	        redirect action: 'show', id: connectionInstance.id
        	break
                
	}
    }

    /**
     *  Shows connection.
     */
    def show() {
        def connectionInstance = Connection.get(params.id)
        if (!connectionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'connection.label', default: 'Connection'), params.id])
            redirect action: 'list'
            return
        }

        [connectionInstance: connectionInstance]
    }

    /**
     *  Change the values of a connection.
     */
    def edit() {
	switch (request.method) {
            
            case 'GET':
	        def connectionInstance = Connection.get(params.id)
	        if (!connectionInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'connection.label', default: 'Connection'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [connectionInstance: connectionInstance]
		break
                
            case 'POST':
	        def connectionInstance = Connection.get(params.id)
	        if (!connectionInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'connection.label', default: 'Connection'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (connectionInstance.version > version) {
	                connectionInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'connection.label', default: 'Connection')] as Object[],
	                          "Another user has updated this Connection while you were editing")
	                render view: 'edit', model: [connectionInstance: connectionInstance]
	                return
	            }
	        }

	        connectionInstance.properties = params

	        if (!connectionInstance.save(flush: true)) {
	            render view: 'edit', model: [connectionInstance: connectionInstance]
	            return
	        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'connection.label', default: 'Connection'), connectionInstance.id])
	        redirect action: 'show', id: connectionInstance.id
		break
	}
    }

    /**
     *  Deletes a connection.
     */
    def delete() {
        def connectionInstance = Connection.get(params.id)
        if (!connectionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'connection.label', default: 'Connection'), params.id])
            redirect action: 'list'
            return
        }

        try {
            connectionInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'connection.label', default: 'Connection'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'connection.label', default: 'Connection'), params.id])
            redirect action: 'show', id: params.id
        }
    }
    
} // eoc
