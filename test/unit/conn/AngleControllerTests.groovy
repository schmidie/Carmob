package conn



import org.junit.*
import grails.test.mixin.*

@TestFor(AngleController)
@Mock(Angle)
class AngleControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/angle/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.angleInstanceList.size() == 0
        assert model.angleInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.angleInstance != null
    }

    void testSave() {
        controller.save()

        assert model.angleInstance != null
        assert view == '/angle/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/angle/show/1'
        assert controller.flash.message != null
        assert Angle.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/angle/list'


        populateValidParams(params)
        def angle = new Angle(params)

        assert angle.save() != null

        params.id = angle.id

        def model = controller.show()

        assert model.angleInstance == angle
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/angle/list'


        populateValidParams(params)
        def angle = new Angle(params)

        assert angle.save() != null

        params.id = angle.id

        def model = controller.edit()

        assert model.angleInstance == angle
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/angle/list'

        response.reset()


        populateValidParams(params)
        def angle = new Angle(params)

        assert angle.save() != null

        // test invalid parameters in update
        params.id = angle.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/angle/edit"
        assert model.angleInstance != null

        angle.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/angle/show/$angle.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        angle.clearErrors()

        populateValidParams(params)
        params.id = angle.id
        params.version = -1
        controller.update()

        assert view == "/angle/edit"
        assert model.angleInstance != null
        assert model.angleInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/angle/list'

        response.reset()

        populateValidParams(params)
        def angle = new Angle(params)

        assert angle.save() != null
        assert Angle.count() == 1

        params.id = angle.id

        controller.delete()

        assert Angle.count() == 0
        assert Angle.get(angle.id) == null
        assert response.redirectedUrl == '/angle/list'
    }
}
