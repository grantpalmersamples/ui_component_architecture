/**
 * An enumeration of all questions that may be asked in this workflow.  They are grouped by
 * question format.  Adding hierarchy based on question format is also an option, but not
 * necessary.
 */
enum class QuestionType {
    // multiple choice questions
    ENTREE, // which entree do you want?
    DRINK, // which drink do you want?

    // boolean questions
    TO_GO, // do you want the order to go?
    SUPERSIZE; // do you want to supersize the entree?
}