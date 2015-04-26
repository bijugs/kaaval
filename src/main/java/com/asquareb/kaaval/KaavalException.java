/* 
# Author: Biju Nair
# Github: https://github.com/bijugs
#
# License
# =======
#
# [Apache 2.0 license](http://www.apache.org/licenses/LICENSE-2.0)
#
# Unless required by applicable law or agreed to in writing, software distributed
# under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
# CONDITIONS OF ANY KIND, either expressed or implied. See the license for the specific
# language governing permissions and limitations under the license.
#
# Copyright (c) 2015 The Authors, All Rights Reserved.
*/

package com.asquareb.kaaval;

/**
 * The function of this class is to generate exception objects customized 
 * to encryption and decryption utility.
 * It includes an exceptionCode and a exceptioMessage which need to be set
 * by the object creator and can be printed using  PrintProtectException()
 * method call.
 * Some of the common error messages are
 * exceptionCode | exceptionMethod
 * ------------------------------------------------------------------------
 *       1       | Error in key file during encryption
 *       2       | Other errors during encryption
 *       3	 | Error in reading key file during decryption
 *       4	 | Other errors during decryption
 *	 5	 | Key file is not for use in this machine
 */
public class KaavalException extends Exception{
	
	private static final long serialVersionUID = -500694449077764845L;
	/**
	 * Variable to store the error message
	 */
	private String exceptionMessage;
	/**
	 * Variable to store the error code
	 */
	private int exceptionCode;
	/**
	 * Variable to store the original exception object
	 */
	private Exception originalException = null;
	/**
	 * Constructor to create ProtectException object using
	 * an errorCode and errorMessage
	 */
	public KaavalException(int exCode, String exMessage) {
		exceptionCode = exCode;
		exceptionMessage = exMessage;
	}
	/**
	 * Constructor to create ProtectException object using
	 * an errorCode, errorMessage and an exception object. For e.g. if there
	 * is an IOException during decryption this method can be used to 
	 * create a ProtectException object which also includes the IOException
	 * object
	 */
	public KaavalException(int exCode, String exMessage, Exception ex) {
		exceptionCode = exCode;
		exceptionMessage = exMessage;
		originalException = ex;
	}
	/**
	 * Method to get ExceptionCode value in the ProtectException object
	 */
	public int getProtectExceptionCode() {
		return exceptionCode;
	}
	/**
	 * Method to get ExceptionMessage value in the ProtectException object
	 */
	public String getProtectExceptionMessage() {
		return exceptionMessage;
	}
	/**
	 * Method to get original exception object in the ProtectException object
	 */
	public Exception getOriginalExceptionObject() {
		return originalException;
	}
	/**
	 * Method to print the ProtectException object which will also print the 
	 * trace of the Original exception if any
	 */
	public void PrintProtectException() {
		System.out.println(exceptionCode + " " + exceptionMessage);
		if (originalException != null)
			originalException.printStackTrace();
	}
}
