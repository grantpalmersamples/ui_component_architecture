/*
These should be viewed as universal, i.e. applicable to any UI component, not just components in
the questionnaire laid out in this example.
*/

/**
 * Instructions to display a specific UI component in a specific manner
 *
 * The properties stored here will vary based on the framework being used
 */
interface IUiComponentDisplaySpec

/**
 * Something that can display UI components
 *
 * In practice, this will probably wrap some framework class
 */
interface IUiDisplay {
    fun navigate(navigationSpec: IUiComponentDisplaySpec)
}