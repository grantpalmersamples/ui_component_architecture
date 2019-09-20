/*
MVP contracts define an exchange of information between a view and a presenter.  In this example,
an MVP contract corresponds to a question format.  Each question format must have its own contract.

NOTE: Both of the formats below involve selecting predetermined options, but it's just as easy to
support numeric and textual input, etc.
 */

/**
 * The MVP contract shared by all questions.  Nothing here depends on the format of the question.
 */
interface QuestionMvp {
    interface View {
        fun displayQuestion(type: QuestionType, orderData: IOrderData)
        fun goBack()
    }

    interface Presenter<R> {
        val questionText: String

        // the response type varies by question format, but all questions must accept a response.
        fun setResponse(data: R)
    }
}

/**
 * A view model for a selectable option, typically presented in a list.
 */
interface IOptionViewModel {
    val text: String
}

/**
 * The MVP contract specific to multiple choice questions.  This format supports an arbitrary
 * number of options.
 */
interface MultiChoiceQuestionMvp {
    interface View : QuestionMvp.View

    interface Presenter : QuestionMvp.Presenter<Int> {
        val optionCount: Int
        fun getOptionViewModel(index: Int): IOptionViewModel
    }
}

/**
 * The MVP contract specific to boolean questions.  This format supports a true option and a false
 * option.
 */
interface BooleanQuestionMvp {
    interface View : QuestionMvp.View

    interface Presenter : QuestionMvp.Presenter<Boolean> {
        val trueOptionViewModel: IOptionViewModel
        val falseOptionViewModel: IOptionViewModel
    }
}