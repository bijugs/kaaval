# kaaval
Simple Java utility to encrypt/decrypt strings

# Use
This can be used to encrypt passwords which are stored in application config files in plain text.

# Usage
- Clone the repo.
- Generate ``jar`` file using Maven ``mvn package`` (or other means).
- To encrypt password used by an application, copy the ``jar`` file and the ``Apache commons-codec`` jar file and set the classpath.
- Run the `` java com.asquareb.kaaval.ProtectedConfigFile `` or use `` keygen.sh `` shell script in the repo.
- The program prompts for the string to the encrypted which in this case is the password used by the application. Enter the string.
- The program prompts to choose a string phrase which satisfies some criteria.
- Once the phrase is entered, the program prompts for an application code which identifies the application. Enter a short 3 or 4 character string to identify the application.
- The program writes the encrypted string on the console which can be copied into the application configuration file instead of the password in plain text.
- The program also generates a key file with application code entered as prefix. Copy this file to a location which is redeable only to the service id running the application.
- In order to decrypt the password for application to use, the application can call the ``decrypt`` method in ``com.asquareb.kaaval.ProtectedConfigFile`` class passing the encrypted string and the keyfile location.

# Example Key Generation

```

gsbiju$ ./bin/keygen.sh 
******************************
Classpath set to :
/development/maven/kaaval/target/kaaval-1.0-SNAPSHOT.jar:/repository/commons-codec/commons-codec/1.9/commons-codec-1.9.jar
******************************
Enter E - Encrypt,D - Decrypt,X - Exit :E
Enter the string to encrypt :PASSWORD
Choose a phrase of min 8 chars in length 
 Also the password should include 2 Cap,
 2 small letters,2 numeric and 2 non alpha numeric chars
Enter a phrase to encrypt :ASdfgh12*(
Enter application code :TEST
Encrypted password :Kd2LoEbzGDvo3Edeh/v4+w==
Key stored in :TEST2592
*** Provide the encrypted string to app team
*** Store the key file in a secure durectory
*** Inform the key file name and location to app team

```

# Example Key Verification

The encrypted string and the key file generated can be used to verify the decryption process. Only requirement is that it need to be done on the machine where the encryption was done.

```
gsbiju$ ./bin/keygen.sh 
******************************
Classpath set to :
/development/maven/kaaval/target/kaaval-1.0-SNAPSHOT.jar:/repository/commons-codec/commons-codec/1.9/commons-codec-1.9.jar
******************************
Enter E - Encrypt,D - Decrypt,X - Exit :D
Enter the string to Decrypt :Kd2LoEbzGDvo3Edeh/v4+w==
Enter the name of key file :TEST2592
Decrypted string :PASSWORD
```

Copyright
=========

Copyright 2015, [Apache 2.0 license](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software distributed 
under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR 
CONDITIONS OF ANY KIND, either expressed or implied. See the license for the specific 
language governing permissions and limitations under the license.

Contributing
============
1. Fork the repository on Github
2. Create a named feature branch (like add-component-x)
3. Make your code changes
4. Test that your changes work, for example with Vagrant
5. Submit a Pull Request using Github
