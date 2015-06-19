UTEID: kge227; ryt96;
FIRSTNAME: Kevin; Roger;
LASTNAME: Esswein; Tang;
CSACCOUNT: kesswein; rtang96;
EMAIL: kesswein@utexas.edu; roger.tang@utexas.edu;

[Program 2]
[Description]
The code introduces three new ops, CREATE, DESTROY, and RUN. Create creates a new object the same level as the subject and initializes to 0. Destroy deletes an object no matter the level of the subject. Finally, run writes down the stored information of the subject into an out file. In this program, two subjects work together to pass a message by having the lower level subject detect whether or not a particular action has been taken by the higher level subject and writing down a 1 or 0 into its temporary storage appropriately. By detecting massive numbers of 1's and 0's, entire files can be transferred from the high level subject to the low level subject against the policy rules of Bell and Lapadula.

[Machine Information]
Architecture:          x86_64
CPU op-mode(s):        32-bit, 64-bit
Byte Order:            Little Endian
CPU(s):                8
On-line CPU(s) list:   0-7
Thread(s) per core:    2
Core(s) per socket:    4
Socket(s):             1
NUMA node(s):          1
Vendor ID:             GenuineIntel
CPU family:            6
Model:                 58
Stepping:              9
CPU MHz:               1600.000
BogoMIPS:              6984.21
Virtualization:        VT-x
L1d cache:             32K
L1i cache:             32K
L2 cache:              256K
L3 cache:              8192K
NUMA node0 CPU(s):     0-7

[Source Description]
Pride and prejudice comes from http://www.gutenberg.org/cache/epub/1342/pg1342.txt
Metamorphosis comes from https://www.gutenberg.org/files/5200/5200-h/5200-h.htm
We made TestCase1.txt and TestofCharacters.txt

[Finish]
We finished all of this assignment.

[Results summary]
[No.]   [DocumentName]          [Size]          [Bandwidth]
1       Pride and Prejudice     704146 bytes    1786 bits/ms
2       Metamorphosis           138492 bytes    1446 bits/ms
3       TestCase1               65 bytes        23 bits/ms
4       TestofCharacters.txt    93 bytes        25 bits/ms

