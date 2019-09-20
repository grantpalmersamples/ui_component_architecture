enum class EntreeType
enum class DrinkType

/**
 * The data passed to all components in the navigation flow.
 *
 * In this case, since the answers to previous questions may affect which questions are asked next,
 * we pass a data object containing all answers given so far.
 */
interface IOrderData {
    var entree: EntreeType?
    var drink: DrinkType?
    var toGo: Boolean?
    var supersize: Boolean?
}