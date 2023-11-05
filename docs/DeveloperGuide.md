---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# BayMeds Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

_{ list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well }_

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/AY2324S1-CS2103T-T15-2/tp/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2324S1-CS2103T-T15-2/tp/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2324S1-CS2103T-T15-2/tp/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PrescriptionListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2324S1-CS2103T-T15-2/tp/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2324S1-CS2103T-T15-2/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2324S1-CS2103T-T15-2/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `PrescriptionListParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a prescription).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `PrescriptionListParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `BayMedsParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2324S1-CS2103T-T15-2/tp/tree/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the BayMeds data i.e., all `Prescription` objects (which are contained in a `UniquePrescriptionList` object).
* stores the currently 'selected' `Prescription` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Prescription>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

[//]: # (**Note:** An alternative &#40;arguably, a more OOP&#41; model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>)

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>


### Storage component

**API** : [`Storage.java`](https://github.com/AY2324S1-CS2103T-T15-2/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both BayMeds data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `Storage` and `UserPrefsStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Add feature

The add prescription feature is facilitated by the `AddCommandParser`.

Given below is an example usage scenario and how the add prescription mechanism behaves at each step.

Step 1. The user types the command `add mn/Propranolol d/4 f/Daily ed/20/01/2024`. Upon pressing enter, the `Ui` triggers the `execute` method in `Logic`, passing the input text to the `PrescriptionListparser` in `Logic`. The `PrescriptionListParser` then checks the command type to determine which command parser to call.

Step 2. Upon checking that it is an `add` command, the `AddCommandParser` will be created to `parse` the input text. It creates an `argMultiMap`, which contains the mappings of each recognised prefix in the input text, and its associated value.

**Note:** If there are absent prefixes that are compulsory, it will throw a `ParseException` error.

Step 3. The `AddCommandParser` will then create a new `Prescription` object using the `argMultiMap` with the prefix fields and values present. Optional fields which were not provided will be initialised as `null`. The `AddCommandParser` subsequently returns a new `AddCommand` object with the prescription to be added stored as an attribute.

**Note:** For the prescription's `startDate`, it will be initialised to `LocalDate.now()` if no start date is given, using the date of creation as the default `startDate` value.

Step 4: `Logic` then calls `AddCommand`'s `execute`. This will call `Model`'s `addPrescription` method, passing the prescription to be added. The `Model` will interact with the in-memory `PrescriptionList`, adding the prescription into it. Finally, the `Model` sets the `filteredPrescriptions` to show all prescriptions in the existing `PrescriptionList`.

The following sequence diagram shows how the `add` operation works.

<puml src="diagrams/AddCommandSequenceDiagram.puml" height="350" />

The following activity diagram summarizes what happens when the user executes an `add` command.

<puml src="diagrams/AddCommandActivityDiagram.puml" height="350" />

Design considerations:

Prescriptions may have an uneven consumption interval. For example, some prescriptions only needs to be consumed every Wednesday and Sunday. In such scenarios, users will need to input this prescription as 2 separate inputs with the same prescription name.

To cater for this, we are using every prescription's `name` and `startDate` to identify each prescription. Every `Prescription` must therefore have both these fields. As such, if no start date was provided, it will be initialised to a default value.

### Edit feature
The Edit Command is a fundamental feature of the BayMeds application, allowing users to modify the details of a prescription. It leverages the `EditPrescriptionDescriptor` and follows a specific edit mechanism.

#### `EditPrescriptionDescriptor`

The `EditPrescriptionDescriptor` is a crucial component for processing edit commands. It serves as a container for holding the new values that the user wishes to apply during the edit operation. This descriptor is essential for maintaining consistency and ensuring that only valid changes are made to a prescription.

Shown is an example of the usage of the edit command, which shows the EditPrescriptionDescriptor in action.

Step 1. User initiates an edit command, e.g., `edit 1 mn/UpdatedMedicationName d/3`.

Step 2. The `EditCommandParser` processes the user's input, extracting the prescription index and the edits to be made.

Step 3. The `EditCommandParser` then creates an instance of the `EditPrescriptionDescriptor`, populating it with the provided changes. In the example above, the medication name (`mn`) is updated to "UpdatedMedicationName," and the dosage (`d`) is updated to "3".

Step 4. The `EditCommandParser` then creates an instance of the `EditCommand` class, passing in the prescription index and the `EditPrescriptionDescriptor` instance.

Step 5. The `EditCommand` class then calls the `execute` method, which carries out checks to ensure that the prescription index is valid. If the prescription index is invalid, an error message is returned to the user.

