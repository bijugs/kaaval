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
 * Java class to verify whether a password string 
 * satisfies the minimum conditio set.
 * The current rules are
 * - Password should be of min 8 characters in length
 * - Should have atleast 4 Alphabets out of which 2 caps and 2 small letters
 * - Should have atleast 2 numeric values
 * - Should have atleast 2 non alphanumeric
 */
public class PasswordRules {
	/**
	 * Variables to store password rules
	 */
	static final int LENGTH = 8;	//Min length of the password
	static final int ALPHALEN = 4;	//Min number of alphabets
	static final int NUMLEN = 2;	//Min number of numeric values
	static final int NONALNULEN = 2;//Min number of non alpha-numeric values
	static final int CAPLEN = 2;	//Min number of alphabets in caps
	static final int SMALLLEN = 2;	//Min number of alphabets in small caps
	
	/**
	 * Method to call to verify the password rules.
	 * Accepts the password to be verified as a char array
	 */
	public static boolean verifyPassword(char[] str) {
		/**
		 * Variables to verify password rules
		 */
		int vLength = 0;
		int vAlphalen = 0;
		int vNumlen = 0;
		int vNonalnulen = 0;
		int vCaplen = 0;
		int vSmalllen = 0;
		vLength = str.length;
		for (int i = 0; i < vLength; i++) {
			if (str[i] > 0x30 && str[i] < 0x3a) {
				vNumlen++;
			} else if (str[i] > 0x40 && str[i] < 0x5b) {
				vAlphalen++;
				vCaplen++;
			} else if (str[i] > 0x60 && str[i] < 0x7b) {
				vAlphalen++;
				vSmalllen++;
			} else
				vNonalnulen++;
		}
		if (vLength >= LENGTH && vAlphalen >= ALPHALEN && vNumlen >= NUMLEN
				&& vNonalnulen >= NONALNULEN && vCaplen >= CAPLEN && vSmalllen >= SMALLLEN)
			return true;
		else
			System.out.println("6: Entered phrase didn't meet standards");
			return false;
	}
}

