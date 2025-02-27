# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-slim

# Set the working directory in the container
WORKDIR /app

# Copy the sbt configuration file and the source code
COPY build.sbt .
COPY project ./project
COPY src ./src

# Create directories to store the data
RUN mkdir -p ./data/raw ./data/processed

# Install sbt
RUN apt-get update && \
    apt-get install -y curl gnupg && \
    echo "deb https://repo.scala-sbt.org/scalasbt/debian all main" | tee /etc/apt/sources.list.d/sbt.list && \
    echo "deb https://repo.scala-sbt.org/scalasbt/debian /" | tee /etc/apt/sources.list.d/sbt_old.list && \
    curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x99E82A75642AC823" | apt-key add && \
    apt-get update && \
    apt-get install -y sbt && \
    apt-get clean

# Cache sbt dependencies
RUN sbt update

# Compile the project
RUN sbt compile

# Run the application
CMD ["sbt", "run"]