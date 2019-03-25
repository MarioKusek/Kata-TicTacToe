**Inbox**



**Test cases**

- [x] inserting valid move
	- [x] move is valid if position is empty and is valid position
- [x] move is invalid if user enters invalid field
	- [x] then the same player needs to enter move
- [x] inserting invalid move

**Assumptions**

- [x] first player is o

**Integration**

- [x] start app

```
   1 2 3
  a | | 
   -----
  b | |
   -----
  c | |
```

  	- [x] write whose turn is

    	  `Player (o) insert move`
   - [x] player inserts: a1
   - [x] detect end
       		- [ ] if end is detected (no empty fields or we have a winner)
   - [x] if it is end
        - [x] write winner or draw
        - [x] exit program
   - [x] if not end
        - [x] change player
   - [x] repeat with writing field 
   - [x] write field