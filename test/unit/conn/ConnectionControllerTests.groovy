package conn



import org.junit.*
import grails.test.mixin.*

@TestFor(ConnectionController)
@Mock(Connection)
class ConnectionControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/connection/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.connectionInstanceList.size() == 0
        assert model.connectionInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.connectionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.connectionInstance != null
        assert view == '/connection/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/connection/show/1'
        assert controller.flash.message != null
        assert Connection.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/connection/list'


        populateValidParams(params)
        def connection = new Connection(params)

        assert connection.save() != null

        params.id = connection.id

        def model = controller.show()

        assert model.connectionInstance == connection
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/connection/list'


        populateValidParams(params)
        def connection = new Connection(params)

        assert connection.save() != null

        params.id = connection.id

        def model = controller.edit()

        assert model.connectionInstance == connection
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/connection/list'

        response.reset()


        populateValidParams(params)
        def connection = new Connection(params)

        assert connection.save() != null

        // test invalid parameters in update
        params.id = connection.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/connection/edit"
        assert model.connectionInstance != null

        connection.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/connection/show/$connection.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        connection.clearErrors()

        populateValidParams(params)
        params.id = connection.id
        params.version = -1
        controller.update()

        assert view == "/connection/edit"
        assert model.connectionInstance != null
        assert model.connectionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/connection/list'

        response.reset()

        populateValidParams(params)
        def connection = new Connection(params)

        assert connection.save() != null
        assert Connection.count() == 1

        params.id = connection.id

        controller.delete()

        assert Connection.count() == 0
        assert Connection.get(connection.id) == null
        assert response.redirectedUrl == '/connection/list'
    }
}
