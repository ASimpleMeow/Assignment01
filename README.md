# Assignment01 by Oleksandr Kononov
This program will Auto-Complete a word for you when you type.

It's feature's include:


-A Functional GUI

-An Image (Alex search)

-Realtime (when a key is released) Autocomplete

-Demonstration of weightOf, bestMatch and matches methods (which adjustable k value for matches)

-A Help Button

-An Exit Button

-An AutoComplete Key

  ->If you press the CTRL key, your unfinished word will be replaced by the highest weight term that has it's prefix
  
-A Google Search Key

  ->If you press the ENTER key, your whole input will be Google Searched in your browser


Requirements:


-Internet Connection

  ->This program must have an internet connection to take it's data of weight and terms
  
  ->If the Internet is absent, you will be presented with an Error box informing you of so
  
-Computer

-Mouse

-Keyboard

-Screen


Tesing:



-As of 19:00 03/11/2016 - All the JUnit tests for this program pass and the program itself is working correctly and as expected

-There are a totoal of 5 JUnit tests present

  ->BinarySearchTest tests the Binary Search class
  
  ->BruteforceAutocompleteTest tests the BruteforceAutocomplete class
  
  ->QuickAutocompleteTest tests the QuickAutocomplete class
  
  ->TermListTest tests the TermList class
  
  ->TermTest tests the Term class


Testing Strategy:

I will use the assertEquals method from the JUnit library to test my expected result to the actual results. To test the Exceptions
I will add the (expected = "Exception".class) after the @Test and in the method make the program test something that should 
return an Exception. This outlines my testing strategy, for further details you can look at the JUnit test cases in the 
test source folder. 

Oleksandr Kononov (20071032),
Algorithms,
Entertainment Systems,
Year 2.
