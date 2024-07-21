#!/bin/bash

#########################################################
# The purpose of this script it to provide auto         #
# updates to Apache Tomcat when a file is updated/saved #
#########################################################

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

# start server before monitoring 
/usr/local/tomcat9/bin/startup.sh 

# when change occures in "src" dir run the refresh function above
find src -type f -not -path "src/main/resources/comments.json" | entr -r bash -c ./refresh.sh

#close server when script ends
shutdown