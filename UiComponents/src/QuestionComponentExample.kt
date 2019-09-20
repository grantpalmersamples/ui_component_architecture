interface IQuestionComponent : IUiComponentDisplaySpec

/**
 * The purpose of a component in the context of MVP is to hook up a view and a presenter.  A
 * component creates one and only one concrete view-presenter pair.  A display is able to navigate
 * to a component with no strings attached.
 */
class FullScreenEntreeQuestionComponent(private val orderData: IOrderData) : IQuestionComponent {
    private val view: MultiChoiceQuestionMvp.View
    private val presenter: IEntreeQuestionPresenter

    init {
        val view = FullScreenMultiChoiceQuestionView()
        presenter = EntreeQuestionPresenter(view, orderData)
        view.presenter = presenter
        this.view = view
    }
}