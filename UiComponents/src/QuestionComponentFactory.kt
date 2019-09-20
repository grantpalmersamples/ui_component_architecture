/*
Here we use the abstract factory pattern to instantiate components.  The components in turn hook
up one specific view presenter pair.
*/

interface IQuestionComponentFactory {
    fun newQuestionComponent(questionType: QuestionType, orderData: IOrderData): IQuestionComponent?
}

object FullScreenQuestionComponentFactory : IQuestionComponentFactory {
    override fun newQuestionComponent(questionType: QuestionType, orderData: IOrderData) =
            when (questionType) {
                QuestionType.ENTREE -> FullScreenEntreeQuestionComponent(orderData)
                QuestionType.DRINK -> FullScreenDrinkQuestionComponent(orderData)
                QuestionType.TO_GO -> FullScreenToGoQuestionComponent(orderData)
                QuestionType.SUPERSIZE -> FullScreenSupersizeQuestionComponent(orderData)
            }
}

object CompactQuestionComponentFactory : IQuestionComponentFactory {
    override fun newQuestionComponent(questionType: QuestionType, orderData: IOrderData) =
            when (questionType) {
                QuestionType.ENTREE -> CompactEntreeQuestionComponent(orderData)
                QuestionType.DRINK -> CompactDrinkQuestionComponent(orderData)
                QuestionType.TO_GO -> CompactToGoQuestionComponent(orderData)
                QuestionType.SUPERSIZE -> CompactSupersizeQuestionComponent(orderData)
            }
}

object QuestionComponentMetaFactory {
    fun getQuestionFactory(displayType: ComponentDisplayType) =
            when(displayType) {
                ComponentDisplayType.FULL_SCREEN -> FullScreenQuestionComponentFactory
                ComponentDisplayType.COMPACT -> CompactQuestionComponentFactory
            }
}

object AnyQuestionComponentFactory {
    fun newQuestionComponent(
            questionType: QuestionType,
            displayType: ComponentDisplayType,
            orderData: IOrderData
    ) =
            QuestionComponentMetaFactory
                    .getQuestionFactory(displayType)
                    .newQuestionComponent(questionType, orderData)
}