/*Write a program that outputs the string representation of numbers from 1 to n.

But for multiples of three it should output “Fizz” 
instead of the number and for the multiples of five output “Buzz”. 
For numbers which are multiples of both three and five output “FizzBuzz”.

Example:

n = 15,

Return:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]*/
package leetcode.easy;

import java.util.LinkedList;
import java.util.List;

public class LeetCode_0412_FizzBuzz {

	public static List<String> fizzBuzz(int n) {
		List<String> l = new LinkedList<>();
		for (int i = 1; i <= n; i++) {
			l.add(i % 15 == 0 ? "FizzBuzz" : (i % 5 == 0 ? "Buzz" : (i % 3 == 0 ? "Fizz" : String.valueOf(i))));
		}
		return l;
	}

}
