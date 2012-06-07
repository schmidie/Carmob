package conn



import org.junit.*
import grails.test.mixin.*

@TestFor(TempTripController)
@Mock(TempTrip)
class TempTripControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/tempTrip/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.tempTripInstanceList.size() == 0
        assert model.tempTripInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.tempTripInstance != null
    }

    void testSave() {
        controller.save()

        assert model.tempTripInstance != null
        assert view == '/tempTrip/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/tempTrip/show/1'
        assert controller.flash.message != null
        assert TempTrip.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/tempTrip/list'


        populateValidParams(params)
        def tempTrip = new TempTrip(params)

        assert tempTrip.save() != null

        params.id = tempTrip.id

        def model = controller.show()

        assert model.tempTripInstance == tempTrip
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/tempTrip/list'


        populateValidParams(params)
        def tempTrip = new TempTrip(params)

        assert tempTrip.save() != null

        params.id = tempTrip.id

        def model = controller.edit()

        assert model.tempTripInstance == tempTrip
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/tempTrip/list'

        response.reset()


        populateValidParams(params)
        def tempTrip = new TempTrip(params)

        assert tempTrip.save() != null

        // test invalid parameters in update
        params.id = tempTrip.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/tempTrip/edit"
        assert model.tempTripInstance != null

        tempTrip.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/tempTrip/show/$tempTrip.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        tempTrip.clearErrors()

        populateValidParams(params)
        params.id = tempTrip.id
        params.version = -1
        controller.update()

        assert view == "/tempTrip/edit"
        assert model.tempTripInstance != null
        assert model.tempTripInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/tempTrip/list'

        response.reset()

        populateValidParams(params)
        def tempTrip = new TempTrip(params)

        assert tempTrip.save() != null
        assert TempTrip.count() == 1

        params.id = tempTrip.id

        controller.delete()

        assert TempTrip.count() == 0
        assert TempTrip.get(tempTrip.id) == null
        assert response.redirectedUrl == '/tempTrip/list'
    }
}