Step 6. The 'EditCommand' class uses the `EditPrescriptionDescriptor` instance and the Prescription to be edited to create a new Prescription with the edited fields.

Step 7. The edited prescription is then checked to ensure that it is valid. If the edited prescription is invalid, an error message is returned to the user.

Step 8. The `EditCommand` class then calls the `editPrescription` method in the `Model` class, passing in the prescription index and the edited prescription.



The following class diagram shows the relationship between the `EditPrescriptionDescriptor` and the `EditCommand` class.

<puml src="diagrams/EditCommandClassDiagram.puml" width="550" />


### Take / untake feature

**Note:** The untake feature is similar to the take feature but reverses the incrementing and decrementing of consumption count and stock respectively, given below is the guide for the take feature.

The take prescription feature is facilitated by the `TakeCommandParser`.

Given below is an example usage scenario and how the take prescription mechanism behaves at each step.

Step 1. The user types the command `take 1 d/4`. Upon pressing enter, the `Ui` triggers the `execute` method in `Logic`, passing the input text to the `PrescriptionListparser` in `Logic`. The `PrescriptionListParser` then checks the command type to determine which command parser to call.

Step 2. Upon checking that it is a `take` command, the `TakeCommandParser` will be created to `parse` the input text. It creates an `argMultiMap`, which contains the mappings of each recognised prefix in the input text, and its associated value.

**Note:** If no dosage is specified, the default dosage to take will be 1.

Step 3. The `TakeCommandParser` subsequently returns a new `TakeCommand` object with the index of the prescription to take and the dosage to take.

Step 4: `Logic` then calls `TakeCommand`'s `execute`. This will call `Model`'s `getFilteredPrescriptionList` method and retrieve the prescription based on the index from the in-memory `PrescriptionList`, necessary checks are done to check if the index is valid and there is enough stock.
`TakeCommand`'s executeTake is then called, incrementing the consumption count and decrementing the stock of the prescription. Finally, the completed status is set based on the consumption count and dosage of the prescription and the `Model` sets the `filteredPrescriptions` to show the prescription that was just taken from.

The following class diagram shows the classes associated with executing a take command.

<puml src="diagrams/TakeCommandClassDiagram.puml" height="350" />

Design considerations:

Prescriptions may have duplicate names but different start dates.

To cater for this, the take command uses the index instead of the name of the medication when executing.


### List today feature

The list today feature is facilitated by the `ListTodayCommandParser`.

Given below is an example usage scenario and how the list today mechanism behaves at each step.

