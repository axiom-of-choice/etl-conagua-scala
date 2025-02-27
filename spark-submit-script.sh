#!/bin/bash
# Spark-submit script for the ETL Conagua Scala application

# Application JAR and main class
# Define the repository path (set via environment variable, or default to the current directory)
REPO_PATH=${REPO_PATH:-$(pwd)}
APP_JAR="${REPO_PATH}/target/scala-2.12/etl-conagua-assembly-1.0.jar"
MAIN_CLASS="etl.Main"  # Change to your actual main class

# Spark configuration settings
# SPARK_MASTER="spark://your-spark-master:7077"  # Change to your Spark master URL
SPARK_MASTER="local[*]"                          # Use "local[*]" for local mode
# DEPLOY_MODE="cluster"                          
DEPLOY_MODE="client"                             # Use "cluster" if desired
NUM_EXECUTORS=2
EXECUTOR_CORES=2
EXECUTOR_MEMORY="4g"

# Additional application arguments (if any)
APP_ARGS="$@"

# Run the Spark application
spark-submit \
    --class ${MAIN_CLASS} \
    --master ${SPARK_MASTER} \
    --deploy-mode ${DEPLOY_MODE} \
    --num-executors ${NUM_EXECUTORS} \
    --executor-cores ${EXECUTOR_CORES} \
    --executor-memory ${EXECUTOR_MEMORY} \
    ${APP_JAR} ${APP_ARGS}