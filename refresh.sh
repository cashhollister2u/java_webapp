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

refresh