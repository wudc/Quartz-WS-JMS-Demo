# Quartz-WS-JMS-Demo
Quartz, Web Service, and JMS Demo Using a Simple Payment Service
A simple demo project designed for doing the following:
1) Spring boot applcation
2) Integrated with the Quartz library to schedule the payment submit jobs scheduled for every 5 seconds
3) Each job calls a web service client to submit the payment request
4) A separate simple WS server receives the submit request and returns an acknowledgement (Accepted, Pending, and Rejected)
5) Quartz tables and triggers are in a Postgres database server

This project demonstrates the following:
Spring boot controller -> Scheduler service to scheduled payment job
Payment Job -> Payment service
Payment service -> WS client
WS client -> WS server
WS server acknowledgement -> WS client
WS client acknowledgement -> Payment service
Payment service acknowledge -> Payment job to display output

To do list:
1) Payment service update database with acknowledgement
2) Create JMS server to generate business response message
3) Create JMS listener to update business response in database
4) Separate Spring boot main application from Quartz scheduler and WS client
5) Package Quartz and WS client code into a jar as library to be used by the Sprint boot main app

Main components:
1) Spring Boot
2) Quartz
3) WS server and client
4) Postgres database
5) JMS server and client
