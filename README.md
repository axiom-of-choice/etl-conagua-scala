# ETL Conagua

## What is it?
This repository is a small project consisting of an ETL pipeline using Spark Scala and a public API:

1. Request the follwing endpoint to download the GZIP about weather foerecast in Mexico per day by municipality: https://smn.conagua.gob.mx/tools/GUI/webservices/?method=1 
2. Converts the GZIP into a json file
3. Reads the data with Spark and write it into a parquet

## How to use it locally?

It is pretty simple, you just need to check if sbt and scala is appropiately installed

To install dependencies:

`sbt compile` 

To health check

`sbt "runMain etl.hello.Hello"`

If everything went good then run to run the app locally in your machine.

`sbt "runMain etl.Main"`

## Run tests
`sbt test`

## How to use it in a container?

Run `docker build -t etl-conagua-scala .` to build the docker image.

Run `docker run -it --rm etl-conagua-scala` after building the image, you'll see the results in the shell.

## How to use it in a Spark cluster?

Run `sbt assemlbly` to build the jar. This will run the tests as well before building the Jar.

Change the permissions of the shell script `chmode 777 spark-submit-script.sh`

Run `./spark-submit.sh`