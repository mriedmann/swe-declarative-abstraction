# swe-declarative-abstraction

Your are a programmer. A junior programmer in a new team of a bank. As the new guy the lead developer of the team, your boss, gives you the same task as he did for the other junior devs in your team. You have to implement a connector to a third-party solution which is managing securities/stocks for the bank.

The system you have to connect to is a very old legacy system and therefore the request and response objects are formatted as fixed width text (in our example you get files, in reality you can request the files via http calls). The first guy who got this task started to implement the first connector. It is able to read information about security accounts. The lead developer checked the code and was quite ok with it. It fullfills exactly the needs. At this time the business application just needed overview data from this legacy system.

Time was rising and 4 months later the next junior dev joined the team. The lead developer knew he has again a business functionality in the backlog which was related to securities. He spared it for the new guy who had to develop the connector for reading meta information from stocks. The lead dev checked the code and was a bit dissapointed. The junior copied the connector which already existed and changed it to read the information from the new transaction (file). Code reuse => zero. But because the team was busy at that time and from a functional point of view there was nothing bad with the implementation the lead dev decided to postpone the refactoring.

Then you joined the team!
The lead developer was thinking about your first task. He had the same idea as he had for the other two juniors. The security connector! But he remembered the lack of code reuse in the current solution. So he decided to takle this together with you (we did during the lecture). You refactored together with the lead developer the two connectors. The next day in the daily standup you mentioned this refactoring as your task and you planned to takle the next connector for today. After you a business analyst was mentioning new upcoming business requirements which are all related to securities. After the standup you and the lead developer recognised more than 30 transactions (files) are needed in the upcoming weeks to be implemented.

The lead developer decided to postpone your current task and asked you to implement a library to handle these fixed width text format. Due to licensing problems all available open source solutions on the market are not useable in your project. So you have to develop your own one. The only requirement the lead developer mentioned: The library should not force the usage of imperative programming. He want's you to implement the solution in a way, whenever you have to implement a new transaction you simple describe the data fields of your model and the mapping from/to the fixed width file format is handles by the library. He gave you two possible hints how it might be solved:

1. Define your own DSL which is used for the mapping (compare JPA XML Mapping filles)
2. Do the mapping through Annotations (compare JPA Annotiations)

You can decide which approach you prefer or you find another way how to do the mapping between model and file in a declarative way. For the sake of simplicity you should refactor the existing connectors (new implementations of the interface) and fullfill the requirements currently exist for the two given datastructures. You only need to read the data from the files, no write transactions required.

See here a description of the data structures:

## SecurityAccountOverview

### Structure

fieldname | length | example | padding char | alignment
--------- | ------ | ------- | ------------ | ---------
Transaction name | 40 | SecurityAccountOverview | blank | left
Security Account Number | 10 | 12345678 | 0 | right
Risk category | 2 | 00 | none | left
Depot Owner Lastname | 30 | MUSTERMANN | blank | right
Depot Owner Firstname | 30 | MAX UND MARIA | blank | right
Currency | 3 | EUR | blank | left
Balance | 17 | 1692.45 | blank | right

## SecurityConfiguration

### Structure

fieldname | length | example | padding char | alignment
--------- | ------ | ------- | ------------ | ---------
Transaction name | 40 | SecurityConfiguration | blank | left
ISIN | 12 | AT0000937503 | none | left
Risk category | 2 | 02 | none | left
Name | 30 | voestalpine Aktie | blank | right
Currency | 3 | EUR | blank | left
52 Weeks Highest | 10 | 54.98 | blank | right
52 Weeks Lowest | 10 | 29.60 | blank | right

## Risk Category

Category | Value
-------- | -----
NON-EXISTING | 00
EXECUTION-ONLY | 01
LOW | 02
MIDDLE | 04
HIGH | 06
SPECULATIVE | 08

## Disclaimer

Have fun and do not hesitate to ask questions. The goal of this lesson is not a perfect implementation, rather finding the right level of abstraction and getting more in touch with declarative programming.

