# Set the classpath to include the required jar files
export CLASSPATH=/Users/gsbiju/development/maven/kaaval/target/kaaval-1.0-SNAPSHOT.jar:~/.m2/repository/commons-codec/commons-codec/1.9/commons-codec-1.9.jar
# Uncomment the following line to see whether the classpath is set
echo "******************************"
echo "Classpath set to :"
echo $CLASSPATH
echo "******************************"
# execute the utility class to create an encrypted text
java com.asquareb.kaaval.ProtectedConfigFile
