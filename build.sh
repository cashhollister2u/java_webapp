#!/bin/bash

#########################################################
# The purpose of this script it to provide auto         #
# updates to Apache Tomcat when a file is updated/saved #
#########################################################

# Function to handle hot refresh
refresh() {
    # make the .war dir
    mvn clean package -P exploded

    # move the updated dir to tomcat
    sudo cp -r  target/java_webapp /usr/local/tomcat9/webapps/

    # restart tomcat server
    /usr/local/tomcat9/bin/shutdown.sh
    /usr/local/tomcat9/bin/startup.sh

    # Get the directory where the script is located
    dir_path=$(dirname "$(realpath "$0")")

    # Extract the directory name
    dir_name=$(basename "$dir_path")

    echo ""
    echo ""
    echo "Build Complete"
    echo ""
    echo "web page running on http://localhost:8080/$dir_name"
}

#make refresh function available to being called by entr when file is saved
export -f refresh

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
find src -type f | entr -r bash -c ./refresh.sh

#close server when script ends
shutdown