Step 1. `Ui` and `Logic` works similarly to when [adding prescriptions](#add-feature). However, a `ListTodayCommandParser` is created instead.

**Note:** If there are extra fields in the input, it will throw a `ParseException` error.

Step 2. The `ListTodayCommandParser` subsequently returns a new `ListTodayCommand` object.

Step 3: `Logic` then calls `ListTodayCommand`'s `execute`. This will create a new `IsTodayPredicate` object which is passed into the method call for `Model`'s `updateFilteredPrescriptionList`.

Step 4: The `Model` will then update the in-memory `FilteredList<Prescription>` with the new predicate. Finally, the `Model` sets the `filteredPrescriptions` to show only today's prescription in the existing `PrescriptionList`.

The following sequence diagram shows how the `listToday` operation works.

<puml src="diagrams/ListTodayCommandSequenceDiagram.puml" height="350" />

The following activity diagram summarizes what happens when the user executes an `listToday` command.

<puml src="diagrams/ListTodayCommandActivityDiagram.puml" height="350" />

Design considerations:

As there are no input parameters for `ListTodayCommand`, it is possible to implement this without `ListTodayCommandParser`. However, `ListTodayCommandParser` was created to utilise methods in `argMultiMap` to check for extra fields.

The result of `IsTodayPredicate` depends on the prescription's `startDate`. It is intentional that monthly frequencies are based on increments of 30 days rather than the absolute day of each month.

### List completed feature

### Find feature

### Remind prescription feature

### \[Proposed\] Check prescription interaction feature

The check prescription interaction feature is facilitated by the `AddCommandParser`.

Given below is an example usage scenario and how the check prescription interaction mechanism behaves at each step.

Step 1. `Ui` and `Logic` works similarly to when [adding prescriptions](#add-feature). It creates an `argMultiMap`, which contains the mappings of each recognised prefix in the input text, and its associated value.

**Note:** If there are extra fields in the input, it will throw a `ParseException` error.

Step 2. The `AddCommandParser` subsequently returns a new `AddCommand` object.

Step 3: `Logic` then calls `AddCommand`'s `execute`. The ModelManager will check through the Prescriptions in the PrescriptionList to ensure that there are no clashing Drugs.

**Note:** If the prescription to be added contains a drug that will clash with an existing prescription, an warning message is returned to the user. The prescription will still be added.

Step 4: The `Model` will then update the in-memory `FilteredList<Prescription>` with the new drugs.

The following object oriented domain model shows the class structure of the problem domain.

<puml src="diagrams/CheckInteractionOOD.puml" height="350" />

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

<puml src="diagrams/UndoRedoState0.puml" alt="UndoRedoState0" />

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</box>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

<puml src="diagrams/UndoRedoState3.puml" alt="UndoRedoState3" />


<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</box>

The following sequence diagram shows how the undo operation works:

<puml src="diagrams/UndoSequenceDiagram.puml" alt="UndoSequenceDiagram" />

<box type="info" seamless>

**Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</box>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

<puml src="diagrams/UndoRedoState4.puml" alt="UndoRedoState4" />

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

<puml src="diagrams/UndoRedoState5.puml" alt="UndoRedoState5" />

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/CommitActivityDiagram.puml" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has a need to manage a significant number of prescriptions
* prefers a quick way of tracking medication needs, dosage and related health information
* prefers desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**:

* manage prescriptions faster than a typical mouse/GUI driven app
* track dosage schedule and instructions
* track medical history
* log symptoms


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                            | I want to …​                                        | So that I can…​                                                    |
|----------|------------------------------------|-----------------------------------------------------|--------------------------------------------------------------------|
| `* * *`  | sickly patient                     | add prescriptions                                   | manage additional prescriptions should I be prescribed them        |
| `* * *`  | recovering patient                 | remove prescriptions                                | remove prescriptions that the doctor deems unnecessary from now on |
| `* * *`  | forgetful patient                  | mark the medication as consumed                     | not accidentally overdose on a certain medication                  |
| `* * *`  | forgetful patient                  | list all my prescriptions                           | track all the medications I am currently taking                    |
| `* *`    | forgetful patient                  | list all the medications I have not taken today     | follow my prescription accurately                                  |
| `* *`    | patient undergoing a tapering plan | edit prescriptions that I have added                | adjust my dosage schedules easily                                  |
| `* *`    | forgetful patient                  | get daily reminders of what medications to take     | take my medication on time                                         |
| `*`      | forgetful patient                  | get a reminder when a medication is about to expire | premptively stock up before it runs out                            |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `BayMeds` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Delete a prescription**

**MSS**

1.  User requests to list prescriptions.
2.  BayMeds shows a list of prescriptions.
3.  User requests to delete a specific prescription in the list.
4.  BayMeds deletes the prescription.

    Use case ends.

**Extensions**

* 1a. The given command is invalid.

    * 1a1. BayMeds shows an error message.

      Use case resumes at step 1.

* 2a. The list is empty.

  Use case ends.

* 3a. The given prescription is not in the list.

    * 3a1. BayMeds shows an error message.

      Use case resumes at step 2.

**Use case: List all prescriptions**

**MSS**

1.  User requests to list all prescriptions.
2.  BayMeds shows a list of prescriptions together with their details.

    Use case ends.

**Extensions**

* 1a. The given command is invalid.

    * 1a1. BayMeds shows an error message.

      Use case resumes at step 1.

* 2a. The list is empty.

  Use case ends.

**Use case: List medications to be consumed today**

**MSS**

1.  User requests to list all remaining medications to be consumed today.
2.  BayMeds shows a list of medications to be consumed today.

    Use case ends.

**Extensions**

* 1a. The given command is invalid.

    * 1a1. BayMeds shows an error message.

      Use case resumes at step 1.

* 2a. The list is empty.

  Use case ends.

*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 prescriptions without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  Should be able to give a notification to the user when the application is running. (Time to take medication / medication is about to expire)
5.  Should be able to track current date and time when the application is running.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Medication**: A drug identified by a name
* **Prescription**: Uniquely identified by a medication, a frequency and a quantity

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample prescriptions. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a medication

1. Deleting a medication while all medications are being shown

   1. Prerequisites: List all medications using the `list` command. Multiple medications in the list.

   1. Test case: `delete --medication "Doxazosin"`<br>
      Expected: Doxazosin is deleted from the list. Details of the deleted medication shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete --medication "Watsons"`<br>
      Expected: No medication is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...`<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
