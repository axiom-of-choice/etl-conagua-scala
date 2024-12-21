# ETL Conagua

## What is it?
This repository is a small project consisting of an ETL pipeline using Spark Scala and a public API:

1. Request the follwing endpoint to download the GZIP about weather foerecast in Mexico per day by municipality: https://smn.conagua.gob.mx/tools/GUI/webservices/?method=1 
2. Converts the GZIP into a json file
3. Reads the data with Spark and write it into a parquet

## How to use it?

It is pretty simple, you just need to check if sbt and scala is appropiately installed
`sbt "runMain etl.hello.Hello"`
IF everything went good then use
`sbt "runMain etl.Main"`