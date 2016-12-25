# Day 25: Solution Walkthrough

My solution for this final day was very manual. I initialized register `a` with a placeholder value `a0`, then stepped through the instructions list (my input) and tracked the values in each register. Looping sets of instructions have been identified. Particularly non-obvious steps are described in more detail in separate sections of this document.

## Annotated Execution Trace

	|  # |      instr |        a       |     b     |      c      |     d     |
	+----+------------+----------------+-----------+-------------+-----------+
	|  0 | cpy a d    |              a0|          0|            0|         a0|
	|  1 | cpy 4 c    |              a0|          0|            4|         a0|
	|  2 | cpy 633 b  |              a0|        633|            4|         a0|
	|  3 | inc d      |              a0|        633|            4|     a0 + 1| }                            }
	|  4 | dec b      |              a0|        632|            4|     a0 + 1| }                            }
	|  5 | jnz b -2   |              a0|        632|            4|     a0 + 1| } 633 loops before advancing }
	|  6 | dec c      |              a0|          0|            3|   a0 + 633|                              }
	|  7 | jnz c -5   |              a0|          0|            3|   a0 + 633|                              } 4 loops before advancing
	|  8 | cpy d a    |       a0 + 2532|          0|            0|  a0 + 2532| The value in register `d` is never updated again. However, it will be copied into register `a` each time we start the loop over (via instruction 29).
	|  9 | nop        |                |           |             |           |
	| 10 | cpy a b    |       a0 + 2532|  a0 + 2532|            0|           |
	| 11 | cpy 0 a    |               0|  a0 + 2532|            0|           |
	| 12 | cpy 2 c    |               0|  a0 + 2532|            2|           |
	| 13 | jnz b 2    |               0|  a0 + 2532|            2|           | }                          }
	| 14 | jnz 1 6    |                |           |             |           |
	| 15 | dec b      |               0|  a0 + 2531|            2|           | }                          }
	| 16 | dec c      |               0|  a0 + 2531|            1|           | }                          }
	| 17 | jnz c -4   |               0|  a0 + 2531|            1|           | } 2 loops before advancing }
	| 18 | inc a      |               1|  a0 + 2530|            0|           |                            }
	| 19 | jnz 1 -7   |                |           |             |           |                            } Looping until b = 0
	| 20 | cpy 2 b    | ⌊(a0 + 2532)/2⌋|          2| 2 - (a0 % 2)|           | See § "Determining register values at instructions 14 and 20"
	| 21 | jnz c 2    | ⌊(a0 + 2532)/2⌋|          2| 2 - (a0 % 2)|           | }
	| 22 | jnz 1 4    |                |           |             |           |
	| 23 | dec b      | ⌊(a0 + 2532)/2⌋|          1| 2 - (a0 % 2)|           | }
	| 24 | dec c      | ⌊(a0 + 2532)/2⌋|          1| 1 - (a0 % 2)|           | }
	| 25 | jnz 1 -4   | ⌊(a0 + 2532)/2⌋|   (a0 % 2)|            0|           | } 2 - (a0 % 2) loops until c = 0
	| 26 | nop        | ⌊(a0 + 2532)/2⌋|   (a0 % 2)|            0|           |
	| 27 | out b      | ⌊(a0 + 2532)/2⌋|   (a0 % 2)|            0|           | See § "Instruction 27 and beyond"
	| 28 | jnz a -19  |                |           |             |           |
	| 29 | jnz 1 -21  |                |           |             |           |



## Determining register values at steps 14 and 20

The sample outputs below represent the sequence of instructions executed immediately before instructions 14 and 20 were executed for the very first time. From this we deduce that after instruction 20 is first executed, the register values are as follows:

    a = ⌊(a0 + 2532)/2⌋
    b = 2
    c = 2 - (a0 % 2)
    d = a0 + 2532

### a0 = 6

	 15 | dec b     | {a=1267, b=2, c=1, d=2538}
	 16 | dec c     | {a=1267, b=2, c=0, d=2538}
	 17 | jnz c -4  | {a=1267, b=2, c=0, d=2538}
	 18 | inc a     | {a=1268, b=2, c=0, d=2538}
	 19 | jnz 1 -7  | {a=1268, b=2, c=0, d=2538}
	 12 | cpy 2 c   | {a=1268, b=2, c=2, d=2538}
	 13 | jnz b 2   | {a=1268, b=2, c=2, d=2538}
	 15 | dec b     | {a=1268, b=1, c=2, d=2538}
	 16 | dec c     | {a=1268, b=1, c=1, d=2538}
	 17 | jnz c -4  | {a=1268, b=1, c=1, d=2538}
	 13 | jnz b 2   | {a=1268, b=1, c=1, d=2538}
	 15 | dec b     | {a=1268, b=0, c=1, d=2538}
	 16 | dec c     | {a=1268, b=0, c=0, d=2538}
	 17 | jnz c -4  | {a=1268, b=0, c=0, d=2538}
	 18 | inc a     | {a=1269, b=0, c=0, d=2538}
	 19 | jnz 1 -7  | {a=1269, b=0, c=0, d=2538}
	 12 | cpy 2 c   | {a=1269, b=0, c=2, d=2538}
	 13 | jnz b 2   | {a=1269, b=0, c=2, d=2538}
	 14 | jnz 1 6   | {a=1269, b=0, c=2, d=2538}
	 20 | cpy 2 b   | {a=1269, b=2, c=2, d=2538}

