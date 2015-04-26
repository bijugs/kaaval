# Set the classpath to include the required jar files
export CLASSPATH=$CLASSPATH:./text-security.jar:./commons-codec-1.5.jar
# Uncomment the following line to see whether the classpath is set
#echo $CLASSPATH
# execute the utility class to create an encrypted text
java com.sieac.security.utility.ProtectedConfigFile
