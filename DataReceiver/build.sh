#!/bin/bash

# Clean and package each service
for service in data-receiver internal-processor external-processor data-monitoring
do
  echo "Building $service..."
  cd $service
  mvn clean package
  cd ..
done

# Run all services
echo "Starting DataReceiver..."
java -jar data-receiver/target/data-receiver-1.0-SNAPSHOT.jar &
echo "Starting InternalDataProcessor..."
java -jar internal-processor/target/internal-processor-1.0-SNAPSHOT.jar &
echo "Starting ExternalDataProcessor..."
java -jar external-processor/target/external-processor-1.0-SNAPSHOT.jar &
echo "Starting DataMonitoring..."
java -jar data-monitoring/target/data-monitoring-1.0-SNAPSHOT.jar &