### a0 = 7

	 19 | jnz 1 -7  | {a=1268, b=3, c=0, d=2539}
	 12 | cpy 2 c   | {a=1268, b=3, c=2, d=2539}
	 13 | jnz b 2   | {a=1268, b=3, c=2, d=2539}
	 15 | dec b     | {a=1268, b=2, c=2, d=2539}
	 16 | dec c     | {a=1268, b=2, c=1, d=2539}
	 17 | jnz c -4  | {a=1268, b=2, c=1, d=2539}
	 13 | jnz b 2   | {a=1268, b=2, c=1, d=2539}
	 15 | dec b     | {a=1268, b=1, c=1, d=2539}
	 16 | dec c     | {a=1268, b=1, c=0, d=2539}
	 17 | jnz c -4  | {a=1268, b=1, c=0, d=2539}
	 18 | inc a     | {a=1269, b=1, c=0, d=2539}
	 19 | jnz 1 -7  | {a=1269, b=1, c=0, d=2539}
	 12 | cpy 2 c   | {a=1269, b=1, c=2, d=2539}
	 13 | jnz b 2   | {a=1269, b=1, c=2, d=2539}
	 15 | dec b     | {a=1269, b=0, c=2, d=2539}
	 16 | dec c     | {a=1269, b=0, c=1, d=2539}
	 17 | jnz c -4  | {a=1269, b=0, c=1, d=2539}
	 13 | jnz b 2   | {a=1269, b=0, c=1, d=2539}
	 14 | jnz 1 6   | {a=1269, b=0, c=1, d=2539}
	 20 | cpy 2 b   | {a=1269, b=2, c=1, d=2539}

### a0 = 8

	 15 | dec b     | {a=1268, b=2, c=1, d=2540}
	 16 | dec c     | {a=1268, b=2, c=0, d=2540}
	 17 | jnz c -4  | {a=1268, b=2, c=0, d=2540}
	 18 | inc a     | {a=1269, b=2, c=0, d=2540}
	 19 | jnz 1 -7  | {a=1269, b=2, c=0, d=2540}
	 12 | cpy 2 c   | {a=1269, b=2, c=2, d=2540}
	 13 | jnz b 2   | {a=1269, b=2, c=2, d=2540}
	 15 | dec b     | {a=1269, b=1, c=2, d=2540}
	 16 | dec c     | {a=1269, b=1, c=1, d=2540}
	 17 | jnz c -4  | {a=1269, b=1, c=1, d=2540}
	 13 | jnz b 2   | {a=1269, b=1, c=1, d=2540}
	 15 | dec b     | {a=1269, b=0, c=1, d=2540}
	 16 | dec c     | {a=1269, b=0, c=0, d=2540}
	 17 | jnz c -4  | {a=1269, b=0, c=0, d=2540}
	 18 | inc a     | {a=1270, b=0, c=0, d=2540}
	 19 | jnz 1 -7  | {a=1270, b=0, c=0, d=2540}
	 12 | cpy 2 c   | {a=1270, b=0, c=2, d=2540}
	 13 | jnz b 2   | {a=1270, b=0, c=2, d=2540}
	 14 | jnz 1 6   | {a=1270, b=0, c=2, d=2540}
	 20 | cpy 2 b   | {a=1270, b=2, c=2, d=2540}

