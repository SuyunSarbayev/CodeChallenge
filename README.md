# Welcome

Hi there, thank you for start reading README first. The goal of current technical task is to bring developer into the environment which we all facing when joining a new team. Usually there is some architecture already in the project together with some awesome custom components, bugs and pieces of code which need to be refactored.

So you are the lucky one who can help with all open tasks.

The idea for the project is pretty simple. It's a viewer of events with the possibility to see the details of each of it. Existing project contains only a skeleton based on MVVM + Clean Architecture including some mock data. Your task is to implement some business logic as well as refactoring some parts of the code.


# Use Cases
- As a user I want to see a list of upcoming events sorted by date (ascending)
- As a user I want to be able to sort events by price (both descending/ascending)
- As a user I want to see details of a concrete event
## Optional
- As a user I want to filter out outdated events

# Design
There is no special design, but there are some requirements for each screen/which data should be shown.
- Events overview screen should:
    - include a list of events with title and date
    - should contain pull-down to refresh gesture to reload the data
    - buttons for sorting by date/price
    - button to filter out outdated events (optional)
- Event details screen should:
    - should contain: title, event description, date of event, address, contact phone, buy button with price on it

# API dependencies
Documentation for API's could be found in Postman collection under the folder 'docs/EventsList.postman_collection.json'.

# TODO:
### Business logic tasks:
[ ] implement ASYNCHRONOUS backend communication instead of MOCK samples (see Postman collection for reference and infrastructure/network project package)
[ ] fix date formatting on overview page to the following format: "05 August 2021"
[ ] add correct date for "fromDate" and "untilDate" for events overview page title
[ ] implement sorting by date and by price for overview screen
[ ] implement event details screen

### Code refactoring tasks:
[ ] EventsOverviewFragment contains the mock of the events. Please refactor in order to fetch the data from backend and to make the part of code testable.
[ ] Write UnitTests for sorting/filtering logic of events overview list

## Optional
### Business logic tasks:
[ ] implement logic of filtering only upcoming events (events which are older than today do not show in the list)

### Code refactoring tasks:
[ ] Write UnitTests for EventsOverviewAndroidViewModel

# Good to know
- Feel free to add any dependencies that you need into the project, to be able to complete the task
- The goal of UnitTest coverage is 80+%
- Completed tasks mark as [x] in this README
