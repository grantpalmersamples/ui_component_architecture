# UI Component Architecture
The purpose of this architecture is to allow for reuse of layouts and front-end logic while enabling dynamic navigation between UI components.  It allows you to write a single function that takes in an identifier for a type of UI component and produces an instance of that component that can then be displayed.  This makes it possible to fully separate navigation logic from view logic.  You could, for example, write a finite state machine to describe your navigation flow and use its output to instantiate the appropriate UI component in response to any event.

For the example code posted here, I wrote a skeleton implementation in the context of a program for placing an order at McDonald's

Earlier this year, I gave a talk about this architecture at ASICS Digital, Inc.  It describes the problem that I initially ran into and the concepts behind my solution.  Note that a lot of the examples are based on the Runkeeper app, which I worked on during my last co-op.  Here's a rough transcript that I wrote out in preparation for the talk.  It should serve as a good introduction to the code in this repo.


## MVP Pattern, Reusability, and Component-Based UI Design

Hi everyone, my name's Grant and I work on the Runkeeper app.  I’m not the most experienced developer, but I do spend a lot of time thinking abstractly about the problems I run into.  Today I’m gonna talk about a problem that really got me thinking and an architecture I’ve come up with in response to it.  I’ll start with some examples and general concepts to illustrate the need for MVP and how it relates to the idea of UI components.  Then I’ll talk about how to leverage these concepts to dynamically display components.

First of all, for the purpose of this talk I want to define a UI component as something that facilitates an exchange of information between the user and the backend.  Every component has two key attributes that I’ll focus on for the next few minutes: purpose and delivery.  

To illustrate this, let’s take a look at the problem that got me thinking.  In the Runkeeper app, we ask users a series of questions to build them a training plan.

One question asks which types of runs they’ve done recently and allows them to select any number of options from a list.  This question is delivered as a full screen layout, but it could also be delivered as a popup or something.  The purpose of the question is the same either way, but the delivery changes.  So, we’d need two different components.  

Another question asks which days of the week they want to train on.  This question also presents a list of selectable options.  In theory, it should be able to use the same layouts, but the questions have different purposes and their responses are used differently.  So, again, we’d need two different components.

One thing I want to point out here is that when you select the “none” option for the "recent runs" question, the other options get disabled and vice-versa.  On the "training days" question, when you select a specified number of days, the other options get greyed out.  These behaviors are part of the purposes of the components.  A component’s purpose isn’t just about where the response goes--it's more broadly about what information is displayed an stored.

Although the presentations and purposes of these components may vary, they clearly have something in common, right?  They’re all “checkbox” questions.  They involve selecting options from a list.  And in terms of the app, this means that that for this type of question, the *way* in which information is exchanged between the delivery code and the purpose code is always the same.  So, clearly, there must be some way we can create components without writing all of these “purposes” and “deliveries” over and over, right?  Ideally, we'd like to mix and match them.

We can accomplish this with the MVP pattern, in which we divide our code into models, views, and presenters.  You all know what a model is, so I won't get into that.  What we care about is purpose and delivery.  In MVP, a view handles the delivery of information.  It displays whatever information it’s told to and notifies the presenter when the user interacts.  A presenter determines the purpose of the component.  It acts as a bridge between the model and the view, and it’s responsible for stuff like providing strings and other data to the view and determining how both the view and the model are updated in response to user interaction.

So, the view handles delivery, and the presenter handles purpose.  Since these are the two key attributes of UI components, we can use the MVP pattern to create components by combining pairs of compatible views and presenters.

But what determines whether a view and presenter are compatible?  Well, as with the "recent runs" and "training days" questions, a component can be created when the *way* in which information is exchanged between the delivery code and the purpose code makes logical sense.  You couldn't, for example, use a view with a list of selectable options to ask the user for their name.  There would be no reasonable way for that type of information to move between the view and the presenter.  To clearly define an interaction in MVP, it's often helpful to write a contract consisting of two intercfaces--one containing required view behaviors and another containing required presenter behaviors.

Now that we’ve covered MVP, let’s focus on something that makes this question flow particularly tricky.  It’s dynamic.  The answer to one question may require the app to ask follow-up questions.  

For example, if you say you want to train on three days per week, you’re then asked which three days you want to train on.  If the question flow is reasonably short and simple, we can get away with hard-coding the navigation logic.  Otherwise, we need to find a way to serve up components dynamically.

I’ll quickly sum up what I’ve said so far.  We have related categories of components.  A category is a question format, which corresponds to an MVP contract.  A component is a combination of a view and presenter that fulfills an MVP contract and facilitates a question.  

So, how do we create components dynamically?  Well, there’s a well-known pattern for instantiating objects from related families of types—the abstract factory pattern.

To illustrate this with a minimal example, I wrote an outline for part of a question flow that allows users to place an order at McDonald’s.


(In the actual talk, I did a brief code walk.  The code posted here has some comments that provide a rough explanation of the pieces I discussed.  Hopefully this has been a helpful introduction!)