### a0 = 9

	 19 | jnz 1 -7  | {a=1269, b=3, c=0, d=2541}
	 12 | cpy 2 c   | {a=1269, b=3, c=2, d=2541}
	 13 | jnz b 2   | {a=1269, b=3, c=2, d=2541}
	 15 | dec b     | {a=1269, b=2, c=2, d=2541}
	 16 | dec c     | {a=1269, b=2, c=1, d=2541}
	 17 | jnz c -4  | {a=1269, b=2, c=1, d=2541}
	 13 | jnz b 2   | {a=1269, b=2, c=1, d=2541}
	 15 | dec b     | {a=1269, b=1, c=1, d=2541}
	 16 | dec c     | {a=1269, b=1, c=0, d=2541}
	 17 | jnz c -4  | {a=1269, b=1, c=0, d=2541}
	 18 | inc a     | {a=1270, b=1, c=0, d=2541}
	 19 | jnz 1 -7  | {a=1270, b=1, c=0, d=2541}
	 12 | cpy 2 c   | {a=1270, b=1, c=2, d=2541}
	 13 | jnz b 2   | {a=1270, b=1, c=2, d=2541}
	 15 | dec b     | {a=1270, b=0, c=2, d=2541}
	 16 | dec c     | {a=1270, b=0, c=1, d=2541}
	 17 | jnz c -4  | {a=1270, b=0, c=1, d=2541}
	 13 | jnz b 2   | {a=1270, b=0, c=1, d=2541}
	 14 | jnz 1 6   | {a=1270, b=0, c=1, d=2541}
	 20 | cpy 2 b   | {a=1270, b=2, c=1, d=2541}

### a0 = 10

	 15 | dec b     | {a=1269, b=2, c=1, d=2542}
	 16 | dec c     | {a=1269, b=2, c=0, d=2542}
	 17 | jnz c -4  | {a=1269, b=2, c=0, d=2542}
	 18 | inc a     | {a=1270, b=2, c=0, d=2542}
	 19 | jnz 1 -7  | {a=1270, b=2, c=0, d=2542}
	 12 | cpy 2 c   | {a=1270, b=2, c=2, d=2542}
	 13 | jnz b 2   | {a=1270, b=2, c=2, d=2542}
	 15 | dec b     | {a=1270, b=1, c=2, d=2542}
	 16 | dec c     | {a=1270, b=1, c=1, d=2542}
	 17 | jnz c -4  | {a=1270, b=1, c=1, d=2542}
	 13 | jnz b 2   | {a=1270, b=1, c=1, d=2542}
	 15 | dec b     | {a=1270, b=0, c=1, d=2542}
	 16 | dec c     | {a=1270, b=0, c=0, d=2542}
	 17 | jnz c -4  | {a=1270, b=0, c=0, d=2542}
	 18 | inc a     | {a=1271, b=0, c=0, d=2542}
	 19 | jnz 1 -7  | {a=1271, b=0, c=0, d=2542}
	 12 | cpy 2 c   | {a=1271, b=0, c=2, d=2542}
	 13 | jnz b 2   | {a=1271, b=0, c=2, d=2542}
	 14 | jnz 1 6   | {a=1271, b=0, c=2, d=2542}
	 20 | cpy 2 b   | {a=1271, b=2, c=2, d=2542}

## Instruction 27 and beyond

Instruction 27 is the `out` instruction that emits a value. Per the trace above, if we start with register values

    |              a0|          0|            0|          0|

then the first time we reach instruction 27, the updated register values will be

    | ⌊(a0 + 2532)/2⌋|   (a0 % 2)|            0|  a0 + 2532|

Thus `a0 % 2` is our first output value. Per the puzzle instructions, this must be a `0`, so `a0 + 2532` must be even

What happens next? If we inspect instructions 28 and 29

    jnz a -19
	jnz 1 -21

we see that we are guaranteed to jump back to

1. instruction 9, if register `a` holds any value other than 0;
2. instruction 8, if register `a` holds the value 0.

In case 1, we resume execution from instruction 9 with register `a` holding the value `⌊(a0 + 2532)/2⌋`. All subsequent instructions will be executed exactly as before but with this 'new' initial value, so our second output value will be

    ⌊(a0 + 2532)/2⌋ % 2

Per the puzzle instructions, this must be a `1`, so ` ⌊(a0 + 2532)/2⌋` must be odd. Similarly, `⌊(a0 + 2532)/4⌋` must be even, `⌊(a0 + 2532)/8⌋` must be odd, etc.

Eventually, we will reach a loop count `n` such that `⌊(a0 + 2532)/2^n⌋ = 0` (case 2). When this happens, the value in register `a` is reset to `a0 + 2532` by instruction 8, and we resume the 'inner loop' between instructions 9 and 28 detailed above.

When combined, the statements above imply that we must find the smallest number `a0` such that the binary representation of `a0 + 2532` matches the regular expression `^(10)+$`. Note that we must not allow a trailing `1` in the binary representation, since otherwise our final output signal will include adjacent `1`s. The number satisfying this constraint is computed to be 2730 in `Main.kt`. Thus `a0 = 198`, which is the correct solution to this puzzle!
