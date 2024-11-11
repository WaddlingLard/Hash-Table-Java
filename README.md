# Project 3: Experiments with Hashing

* Author: Brian Wu 
* Class: CS321 Section #001
* Semester: Spring 2023

## Overview

This program contains several classes that allows the creation of a
hashtable. Using the linearProbing and doubleHashing classes, you can insert
HashObjects into the hashtable and measure effectiveness of the two insertion
methods.

## Reflection

This project taught me the importance to look at the bigger picture than the finer details.
It was fun to code everything up even if it looked intimidating at first. The more vague a problem 
becomes the more ways the mind can think of. For example, its been awhile since I've done anything 
with abstract classes and methods. There's these dusty old relics of knowledge from 121 and 221 
that are trapped deep inside my brain. Don't get me wrong, the example helped but it was tricky to
refresh my mind on this topic. It's these topics that seemed small at first that become bigger
concepts that I can internalize and remember.

Another problem that I ran into while coding this project was my box-like thinking. I would always
run into a barrier that couldn't be solved because I was missing one minor detail or method. I
should be thinking more like "I can do it, so why not!" rather than "Should I do it?". There
were many things I created along the coding of this project to make my life easier. Could it have
been done in a more streamlined way? Yeah, probably. If there is one thing that I want to polish
up its cleaning up the Hashtable class. There is a lot of things I could've done to optimize that
driver class, but I didn't think of them at the time. All in all, an easier project than Stardew
Valley, but it still wasn't simple.

## Compiling and Using

First, you compile all the files by using the command:

javac *.java

Then, calling the HashtableTest class using this command structure:

java HashtableTest dataType loadFactor [debugLevel]

It's important to note that the debugLevel is optional as you only need
that when troubleshooting the code or are just curious how it works.

## Results 

The results for this test are as follows:

NOTE: All tests use the twin prime value of 95971 which will be the size of the array
housing all of the HashObjects. The actual size of the hashtable will depend on the
loadfactor of the hashtable.

Integer(Random) Testing: 

java HashtableTest (1) Scenario

| Scenario (LoadFactor)       | LinearProbing (Avg Probes) | DoubleHashing (Avg Probes) |
| :-------------------------: | :------------------------: | :------------------------: |
| 0.50                        | 1.51                       | 1.39                       |
| 0.60                        | 1.77                       | 1.54                       |
| 0.70                        | 2.15                       | 1.72                       |
| 0.80                        | 2.99                       | 2.01                       |
| 0.90                        | 5.47                       | 2.57                       |
| 0.95                        | 9.98                       | 3.16                       |
| 0.99                        | 36.96                      | 4.59                       |

Summary: It appears that inserting random data is severly hindering linearProbing while
doubleHashing is more efficent at storing random numbers.

Date Testing:

java HashtableTest (2) Scenario

| Scenario (LoadFactor)       | LinearProbing (Avg Probes) | DoubleHashing (Avg Probes) |
| :-------------------------: | :------------------------: | :------------------------: |
| 0.50                        | 1.08                       | 1.12                       |
| 0.60                        | 1.09                       | 1.17                       |
| 0.70                        | 1.09                       | 1.23                       |
| 0.80                        | 1.15                       | 1.45                       |
| 0.90                        | 1.47                       | 2.12                       |
| 0.95                        | 1.87                       | 2.79                       |
| 0.99                        | 3.54                       | 4.38                       |

Summary: It appears that inserting a fixed date and adding an iterative amount of time is
less taxing on linearProbing. That isn't to say that doubleHashing is still quick and efficent,
it's just beat out by linearProbing.


Word-List.txt Testing:

java HashtableTest (3) Scenario

| Scenario (LoadFactor)       | LinearProbing (Avg Probes) | DoubleHashing (Avg Probes) |
| :-------------------------: | :------------------------: | :------------------------: |
| 0.50                        | 1.60                       | 1.39                       |
| 0.60                        | 2.15                       | 1.53                       |
| 0.70                        | 3.60                       | 1.72                       |
| 0.80                        | 6.71                       | 2.02                       |
| 0.90                        | 19.81                      | 2.57                       |
| 0.95                        | 110.59                     | 3.19                       |
| 0.99                        | 471.67                     | 4.70                       |

Summary: This is the most egregious scenario showing that linearProbing pales in comparison to
doubleHashing. The efficency is by a factor of 100 when you look at the loadFactors approaching 
1.

Takeaway: LinearProbing is a simple technique that "gets the job done", but in more advanced 
scenarios like storing string values and even inputing random data DoubleHashing is incredibly more
efficent. 

## Sources used

I was having trouble with creating a system for identifying prime numbers so I found
this guide to help me better understand it.

https://www.geeksforgeeks.org/java-program-to-check-if-a-number-is-prime-or-not/

Finally, remove these instructions from your README.md after you are finished!

----------

## Notes

* This README template is using Markdown. Here is some help on using Markdown: 
[markdown cheatsheet](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet)


* Markdown can be edited and viewed natively in most IDEs such as Eclipse and VS Code. Just toggle
between the Markdown source and preview tabs.

* To preview your README.md output online, you can copy your file contents to a Markdown editor/previewer
such as [https://stackedit.io/editor](https://stackedit.io/editor).
