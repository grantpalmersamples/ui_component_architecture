/*
Here's an example of a rough implementation of a view and presenter.  The view and presenter below
implement interfaces in the same MVP contract.  Therefore they are compatible and can be hooked up
to create a component.

NOTE: There are some TODOs for functionality that is beyond the scope of this example and would
require more context.
 */

class FullScreenMultiChoiceQuestionView : MultiChoiceQuestionMvp.View {
    // we assign these externally after instantiation
    var presenter: MultiChoiceQuestionMvp.Presenter? = null
    var display: IUiDisplay? = null

    /**
     * This method is the point of this whole thing.  With this, we can
     */
    override fun displayQuestion(type: QuestionType, orderData: IOrderData) {
        val displayType: ComponentDisplayType = TODO() // This gets decided somewhere
        AnyQuestionComponentFactory.newQuestionComponent(type, displayType, orderData)?.let {
            display?.navigate(it)
        }
    }

    override fun goBack() = TODO()

    /**
     * This gets hooked up to a click event or something
     */
    private fun onAnswerSelected(index: Int) {
        presenter?.setResponse(index)
    }
}

interface IEntreeQuestionPresenter : MultiChoiceQuestionMvp.Presenter

class EntreeQuestionPresenter(
        private val view: MultiChoiceQuestionMvp.View,
        private val orderData: IOrderData
) : IEntreeQuestionPresenter {
    private val optionViewModels: List<IOptionViewModel> = TODO()

    override val questionText = "Which entree would you like to order?"

    override fun setResponse(data: Int) {
        orderData.entree = EntreeType.values()[data]
    }

    override val optionCount get() = optionViewModels.size

    override fun getOptionViewModel(index: Int) = optionViewModels[index]
}