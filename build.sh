#!/bin/bash

# Function to handle shutdown
shutdown() {
    echo "Shutting down Tomcat..."
    /usr/local/tomcat9/bin/shutdown.sh
    exit
}

# Trap SIGINT (Ctrl + C) and call the shutdown function
trap shutdown SIGINT

echo "Building java webApp"
echo ""
echo ""

/usr/local/tomcat9/bin/startup.sh

find src -type f | entr -r ./refresh.sh

shutdown