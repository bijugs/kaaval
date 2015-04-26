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

import junit.framework.TestCase;

public class PasswordRulesTest extends TestCase {

	public void testVerifyPassword() {
		boolean result = PasswordRules.verifyPassword("ABCDEFGH".toCharArray());
		assertEquals("Phrase includes only cap letters",false,result);
	}
	
	public void testVerifyPassword1() {
		boolean result = PasswordRules.verifyPassword("abcdefgh".toCharArray());
		assertEquals("Phrase includes only small letters",false, result);
	}
	
	public void testVerifyPassword3() {
		boolean result = PasswordRules.verifyPassword("12345678".toCharArray());
		assertEquals("Phrase includes only numbers",false, result);
	}
	
	public void testVerifyPassword4() {
		boolean result = PasswordRules.verifyPassword("!@#$%^&*".toCharArray());
		assertEquals("Phrase includes only numbers",false, result);
	}
	
	public void testVerifyPassword5() {
		boolean result = PasswordRules.verifyPassword("ABCD".toCharArray());
		assertEquals("Phrase is less than 8 characters",false, result);
	}
	
	public void testVerifyPassword6() {
		boolean result = PasswordRules.verifyPassword("ABcd1234".toCharArray());
		assertEquals("Phrase doesn't include special charcters",false, result);
	}
	
	public void testVerifyPassword7() {
		boolean result = PasswordRules.verifyPassword("A!@B1234".toCharArray());
		assertEquals("Phrase doesn't include small letters",false, result);
	}
	
	public void testVerifyPassword8() {
		boolean result = PasswordRules.verifyPassword("!@xy1234".toCharArray());
		assertEquals("Phrase doesn't include cap letters",false, result);
	}
	
	public void testVerifyPassword9() {
		boolean result = PasswordRules.verifyPassword("CDxy!@#$".toCharArray());
		assertEquals("Phrase doesn't include numbers",false, result);
	}
	
	public void testVerifyPassword10() {
		boolean result = PasswordRules.verifyPassword("CDxy12#$".toCharArray());
		assertEquals("Phrase satisfies the requirements",true, result);
	}
	
	public void testVerifyPassword11() {
		boolean result = PasswordRules.verifyPassword("CDxy12#$!@#$DFGsc678".toCharArray());
		assertEquals("Phrase matches the requirements",true, result);
	}
}
