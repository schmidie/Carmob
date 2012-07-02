package conn

import org.springframework.dao.DataIntegrityViolationException

class UserController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    /**
     *  Redirection to the index with a list of all users.
     */
    def index() {
        redirect action: 'list', params: params
    }

    /**
     *  Lists all users.
     */
    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    }

    /**
     *  Creats a new user.
     */
    def create() {
	
        switch (request.method) {
            
            case 'GET':
        	[userInstance: new User(params)]
		break
            case 'POST':
	        def userInstance = new User(params)
	        if (!userInstance.save(flush: true)) {
	            render view: 'create', model: [userInstance: userInstance]
	            return
	        }

                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
                redirect action: 'show', id: userInstance.id
		break
	}
    }

    /**
     *  Shows all users.
     */
    def show() {
        def userInstance = User.get(params.id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect action: 'list'
            return
        }

        [userInstance: userInstance]
    }

    /**
     *  Change the values of an user.
     */
    def edit() {
	
        switch (request.method) {
            
            case 'GET':
	        def userInstance = User.get(params.id)
	        if (!userInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [userInstance: userInstance]
		break
            
            case 'POST':
	        def userInstance = User.get(params.id)
	        if (!userInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (userInstance.version > version) {
	                userInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'user.label', default: 'User')] as Object[],
	                          "Another user has updated this User while you were editing")
	                render view: 'edit', model: [userInstance: userInstance]
	                return
	            }
	        }

	        userInstance.properties = params

	        if (!userInstance.save(flush: true)) {
	            render view: 'edit', model: [userInstance: userInstance]
	            return
	        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
	        redirect action: 'show', id: userInstance.id
		break
	}
    }

    /**
     *  Delete an user.
     */
    def delete() {
        def userInstance = User.get(params.id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect action: 'list'
            return
        }

        try {
            userInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect action: 'show', id: params.id
        }
    }
    
    def profil() {}
    
} // eoc