/*
Ignore these, I've just defined them for use in the example factories.
 */

class FullScreenDrinkQuestionComponent(private val orderData: IOrderData) : IQuestionComponent

class FullScreenToGoQuestionComponent(private val orderData: IOrderData) : IQuestionComponent
class FullScreenSupersizeQuestionComponent(private val orderData: IOrderData) : IQuestionComponent


class CompactEntreeQuestionComponent(private val orderData: IOrderData) : IQuestionComponent
class CompactDrinkQuestionComponent(private val orderData: IOrderData) : IQuestionComponent

class CompactToGoQuestionComponent(private val orderData: IOrderData) : IQuestionComponent
class CompactSupersizeQuestionComponent(private val orderData: IOrderData) : IQuestionComponent